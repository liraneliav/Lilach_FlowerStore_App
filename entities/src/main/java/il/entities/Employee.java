package il.entities;

import java.io.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    private int permission;
    private String username;
    private String identifyNumbers;

    @ManyToOne
    private Store store;

    public Employee(){}

    public Employee(String name, String username, String pass, int permission, Store store){
        this.name = name;
        this.username =username;
        this.password =pass;
        this.permission = permission; // 1:= system admin, 2:= store wide manager 3:= shop manager, 4:= service employee, 5: shop employee
    }

    /* gets and sets*/


    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifyNumbers() {
        return identifyNumbers;
    }

    public void setIdentifyNumbers(String identifyNumbers) {
        this.identifyNumbers = identifyNumbers;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
//
//    public String getStore() {
//        return store;
//    }
//
//    public void setStore(String store) {
//        this.store = store;
//    }

    /* end gets and sets*/
}
