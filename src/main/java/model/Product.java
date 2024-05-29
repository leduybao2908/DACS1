package model;
import java.util.Vector;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private int productID;

    @Column(name = "Name", length = 100)
    private String name;

    @Column(name = "Price")
    private float price;

    @Column(name = "StockQuantity")
    private int stockQuantity;

    @Column(name = "categoryId")
    private int categoryId;

    // Constructors, getters, and setters

    public Product() {}

    public Product(int productID, String name, float price, int stockQuantity, int categoryId) {
    	this.productID = productID;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
	
	 public Object[] toObject() {
	    	return new Object[]{"" + productID, name,  price,  stockQuantity,  categoryId};
	    }
}
