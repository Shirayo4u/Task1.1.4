package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final SessionFactory SESSION_FACTORY = Util.getSessionFactory();
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS usr( " +
            "id INTEGER GENERATED ALWAYS AS IDENTITY, " +
            "PRIMARY KEY (id)," +
            "name VARCHAR NOT NULL," +
            "lastName VARCHAR NOT NULL," +
            "age INTEGER NOT NULL) ";
    private static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS usr";
    private static final String CLEAN_TABLE_SQL = "TRUNCATE TABLE usr";
    private static final String FROM_TABLE_SQL = "FROM User";

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();

            session.createNativeQuery(CREATE_TABLE_SQL).executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = SESSION_FACTORY.openSession();
        try {
            session.beginTransaction();

            session.createNativeQuery(DROP_TABLE_SQL)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();

            User user = new User();
            user.setAge(age);
            user.setName(name);
            user.setLastName(lastName);

            session.save(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {

        try (Session session = SESSION_FACTORY.openSession()) {

            session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {

        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();

            List<User> userList = session.createQuery(FROM_TABLE_SQL).getResultList();

            session.getTransaction().commit();
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            session.createNativeQuery(CLEAN_TABLE_SQL).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}