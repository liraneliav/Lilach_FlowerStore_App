package il.entities;


import javax.persistence.*;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Complain complain;

    @ManyToOne
    private Store store;

    private String dateReceive;
    private String timeReceive;
    private String dateOrder;
    private String timeOrder;
    private double sum;
    private String greeting;
    private String nameReceives;
    private String phoneReceives;
    private String address;

    public Order(User user, String dateReceive, String timeReceive, String dateOrder, String timeOrder, double sum, String greeting, String nameReceives, String phoneReceives, String address) {
        this.user = user;
        this.dateReceive = dateReceive;
        this.timeReceive = timeReceive;
        this.dateOrder = dateOrder;
        this.timeOrder = timeOrder;
        this.sum = sum;
        this.greeting = greeting;
        this.nameReceives = nameReceives;
        this.phoneReceives = phoneReceives;
        this.address = address;
        this.complain = null;
    }

    public Order() {}

    public Complain getComplain() {
        return complain;
    }

    public void setComplain(Complain complain) {
        this.complain = complain;
    }

    public String getPhoneReceives() {
        return phoneReceives;
    }

    public void setPhoneReceives(String phoneReceives) {
        this.phoneReceives = phoneReceives;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDateReceive() {
        return dateReceive;
    }

    public void setDateReceive(String dateReceive) {
        this.dateReceive = dateReceive;
    }

    public String getTimeReceive() {
        return timeReceive;
    }

    public void setTimeReceive(String timeReceive) {
        this.timeReceive = timeReceive;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(String timeOrder) {
        this.timeOrder = timeOrder;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getNameReceives() {
        return nameReceives;
    }

    public void setNameReceives(String nameReceives) {
        this.nameReceives = nameReceives;
    }

    public String getPhoneRecives() {
        return phoneReceives;
    }

    public void setPhoneRecives(String phoneRecives) {
        this.phoneReceives = phoneRecives;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
