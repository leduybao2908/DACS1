package view;
import chartjava.*;   
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import database.JDBCUtil;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import dao.AdminItemDAO;
import model.Product;
import model.ProductCart;
import supportview.BackgroundPanel;
import supportview.ButtonGradient;
import supportview.Combobox;
import supportview.MaterialTabbed;
import supportview.MultiClientServer;
import supportview.RoundJTextField;
import supportview.TableCustom;
import model.Employee;
import model.Product;

import javax.swing.JTabbedPane; 
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
public class AdminScreen extends JFrame {

	public static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTextField textField;
	public JTextField TextT0talManage;
	public JTextField TextTotalCart;
	public JTable TableTotalSalesList;
	public JTable TableSalesList;
	public JTable TableTotalManagementList;
	public JTextField textFieldIDItem;
	public JTextField textFieldNameItem;
	public JTextField textFieldOldCount;
	public JTextField textFieldCostInput;
	public DefaultTableModel tableModel;
	public DefaultTableModel tableModelTotalManage;
	public AdminItemDAO AdminItemDAO = new AdminItemDAO() ; 
//	ArrayList<Product> result = new ArrayList<Product>();
	public JTextField textFieldIDItemSell;
	public JTextField textFieldNameItemSell;
	public JTextField textFieldCostSell;
	public JTextField textFieldCountNeedBuy;
	public DefaultTableModel tableModelSale;
	public int currentCount;
    public static final String TOTAL_KEY = "totalKey";
	public float totalCost = 0.0f;
	public JTextField textFieldCountSellWarehouse;
	public JTextField textFieldNewCount;
	public JTextField textFieldSearchSell;
	private JTextField textFieldCateID;
	private DefaultTableModel tableModelEmployeeManage;
	private JTable TableEmployeeList;
	private JTextField TextEmployeeName;
	private JTextField TextEmployeePhone;
	private JTextField TextEmployeeGmail;
	private JTextField TextEmployeeWorkHour;
	private JTextField textFieldSearchEmployee;
	private JTextField textFieldFindManageProduct;
	private JTextField textFieldIDCATE;
	private JTextField textFieldID;
	private JTextField textFieldPassEmployee;
	
