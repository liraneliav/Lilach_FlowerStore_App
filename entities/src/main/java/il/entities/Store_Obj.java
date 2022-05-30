package il.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="stores")
public class Store_Obj {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int managerID;
    private String address;
    @ManyToMany
    private List<Employee> listEmployees=new ArrayList<Employee>();;
    @ManyToMany
    private List<User> listUsers=new ArrayList<User>();;
    @ManyToMany
    private List<Order> listOrders=new ArrayList<Order>();;
    @ManyToMany
    private List<Complain> listComplains=new ArrayList<Complain>();;

    /*
    @ManyToMany
    @JoinTable(name = "stores_Employee_Obj",
            joinColumns = @JoinColumn(name = "store_Obj_id", referencedColumnName = "employee_Obj_id"))

     */
    public Store_Obj() {}

    public Store_Obj(int managerID, String address) {
        this.managerID = managerID;
        this.address = address;
    }


    public void addEmployee(Employee employee) {
        listEmployees.add(employee);
    }
    public void addOrder(Order order) {
        listOrders.add(order);
    }
    public void addComplain(Complain complain) {
        listComplains.add(complain);
    }
    public void addUser(User user) {
        listUsers.add(user);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Employee> getListEmployees() {
        return listEmployees;
    }

    public void setListEmployees(List<Employee> listEmployees) {
        this.listEmployees = listEmployees;
    }

    public List<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    public List<Order> getListOrders() {
        return listOrders;
    }

    public void setListOrders(List<Order> listOrders) {
        this.listOrders = listOrders;
    }

    public List<Complain> getListComplains() {
        return listComplains;
    }

    public void setListComplains(List<Complain> listComplains) {
        this.listComplains = listComplains;
    }
}
