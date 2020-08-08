package pl.sda.dao;

import java.sql.*;

public class DepartmentDaoImpl {

    void printAll(){
        try (final Connection connection = connect()) {
            printAllData(connection);
        } catch (SQLException exp) {
            System.out.println("Błąd połączenia " + exp.getMessage());
        }
    }

    private void printAllData(Connection connection) throws SQLException {
        String sqlQuery = "Select * from department";
        Statement statement = connection.createStatement();
        statement.execute(sqlQuery);
        printDepartmentData(statement.getResultSet());
        statement.close();
    }

    void printDetails(Integer departmentId) {
        try (final Connection connection = connect()) {
            printDepartmentData(connection, departmentId);
        } catch (SQLException exp) {
            System.out.println("Błąd połączenia " + exp.getMessage());
        }

    }

    private void printDepartmentData(Connection connection, Integer departmentId) throws SQLException {
        final String sqlQuery = "select * from department where department_id = ?";
        final PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, departmentId);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();

        printDepartmentData(resultSet);
        preparedStatement.close();
    }

    private void printDepartmentData(ResultSet resultSet) throws SQLException {
        StringBuilder builder = new StringBuilder();

        while (resultSet.next()) {
            Integer departmentId = resultSet.getInt("department_id");
            String departmentName = resultSet.getString("name");
            builder.append(departmentId);
            builder.append(" ");
            builder.append(departmentName);
            builder.append("\n");
        }
        System.out.println(builder.toString());
    }

    private Connection connect() throws SQLException {
        final String connectionUrl = "jdbc:mysql://localhost:3306/zad0?autoReconnect=true&useSSL=false&serverTimezone=UTC";
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/zad0?autoReconnect=true&useSSL=false" +
                "&serverTimezone=UTC", "scott", "scott");
    }


}
