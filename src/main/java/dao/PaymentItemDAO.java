package dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.barcodelib.barcode.Linear;

import view.Paymentscreen;
import database.JDBCUtil;
import model.ProductCart;
import model.billModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class PaymentItemDAO {

	public static PaymentItemDAO getInstanitemDAO()
	{
	return new PaymentItemDAO();
	}
	
	public ArrayList<ProductCart> selectProductCart() {	
		 ArrayList<ProductCart> result = new ArrayList<ProductCart>();
   try {
        Connection con = JDBCUtil.getConnection();
 
        Statement st = con.createStatement();
        String sql = "Select * from productcart" ;
        ResultSet rs = st.executeQuery(sql);
        while(rs.next())
        { 
       	 int ProductID = rs.getInt("ProductIDcart");
        	 String Name = rs.getString("Namesellcart");    
        	 float Price = rs.getFloat("Pricecart");
        	 int StockQuantity = rs.getInt("StockQuantitycart");
        	 int categoryId = rs.getInt("categoryIdcart");

            ProductCart item = new ProductCart(ProductID, Name, Price, StockQuantity, categoryId);
      result.add(item);
        }
   } catch (Exception e) {
   }
return result;
	    }
	
	public int insert(billModel t) {
		
		  try {
		        Connection con = JDBCUtil.getConnection();
		        String sql = "INSERT INTO bills (BillID, SaleDate, TotalAmount, CustomerName, CustomerPhone) " +
		                     "VALUES (?, ?, ?, ?, ?)";
		        			      
		        try (PreparedStatement pst = con.prepareStatement(sql)) {
		            pst.setInt(1, t.getIdbill());
		            pst.setString(2, t.getSaledate());
		            pst.setFloat(3, t.getTotalcount());
		            pst.setString(4, t.getCustomername());
		            pst.setString(5, t.getCustomerphone());
		            
		            int result = pst.executeUpdate();			            
		            return result;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Cannot insert , please check information.");
		    }
		    return 0;
	}
	
	public float getTotalCostFromTable() {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT SUM(StockQuantitycart * Pricecart) AS totalCost FROM productcart";
            preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getFloat("totalCost");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  0; // Return 0 if there's an error
    } 
	
	public int getBillCount() {
	    int count = 0;
	    try {
	        Connection con = JDBCUtil.getConnection();
	        String sql = "SELECT COUNT(*) AS count FROM bills";
	        
	        try (PreparedStatement pst = con.prepareStatement(sql)) {
	            ResultSet rs = pst.executeQuery();
	            if (rs.next()) {
	                count = rs.getInt("count");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle or log the exception as needed
	    }
	    return count;
	}
	
	public int deleteProductcart() {
	    try {
	        Connection con = JDBCUtil.getConnection();
	        // Sử dụng PreparedStatement để tránh SQL Injection và tăng hiệu suất
	        String sql = "DELETE FROM productcart";

	        try (PreparedStatement pst = con.prepareStatement(sql)) {
	            // Thực hiện câu lệnh SQL và lấy kết quả
	            int result = pst.executeUpdate();
	            return result;  // You may want to return the number of affected rows
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Xử lý exception (quay lại mã lỗi hoặc thông báo lỗi)
	    }
	    return 0;
	}	
	
    public void writeToFile(String filePath, String timebill, int BillId, String namecustomer, String phonenumbercustomer, float moneycustomergive, float totalmoney, float changemoney) {
        try {
            // Tạo đối tượng File với đường dẫn cụ thể
            File file = new File(filePath);

            // Kiểm tra nếu file không tồn tại thì tạo file mới
            if (!file.exists()) {
                file.createNewFile();
            }

            // Sử dụng FileWriter để ghi dữ liệu vào file
            FileWriter writer = new FileWriter(file, true); // true để ghi tiếp vào file thay vì ghi đè
            writer.write("Time: " + timebill + "\n");
            writer.write("Bill ID: " + BillId + "\n");
            writer.write("Customer Name: " + namecustomer + "\n");
            writer.write("Customer PhoneNumber: " + phonenumbercustomer + "\n");
            writer.write("Money Customer Gives: " + moneycustomergive + "\n");
            writer.write("Total Money: " + totalmoney + "\n");
            writer.write("Change Money: " + changemoney + "\n");

           
            writer.write("-----------\n");

            // Đóng FileWriter sau khi ghi xong
            writer.close();

        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi ghi vào file.");
            e.printStackTrace();
        }
    }
    
    public void writeTableToFile(DefaultTableModel tableModelBill, String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            for (int i = 0; i < tableModelBill.getRowCount(); i++) {
                for (int j = 0; j < tableModelBill.getColumnCount(); j++) {
                    writer.write(tableModelBill.getValueAt(i, j).toString() + "\t");
                }
                writer.write("\n");
            }
            writer.write("-----------\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi ghi vào file.");
            e.printStackTrace();
        }
    }
    
  
    public void openFileExplorer(String filePath) {
        try {
            // Mở thư mục chứa file trong File Explorer
            String folderPath = new File(filePath).getParent();
            ProcessBuilder processBuilder = new ProcessBuilder("explorer.exe", folderPath);
            processBuilder.start();
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi mở File Explorer.");
            e.printStackTrace();
        }
    }
	
}
