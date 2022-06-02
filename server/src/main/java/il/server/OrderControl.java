package il.server;

import il.entities.*;

import java.io.IOException;

public class OrderControl {

    public static void cancelOrder(int id){
        testDB.openSssion();
        Order a = testDB.session.get(Order.class, id);
//        checkRefunds(a);
        testDB.session.delete(a);
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }
        /** added 2 func for refunds*/

//    private static void checkRefunds(Order a) {
//        String current_date = java.time.LocalDate.now().toString();
//        if (current_date.equals(a.getDateReceive())) {
//            int current_time = timeToInt(java.time.LocalTime.now().toString());
//            int order_time = timeToInt(a.getTimeReceive());
//            if (order_time - current_time < 300) {
//                if (order_time - current_time > 60) {
//                    //a.get 50% refunds
//                }
//                return;
//            }
//            //a.get 100% refunds
//            return;
//        }
//        //a.get 100% refunds
//    }
//    public static int timeToInt(String time){
//        time = time.replace(":", "");
//        time = time.substring(0,3);
//        return Integer.parseInt(time);
//    }

    public static void newOrder(Order order, int storeID, int userID) throws IOException {
        testDB.openSssion();
        Store store = testDB.session.get(Store.class, storeID);
        User user = testDB.session.get(User.class, userID);

        if(!user.getListstore().contains(store)){
            System.out.println(user.getUserName() + " try to made order is store that he never register!");
        }
        else{
            for(CartProduct p : order.getProducts())
                testDB.session.save(p);

            testDB.session.save(order);
            user.addOrder(order);
            store.addOrder(order);
        }
        testDB.session.flush();
        testDB.session.getTransaction().commit(); // Save everything.
        testDB.closeSession();
    }

}
