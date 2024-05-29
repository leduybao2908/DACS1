package view;
import java.awt.Color; 
import java.awt.EventQueue;  
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
//import database.JDBCUtil;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import dao.CashierItemDAO;
import model.Product;
import model.ProductCart;
import supportview.BackgroundPanel;
import supportview.ButtonGradient;
import supportview.ClientFrame;
import supportview.Combobox;
import supportview.RoundJTextField;
import supportview.TableCustom;

import javax.swing.JTabbedPane; 
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.Timer;
import javax.swing.JRadioButton;

public class CashierScreen extends JFrame {
	public static String userName;
	public static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTextField textField;
	public JTextField TextTotalCart;
	public JTable TableTotalSalesList;
	public JTable TableSalesList;
	public JTextField textFieldNameItem;
	public DefaultTableModel tableModel;
	public DefaultTableModel tableModel1;
	public CashierItemDAO ItemDAO = new CashierItemDAO() ; 
	public JTextField textFieldCostSell;
	public JTextField textFieldCountNeedBuy;
	public DefaultTableModel tableModelSale;
	public int currentCount;
    public static final String TOTAL_KEY = "totalKey";
	public float totalCost = 0.0f;
	public JTextField textFieldCountSellWarehouse;
	public JTextField textFieldNewCount;
	public JTextField textFieldFind;
	public JTextField textFieldNameItemSell;
	public JTextField textFieldIDItemSell;
	public JTextField textFieldCateID;
	private JLabel timerLabel;

