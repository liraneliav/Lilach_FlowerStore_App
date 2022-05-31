package il.entities;

import javax.persistence.*;

@Entity
@Table(name = "complain")
public class Complain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne (targetEntity = User.class)
    private User user;

    @ManyToOne
    private Store store;
    @Column(name = "complain_date")
    private String date;
    private double answer;
    private boolean isHandle;
    private String complain_data;


    public Complain(User user, String date, double answer, boolean isHandle, String complain) {
        this.user= user;
        this.date = date;
        this.answer = answer;
        this.isHandle = isHandle;
        this.complain_data = complain;

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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
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
        return complain_data;
    }

    public void setComplain(String complain) {
        this.complain_data = complain;
    }
}
