package jm.task.core.jdbc.dao;

import java.sql.*;


public class ConnectionUtils {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        return MySQLConnectionUtils.getMySQLConnection();
    }
    public static void executeQuery(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    public static void executeInsertQuery(Connection connection, String query) throws SQLException {

        Statement statement = connection.createStatement();
       statement.execute (query);
   }
    public static ResultSet executeResultSet(Connection connection, String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }

}