	public CashierScreen(final String userName) {
	        // ...
	    //    ChangeScreen newScreenInstance = new ChangeScreen(this); // Pass 'this' as the argument
	        // ...
					
	        setTitle("Badminton Store");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		URL url = CashierScreen.class.getResource("52745-badminton-icon.png");
		if (url != null) {
		    Image imagethemgiohang = Toolkit.getDefaultToolkit().createImage(url);
		    this.setIconImage(imagethemgiohang); 
		} 

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 16));
		tabbedPane.setBounds(40, 0, 1000, 700);
		contentPane.add(tabbedPane);
		
		Image backgroundImage = Toolkit.getDefaultToolkit().createImage(CashierScreen.class.getResource("bg.jpg"));
        BackgroundPanel panelSale = new BackgroundPanel(backgroundImage);

		tabbedPane.addTab("SELL", null, panelSale, null);
		panelSale.setLayout(null);
		
		// TẠO BẢNG VÀ THAO TÁC VỚI BẢNG
		
		tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"ProductID", "Name", "Price", "StockQuantity", "CategoryId"});
		TableTotalSalesList = new JTable(tableModel);
		TableTotalSalesList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		JScrollPane scrollPaneTableTotalSalesList = new JScrollPane(TableTotalSalesList);
		TableCustom.apply(scrollPaneTableTotalSalesList,TableCustom.TableType.MULTI_LINE);
		scrollPaneTableTotalSalesList.setBounds(10, 129, 975, 194);
		panelSale.add(scrollPaneTableTotalSalesList);
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
				
		tableModelSale = new DefaultTableModel(new Object[][]{}, new String[]{"ID PRODUCT", "NAME PRODUCT", "PRICE", "QUANTITY", "IDCATEGORY"});
		TableSalesList = new JTable(tableModelSale);
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
			            Object countofTableTotalSalesList = CashierItemDAO.getStockQuantityByProductID(idproductselect);
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
		scrollPaneTableSalesList.setBounds(10, 502, 975, 76);
		scrollPaneTableSalesList.setToolTipText("");
		panelSale.add(scrollPaneTableSalesList);
		TableDachSachTongSell();
		
		// TẠO LABEL VÀ TEXTFIELD

		JLabel LabelTotalSalesList = new JLabel("LIST OF ITEMS");
		LabelTotalSalesList.setBounds(405, 79, 205, 27);
		LabelTotalSalesList.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelSale.add(LabelTotalSalesList);
				
		TextTotalCart = new RoundJTextField(60);
		TextTotalCart.setBounds(224, 623, 237, 40);
		TextTotalCart.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelSale.add(TextTotalCart);
		TextTotalCart.setColumns(10);		

		JLabel LabelTongTienSell = new JLabel("TOTAL MONEY");
		LabelTongTienSell.setBounds(291, 586, 126, 27);
		LabelTongTienSell.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelSale.add(LabelTongTienSell);		
	
		textFieldCostSell = new RoundJTextField(60);
		textFieldCostSell.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldCostSell.setColumns(10);
		textFieldCostSell.setBounds(698, 339, 221, 38);
		panelSale.add(textFieldCostSell);
		
		textFieldCountNeedBuy = new RoundJTextField(60);
		textFieldCountNeedBuy.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldCountNeedBuy.setColumns(10);
		textFieldCountNeedBuy.setBounds(843, 393, 76, 38);
		panelSale.add(textFieldCountNeedBuy);
		
		JLabel LabelIdItemSell = new JLabel("ID ITEM");
		LabelIdItemSell.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelIdItemSell.setBounds(52, 333, 95, 44);
		panelSale.add(LabelIdItemSell);
		
		JLabel LabelNameItemSell = new JLabel("ITEM NAME");
		LabelNameItemSell.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelNameItemSell.setBounds(52, 387, 135, 44);
		panelSale.add(LabelNameItemSell);
		
		JLabel lblCostSell = new JLabel("ITEM PRICE");
		lblCostSell.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCostSell.setBounds(555, 333, 116, 44);
		panelSale.add(lblCostSell);
		
		JLabel LabelCountSell = new JLabel("QUANTITY");
		LabelCountSell.setFont(new Font("Tahoma", Font.BOLD, 16));
		LabelCountSell.setBounds(555, 387, 95, 44);
		panelSale.add(LabelCountSell);
		
		JLabel lblIdCate = new JLabel("ID CATE");
		lblIdCate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIdCate.setBounds(301, 330, 95, 44);
		panelSale.add(lblIdCate);
		textFieldFind =new RoundJTextField(60);
		textFieldFind.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldFind.setBounds(118, 29, 346, 40);
		panelSale.add(textFieldFind);
		textFieldFind.setColumns(10);
				
		textFieldCountSellWarehouse = new RoundJTextField(60);
		textFieldCountSellWarehouse.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldCountSellWarehouse.setBounds(698, 393, 126, 38);
		panelSale.add(textFieldCountSellWarehouse);
		textFieldCountSellWarehouse.setColumns(10);
					
		textFieldNameItemSell = new RoundJTextField(60);
		textFieldNameItemSell.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldNameItemSell.setColumns(10);
		textFieldNameItemSell.setBounds(197, 393, 271, 38);
		panelSale.add(textFieldNameItemSell);
		
		textFieldIDItemSell = new RoundJTextField(60);
		textFieldIDItemSell.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldIDItemSell.setColumns(10);
		textFieldIDItemSell.setBounds(197, 333, 76, 38);
		panelSale.add(textFieldIDItemSell);
		
		textFieldCateID = new RoundJTextField(60);
		textFieldCateID.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldCateID.setColumns(10);
		textFieldCateID.setBounds(392, 333, 76, 38);
		panelSale.add(textFieldCateID);
		
		// TẠO NÚT VÀ THAO TÁC VỚI NÚT
		
		JButton ButtonAddToCart = new supportview.ButtonGradient();
		ButtonAddToCart.setText("ADD TO CART");
		ButtonAddToCart.setBounds(222, 452, 221, 40);
		ButtonAddToCart.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(CashierScreen.class.getResource("Cart_icon.png"))));
		ButtonAddToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                CashierItemDAO.inserttablesell(item);
                Product itemud = new Product(iditemsell, nameitemsell, costinputsell, countsellnew, idcategory);
                CashierItemDAO.update(itemud);  
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
               TableMua();
            }
        });
		ButtonAddToCart.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelSale.add(ButtonAddToCart);
		
		JButton btnRefresh =  new supportview.ButtonGradient();
		btnRefresh.setText("REFRESH");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshbutton();
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRefresh.setBounds(739, 623, 126, 40);
		panelSale.add(btnRefresh);
		
		ButtonGradient btnDeleteItemCart = new supportview.ButtonGradient();
		btnDeleteItemCart.setText("REMOVE FROM CART");
		
		btnDeleteItemCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int iditemsell = Integer.parseInt(textFieldIDItemSell.getText());
                int idcategory = Integer.parseInt(textFieldCateID.getText());
                String nameitemsell = textFieldNameItemSell.getText(); 
                int countsell = Integer.parseInt(textFieldCountNeedBuy.getText());
                int countofTableTotalSalesList = Integer.parseInt(textFieldCountSellWarehouse.getText());
                float costinputsell =Float.parseFloat( textFieldCostSell.getText());
                int countsellnew = countsell + countofTableTotalSalesList;
                
            	  int row = TableSalesList.getSelectedRow();
                  if(row ==-1)
                  {
                    JOptionPane.showMessageDialog(CashierScreen.this, "Please select row you want to delete!","Error",JOptionPane.ERROR_MESSAGE);
                  }
                  else
                  {
                        int Confirm = JOptionPane.showConfirmDialog(CashierScreen.this, "Are you sure ?");
                        if (Confirm==JOptionPane.YES_OPTION)
                        {    
                        	Product itemud = new Product(iditemsell, nameitemsell, costinputsell, countsellnew, idcategory);
                        	CashierItemDAO.update(itemud); 
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
                    CashierItemDAO.deletesalecart(itemid); 
                    tableModelSale.removeRow(row);                     
          }
                  }	                 
                 clearFields();
             }
         });
		
		btnDeleteItemCart.setIcon(new ImageIcon(CashierScreen.class.getResource("/view/Delete_icon.png")));
		btnDeleteItemCart.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDeleteItemCart.setBounds(612, 452, 267, 40);
		panelSale.add(btnDeleteItemCart);
		
		ButtonGradient btnFind = new supportview.ButtonGradient();
		btnFind.setText("SEARCH");
		btnFind.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String searchTerm = textFieldFind.getText();
		        searchByName(searchTerm);
		    }
		});

		btnFind.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnFind.setBounds(536, 26, 135, 43);
		panelSale.add(btnFind);
		
		ButtonGradient ButtonBuy = new supportview.ButtonGradient();
		ButtonBuy.setText("BUY");

		ButtonBuy.setHorizontalAlignment(SwingConstants.LEFT);
		ButtonBuy.setBounds(524, 624, 126, 38);
		ButtonBuy.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(CashierScreen.class.getResource("cash-icon.png"))));
		ButtonBuy.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelSale.add(ButtonBuy);
		
		ButtonBuy.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	             
	            	openpaymentscreenn();
	            }
	        });
		
		final Combobox comboBoxSell = new Combobox<>();
		comboBoxSell.setLabeText("Category");
		comboBoxSell.setFont(new Font("Arial", Font.PLAIN, 16));
		comboBoxSell.setBounds(739, 24, 144, 43);


