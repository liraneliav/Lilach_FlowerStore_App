package il.client.events;

import il.entities.Complain;

import java.util.List;

public class ComplaintEvent {

    private List<Complain> complainList;

    public List<Complain> getComplainList() {
        return  complainList;
    }

    public void setComplainList(List<Complain>  complainList) {
        this. complainList =  complainList;
    }


    public ComplaintEvent(List<Complain>  complainList){
        this. complainList =  complainList;
    }
}


