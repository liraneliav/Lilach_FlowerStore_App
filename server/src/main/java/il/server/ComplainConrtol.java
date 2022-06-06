package il.server;

import il.entities.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static il.server.SimpleServer.getAllItems;

public class ComplainConrtol{

    public static void newComplain(Complain complain, int orderID) throws IOException {
        testDB.openSession();
        System.out.println("new complain to order "+ orderID);
        Order order = testDB.session.get(Order.class, orderID);
        if(order==null){
            System.out.println("not maje new complain: not found order");
        }
        else{
            testDB.session.save(complain);
            Store store = order.getStore();
            User user = order.getUser();

            store.addComplain(complain);
            user.addComplain(complain);
            order.setComplain(complain);
            testDB.session.flush();
            testDB.session.getTransaction().commit(); // Save everything.
        }
        testDB.closeSession();
    }

    public static void complainAnswer(String answer, double refund, int complainID) throws IOException {
        double amount=0;
        System.out.println("answer to complain "+ complainID);
        testDB.openSession();
        Complain complain = testDB.session.get(Complain.class, complainID);
        if(complain==null){
            System.out.println("not find complain "+complainID);
        }
        else{
            User user = complain.getUser();
            if(refund>0)
                amount = (refund/100)*complain.getOrder().getSum();
            user.setCredit(user.getCredit() + amount);
            complain.setAnswer_text(answer);
            complain.setRefund(refund);
            complain.setStatus(false);
            testDB.session.flush();
            testDB.session.getTransaction().commit(); // Save everything.
            sendmail(complainID, createAnswer(complainID ,amount));
        }
        testDB.closeSession();
    }

    public static void sendmail(int complainID, String answer){
        testDB.openSession();
        Complain complain = testDB.session.get(Complain.class, complainID);
        User user = complain.getUser();
        String userMail = user.getMail();
        String title = "Replay for complain number:" + complain.getId();
        testDB.closeSession();
        SendEmail.sendTo(userMail,title,answer);
    }

    public static String createAnswer(int complainID, double refund){
        testDB.openSession();
        Complain complain = testDB.session.get(Complain.class, complainID);
        User user = complain.getUser();
        String userMail = user.getMail();
        String text = "Hi "+user.getName()+" we can confirm that we've proceeded with your compliant numnber: "+
                complain.getId()+" \n"+"Our answer is: "+complain.getComplain_text()+" \n";
        if(refund>0) {
            text += "Your account will be refunded with: " + refund + "₪ of your order number: " + complain.getOrder().getId();
            double newC = user.getCredit()+refund;
            user.setCredit(newC);
        }
        text+="\nHave a nice day, Lilach team.";
        testDB.closeSession();
        return text;
    }

    public static LinkedList<Complain> AllComplaintsForUser(LinkedList<Complain> complains){
        LinkedList<Complain> c = new LinkedList<>();
        for(Complain comp : complains){
            if(comp.isStatus())
                c.add(comp.getComplainForClient());
        }
        return c;
    }


    public static LinkedList<Complain> getAllOpenComplaint(){
        LinkedList<Complain> c = new LinkedList<>();
        List<Complain> complains = getAllItems(Complain.class);
        for(Complain comp : complains){
            if(comp.isStatus())
                c.add(comp.getComplainForClient());
        }
        return c;
    }

}