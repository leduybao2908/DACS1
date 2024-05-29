package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Tạo session factory từ file cấu hình hibernate.cfg.xml (nếu có)
            // hoặc cấu hình thông qua code
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/badminton_shop");
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "29082005");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

            // Thêm entity class vào configuration
            configuration.addAnnotatedClass(model.Product.class);
            configuration.addAnnotatedClass(model.BillItem.class);
            configuration.addAnnotatedClass(model.Bill.class);
            configuration.addAnnotatedClass(model.ProductCart.class);
            configuration.addAnnotatedClass(model.Employee.class);
            configuration.addAnnotatedClass(model.Admin.class);



            // Tạo session factory
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
