package util;

import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static Connection connection;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            creatDatabase();
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/maktab_58_db?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "george1378");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                System.out.println("Hibernate Java Config serviceRegistry created");
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                return sessionFactory;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void creatDatabase(){
        String dataBaseName = "maktab_58_db";
        String dataBaseUser = "user";
        String dataBasePassword = "1234";
        boolean dropAllTableOnApplicationStartup = true;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "george1378");
            Statement statement = con.createStatement();
            statement.executeUpdate(String.format("create user if not exists '%s' identified by '%s'", dataBaseUser, dataBasePassword));
            statement.executeUpdate("create database if not exists " + dataBaseName);
            statement.executeUpdate(String.format("grant all privileges on `%s`.* to '%s'", dataBaseName, dataBaseUser));
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBaseName, dataBaseUser, dataBasePassword);
        }catch (ClassNotFoundException | SQLException | RuntimeException e){
            System.out.println("\033[0;31m"+"Exception: cannot connect to dataBase!"+"\033[0m");
            System.out.println("\033[0;33m"+"hint: check your dataBase password!"+"\033[0m");
        }
    }
}
