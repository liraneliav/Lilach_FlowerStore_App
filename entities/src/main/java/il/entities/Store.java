package il.entities;







import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int managerID;
    private String address;


    @ManyToMany
    private List<Employee> listEmployees=new ArrayList<Employee>();

    @OneToMany(mappedBy = "store")
    private List<Employee> listEmployee=new ArrayList<Employee>();

    @ManyToMany
    @JoinTable(
            name = "stores_users",
            joinColumns = @JoinColumn(name = "stores_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id"))
    private Set<User> users=new HashSet<>();

    @OneToMany(mappedBy = "store")
    private List<Order> listOrders=new ArrayList<Order>();

    @OneToMany(mappedBy = "store")
    private List<Complain> listComplains=new ArrayList<Complain>();


    public Store() {}

    public Store(int managerID, String address) {
        this.managerID = managerID;
        this.address = address;
    }

    public void addEmployee(Employee employee) {
        listEmployees.add(employee);
    }
    public void removeEmployee(Employee employee) {
        listEmployees.remove(employee);
    }
    public void addOrder(Order order) {
        listOrders.add(order);
    }
    public void removeOrder(Order order) {
        listOrders.remove(order);
    }
    public void addComplain(Complain complain) {
        listComplains.add(complain);
    }
    public void removeComplain(Complain complain) {
        listComplains.remove(complain);
    }
//    public void addUser(User user) {
//        listUsers.add(user);
//    }
//    public void removeUser(User user){
//        listUsers.remove(user);
//    }

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

//    public List<User> getListUsers() {
//        return listUsers;
//    }
//
//    public void setListUsers(List<User> listUsers) {
//        this.listUsers = listUsers;
//    }

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
