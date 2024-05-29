package model;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "billitems")
public class BillItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BillItemID")
    private int billItemID;

    @ManyToOne
    @JoinColumn(name = "BillID")
    private Bill bill;

    @Column(name = "ProductID")
    private int productID;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "SalePrice", precision = 10, scale = 2)
    private BigDecimal salePrice;

    // Constructors, getters, and setters

    public BillItem() {}

    public BillItem(Bill bill, int productID, int quantity, BigDecimal salePrice) {
        this.bill = bill;
        this.productID = productID;
        this.quantity = quantity;
        this.salePrice = salePrice;
    }

    public int getBillItemID() {
        return billItemID;
    }

    public void setBillItemID(int billItemID) {
        this.billItemID = billItemID;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
}
