package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            SessionFactory factory =  Util.getSessionFactory();
            Session session = factory.openSession();
            Transaction trans = session.getTransaction();
            NativeQuery query = session.createNativeQuery ("CREATE TABLE `users` ("
                    + "`id` BIGINT NOT NULL AUTO_INCREMENT ,"
                    + "`name` VARCHAR(200) NULL,"
                    + "`lastName` VARCHAR(200) NULL,"
                    + "`age` TINYINT NULL,"
                    + "PRIMARY KEY (`id`))" );
            trans.begin();
            query.executeUpdate();
            trans.commit();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            SessionFactory factory = Util.getSessionFactory();
            Session session = factory.openSession();
            Transaction trans = session.getTransaction();
            trans.begin();
            NativeQuery query = session.createNativeQuery("DROP TABLE `users`");
            query.executeUpdate();
            trans.commit();
            session.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
          User user= new User(name, lastName, age);
          Session session = Util.getSessionFactory().openSession();
          session.save(user);
          session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        NativeQuery query = session.createNativeQuery("delete from users where id=:id");
        query.setParameter("id", id);
        Transaction transaction = session.beginTransaction();
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        Query<User> query = session.createNamedQuery("get_all_users", User.class);
        List<User> list = query.getResultList();
        session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Query query = session.createNativeQuery("delete from users");
        Transaction transaction = session.beginTransaction();
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
