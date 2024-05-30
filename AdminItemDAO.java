package dao;

import model.Product;
import model.ProductCart;
import model.Employee;
import model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class AdminItemDAO {

    // Phương thức để lấy danh sách sản phẩm từ cơ sở dữ liệu
    public ArrayList<Product> selectAll() {
        ArrayList<Product> result = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery("from Product", Product.class);
            result = (ArrayList<Product>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    
 // Phương thức lấy tất cả nhân viên từ cơ sở dữ liệu
    public ArrayList<Employee> selectAllEmployee() {
        ArrayList<Employee> result = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
            List<Employee> employeeList = query.list();
            result.addAll(employeeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<ProductCart> selectProductCart() {
        ArrayList<ProductCart> result = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<ProductCart> query = session.createQuery("from ProductCart", ProductCart.class);
            result = (ArrayList<ProductCart>) query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<Employee> searchByNameEmployee(String employeeName) {
        ArrayList<Employee> result = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Employee> query = session.createQuery("from Employee where name like :employeeName", Employee.class);
            query.setParameter("employeeName", "%" + employeeName + "%");
            result = (ArrayList<Employee>) query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<Product> selectCategory(int CateID) {
        ArrayList<Product> result = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery("from Product where categoryId = :categoryId", Product.class);
            query.setParameter("categoryId", CateID);
            result = (ArrayList<Product>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Product> searchByName(String itemName) {
        ArrayList<Product> result = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery("from Product where name like :itemName", Product.class);
            query.setParameter("itemName", "%" + itemName + "%");
            result = (ArrayList<Product>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int insert(Product t) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            return 1;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot insert item, please check information.");
        }
        return 0;
    }

    public int inserttablesell(ProductCart t) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            return 1;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot insert item, please check information.");
        }
        return 0;
    }

    public int deleteTableSell() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM ProductCart");
            int result = query.executeUpdate();
            transaction.commit();
            System.out.println(result);
            return result;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return 0;
    }

    public int deletesalecart(int ProductIDcart) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            ProductCart productCart = session.get(ProductCart.class, ProductIDcart);
            if (productCart != null) {
                session.delete(productCart);
                transaction.commit();
                return 1;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteTotalTableProduct(int iditem) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, iditem);
            if (product != null) {
                session.delete(product);
                transaction.commit();
                return 1;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteEmployee(int idemployee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, idemployee);
            if (employee != null) {
                session.delete(employee);
                transaction.commit();
                return 1;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return 0;
    }

    public int update(Product t) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            return 1;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot update item, please check information.");
        }
        return 0;
    }

    public int updateemployee(Employee t) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            return 1;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot update item, please check information.");
        }
        return 0;
    }

    public static int insertloginCashier(Employee t) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            return 1;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot insert user, please check information.");
        }
        return 0;
    }

    public static int insertloginAdmin(Admin admin) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(admin);
            transaction.commit();
            return 1;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot insert user, please check information.");
        }
        return 0;
    }

    public static boolean checkLogin(String username, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Employee> query = session.createQuery("FROM Employee WHERE name = :username AND password = :password", Employee.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.uniqueResult() != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean checkLoginAdmin(String username, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Admin> query = session.createQuery("FROM Admin WHERE name = :username AND password = :password", Admin.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.uniqueResult() != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean checkEmailExistence(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Sử dụng HQL để kiểm tra sự tồn tại của email trong bảng Employee
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Employee WHERE gmail = :email", Long.class);
            query.setParameter("email", email);
            Long count = query.uniqueResult();
            
            // Nếu số lượng lớn hơn 0, email tồn tại
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

 

    public int getStockQuantityByProductID(int productID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Product product = session.get(Product.class, productID);
            if (product != null) {
                return product.getStockQuantity();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getEmployeeCount() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Employee", Long.class);
            return query.uniqueResult().intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getAdminCount() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Admin", Long.class);
            return query.uniqueResult().intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int updatePasswordByEmail(String email, String newPassword) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Fetch the employee with the given email
            Query<Employee> query = session.createQuery("from Employee where gmail = :email", Employee.class);
            query.setParameter("email", email);
            Employee employee = query.uniqueResult();

            if (employee != null) {
            	System.out.println("success");
                // Update the password
                employee.setPassword(newPassword);
                session.update(employee);
                transaction.commit();
                return 1; // Success
            } else {
                return 0; // Employee not found
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return -1; // Error
    }
}