	public AdminScreen() {
	        // ...
	    //    ChangeScreen newScreenInstance = new ChangeScreen(this); // Pass 'this' as the argument
	        // ...
	    setTitle("Badminton Store");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		URL url = AdminScreen.class.getResource("52745-badminton-icon.png");
		if (url != null) {
		    Image imagethemgiohang = Toolkit.getDefaultToolkit().createImage(url);
		    this.setIconImage(imagethemgiohang);
		}

		MaterialTabbed tabbedPane = new supportview.MaterialTabbed();
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tabbedPane.setBounds(40, 0, 1000, 750);
		contentPane.add(tabbedPane);
		Image backgroundImage = Toolkit.getDefaultToolkit().createImage(CashierScreen.class.getResource("bg.jpg"));
        BackgroundPanel panelSale = new BackgroundPanel(backgroundImage);

		tabbedPane.addTab("SELL", null, panelSale, null);
		panelSale.setLayout(null);
		
		// TẠO BẢNG VÀ THAO TÁC VỚI B
		
		tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"ProductID", "Name", "Price", "StockQuantity", "CategoryId"});
		TableTotalSalesList = new JTable(tableModel);
		TableTotalSalesList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TableTotalSalesList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Get the selected row index
				int selectedRow = TableTotalSalesList.getSelectedRow();
				// Check if a row is selected and within bounds
				if (selectedRow != -1 && selectedRow < TableTotalSalesList.getRowCount()) {
					// Debug print statements
				
		
					// Ensure the selected row has the correct number of columns
					if (TableTotalSalesList.getColumnCount() >= 5) {
						Object ProductID = TableTotalSalesList.getValueAt(selectedRow, 0);
						Object Name = TableTotalSalesList.getValueAt(selectedRow, 1);
						Object Price = TableTotalSalesList.getValueAt(selectedRow, 2);
						Object StockQuantity = TableTotalSalesList.getValueAt(selectedRow, 3);
						Object categoryId = TableTotalSalesList.getValueAt(selectedRow, 4);
		
						textFieldIDItemSell.setText(ProductID != null ? ProductID.toString() : "");
						textFieldNameItemSell.setText(Name != null ? Name.toString() : "");
						textFieldCountSellWarehouse.setText(StockQuantity != null ? StockQuantity.toString() : "");
						textFieldCostSell.setText(Price != null ? Price.toString() : "");
						textFieldCateID.setText(categoryId != null ? categoryId.toString() : "");
					} else {
					}
				} else {
				}
			}
		});
		
		JScrollPane scrollPaneTableTotalSalesList = new JScrollPane(TableTotalSalesList);
		TableCustom.apply(scrollPaneTableTotalSalesList,TableCustom.TableType.MULTI_LINE);
		scrollPaneTableTotalSalesList.setBounds(10, 90, 975, 194);
		panelSale.add(scrollPaneTableTotalSalesList);
		
		tableModelSale = new DefaultTableModel(new Object[][]{}, new String[]{"ID PRODUCT", "NAME PRODUCT", "PRICE", "QUANTITY", "IDCATEGORY"});
		TableSalesList = new JTable(tableModelSale);
		
		TableSalesList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TableSalesList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        // Get the selected row index
		        int selectedRow = TableSalesList.getSelectedRow();
		        // Check if a row is selected
		        if (selectedRow != -1) {
		            // Get data from the selected row
		            Object iditem = TableSalesList.getValueAt(selectedRow, 0);
		            int idproductselect;
		            if (iditem instanceof Integer) {
		                idproductselect = (Integer) iditem;
		            } else if (iditem instanceof String) {
		                try {
		                    idproductselect = Integer.parseInt((String) iditem);
		                } catch (NumberFormatException ex) {
		                    ex.printStackTrace();
		                    return;
		                }
		            } else {
		                System.err.println("Unexpected type: " + iditem.getClass().getName());
		                return;
		            }

		            Object nameitem = TableSalesList.getValueAt(selectedRow, 1);
		            Object costinput = TableSalesList.getValueAt(selectedRow, 2);
		            Object count = TableSalesList.getValueAt(selectedRow, 3);
		            Object countofTableTotalSalesList = AdminItemDAO.getStockQuantityByProductID(idproductselect);
		            Object categoryId = TableSalesList.getValueAt(selectedRow, 4);

		            // Display data in TextFields
		            textFieldIDItemSell.setText(iditem.toString());
		            textFieldNameItemSell.setText(nameitem.toString());     
		            textFieldCountSellWarehouse.setText(countofTableTotalSalesList.toString());    
		            textFieldCountNeedBuy.setText(count.toString());    
		            textFieldCostSell.setText(costinput.toString());
		            textFieldCateID.setText(categoryId.toString());

		            int countbfupdate;
		            int countadd;
		            try {
		                countbfupdate = Integer.parseInt(textFieldCountSellWarehouse.getText());
		                countadd = Integer.parseInt(textFieldCountNeedBuy.getText());
		            } catch (NumberFormatException ex) {
		                ex.printStackTrace();
		                return;
		            }
		        }
		    }
		});

		
		JScrollPane scrollPaneTableSalesList = new JScrollPane(TableSalesList);
		TableCustom.apply(scrollPaneTableSalesList,TableCustom.TableType.MULTI_LINE);
		scrollPaneTableSalesList.setBounds(10, 456, 975, 122);
		scrollPaneTableSalesList.setToolTipText("");
		panelSale.add(scrollPaneTableSalesList);
		TableDachSachTongSell();
		
		JButton ButtonAddToCart = new supportview.ButtonGradient();
		ButtonAddToCart.setText("ADD TO CART");	
		ButtonAddToCart.setBounds(198, 406, 221, 40);
		ButtonAddToCart.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminScreen.class.getResource("Cart_icon.png"))));
		ButtonAddToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				
            	// Lấy dữ liệu từ bảng vào text
            	int iditemsell = Integer.parseInt(textFieldIDItemSell.getText());
            	int idcategory = Integer.parseInt(textFieldCateID.getText());
                String nameitemsell = textFieldNameItemSell.getText(); 
                int countsell = Integer.parseInt(textFieldCountNeedBuy.getText());
                float costinputsell =Float.parseFloat( textFieldCostSell.getText());
                int count = Integer.parseInt(textFieldCountSellWarehouse.getText());
                
                // Tính toán dữ liệu
                
                float itemTotalCost = costinputsell * countsell;               
                totalCost +=  itemTotalCost;  
                int newCount = count - countsell;               
                TextTotalCart.setText(String.valueOf(totalCost)); 
                
               textFieldCountSellWarehouse.setText(String.valueOf(newCount));
               int countsellnew = Integer.parseInt(textFieldCountSellWarehouse.getText());
                ProductCart item = new ProductCart(iditemsell, nameitemsell,costinputsell, countsell,idcategory);
                AdminItemDAO.inserttablesell(item);
                Product itemud = new Product(iditemsell, nameitemsell, costinputsell, newCount, idcategory);
                AdminItemDAO.update(itemud);  
                    // Cập nhật bảng ngay tại đây
                DefaultTableModel model = (DefaultTableModel) TableTotalSalesList.getModel();
                int selectedRow = TableTotalSalesList.getSelectedRow();
                if (selectedRow != -1) {
                    model.setValueAt(iditemsell, selectedRow, 0);
                    model.setValueAt(nameitemsell, selectedRow, 1);
                    model.setValueAt(costinputsell, selectedRow, 2);
                    model.setValueAt(countsellnew, selectedRow, 3);
                    model.setValueAt(idcategory, selectedRow, 4);
                }
                SaleTable();               
            }
        });
		
		ButtonAddToCart.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelSale.add(ButtonAddToCart);
		
		JLabel LabelTongTienSell = new JLabel("TOTAL MONEY");
		LabelTongTienSell.setBounds(291, 586, 126, 27);
		LabelTongTienSell.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelSale.add(LabelTongTienSell);
		
		JButton ButtonSell = new supportview.ButtonGradient();
		ButtonSell.setText("SELL");
		ButtonSell.setHorizontalAlignment(SwingConstants.LEFT);
		ButtonSell.setBounds(524, 624, 126, 38);
		ButtonSell.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminScreen.class.getResource("cash-icon.png"))));

		ButtonSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelSale.add(ButtonSell);
		
		TextTotalCart = new JTextField();
		TextTotalCart.setBounds(224, 623, 237, 40);
		TextTotalCart.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelSale.add(TextTotalCart);
		TextTotalCart.setColumns(10);	
		
		textFieldIDItemSell = new JTextField();
		textFieldIDItemSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldIDItemSell.setColumns(10);
		textFieldIDItemSell.setBounds(172, 294, 76, 38);
		panelSale.add(textFieldIDItemSell);
		
		textFieldNameItemSell = new JTextField();
		textFieldNameItemSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldNameItemSell.setColumns(10);
		textFieldNameItemSell.setBounds(172, 358, 271, 38);
		panelSale.add(textFieldNameItemSell);
		
		textFieldCostSell = new JTextField();
		textFieldCostSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldCostSell.setColumns(10);
		textFieldCostSell.setBounds(698, 293, 221, 38);
		panelSale.add(textFieldCostSell);
		
		textFieldCountNeedBuy = new JTextField();
		textFieldCountNeedBuy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldCountNeedBuy.setColumns(10);
		textFieldCountNeedBuy.setBounds(843, 347, 76, 38);
		panelSale.add(textFieldCountNeedBuy);
		
		JLabel LabelIdItemSell = new JLabel("ID ITEM");
		LabelIdItemSell.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelIdItemSell.setBounds(52, 294, 95, 44);
		panelSale.add(LabelIdItemSell);
		
		JLabel LabelNameItemSell = new JLabel("ITEM NAME");
		LabelNameItemSell.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelNameItemSell.setBounds(52, 355, 135, 44);
		panelSale.add(LabelNameItemSell);
		
		JLabel lblCostSell = new JLabel("ITEM PRICE");
		lblCostSell.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCostSell.setBounds(555, 287, 116, 44);
		panelSale.add(lblCostSell);
		
		JLabel LabelCountSell = new JLabel("QUANTITY");
		LabelCountSell.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelCountSell.setBounds(555, 341, 95, 44);
		panelSale.add(LabelCountSell);
		
		textFieldCountSellWarehouse = new JTextField();
		textFieldCountSellWarehouse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldCountSellWarehouse.setBounds(698, 347, 126, 38);
		panelSale.add(textFieldCountSellWarehouse);
		textFieldCountSellWarehouse.setColumns(10);
		
		JButton btnNewButton = new supportview.ButtonGradient();
		btnNewButton.setText("REFRESH");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			refreshbutton();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(739, 623, 126, 40);
		panelSale.add(btnNewButton);
		
		JButton btnDeleteItemCart = new supportview.ButtonGradient();
		btnDeleteItemCart.setText("REMOVE FROM CART");
		btnDeleteItemCart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	int iditemsell = Integer.parseInt(textFieldIDItemSell.getText());
                String nameitemsell = textFieldNameItemSell.getText(); 
                int countsell = Integer.parseInt(textFieldCountNeedBuy.getText());
                int countofTableTotalSalesList = Integer.parseInt(textFieldCountSellWarehouse.getText());
                float costinputsell =Float.parseFloat( textFieldCostSell.getText());
                int countsellnew = countsell + countofTableTotalSalesList;
                int idcategory = Integer.parseInt(textFieldCateID.getText());
                
            	  int row = TableSalesList.getSelectedRow();
                  if(row ==-1)
                  {
                    JOptionPane.showMessageDialog(AdminScreen.this, "Please select row you want to delete!","Error",JOptionPane.ERROR_MESSAGE);
                  }
                  else
                  {
                        int Confirm = JOptionPane.showConfirmDialog(AdminScreen.this, "Are you sure ?");
                        if (Confirm==JOptionPane.YES_OPTION)
                        {    
                        	Product itemud = new Product(iditemsell, nameitemsell, costinputsell, countsellnew, idcategory);
                        	AdminItemDAO.update(itemud); 
                        // Cập nhật bảng ngay tại đây
                    DefaultTableModel model = (DefaultTableModel) TableTotalSalesList.getModel();
                    int selectedRow = TableTotalSalesList.getSelectedRow();
                    if (selectedRow != -1) {
                        model.setValueAt(iditemsell, selectedRow, 0);
                        model.setValueAt(nameitemsell, selectedRow, 1);
                        model.setValueAt(costinputsell, selectedRow, 2);
                        model.setValueAt(countsellnew, selectedRow, 3);
                        model.setValueAt(idcategory, selectedRow, 4);

                    }                        
                    int itemid =Integer.valueOf(String.valueOf( TableSalesList.getValueAt(row,0)));                
                    AdminItemDAO.deletesalecart(itemid); 
                    tableModelSale.removeRow(row);      
					refreshTable1();               
          }
		  
                  }	            
				
                  clearFields();
             }
         });
		
		btnDeleteItemCart.setIcon(new ImageIcon(AdminScreen.class.getResource("/view/Delete_icon.png")));
		btnDeleteItemCart.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDeleteItemCart.setBounds(612, 406, 267, 40);
		panelSale.add(btnDeleteItemCart);
		
		textFieldSearchSell = new JTextField();
		textFieldSearchSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldSearchSell.setBounds(117, 27, 346, 40);
		panelSale.add(textFieldSearchSell);
		textFieldSearchSell.setColumns(10);
		
		JButton btnSearchSell = new supportview.ButtonGradient();
		btnSearchSell.setText("SEARCH");
		btnSearchSell.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String searchTerm = textFieldSearchSell.getText();
		        searchByName(searchTerm);
		    }
		});

		btnSearchSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearchSell.setBounds(536, 26, 135, 43);
		panelSale.add(btnSearchSell);
		
		JLabel lblNewLabel = new JLabel("CATE ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(281, 294, 76, 38);
		panelSale.add(lblNewLabel);
		
		textFieldCateID = new JTextField();
		textFieldCateID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldCateID.setColumns(10);
		textFieldCateID.setBounds(367, 297, 76, 38);
		panelSale.add(textFieldCateID);
		
		final Combobox comboBoxSell = new Combobox<>();
		comboBoxSell.setLabeText("Category");
		
		comboBoxSell.setFont(new Font("Arial", Font.PLAIN, 16));
		comboBoxSell.setBounds(739, 24, 144, 43);
		panelSale.add(comboBoxSell);
		comboBoxSell.addItem("");
		comboBoxSell.addItem("Vợt");
		comboBoxSell.addItem("Giày");
		comboBoxSell.addItem("Trang phục");
		comboBoxSell.addItem("Vật phẩm khác");
		Font fontSell = new Font("Arial", Font.PLAIN, 16);
		comboBoxSell.setFont(fontSell);
		comboBoxSell.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Xử lý hành động tương ứng với mỗi lựa chọn
                String selectedOption = (String) comboBoxSell.getSelectedItem();
                switch (selectedOption) {
                    case "Vợt":
                        // Xử lý khi "Vợt" được chọn
						
                    	SelectComboBoxSellTable(1);
				    break;
                    case "Giày":
                        // Xử lý khi "Giày" được chọn
                   // 	String giay = "2";
				  

                    	SelectComboBoxSellTable(2);
                    	break;
                    case "Trang phục":
                        // Xử lý khi "Trang phục" được chọn
                      //       	String trangphuc = "3";

                    	SelectComboBoxSellTable(3);          
                    	break;
                    case "Vật phẩm khác":
                        // Xử lý khi "Vật phẩm khác" được chọn
                    	//String khac = "4";

                    	SelectComboBoxSellTable(4);   
                    	break;
                    default:
                        break;
                }
            }
        });
		
		
		ButtonSell.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	           
	            	openpaymentscreenn();
	            }
	        });

		//Phần tab quản lý
		
		
        BackgroundPanel panelManageProduct = new BackgroundPanel(backgroundImage);
		tabbedPane.addTab("MANAGE PRODUCT", null, panelManageProduct, null);
		panelManageProduct.setLayout(null);
		
		JLabel LabelTotalManagementList = new JLabel("LIST OF ITEMS");
		LabelTotalManagementList.setHorizontalAlignment(SwingConstants.CENTER);
		LabelTotalManagementList.setFont(new Font("Tahoma", Font.BOLD, 18));
		LabelTotalManagementList.setBounds(410, 63, 184, 44);
		panelManageProduct.add(LabelTotalManagementList);
				
		JButton ButtonAddManage = new supportview.ButtonGradient();
		ButtonAddManage.setText("ADD TO INVENTORY");
		ButtonAddManage.setHorizontalAlignment(SwingConstants.LEFT);
		ButtonAddManage.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminScreen.class.getResource("Add_icon.png"))));
		ButtonAddManage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ButtonAddManage.setBounds(491, 372, 301, 50);
		ButtonAddManage.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	int iditem = Integer.parseInt(textFieldIDItem.getText());
	                String nameitem= textFieldNameItem.getText();
	                int currentCount = Integer.parseInt(textFieldNewCount.getText());	               																							
	                float costinput =Float.parseFloat( textFieldCostInput.getText());	
	            	int idCATE = Integer.parseInt(textFieldIDCATE.getText());
	                Product item = new Product( iditem,nameitem, costinput,currentCount,idCATE );
	                AdminItemDAO.insert(item);	                        
	               float currentTotal = Float.parseFloat(TextT0talManage.getText());
	       //        float newTotal = currentTotal - costinput*currentCount;
	//               updateTextTongTienManage(newTotal);

	               TableDachSachTongManage();
	               TableDachSachTongSell();
	               clearFields();
	            }
	        });
		panelManageProduct.add(ButtonAddManage);
		
		JLabel LabelTotalManage = new JLabel("CURRENT AMOUNT:");
		LabelTotalManage.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelTotalManage.setBounds(10, 610, 184, 44);
		panelManageProduct.add(LabelTotalManage);
		
		TextT0talManage = new JTextField();
		TextT0talManage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		float savedTotal = Preferences.userNodeForPackage(AdminScreen.class).getFloat(TOTAL_KEY, 1000000000f);
        TextT0talManage.setText(String.valueOf(savedTotal));

		TextT0talManage.setBounds(218, 612, 366, 38);
		panelManageProduct.add(TextT0talManage);
		TextT0talManage.setColumns(10);

		tableModelTotalManage = new DefaultTableModel(new Object[][]{}, new String[]{"ID PRODUCT", "NAME PRODUCT", "PRICE", "QUANTITY", "IDCATEGORY"});
		TableTotalManagementList = new JTable(tableModelTotalManage);
		TableTotalManagementList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		           
		JScrollPane scrollPaneTotalManagementList = new JScrollPane(TableTotalManagementList);
		TableCustom.apply(scrollPaneTotalManagementList,TableCustom.TableType.MULTI_LINE);
		scrollPaneTotalManagementList.setBounds(10, 117, 995, 200);
		panelManageProduct.add(scrollPaneTotalManagementList);		
		TableDachSachTongManage();
		
		JLabel LabelIdItemManage = new JLabel("ID ITEM");
		LabelIdItemManage.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelIdItemManage.setBounds(32, 375, 95, 44);
		panelManageProduct.add(LabelIdItemManage);
		
		JLabel LabelNameItem = new JLabel("ITEM NAME");
		LabelNameItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelNameItem.setBounds(32, 427, 95, 44);
		panelManageProduct.add(LabelNameItem);
		
		JLabel LabelOldCount = new JLabel("OLD COUNT");
		LabelOldCount.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelOldCount.setBounds(32, 481, 116, 44);
		panelManageProduct.add(LabelOldCount);
		
		JLabel LabelCostInput = new JLabel("IMPORT PRICE");
		LabelCostInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelCostInput.setBounds(32, 535, 144, 44);
		panelManageProduct.add(LabelCostInput);
		
		textFieldIDItem = new JTextField(60);
		textFieldIDItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldIDItem.setColumns(10);
		textFieldIDItem.setBounds(172, 378, 70, 38);
		panelManageProduct.add(textFieldIDItem);
		
		textFieldNameItem = new JTextField(60);
		textFieldNameItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldNameItem.setColumns(10);
		textFieldNameItem.setBounds(172, 427, 259, 38);
		panelManageProduct.add(textFieldNameItem);
		
		textFieldOldCount = new JTextField();
		textFieldOldCount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldOldCount.setColumns(10);
		textFieldOldCount.setBounds(172, 484, 70, 38);
		panelManageProduct.add(textFieldOldCount);
		
		textFieldCostInput = new JTextField();
		textFieldCostInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldCostInput.setColumns(10);
		textFieldCostInput.setBounds(172, 534, 259, 38);
		panelManageProduct.add(textFieldCostInput);
		
		JLabel LabelVND = new JLabel("VND");
		LabelVND.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelVND.setBounds(594, 610, 40, 44);
		panelManageProduct.add(LabelVND);
		
		JButton ButtonUpdateManage = new supportview.ButtonGradient();
		ButtonUpdateManage.setText("UPDATE QUANTITY OF ITEMS");
		ButtonUpdateManage.setHorizontalAlignment(SwingConstants.LEFT);
		ButtonUpdateManage.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminScreen.class.getResource("update_icon.png"))));

		ButtonUpdateManage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ButtonUpdateManage.setBounds(491, 442, 301, 50);
		
			ButtonUpdateManage.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	int iditem = Integer.parseInt(textFieldIDItem.getText());
	            	int idcate = Integer.parseInt(textFieldIDCATE.getText());
	                String nameitem= textFieldNameItem.getText();
	                int count =Integer.parseInt( textFieldNewCount.getText());
	                float costinput =Float.parseFloat( textFieldCostInput.getText());
	                float currentTotal = Float.parseFloat(TextT0talManage.getText());
	                int oldcount =Integer.parseInt( textFieldOldCount.getText());	                
	                float total = (count - oldcount)*costinput;	                
	                float newtotal = currentTotal - total;
	//                updateTextTongTienManage(newtotal); 

	                Product itemud = new Product(iditem, nameitem, costinput,count, idcate);
	                AdminItemDAO.update(itemud);  
	                    // Cập nhật bảng ngay tại đây
	                DefaultTableModel model = (DefaultTableModel) TableTotalManagementList.getModel();
	                int selectedRow = TableTotalManagementList.getSelectedRow();
	                if (selectedRow != -1) {
	                    model.setValueAt(iditem, selectedRow, 0);
	                    model.setValueAt(nameitem, selectedRow, 1);
	                    model.setValueAt(count, selectedRow, 3);
	                    model.setValueAt(costinput, selectedRow, 2);
	                }
              
	            }
	        });			
		panelManageProduct.add(ButtonUpdateManage);

		JButton ButtonXoaManage =new supportview.ButtonGradient();
		ButtonXoaManage.setText("DELETE ITEMS");
		ButtonXoaManage.setHorizontalAlignment(SwingConstants.LEFT);
		ButtonXoaManage.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminScreen.class.getResource("Delete_icon.png"))));
		ButtonXoaManage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ButtonXoaManage.setBounds(491, 513, 301, 50);
		panelManageProduct.add(ButtonXoaManage);
		
		JLabel LabelNewCount = new JLabel("NEW COUNT");
		LabelNewCount.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelNewCount.setBounds(252, 481, 104, 44);
		panelManageProduct.add(LabelNewCount);
		
		textFieldNewCount = new JTextField();
		textFieldNewCount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldNewCount.setColumns(10);
		textFieldNewCount.setBounds(359, 484, 72, 38);
		panelManageProduct.add(textFieldNewCount);
		
		JButton btnLogout = new supportview.ButtonGradient();
		btnLogout.setText("LOG OUT");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Confirm = JOptionPane.showConfirmDialog(AdminScreen.this, "Are you sure ?");
                if (Confirm==JOptionPane.YES_OPTION)
                {    
				openLoginScreen();
                }
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogout.setBounds(828, 612, 138, 44);
		panelManageProduct.add(btnLogout);
		
		textFieldFindManageProduct = new JTextField();
		textFieldFindManageProduct.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldFindManageProduct.setBounds(136, 10, 336, 43);
		panelManageProduct.add(textFieldFindManageProduct);
		textFieldFindManageProduct.setColumns(10);
		
		JButton btnFindMangeProduct =new supportview.ButtonGradient();
		btnFindMangeProduct.setText("SEARCH");
		btnFindMangeProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String searchTerm = textFieldFindManageProduct.getText();
				 searchMangeByName(searchTerm);
			}
		});
		btnFindMangeProduct.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFindMangeProduct.setBounds(536, 10, 135, 43);
		panelManageProduct.add(btnFindMangeProduct);
		
		JLabel lblIdCate = new JLabel("ID CATE");
		lblIdCate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIdCate.setBounds(269, 375, 72, 44);
		panelManageProduct.add(lblIdCate);
		
		textFieldIDCATE = new JTextField();
		textFieldIDCATE.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldIDCATE.setColumns(10);
		textFieldIDCATE.setBounds(359, 378, 72, 38);
		panelManageProduct.add(textFieldIDCATE);
		
		final Combobox comboBox = new Combobox<>();
		comboBox.setLabeText("category");
		comboBox.setBounds(822, 12, 144, 43);
		panelManageProduct.add(comboBox);
		comboBox.addItem("");
		comboBox.addItem("Vợt");
		comboBox.addItem("Giày");
		comboBox.addItem("Trang phục");
		comboBox.addItem("Vật phẩm khác");
		Font font = new Font("Arial", Font.PLAIN, 16);
		comboBox.setFont(font);
		comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Xử lý hành động tương ứng với mỗi lựa chọn
                String selectedOption = (String) comboBox.getSelectedItem();
                switch (selectedOption) {
                    case "Vợt":
                        // Xử lý khi "Vợt" được chọn

                    	SelectComboBoxTable(1);
				    break;
                    case "Giày":
                        // Xử lý khi "Giày" được chọn
                   // 	String giay = "2";
                    	SelectComboBoxTable(2);
                    	break;
                    case "Trang phục":
                        // Xử lý khi "Trang phục" được chọn
                      //       	String trangphuc = "3";

                    	SelectComboBoxTable(3);          
                    	break;
                    case "Vật phẩm khác":
                        // Xử lý khi "Vật phẩm khác" được chọn
                    	//String khac = "4";

                    	SelectComboBoxTable(4);   
                    	break;
                    default:
                        break;
                }
            }
        });
		
		TableTotalManagementList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
	        public void valueChanged(ListSelectionEvent e) {
	            // Get the selected row index
	            int selectedRow = TableTotalManagementList.getSelectedRow();
	            // Check if a row is selected
	            if (selectedRow != -1) {
	                // Get data from the selected row
	                Object iditem = TableTotalManagementList.getValueAt(selectedRow, 0);
	                Object nameitem = TableTotalManagementList.getValueAt(selectedRow, 1);
	                Object costinput = TableTotalManagementList.getValueAt(selectedRow, 2);
	                Object  count = TableTotalManagementList.getValueAt(selectedRow, 3);
	                Object  idcate = TableTotalManagementList.getValueAt(selectedRow, 4);

	                // Display data in TextFields
	                textFieldIDItem.setText(iditem.toString());
	                textFieldNameItem.setText(nameitem.toString());
	                textFieldOldCount.setText(count.toString());
	                textFieldCostInput.setText(costinput.toString());
	                textFieldIDCATE.setText(idcate.toString());
	                
	            }
	        }
	    });    
		
		ButtonXoaManage.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	  int row = TableTotalManagementList.getSelectedRow();
	                  if(row ==-1)
	                  {
	                    JOptionPane.showMessageDialog(AdminScreen.this, "Please select row you want to delete!","Loi",JOptionPane.ERROR_MESSAGE);
	                  }
	                  else
	                  {
	                        int Confirm = JOptionPane.showConfirmDialog(AdminScreen.this, "Are you sure ?");
	                        if (Confirm== JOptionPane.YES_OPTION)
	                        {                            
                        int itemid =Integer.valueOf(String.valueOf( TableTotalManagementList.getValueAt(row,0)));
                        int count =Integer.parseInt( textFieldOldCount.getText());
    	                float costinput =Float.parseFloat( textFieldCostInput.getText());
                        AdminItemDAO.deleteTotalTableProduct(itemid);
                        tableModelTotalManage.removeRow(row);   
                        float currentTotal = Float.parseFloat(TextT0talManage.getText());
 		                float newTotal = currentTotal + costinput*count;
 	//	               updateTextTongTienManage(newTotal);
              }
	                  }	
	                  clearFields();
	             }
	            
	         });	
		//Quản lý nhân viên
		
        BackgroundPanel panelManageCashier = new BackgroundPanel(backgroundImage);
		tabbedPane.addTab("MANAGE CASHIER", null, panelManageCashier, null);
		
		tableModelEmployeeManage = new DefaultTableModel(new Object[][]{}, new String[]{"ID Employee", "NAME", "GMAIL", "PASSWORD", "PHONENUMBER", "WORKHOUR"});
		TableEmployeeList = new JTable(tableModelEmployeeManage);
		TableEmployeeList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		TableEmployeeList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
	        public void valueChanged(ListSelectionEvent e) {
			    // Get the selected row index
	            int selectedRow = TableEmployeeList.getSelectedRow();
	            // Check if a row is selected
	            if (selectedRow != -1) {

	            	Object EmployeeID = TableEmployeeList.getValueAt(selectedRow, 0);
	                Object EmployeeName = TableEmployeeList.getValueAt(selectedRow, 1);
	                Object EmployeeGmail = TableEmployeeList.getValueAt(selectedRow, 2);
	                Object EmployeePass = TableEmployeeList.getValueAt(selectedRow, 3);
	                Object EmployeePhoneNumber = TableEmployeeList.getValueAt(selectedRow, 4);
	                Object EmployeeWorkHour = TableEmployeeList.getValueAt(selectedRow, 5);
	                
	                textFieldID.setText(EmployeeID.toString());
	                TextEmployeeName.setText(EmployeeName.toString());
	                TextEmployeePhone.setText(EmployeeGmail.toString());
	                TextEmployeeGmail.setText(EmployeePhoneNumber.toString());
	                TextEmployeeWorkHour.setText(EmployeeWorkHour.toString());

	            }
	        }
	    });  
		panelManageCashier.setLayout(null);
		
		JScrollPane scrollPaneTableEmployeeList = new JScrollPane(TableEmployeeList);
		TableCustom.apply(scrollPaneTableEmployeeList,TableCustom.TableType.MULTI_LINE);
		scrollPaneTableEmployeeList.setBounds(10, 129, 975, 194);
		panelManageCashier.add(scrollPaneTableEmployeeList);
		
		JLabel LabelEmployeeName = new JLabel("NAME");
		LabelEmployeeName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelEmployeeName.setBounds(94, 395, 118, 44);
		panelManageCashier.add(LabelEmployeeName);
		
		JLabel LabelEmployeePhoneNum = new JLabel("PHONENUMBER");
		LabelEmployeePhoneNum.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelEmployeePhoneNum.setBounds(94, 443, 122, 44);
		panelManageCashier.add(LabelEmployeePhoneNum);
		
		JLabel LabelEmployeGmail = new JLabel("GMAIL");
		LabelEmployeGmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelEmployeGmail.setBounds(94, 491, 122, 44);
		panelManageCashier.add(LabelEmployeGmail);
		
		JLabel LabelEmployeeNameWORKHOUR = new JLabel("WORKHOUR");
		LabelEmployeeNameWORKHOUR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelEmployeeNameWORKHOUR.setBounds(90, 539, 122, 44);
		panelManageCashier.add(LabelEmployeeNameWORKHOUR);
		
		TextEmployeeName = new JTextField();
		TextEmployeeName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TextEmployeeName.setColumns(10);
		TextEmployeeName.setBounds(253, 398, 221, 38);
		panelManageCashier.add(TextEmployeeName);
		
		TextEmployeePhone = new JTextField();
		TextEmployeePhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TextEmployeePhone.setColumns(10);
		TextEmployeePhone.setBounds(253, 446, 221, 38);
		panelManageCashier.add(TextEmployeePhone);
		
		TextEmployeeGmail = new JTextField();
		TextEmployeeGmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TextEmployeeGmail.setColumns(10);
		TextEmployeeGmail.setBounds(253, 494, 221, 38);
		panelManageCashier.add(TextEmployeeGmail);
		
		TextEmployeeWorkHour = new JTextField();
		TextEmployeeWorkHour.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TextEmployeeWorkHour.setColumns(10);
		TextEmployeeWorkHour.setBounds(253, 542, 221, 38);
		panelManageCashier.add(TextEmployeeWorkHour);
		
		JButton btnDeleteEmployee = new supportview.ButtonGradient();
		btnDeleteEmployee.setText("DELETE Employee");
		btnDeleteEmployee.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				int row = TableEmployeeList.getSelectedRow();
                if(row ==-1)
                {
                  JOptionPane.showMessageDialog(AdminScreen.this, "Please select row you want to delete!","Loi",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                      int Confirm = JOptionPane.showConfirmDialog(AdminScreen.this, "Are you sure ?");
                      if (Confirm== JOptionPane.YES_OPTION)
                      {                            
                  int Employeeid =Integer.valueOf(String.valueOf( TableEmployeeList.getValueAt(row,0)));
                  AdminItemDAO.deleteEmployee(Employeeid);
                  tableModelEmployeeManage.removeRow(row);   
        }
                  }
			}
		     	});
		
		btnDeleteEmployee.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDeleteEmployee.setBounds(610, 384, 301, 50);
		panelManageCashier.add(btnDeleteEmployee);
		
		JButton btnUpdateInformation = new supportview.ButtonGradient();
		btnUpdateInformation.setText("UPDATE INFORMATION");
		btnUpdateInformation.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				int Employeeid = Integer.parseInt(textFieldID.getText());
                String Employeename= TextEmployeeName.getText();
            	String Employeephone = TextEmployeePhone.getText();
            	String Employeegmail = TextEmployeeGmail.getText();
            	String Employeepass = textFieldPassEmployee.getText();
            	float EmployeeWH = Float.parseFloat(TextEmployeeWorkHour.getText());

            	Employee itemud = new Employee( Employeeid, Employeename, Employeegmail,Employeepass,Employeephone, EmployeeWH);
                AdminItemDAO.updateemployee(itemud);  
                    // Cập nhật bảng ngay tại đây
                DefaultTableModel model = (DefaultTableModel) TableEmployeeList.getModel();
                int selectedRow = TableEmployeeList.getSelectedRow();
                if (selectedRow != -1) {
                    model.setValueAt(Employeeid, selectedRow, 0);
                    model.setValueAt(Employeename, selectedRow, 1);
                    model.setValueAt(Employeepass, selectedRow, 3);
                    model.setValueAt(Employeegmail, selectedRow, 2);
                    model.setValueAt(Employeephone, selectedRow, 4);
                    model.setValueAt(EmployeeWH, selectedRow, 5);

                }
			}
		     	});
		
		btnUpdateInformation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdateInformation.setBounds(610, 466, 301, 50);
		panelManageCashier.add(btnUpdateInformation);
		
		JButton btnSearchEmployee = new supportview.ButtonGradient();
		btnSearchEmployee.setText("SEARCH");
		btnSearchEmployee.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				 String searchTerm = textFieldSearchEmployee.getText();
			        searchByNameEmployee(searchTerm);
			        TableEmployeeList();
			}
		     	});
		btnSearchEmployee.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearchEmployee.setBounds(623, 10, 133, 38);
		panelManageCashier.add(btnSearchEmployee);
		//btn chat
		
		JButton btnChat =new supportview.ButtonGradient();
		btnChat.setText("CHAT");
		btnChat.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				JFrame serverFrame = new JFrame("Server");
				serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				serverFrame.setSize(400, 300);
				serverFrame.getContentPane().add(new MultiClientServer());
				serverFrame.setVisible(true);
				
			}
		     	});
		btnChat.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminScreen.class.getResource("chat-2-icon.png"))));
		btnChat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnChat.setBounds(679, 546, 182, 50);
		panelManageCashier.add(btnChat);
		textFieldSearchEmployee = new JTextField();
		textFieldSearchEmployee.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldSearchEmployee.setColumns(10);
		textFieldSearchEmployee.setBounds(253, 10, 321, 38);
		panelManageCashier.add(textFieldSearchEmployee);
		
		JLabel lblNewLabel_1 = new JLabel("Employee LIST");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(371, 68, 227, 36);
		panelManageCashier.add(lblNewLabel_1);
		
		textFieldID = new JTextField();
		textFieldID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldID.setColumns(10);
		textFieldID.setBounds(253, 347, 221, 38);
		panelManageCashier.add(textFieldID);
		
		JLabel lblId = new JLabel("ID ");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblId.setBounds(94, 347, 118, 44);
		panelManageCashier.add(lblId);
		
		textFieldPassEmployee = new JTextField();
		textFieldPassEmployee.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textFieldPassEmployee.setColumns(10);
		textFieldPassEmployee.setBounds(253, 590, 221, 38);
		panelManageCashier.add(textFieldPassEmployee);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(90, 584, 122, 44);
		panelManageCashier.add(lblPassword);
		TableEmployeeList();	
		ChartPanel chatPanel = new ChartPanel();
		tabbedPane.addTab("REVENUE STATISTICS ", null, chatPanel, null);

		
	}
	
	// HÀM CHỨC NĂNG
	public void TableDachSachTongSell() {
		   tableModel = (DefaultTableModel) TableTotalSalesList.getModel();
		    ArrayList<Product> itemDAO = new AdminItemDAO().selectAll() ;
		    tableModel.getDataVector().removeAllElements();
		    tableModel.setRowCount(0); // Clear existing data
		    for(int i = 0; i < itemDAO.size();i++) {
		    	tableModel.addRow(itemDAO.get(i).toObject());
		    		    	
		    }
		} 
	
	public void TableEmployeeList() {
		tableModelEmployeeManage = (DefaultTableModel) TableEmployeeList.getModel();
		    ArrayList<Employee> itemDAO = new AdminItemDAO().selectAllEmployee() ;
		    tableModelEmployeeManage.getDataVector().removeAllElements();
		    tableModelEmployeeManage.setRowCount(0); // Clear existing data
		    for(int i = 0; i < itemDAO.size();i++) {
		    	tableModelEmployeeManage.addRow(itemDAO.get(i).toObject());
		    		    	
		    }
		} 
	
	public void TableDachSachTongManage() {
		   tableModelTotalManage = (DefaultTableModel) TableTotalManagementList.getModel();
		    ArrayList<Product> itemDAO = new AdminItemDAO().selectAll() ;
		    tableModelTotalManage.getDataVector().removeAllElements();
		    tableModelTotalManage.setRowCount(0); // Clear existing data
		    for(int i = 0; i < itemDAO.size();i++) {
		    	tableModelTotalManage.addRow(itemDAO.get(i).toObject());
		    }
		}
	
	public void SaleTable() {
		
		tableModelSale = (DefaultTableModel) TableSalesList.getModel();
	    ArrayList<ProductCart> items = new AdminItemDAO().selectProductCart();
	    tableModelSale.getDataVector().removeAllElements();
	    tableModelSale.setRowCount(0); // Clear existing data
	    for (int i = 0; i < items.size(); i++) {
	    	tableModelSale.addRow(items.get(i).toObject());
	    }
	}	
	
