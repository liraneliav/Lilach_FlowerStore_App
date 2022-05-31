package il.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ShoppingCart")
public class ShoppingCart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numOfProducts;
    private double sum;
    private double sumAfterSale;

    @OneToMany(mappedBy = "cart")
    private List<CartProduct> listProducts = new ArrayList<CartProduct>();

    public ShoppingCart() {
        this.numOfProducts = 0;
        this.sum = 0;
        this.sumAfterSale = 0;
    }

    public void addProduct(CartProduct product) {
        if(!listProducts.contains(product))
            this.numOfProducts++;
        listProducts.add(product);
        double price = (product.getPrice()*product.getAmount());
        this.sum+=price;
        this.sumAfterSale = this.sumAfterSale + (product.getPriceSale()*product.getAmount());
    }

    public void removeProduct(CartProduct product){
        listProducts.remove(product);
        this.numOfProducts--;
        double price = (product.getPrice()*product.getAmount());
        this.sum-=price;
        this.sumAfterSale = this.sumAfterSale - (product.getPriceSale()*product.getAmount());
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumOfProducts() {
        return numOfProducts;
    }

    public void setNumOfProducts(int numOfProducts) {
        this.numOfProducts = numOfProducts;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public List<CartProduct> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<CartProduct> listProducts) {
        this.listProducts = listProducts;
    }
}
