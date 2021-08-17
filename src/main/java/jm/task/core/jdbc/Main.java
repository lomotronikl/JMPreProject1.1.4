package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserService service = new UserServiceImpl();

        service .createUsersTable();

        service.saveUser("Vasya1", "Pupkin6", (byte)40);
        System.out.println("User с именем Vasya1 добавлен в базу данных");
        service.saveUser("Vasya2", "Pupkin7", (byte)20);
        System.out.println("User с именем Vasya2 добавлен в базу данных");
        service.saveUser("Vasya3", "Pupkin8", (byte)10);
        System.out.println("User с именем Vasya3 добавлен в базу данных");
        service.saveUser("Vasya4", "Pupkin9", (byte)4);
        System.out.println("User с именем Vasya4 добавлен в базу данных");

        List<User> users= service.getAllUsers();

       // System.out.println(users.size());
        for (User user: users) {
            System.out.println(user);
        }

        service.cleanUsersTable();
        service.dropUsersTable();


    }
}