comboBoxSell.addItem("");
comboBoxSell.addItem("Vợt");
comboBoxSell.addItem("Giày");
comboBoxSell.addItem("Trang phục");
comboBoxSell.addItem("Vật phẩm khác");

// Set font
Font fontSell = new Font("Arial", Font.PLAIN, 16);
comboBoxSell.setFont(fontSell);

// Set background color
comboBoxSell.setBackground(Color.WHITE);

// Set foreground (text) color
comboBoxSell.setForeground(Color.BLACK);



// Set preferred size
comboBoxSell.setPreferredSize(new Dimension(144, 43));
panelSale.add(comboBoxSell);
		comboBoxSell.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Xử lý hành động tương ứng với mỗi lựa chọn
                String selectedOption = (String) comboBoxSell.getSelectedItem();
                switch (selectedOption) {
                    case "Vợt":

                    	SelectComboBoxSellTable(1);
				    break;
                    case "Giày":
                       
                    	SelectComboBoxSellTable(2);
                    	break;
                    case "Trang phục":
                        
                    	SelectComboBoxSellTable(3);          
                    	break;
                    case "Vật phẩm khác":

                    	SelectComboBoxSellTable(4);   
                    	break;
                    default:
                        break;
                }
            }
        });

		ButtonGradient btnChat = new supportview.ButtonGradient();	
			btnChat.setText("Chat");
		btnChat.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				JFrame clientFrame1 = new JFrame("Client");
				clientFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				clientFrame1.setSize(400, 300);
				clientFrame1.getContentPane().add(new ClientFrame(false,userName)); // Đánh dấu client là false
				clientFrame1.setVisible(true);
				
			}

		     	});
			
		btnChat.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminScreen.class.getResource("chat-2-icon.png"))));
		btnChat.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnChat.setBounds(20, 613, 126, 40);
		btnChat.setBorder(new EmptyBorder(1,1,1,1));
		btnChat.setContentAreaFilled(false);
		
		tabbedPane.addTab("Chat", btnChat);
		panelSale.add(btnChat);

		//Timer

		timerLabel = new JLabel("Work Time: 00:00:00");
		timerLabel.setForeground(Color.RED);
