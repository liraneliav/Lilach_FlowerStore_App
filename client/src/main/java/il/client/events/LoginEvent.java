package il.client.events;

import il.entities.*;

import java.util.LinkedList;
import java.util.List;

public class LoginEvent {
    private LinkedList<Complain> complainList=null;
    private LinkedList<Order> orderList=null;
    private LinkedList<Store>  storeList=null;
    private LinkedList<Employee>  empolyeeList=null;


    private LinkedList<User>  userList=null;
    private int storeId;
    private int id;
    private boolean isWorker;
    private String username;
    private int permission;
    private boolean loginStatus;
    private String result;
    private User user;
    private List<Store> userlistStore=null;


    private Store store;

    public LoginEvent(boolean status, String result){
        this.loginStatus = status;
        this.result = result;
    }
    //USER
    public LoginEvent(boolean statsu, User user, LinkedList<Complain> complains, LinkedList<Order> orders, LinkedList<Store> userlistStore){
        this.loginStatus = statsu;
        this.userlistStore = userlistStore;
        this.orderList = orders;
        this.complainList = complains;
        this.user = user;
        this.username = user.getUserName();
        this.isWorker = false;
    }

    //  employee branch manager
    public LoginEvent(BranchManager e, int permission,  LinkedList<Complain> complains, LinkedList<Order> orders){
        this.loginStatus = true;
        this.isWorker = true;
        this.username = e.getUsername();
        this.permission = permission;
        this.store = e.getStore();
        this.orderList=orders;
        this.complainList=complains;
    }
    //admin
    public LoginEvent(SystemAdmin e, int permission,  LinkedList<Complain> complains, LinkedList<Order> orders, LinkedList<Store> stores,LinkedList<User> users,
                      LinkedList<Employee> employees ){
        this.loginStatus = true;
        this.isWorker = true;
        this.username = e.getUsername();
        this.permission = permission;
        this. storeList = stores;
        this.orderList=orders;
        this.complainList=complains;
        this.userList =users;
        this.empolyeeList =employees;
    }
    //customer service
    public LoginEvent(CustomerService e, int permission,  LinkedList<Complain> complains, LinkedList<Order> orders){
        this.loginStatus = true;
        this.isWorker = true;
        this.username = e.getUsername();
        this.permission = permission;
        this.orderList=orders;
        this.complainList=complains;
    }


    //StoreEmployee
    public LoginEvent(StoreEmployee e, int permission,  int storeId){
        this.loginStatus = true;
        this.isWorker = true;
        this.username = e.getUsername();
        this.permission = permission;
    }

    //netwotkmanager
    public LoginEvent( NetworkManger e, int permission,  LinkedList<Complain> complains, LinkedList<Order> orders, LinkedList<Store> stores){
        this.loginStatus = true;
        this.isWorker = true;
        this.username = e.getUsername();
        this.permission = permission;
        this. storeList = stores;
        this.orderList=orders;
        this.complainList=complains;
    }


    public LinkedList<Employee> getEmpolyeeList() {
        return empolyeeList;
    }

    public void setEmpolyeeList(LinkedList<Employee> empolyeeList) {
        this.empolyeeList = empolyeeList;
    }
    public LinkedList<User> getUserList() {
        return userList;
    }

    public void setUserList(LinkedList<User> userList) {
        this.userList = userList;
    }
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<Store> getUserlistStore() {
        return userlistStore;
    }

    public void setUserlistStore(List<Store> userlistStore) {
        this.userlistStore = userlistStore;
    }
    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public boolean isWorker() {
        return isWorker;
    }

    public void setWorker(boolean worker) {
        isWorker = worker;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }


    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LinkedList<Complain> getComplainList() {
        return complainList;
    }

    public void setComplainList(LinkedList<Complain> complainList) {
        this.complainList = complainList;
    }

    public LinkedList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(LinkedList<Order> orderList) {
        this.orderList = orderList;
    }

    public LinkedList<Store> getStoreList() {
        return storeList;
    }

    public void setStoreList(LinkedList<Store> storeList) {
        this.storeList = storeList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