public void SelectComboBoxTable(int CateID) {
		
	tableModelTotalManage = (DefaultTableModel) TableTotalManagementList.getModel();
	    ArrayList<Product> items = new AdminItemDAO().selectCategory(CateID);
	    tableModelTotalManage.getDataVector().removeAllElements();
	    tableModelTotalManage.setRowCount(0); // Clear existing data
	    for (Product result : items) {
	    	tableModelTotalManage.addRow(result.toObject());
	    }
	}	

public void SelectComboBoxSellTable(int CateID) {
	
	tableModel = (DefaultTableModel) TableTotalSalesList.getModel();
	    ArrayList<Product> items = new AdminItemDAO().selectCategory(CateID);
	    tableModel.getDataVector().removeAllElements();
	    tableModel.setRowCount(0); // Clear existing data
	    for (Product result : items) {
	    	tableModel.addRow(result.toObject());
	    }
	}


	private void searchByName(String searchTerm) {
	    ArrayList<Product> searchResults = AdminItemDAO.searchByName(searchTerm);
	    tableModel.getDataVector().removeAllElements();
	    tableModel.setRowCount(0);

	    for (Product result : searchResults) {
	        tableModel.addRow(result.toObject());
	    }
	}
	
	private void searchMangeByName(String searchTerm) {
	    ArrayList<Product> searchResults = AdminItemDAO.searchByName(searchTerm);
	    tableModelTotalManage.getDataVector().removeAllElements();
	    tableModelTotalManage.setRowCount(0);

	    for (Product result : searchResults) {
	    	tableModelTotalManage.addRow(result.toObject());
	    }
	}
	
	private void searchByNameEmployee(String searchTerm) {
	    ArrayList<Employee> searchResults = AdminItemDAO.searchByNameEmployee(searchTerm);
	    tableModelEmployeeManage.getDataVector().removeAllElements();
	    tableModelEmployeeManage.setRowCount(0);

	    for (Employee result : searchResults) {
	    	tableModelEmployeeManage.addRow(result.toObject());
	    }
	}
	
	public void refreshTable() {
	    TableDachSachTongSell();		    
	    TableDachSachTongManage();
	}
	
	
	public void openpaymentscreenn() {
		 
		Paymentscreen paymentscreen = new Paymentscreen();    
		paymentscreen.setVisible(true);
		paymentscreen.setLocationRelativeTo(null);
    }
		
	public void updateTextTongTienManage(float newTotal) {
	    // Format the float value with two decimal places
	    String formattedTotal = String.format("%.2f", newTotal);
	    TextT0talManage.setText(formattedTotal);
	    Preferences.userNodeForPackage(AdminScreen.class).putFloat(TOTAL_KEY, newTotal);
	}
		
	public float getTotalCost() {
        return totalCost;
    } 
	
	public void refreshbutton() {
		
		AdminScreen NewScreen = new AdminScreen();		   
	    NewScreen.setVisible(true);
		  NewScreen.setLocationRelativeTo(null); 
	dispose();
	}
	
	public void openLoginScreen() {
		 
		LoginScreen loginNewScreen = new LoginScreen();    
		loginNewScreen.setVisible(true);
        dispose();
    }
	
	public void clearFields() {
		textFieldIDItemSell.setText("");
        textFieldNameItemSell.setText("");
        textFieldCateID.setText("");
        textFieldCostSell.setText("");
        textFieldCountSellWarehouse.setText("");
        textFieldCountNeedBuy.setText("");
    }

	public void refreshTable1() {
        // Xóa tất cả các hàng hiện có trong bảng
        tableModel.setRowCount(0);
		tableModelEmployeeManage.setRowCount(0);
        // Lấy danh sách sản phẩm từ cơ sở dữ liệu
        ArrayList<Product> productItems = AdminItemDAO.selectAll();

        // Thêm các hàng mới vào mô hình bảng
        for (Product product : productItems) {
            Object[] row = new Object[5];
            row[0] = product.getProductID();
            row[1] = product.getName();
            row[2] = product.getPrice();
            row[3] = product.getStockQuantity();
            row[4] = product.getCategoryId();
            tableModel.addRow(row);
        }
	
		
	 }
    

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminScreen frame = new AdminScreen();
					  frame.setLocationRelativeTo(null); 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
