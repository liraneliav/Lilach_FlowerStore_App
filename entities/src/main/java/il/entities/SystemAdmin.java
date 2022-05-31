package il.entities;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class SystemAdmin extends Employee implements Serializable {

    public SystemAdmin() {
        super();
    }

    public SystemAdmin(String name, String username, String pass, int permission) {
        super(name, username, pass, permission);
    }

}


