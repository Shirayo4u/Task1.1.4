package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Connection connection = Util.getConnection();
            Statement statement = Util.getStatement(connection);

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Task1( " +
                    "id int PRIMARY KEY BY DEFAULT AS IDENTITY, " +
                    "name VARCHAR(30) NOT NULL, " +
                    "lastName VARCHAR(30) NOT NULL, " +
                    "age int NOT NULL, ");

            System.out.println("Таблица создана!");


        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании таблицы" + e);
        }
    }

    public void dropUsersTable() {
        try {
            Connection connection = Util.getConnection();
            Statement statement = Util.getStatement(connection);

            statement.executeUpdate("DROP TABLE Task1");
            System.out.println("Таблица удалена!");

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении таблицы" + e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}