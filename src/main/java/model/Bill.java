package model;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BillID")
    private int billID;

    @Column(name = "SaleDate", length = 100)
    private String saleDate;

    @Column(name = "TotalAmount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "CustomerName", length = 100)
    private String customerName;

    @Column(name = "CustomerPhone", length = 20)
    private String customerPhone;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<BillItem> billItems;

    // Constructors, getters, and setters

    public Bill() {}

    public Bill(String saleDate, BigDecimal totalAmount, String customerName, String customerPhone) {
        this.saleDate = saleDate;
        this.totalAmount = totalAmount;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public List<BillItem> getBillItems() {
        return billItems;
    }

    public void setBillItems(List<BillItem> billItems) {
        this.billItems = billItems;
    }
    public Object[] toObject() {
    	return new Object[]{"" +  saleDate , totalAmount,  customerName, customerPhone};
    }
}
