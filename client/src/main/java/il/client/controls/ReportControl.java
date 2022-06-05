package il.client.controls;

import il.client.SimpleClient;
import il.entities.Message;

import java.io.IOException;

public class ReportControl {

    public static void getReportforStore (int sotreID) throws IOException {
        System.out.println("server send me all report");
        Message message = new Message("getReport");
        message.setStoreID(sotreID);
        SimpleClient.getClient().sendToServer(message);
    }
//    /ashekd whats beeter send one by one or togheter?
//    public static void getReportforStore (int sotreID1, int sotreID2) throws IOException {
//        System.out.println("server send me all reports for 2 stores");
//        Message message = new Message("getReports2Stores");
//        message.setStoreID(sotreID);
//        SimpleClient.getClient().sendToServer(message);
//    }
}
