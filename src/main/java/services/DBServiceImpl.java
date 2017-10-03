package services;

import entities.Contract;
import entities.Document;
import services.datasets.Sessions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import services.dao.SessionsDAO;
import services.dao.UserDAO;
import services.datasets.UserDataSet;
import services.interfaces.DBService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBServiceImpl implements DBService {
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "update";

    private final SessionFactory sessionFactory;
    private Configuration configuration;

   // private Connection connection;

    public DBServiceImpl() {
        try {
            configuration = getH2Connection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        sessionFactory = createSessionFactory(configuration);
    }

    @Override
    public long addUser(String name, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserDAO dao = new UserDAO(session);
        long id = dao.insertUser(name, password);
        transaction.commit();
        session.close();
        return id;
    }

    @Override
    public long addUser(UserDataSet user) {
        return addUser(user.getName(), user.getPassword());
    }

    @Override
    public void addSessionId(String sessionId, UserDataSet user) {
        Session session = sessionFactory.openSession();
        SessionsDAO sessionDAO = new SessionsDAO(session);
        sessionDAO.insertSession(sessionId, user.getId());
        session.close();
    }

    @Override
    public long getId(String name) {
        Session session = sessionFactory.openSession();
        UserDAO dao = new UserDAO(session);
        long id = dao.getUserId(name);
        session.close();
        return id;
    }

    @Override
    public UserDataSet getUserById(long id) {
        Session session = sessionFactory.openSession();
        UserDAO dao = new UserDAO(session);
        UserDataSet user = dao.getUser(id);
        session.close();
        return user;
    }

    @Override
    public UserDataSet getUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        UserDAO dao = new UserDAO(session);
        UserDataSet user = dao.getUserByLogin(login);
        session.close();
        return user;
    }

    @Override
    public UserDataSet getUserBySession(String sessionId) {
        UserDataSet user = null;
        Session session = sessionFactory.openSession();
        SessionsDAO sessionDAO = new SessionsDAO(session);
        long idUser = sessionDAO.getUserId(sessionId);
        if(idUser != 0) {
            UserDAO userDAO = new UserDAO(session);
            user = userDAO.getUser(idUser);
        }
            session.close();
        return user;
    }

    @Override
    public void deleteSession(String sessionId) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        SessionsDAO dao = new SessionsDAO(session);
        dao.delete(sessionId);
        trx.commit();
        session.close();
    }

    //-----------------------------------------------------------------------------------------------------------------

    public void printConnectionInfo() {

            System.out.println("DB name: " + configuration.getProperty("hibernate.dialect"));
            System.out.println("DB driver: " + configuration.getProperty("hibernate.connection.driver_class"));

    }

    private Configuration getH2Connection() throws ClassNotFoundException {
        FileInputStream fis;
        Properties properties = new Properties();
        try {
            fis = new FileInputStream("./src/main/resources/config.properties");
            properties.load(fis);

        } catch (IOException e) {
            System.err.println("ОШИБКА! Файл свойств отсутствует!");
        }
        String login = properties.getProperty("db.login");
        String host = properties.getProperty("db.host.h2");
        String pass = properties.getProperty("db.password");

       // try {
           // Class.forName("org.h2.Driver");
           // return DriverManager.getConnection(host, login, pass);
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(Sessions.class);
        configuration.addAnnotatedClass(Contract.class);
        configuration.addAnnotatedClass(Document.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", host);
        configuration.setProperty("hibernate.connection.username", login);
        configuration.setProperty("hibernate.connection.password", pass);
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;

     //   } catch (ClassNotFoundException | SQLException e1) {
       //     throw new ClassNotFoundException();
     //   }

    }

    private SessionFactory createSessionFactory(Configuration configuration) {

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry registry = builder.build();
        return configuration.buildSessionFactory(registry);
    }


}

