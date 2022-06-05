package il.client.controls;

import il.client.SimpleClient;
import il.entities.Complain;
import il.entities.Message;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ComplainConrtol{

    public static void newComplain(Complain complain, int orderID) throws IOException {
        System.out.println("new complain to order "+ orderID);
        Message message = new Message("newComplain");
        message.setComplain(complain);
        message.setOrderID(orderID);
        SimpleClient.getClient().sendToServer(message);
    }

    public static void getAllOpenComplaints () throws IOException {
        System.out.println("server send me all compliants");
        Message message = new Message("getAllOpenComplaints");
        SimpleClient.getClient().sendToServer(message);
    }
    public static void complainAnswer(String answer, double refund, int complainID) throws IOException {
        Message message = new Message("complainAnswer");
        message.setComplainID(complainID);
        message.setAnswer(answer);
        message.setRefund(refund);
        SimpleClient.getClient().sendToServer(message);
    }


    public static void getAllOpenComplaintsByUser (int userID) throws IOException {
        System.out.println("server send me all compliants for user");
        Message message = new Message("getAllOpenComplaintsForUser");
        message.setUserID(userID);
        SimpleClient.getClient().sendToServer(message);
    }

}