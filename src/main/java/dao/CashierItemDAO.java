package dao;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import model.*;
import util.HibernateUtil;

public class CashierItemDAO {

    public static int inserttablesell(ProductCart t) {
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

    public static int getStockQuantityByProductID(int productID) {
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

    public static int deletesalecart(int ProductIDcart) {
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

    public static ArrayList<Product> searchByName(String itemName) {
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

    public static int update(Product t) {
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
