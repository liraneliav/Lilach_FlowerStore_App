package il.server;

import il.entities.*;

import java.io.IOException;

public class ComplaintConrtol{

    private int storeID;
    private int complainID;
    private String username;
    private String complaintDate;
    private String complaintTime;
    private String complaintDetails;


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
        Message message = new Message("complainAnswer");
        System.out.println("answer to complain "+ complainID);
        testDB.openSession();
        Complain complain = testDB.session.get(Complain.class, complainID);
        if(complain==null){
            System.out.println("not find complain "+complainID);
        }
        else{
            User user = complain.getUser();
            if(refund>0)
                user.setCredit(user.getCredit() + refund);
            complain.setAnswer_text(answer);
            complain.setRefund(refund);
            complain.setStatus(true);
            testDB.session.flush();
            testDB.session.getTransaction().commit(); // Save everything.
        }
        testDB.closeSession();
    }


    public static void setStatusComplaint(int complaintID){
        testDB.openSession();
        Complain a = testDB.session.get(Complain.class, complaintID);
        a.setStatus(false);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }


    public String complaintToMail() {
        return "Complaint number " + complainID +": name= " + username
               + ", complaint date =" + complaintDate + ", complaint time =" + complaintTime +
                + ", complaint details=" + complaintDetails + "is open=" + isOpen + "]";
    }

}
