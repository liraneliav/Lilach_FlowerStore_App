package il.client.controls;

import il.client.CatalogController;
import il.client.SimpleClient;
import il.entities.CartProduct;
import il.entities.Complain;
import il.entities.Message;
import il.entities.Order;

import java.io.IOException;

public class OrderControl {

    public static void testNewOrder() throws IOException {
        Order order = new Order(null, null, "testOrderFromClient", "asas","asas","1212", 0,"121212", "21212","", "");
        order.addProduct(new CartProduct(CatalogController.getFlowerlist().get(0), 3));
        order.addProduct(new CartProduct(CatalogController.getFlowerlist().get(2), 3));

        newOrder(order, 1,1);
    }

    public static void testCancelOrder() throws IOException {
        cancelOrder(1);
    }

    public static void testNewComplain() throws IOException {
        Complain complain = new Complain("it's test complain!", "00/00/00");
        newComplain(complain, 1);
    }

    public static void testAnswerComplain() throws IOException {
        complainAnswer("it's a test answer!", 100, 4);
    }


    public static void cancelOrder(int orderID) throws IOException {
        System.out.println("cancel order: "+ orderID);
        Message message = new Message("cancelOrder");
        message.setTimeCancel(java.time.LocalTime.now().toString());
        message.setDateCancel(java.time.LocalDate.now().toString());
        message.setOrderID(orderID);
        SimpleClient.getClient().sendToServer(message);
    }

    public static void newOrder(Order order, int storeID, int userID) throws IOException {
        System.out.println("made new order to store "+ storeID);
        Message message = new Message("newOrder");
        message.setOrder(order);
        message.setStoreID(storeID);
        message.setUserID(userID);
        SimpleClient.getClient().sendToServer(message);
    }

    public static void newComplain(Complain complain, int orderID) throws IOException {
        System.out.println("new complain to order "+ orderID);
        Message message = new Message("newComplain");
        message.setComplain(complain);
        message.setOrderID(orderID);
        SimpleClient.getClient().sendToServer(message);
    }

    public static void complainAnswer(String answer, double refund, int complainID) throws IOException {
        Message message = new Message("complainAnswer");
        message.setComplainID(complainID);
        message.setAnswer(answer);
        message.setRefund(refund);
        SimpleClient.getClient().sendToServer(message);
    }

}

