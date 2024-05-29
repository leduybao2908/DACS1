package dao;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.query.Query;
import model.*;
import util.HibernateUtil;

public class CashierItemDAO {

    public static CashierItemDAO getInstanitemDAO() {
        return new CashierItemDAO();
    }

    public int inserttablesell(ProductCart t) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot insert item, please check information.");
        }
        return 0;
    }

    public ArrayList<Product> selectAll() {
        ArrayList<Product> result = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery("FROM Product", Product.class);
            result.addAll(query.list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Product> selectCategory(int CateID) {
        ArrayList<Product> result = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery("FROM Product WHERE categoryId = :categoryId", Product.class);
            query.setParameter("categoryId", CateID);
            result.addAll(query.list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getStockQuantityByProductID(int productID) {
        int stockQuantity = 0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Integer> query = session.createQuery("SELECT StockQuantity FROM Product WHERE ProductID = :productId", Integer.class);
            query.setParameter("productId", productID);
            stockQuantity = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stockQuantity;
    }

    public ArrayList<ProductCart> selectProductCart() {
        ArrayList<ProductCart> result = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<ProductCart> query = session.createQuery("FROM ProductCart", ProductCart.class);
            result.addAll(query.list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deletesalecart(int ProductIDcart) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM ProductCart WHERE ProductIDcart = :productID");
            query.setParameter("productID", ProductIDcart);
            int rowsAffected = query.executeUpdate();
            session.getTransaction().commit();
            return rowsAffected;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<Product> searchByName(String itemName) {
        ArrayList<Product> result = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Product> query = session.createQuery("FROM Product WHERE Name LIKE :itemName", Product.class);
            query.setParameter("itemName", "%" + itemName + "%");
            result.addAll(query.list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Product t) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(t);
            session.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot update item, please check information.");
        }
        return 0;
    }

    public int updateWorkHours(String userName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE Employee SET WorkHours = WorkHours + 1 WHERE Name = :userName");
            query.setParameter("userName", userName);
            int rowsAffected = query.executeUpdate();
            session.getTransaction().commit();
            return rowsAffected;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}