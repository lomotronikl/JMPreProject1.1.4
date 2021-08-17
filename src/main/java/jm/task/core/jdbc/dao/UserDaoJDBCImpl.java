package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {


     try (Connection connection= ConnectionUtils.getConnection()){
          ConnectionUtils.executeQuery(connection, "CREATE TABLE `users` ("
                  + "`id` BIGINT NOT NULL AUTO_INCREMENT ,"
                  + "`name` VARCHAR(200) NULL,"
                  + "`lastName` VARCHAR(200) NULL,"
                  + "`age` TINYINT NULL,"
                  + "PRIMARY KEY (`id`))" );
     } catch (SQLSyntaxErrorException e) {
         // пропускаем - таблица уже существует
     } catch (SQLException throwables) {
         throwables.printStackTrace();
     } catch (ClassNotFoundException e) {
         e.printStackTrace();
     }

    }

    public void dropUsersTable() {
        try (Connection connection= ConnectionUtils.getConnection()){
            ConnectionUtils.executeQuery(connection, "DROP TABLE `users`");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection= ConnectionUtils.getConnection()){

            StringBuilder stringBuilder = new StringBuilder("INSERT INTO `users` " +
                    "(`name`, `lastName`, `age`) VALUES ('");
            stringBuilder.append(name).append("', ");
            stringBuilder.append("'").append(lastName).append("', ");
            stringBuilder.append("'").append(age).append("');");
            System.out.println(stringBuilder.toString());
            ConnectionUtils.executeInsertQuery(connection, stringBuilder.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        try (Connection connection= ConnectionUtils.getConnection()){
            StringBuilder stringBuilder = new StringBuilder("DELETE FROM `users` WHERE `users`.`id`=");
            stringBuilder.append(id).append(" ;");

            ConnectionUtils.executeQuery(connection, stringBuilder.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users =new LinkedList<>();
        try (Connection connection= ConnectionUtils.getConnection()){
            ResultSet resultSet = ConnectionUtils.executeResultSet(connection, "SELECT `users`.`id`,\n" +
                    "    `users`.`name`,\n" +
                    "    `users`.`lastName`,\n" +
                    "    `users`.`age`\n" +
                    "FROM `users`;");

            while (resultSet.next()){

                User user= new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getNString(2));
                user.setLastName(resultSet.getNString(3));
                user.setAge(resultSet.getByte(4));
                users.add(user);
            }

        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection= ConnectionUtils.getConnection()){
            ConnectionUtils.executeQuery(connection, "delete from `users`");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
