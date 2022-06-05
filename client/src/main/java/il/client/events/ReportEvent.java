package il.client.events;

import il.entities.Complain;
import il.entities.Order;

import java.util.List;

public class ReportEvent {

    private List<Complain> complainList;


    public ReportEvent(List<Complain>  complainList, List<Order> ordersList){
        this. complainList =  complainList;
        this.ordersList= ordersList;
    }

    public List<Order> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }

    private List<Order> ordersList;

    public List<Complain> getItems() {
        return  complainList;
    }

    public void setItems(List<Complain>  complainList) {
        this. complainList =  complainList;
    }

}
