package il.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "cartProducts")
public class CartProduct extends Product implements Serializable {
    private int amount;

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
