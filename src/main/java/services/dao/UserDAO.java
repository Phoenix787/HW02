package services.dao;

import org.h2.engine.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import services.datasets.UserDataSet;
import services.executor.Executor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.sql.SQLException;

public class UserDAO {
    //private Executor executor;
    private Session session;
  //  private EntityManager em;

    public UserDAO(Session session) {
        this.session = session;
       // EntityManagerFactory emf = session.getEntityManagerFactory();
       // em = emf.createEntityManager();

    }

    public long insertUser(String name, String password) {
      return (Long)session.save(new UserDataSet(name, password));
    }

    public UserDataSet getUser(long id) {
        return (UserDataSet) session.get(UserDataSet.class, id);
    }

    public long getUserId(String name) {
        Criteria criteria = session.createCriteria(UserDataSet.class);
        return ((UserDataSet)criteria.add(Restrictions.eq("name", name)).uniqueResult()).getId();

//        em.getTransaction().begin();
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<UserDataSet> criteriaQuery = criteriaBuilder.createQuery(UserDataSet.class);
//        Root<UserDataSet> userRoot = criteriaQuery.from(UserDataSet.class);
//        criteriaQuery.select(userRoot).where(criteriaBuilder.equal(userRoot.get("name"), name));
//        return em.createQuery(criteriaQuery).getSingleResult().getId();
    }

    public UserDataSet getUserByLogin(String login) {
        Criteria criteria = session.createCriteria(UserDataSet.class);
        return (UserDataSet) criteria.add(Restrictions.eq("name", login)).uniqueResult();
    }

   // public void createTable() throws SQLException {

    //}

 //   public void dropTable() throws SQLException {

   // }



    //--------------------------------------------------------------------------------------
    /*
    *  public UserDAO(Connection connection) {
        executor = new Executor(connection);
    }

    public void insertUser(String name, String password) throws SQLException {
        executor.execUpdate("insert into users (name, password) values ('" + name + "', '" + password + "'");
    }

    public UserDataSet getUser(long id) throws SQLException {
        return executor.execQuery("select * from users where id = " + id, resultSet -> {
            resultSet.next();
            return new UserDataSet(resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3));
        });
    }

    public long getUserId(String name) throws SQLException {
        return executor.execQuery("select id from users where name = '" + name + "'", resultSet -> {
            resultSet.next();
            return resultSet.getLong(1);
        });
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                "name VARCHAR(255), password VARCHAR(255))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table users");
    }
    *
    * */
}
