package model;

import javax.persistence.*;

@Entity
@Table(name = "productcart")
public class ProductCart {

    @Id
    @Column(name = "ProductIDcart")
    private int productIDcart;

    @Column(name = "Namesellcart")
    private String nameSellCart;

    @Column(name = "Pricecart")
    private float priceCart;

    @Column(name = "StockQuantitycart")
    private int stockQuantityCart;

    @Column(name = "categoryIdcart")
    private int categoryIdCart;

    // Constructors
    public ProductCart() {
    }

    public ProductCart(int productIDcart, String nameSellCart, float priceCart, int stockQuantityCart, int categoryIdCart) {
        this.productIDcart = productIDcart;
    	this.nameSellCart = nameSellCart;
        this.priceCart = priceCart;
        this.stockQuantityCart = stockQuantityCart;
        this.categoryIdCart = categoryIdCart;
    }

    // Getters and Setters
    public int getProductIDcart() {
        return productIDcart;
    }

    public void setProductIDcart(int productIDcart) {
        this.productIDcart = productIDcart;
    }

    public String getNameSellCart() {
        return nameSellCart;
    }

    public void setNameSellCart(String nameSellCart) {
        this.nameSellCart = nameSellCart;
    }

    public float getPriceCart() {
        return priceCart;
    }

    public void setPriceCart(float priceCart) {
        this.priceCart = priceCart;
    }

    public int getStockQuantityCart() {
        return stockQuantityCart;
    }

    public void setStockQuantityCart(int stockQuantityCart) {
        this.stockQuantityCart = stockQuantityCart;
    }

    public int getCategoryIdCart() {
        return categoryIdCart;
    }

    public void setCategoryIdCart(int categoryIdCart) {
        this.categoryIdCart = categoryIdCart;
    }
    
    public Object[] toObject() {
    	return new Object[]{"" + productIDcart, nameSellCart, priceCart,  stockQuantityCart,  categoryIdCart};
    }
}
