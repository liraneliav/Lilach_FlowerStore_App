package il.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "cartProducts")
public class CartProduct extends Product implements Serializable {
    private int amount;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne
    private Order order;

    public CartProduct(String name, double price, boolean sale, double discount_perc, String type, String color, int amount){
        super(name, price, sale, discount_perc, type, color);
        this.amount=amount;
    }

    public CartProduct() {
        super();
    }

    public double getPriceSale(){
        if(this.isSale()){
            return this.getPrice() *(1 - this.getDiscount_perc() /100);
        }
        return this.getPrice();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
