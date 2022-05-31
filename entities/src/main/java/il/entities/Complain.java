package il.entities;

import javax.persistence.*;

@Entity
@Table(name="complains")
public class Complain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;
    private String storeID;
    private String date;
    private double answer;
    private boolean isHandle;
    private String complain;

    public Complain(User user, String storeID, String date, double answer, boolean isHandle, String complain) {
        this.user = user;
        this.storeID = storeID;
        this.date = date;
        this.answer = answer;
        this.isHandle = isHandle;
        this.complain = complain;
    }

    public Complain() {}


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

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAnswer() {
        return answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public boolean isHandle() {
        return isHandle;
    }

    public void setHandle(boolean handle) {
        isHandle = handle;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }
}
