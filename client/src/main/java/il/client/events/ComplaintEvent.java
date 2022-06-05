package il.client.events;

import il.entities.Complain;
import il.entities.Store;
import il.entities.StoreEmployee;
import il.entities.User;

import java.util.List;

public class ComplaintEvent {

    private List<Complain> complainList;

    public List<Complain> getItems() {
        return  complainList;
    }

    public void setItems(List<Complain>  complainList) {
        this. complainList =  complainList;
    }


    public ComplaintEvent(List<Complain>  complainList){
        this. complainList =  complainList;
    }
}


