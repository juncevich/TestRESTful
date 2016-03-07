package dao;

import entities.Field;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DBManagerMySQL {

    public static Connection createConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testDB","root","root");

        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();

        }
        return connection;
    }
    public static Boolean isCorrectTable(String schemaName, String tableName){
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

            while (rsSchema.next()){
                availableSchemaList.add(rsSchema.getString(1));
            }

            ResultSet rsTable = stmt.executeQuery(queryTable);
            while (rsTable.next()){
                availableTableList.add(rsTable.getString(1));
            }

            result = availableSchemaList.contains(schemaName) &
                    availableTableList.contains(tableName);
            dbConnect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String queryCreate(String database, String table) {
        Connection dbConnect = null;
        String result = "";

        try {
            dbConnect = DBManagerMySQL.createConnection();
            Statement stmt = dbConnect.createStatement();
            String query = "SHOW CREATE TABLE "+ database + "." + table + ";";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                result = rs.getString(2);
            }

            dbConnect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return result;
    }

    public static List<Field> querySelect(String database, String table) {
        Connection dbConnect = null;
        String result = "";
        List<Field> fieldList = new ArrayList<>();
        try {
            dbConnect = DBManagerMySQL.createConnection();
            Statement stmt = dbConnect.createStatement();
            String query = "DESCRIBE "+ database + "." + table + ";";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Field field = new Field();
                field.setName(rs.getString("Field"));
                field.setPrime(rs.getString("Key"));

                fieldList.add(field);
            }

            dbConnect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return fieldList;
    }


}
