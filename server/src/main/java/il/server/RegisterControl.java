package il.server;

import il.entities.Flower;
import il.entities.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class RegisterControl {
    public static boolean register(User newUser){
        testDB.openSssion();
        try {
            testDB.session.save(newUser);
            testDB.session.flush();
            testDB.session.getTransaction().commit();
            System.out.println("user add to mySQL");
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: register");
        }
        testDB.closeSession();
        return false;
    }


    public static int checknewUser(User newUser){
        List<User> lUsers = getAllUsers();
//        compare_name c = new compare_name();
//        int result = Collections.binarySearch(lUsers,newUser, c.comparename());
        for (User user: lUsers){
            if (user.getUserName().equals(newUser.getUserName()) || user.getIdentifyNumbers().equals(newUser.getIdentifyNumbers()) || user.getCreditCard().equals(newUser.getCreditCard()))
                if (user.getUserName().equals(newUser.getUserName()))
                    return 1;
            if (user.getIdentifyNumbers().equals(newUser.getIdentifyNumbers()))
                return 2;
            if (user.getCreditCard().equals(newUser.getCreditCard())) {
                if (user.getaccountStatus() == 0)
                    return 3;
            }
        }
        return 0;
    }


    public static List<User> getAllUsers(){
        testDB.openSssion();
        CriteriaBuilder builder = testDB.session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.orderBy(builder.asc(root.get("userName")));
        List<User> data = testDB.session.createQuery(query.orderBy()).getResultList();
        LinkedList<User> listItems = new LinkedList<>(data);
        testDB.closeSession();
        return listItems;
    }

}