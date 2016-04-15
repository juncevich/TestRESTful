package dao;

import entities.Field;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс используется для корректной связи.
 * с базой данных.
 */
public class DBManagerMySQL {
    /**
     * Метод создает соединение с базой данных.
     * @return соединение с базой данных.
     */
    static Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"
                    + "localhost:3306/testDB", "root", "root");

        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();

        }
        return connection;
    }

    /**
     * Метод проверяет наличие базы данных и
     * таблицы.
     * @param schemaName Проверяемое база данных.
     * @param tableName Проверяемая таблица.
     * @return true -вводимые данные корректные.
     *         false - данные некорректны.
     * Возможно, метод необходимо выделить в
     * отдельный класс. Т.к. один класс должен
     * выполнять одну функцию.
     */
    public static Boolean isCorrectTable(final String schemaName,
                                         final String tableName) {
        Boolean result = false;
        List<String> availableSchemaList = new ArrayList<>();
        List<String> availableTableList = new ArrayList<>();
        Connection dbConnect = null;
        try {
            dbConnect = DBManagerMySQL.createConnection();
            Statement stmt = dbConnect.createStatement();
            String querySchema = "SHOW SCHEMAS;";
            String queryTable = "SHOW TABLES FROM " + schemaName + ";";
            ResultSet rsSchema = stmt.executeQuery(querySchema);

            while (rsSchema.next()) {
                availableSchemaList.add(rsSchema.getString(1));
            }
            rsSchema.close();

            ResultSet rsTable = stmt.executeQuery(queryTable);
            while (rsTable.next()) {
                availableTableList.add(rsTable.getString(1));
            }
            rsTable.close();
            result = availableSchemaList.contains(schemaName)
                    & availableTableList.contains(tableName);
            dbConnect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                dbConnect.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Problem to closing connection.");
            }

        }

        return result;
    }

    /**
     * Метод возвращает DDL на создание указанной
     * существующей в БД таблицы. Скрипт должен
     * содержать DDL создания первичного ключа.
     * @param database База данных.
     * @param table Таблица
     * @return DDL на создание указанной
     * существующей в БД таблицы.
     */
    public static String queryCreate(final String database,
                                     final String table) {
        Connection dbConnect = null;
        String result = "";

        try {
            dbConnect = DBManagerMySQL.createConnection();
            Statement stmt = dbConnect.createStatement();
            String query = "SHOW CREATE TABLE " + database + "." + table + ";";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                result = rs.getString(2);
            }
            rs.close();
            dbConnect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                dbConnect.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Problem to closing connection.");
            }
        }


        return result;
    }

    /**
     * Метод возвращает список объектов Field.
     * @param database База данных.
     * @param table Таблица.
     * @return список объектов Field.
     */
    public static List<Field> querySelect(final String database,
                                          final String table) {
        Connection dbConnect ;

        List<Field> fieldList = new ArrayList<>();
        try {
            dbConnect = DBManagerMySQL.createConnection();
            Statement stmt = dbConnect.createStatement();
            String query = "DESCRIBE " + database + "." + table + ";";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Field field = new Field();
                field.setName(rs.getString("Field"));
                field.setPrime(rs.getString("Key"));

                fieldList.add(field);
            }
            rs.close();
            dbConnect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return fieldList;
    }


}
