package il.server;

import il.entities.*;
import il.server.ocsf.ConnectionToClient;
import il.server.ocsf.AbstractServer;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SimpleServer extends AbstractServer {

    public SimpleServer(int port, boolean initServer) throws Exception {
        super(port);
        System.out.println("Server listen on port:" + port);
        if(initServer)
            testDB.initMySQL();
        //TestControl.testUserControl();
        UserControl.logOutAllusers();
    }

    public void closeServer() throws IOException {
        testDB.closeSession();
        this.close();
    }


    public static <T> LinkedList<T> getAllItems(Class<T> object){
        testDB.openSession();
        CriteriaBuilder builder = testDB.session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(object);
        Root<T> root = query.from(object);
        List<T> data = testDB.session.createQuery(query).getResultList();
        LinkedList<T> listItems = new LinkedList<>(data);
        testDB.closeSession();
        return listItems;
    }
    private <T, S> LinkedList<T> getAllItemsByKey(Class<T> object, String colum,S key){
        CriteriaBuilder builder = testDB.session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(object);
        Root<T> root = query.from(object);
        query.select(root);
        query.where(builder.equal(root.get(colum),key));
        List<T> data = testDB.session.createQuery(query).getResultList();
        LinkedList<T> listItems = new LinkedList<>(data);
        testDB.closeSession();
        return listItems;
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
            try {
            Message message = (Message) msg;
            Message sendMessage = new Message("");
            if (message.getMessage().equals("login")) {
                String username = message.getUsername();
                String pass = message.getPass();
                boolean isWorker = message.isWorker();

                sendMessage = LoginControl.checkLogin(username, pass, isWorker);
                System.out.println(sendMessage.getLoginResult());
                client.sendToClient(sendMessage);
            }

            if (message.getMessage().equals("getCatalogItems")) {
                sendMessage.setMessage("item catalog list");
               sendMessage.setListItem(getAllItems(Product.class));
                client.sendToClient(sendMessage);
                System.out.println("send Flowers to catalog");
            }

            if (message.getMessage().equals("getAllOpenComplaints")) {
                sendMessage.setMessage("AllOpenComplaints");
                sendMessage.setListComplains(ComplainConrtol.getAllOpenComplaint());
                client.sendToClient(sendMessage);
                System.out.println("send stores to client");
            }
            if (message.getMessage().equals("getAllOpenComplaintsForUser")) {
                sendMessage.setMessage("AllOpenComplaintsForUser");
                sendMessage.setListComplains(getAllItemsByKey(Complain.class, "user_id", message.getUserID()));
                client.sendToClient(sendMessage);
                System.out.println("send stores to client");
            }
            if (message.getMessage().equals("getAllOpenOrdersForUser")) {
                sendMessage.setMessage("AllOpenOrdersForUser");
                sendMessage.setListOrder(getAllItemsByKey(Order.class, "user_id", message.getUserID()));
                client.sendToClient(sendMessage);
                System.out.println("send stores to client");
            }

            if (message.getMessage().equals("getReport")) {
                sendMessage.setMessage("reportData");
                sendMessage.setListOrder(getAllItemsByKey(Order.class, "store_id", message.getStoreID()));
                sendMessage.setListComplains(getAllItemsByKey(Complain.class, "store_id", message.getStoreID()));
                sendMessage.setStoreID(message.getStoreID());
                client.sendToClient(sendMessage);
                System.out.println("send stores to client");
            }

            if (message.getMessage().equals("logout")) {
                int id = message.getIddatabase();
                boolean isworker = message.isWorker();
                if (isworker)
                    LoginControl.setToDiactiveEmp(id);
                else
                    LoginControl.setToDiactiveU(id);
            }

            if (message.getMessage().equals("newItem")) {
                CatalogControl.newItem(message.getProduct());
            }

            if (message.getMessage().equals("setImageItem")) {
                CatalogControl.setImage(message.getIdItem(), message.getbFile());
            }

            if (message.getMessage().equals("register")) {
                String username = message.getUsername();
                String name = message.getName();
                String pass = message.getPass();
                String id = message.getId();
                String credit_card = message.getCredit_card();
                String plan = Message.getPlan();
                List<Store> stores = Message.getStores();

                User newUser = new User(username, pass, credit_card, plan, name, id);
                System.out.println("get register request:" + username);

                String result = RegisterControl.checknewUser(newUser);


                if (result.equals("")) {
                    if (RegisterControl.register(newUser)) {
                        sendMessage.setRegisterStatus(true);
                        sendMessage.setRegisterResult("user as been register!");
                        newUser.addStore2(stores);
                    } else {
                        sendMessage.setRegisterStatus(false);
                        sendMessage.setRegisterResult("Error: somethings wrong with the database.");
                    }
                } else {
                    sendMessage.setRegisterStatus(false);
                    sendMessage.setRegisterResult(result);
                }

                sendMessage.setMessage("result register");
                client.sendToClient(sendMessage);
            }

            if (message.getMessage().equals("setNameItem")) {
                CatalogControl.setName(message.getIdItem(), message.getNameProduct());
            }
            if (message.getMessage().equals("setSaleItem")) {
                CatalogControl.setSale(message.getIdItem(), message.isSale(), message.getDiscountPer());
            }
            if (message.getMessage().equals("setPriceItem")) {
                CatalogControl.setPrice(message.getIdItem(), message.getNewPrice());
            }
            if (message.getMessage().equals("setTypeItem")) {
                CatalogControl.setType(message.getIdItem(), message.getType());
            }
            if (message.getMessage().equals("setColorItem")) {
                CatalogControl.setColor(message.getIdItem(), message.getColor());
            }
            if (message.getMessage().equals("deleteItem")) {
                CatalogControl.deleteItem(message.getIdItem());
            }
            if (message.getMessage().equals("cancelOrder")) {
                OrderControl.cancelOrder(message.getOrderID(),message.getTimeCancel(), message.getDateCancel());
            }
            if (message.getMessage().equals("newOrder")) {
                OrderControl.newOrder(message.getOrder(), message.getStoreID(), message.getUserID());
            }
            if (message.getMessage().equals("newComplain")) {
                ComplainConrtol.newComplain(message.getComplain(), message.getOrderID());
            }
            if (message.getMessage().equals("complainAnswer")) {
                ComplainConrtol.complainAnswer(message.getAnswer(), message.getRefund(), message.getComplainID());
            }
            if (message.getMessage().equals("setUserName")) {
                UserControl.setUserName(message.getUserID(), message.getUsername(), message.isWorker());
            }
            if (message.getMessage().equals("setName")) {
                UserControl.setName(message.getUserID(), message.getName(), message.isWorker());
            }
            if (message.getMessage().equals("setPassword")) {
                UserControl.setPassword(message.getUserID(), message.getPass(), message.isWorker());
            }
            if (message.getMessage().equals("setCreditCard")) {
                UserControl.setCreditCard(message.getUserID(), message.getCredit_card(), message.isWorker());
            }
            if (message.getMessage().equals("setPhone")) {
                UserControl.setPhone(message.getUserID(), message.getPhone(), message.isWorker());
            }
            if (message.getMessage().equals("setAddress")) {
                UserControl.setAddress(message.getUserID(), message.getAddress(), message.isWorker());
            }
            if (message.getMessage().equals("setMail")) {
                UserControl.setName(message.getUserID(), message.getMail(), message.isWorker());
            }



            } catch(IOException e){
            System.out.println(e.getMessage());
            System.out.println("handleMessageFromClient Error!" + client.getInetAddress());
        }
    }
}