timerLabel.setBounds(50, 70, 200, 20); // Điều chỉnh vị trí và kích thước label
panelSale.add(timerLabel); // Thêm label vào giao diện

Timer timer = new Timer(1000, new ActionListener() {
    int seconds =55;
    int minutes = 59;
    int hours = 0;
	int nextHours=1;
	boolean oneHourPassed = false;
    @Override
    public void actionPerformed(ActionEvent e) {
        seconds++;
        if (seconds >= 60) {
            seconds = 0;
            minutes++;
			
            if (minutes >= 60) {
                minutes = 0;
                hours++;
				if (hours == nextHours) {
				oneHourPassed = true;
				nextHours++;
			}
               
            }
        }
        String timeStr = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timerLabel.setText("Work Time: "+timeStr);

        // Kiểm tra đã qua một giờ chưa và thực hiện hành động nếu đã qua
        if (oneHourPassed) {
             // Thực hiện hành động khi đã qua một giờ
    System.out.println("Đã qua một giờ!");
   int itemDAO = new CashierItemDAO().updateWorkHours(userName);
   

    // Đặt lại biến oneHourPassed nếu cần
    oneHourPassed = false;
        }
    }
});

timer.start(); // Bắt đầu đếm thời gian khi khởi động ứng dụng

	}
	
	// HÀM CHỨC NĂNG

	
	
	public void TableDachSachTongSell() {
		   tableModel = (DefaultTableModel) TableTotalSalesList.getModel();
		    ArrayList<Product> AdminItemDAO = new CashierItemDAO().selectAll() ;
		    tableModel.getDataVector().removeAllElements();
		    tableModel.setRowCount(0); // Clear existing data
		    for(int i = 0; i < AdminItemDAO.size();i++) {
		    	tableModel.addRow(AdminItemDAO.get(i).toObject());
		    		    	
		    }
		} 
	
	public void TableMua() {
		tableModelSale = (DefaultTableModel) TableSalesList.getModel();

	    ArrayList<ProductCart> items = new CashierItemDAO().selectProductCart();
	    tableModelSale.getDataVector().removeAllElements();
	    tableModelSale.setRowCount(0); // Clear existing data
	    for (int i = 0; i < items.size(); i++) {
	    	tableModelSale.addRow(items.get(i).toObject());
	    }
	}
	
	private void searchByName(String searchTerm) {
	    ArrayList<Product> searchResults = CashierItemDAO.searchByName(searchTerm);
	    tableModel.getDataVector().removeAllElements();
	    tableModel.setRowCount(0);

	    for (Product result : searchResults) {
	        tableModel.addRow(result.toObject());
	    }
	}
	
	public void openpaymentscreenn() {		 
		Paymentscreen paymentscreen = new Paymentscreen();    
		paymentscreen.setVisible(true);
		paymentscreen.setLocationRelativeTo(null);
    }
	
	public void SelectComboBoxSellTable(int CateID) {
		
		tableModel = (DefaultTableModel) TableTotalSalesList.getModel();
		    ArrayList<Product> items = new CashierItemDAO().selectCategory(CateID);
		    tableModel.getDataVector().removeAllElements();
		    tableModel.setRowCount(0); // Clear existing data
		    for (Product result : items) {
		    	tableModel.addRow(result.toObject());
		    }
		}
		
	public void refreshTable() {
	    TableDachSachTongSell();	
	    TableMua();
	}
	
	public void clearFields() {
        textFieldIDItemSell.setText("");
        textFieldNameItemSell.setText("");
        textFieldCateID.setText("");
        textFieldCostSell.setText("");
        textFieldCountSellWarehouse.setText("");
        textFieldCountNeedBuy.setText("");

    }
	
	public void refreshbutton() {

		clearFields();
		refreshTable();
	
	}
	
	public void openLoginScreen() {
		 
		LoginScreen loginNewScreen = new LoginScreen();    
		loginNewScreen.setVisible(true);
        dispose();
    }
	
	// HÀM MAIN

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final String userName1= userName;
					CashierScreen frame = new CashierScreen(userName1);
					  frame.setLocationRelativeTo(null); 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
