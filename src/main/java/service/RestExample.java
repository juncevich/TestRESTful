package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import dao.DBManagerMySQL;
import entities.Field;

import java.util.ArrayList;
import java.util.List;

@Path("/service")
public class RestExample {

    DBManagerMySQL dbManagerSQL= new DBManagerMySQL();
    String result = " ";

    @GET
    @Path("/create")
    public String selectCreate(@QueryParam("schemaName") String schemaName,
                               @QueryParam("tableName") String tableName){
        if (DBManagerMySQL.isCorrectTable(schemaName, tableName).equals(true)) {
            result = dbManagerSQL.queryCreate(schemaName, tableName);

            return result;
        } else {
            return "Please, enter correct request";
        }
    }

    @GET
    @Path("/select")

    public String selectQuery(@QueryParam("schemaName") String schemaName,
                              @QueryParam("tableName") String tableName){
        if (DBManagerMySQL.isCorrectTable(schemaName, tableName).equals(true)){
            StringBuilder stringBuilder = new StringBuilder("SELECT ");
            List<Field> fieldList = dbManagerSQL.querySelect(schemaName, tableName);
            List<Field> pKeyList = new ArrayList<>();
            for (Field field : fieldList
                    ) {
                if (field.getPrime().equals(true)) {
                    pKeyList.add(field);
                }
                stringBuilder.append(field.getName() + " ");
            }
            stringBuilder.append("FROM " + schemaName + " WHERE ");
            for (Field field : pKeyList
                    ) {
                stringBuilder.append(field.getName() + " = ? ");
            }

            return stringBuilder.toString();
        } else {
            return "Please, enter correct request";
        }
    }

    @GET
    @Path("/update")
    public String updateQuery(@QueryParam("schemaName") String schemaName,
                              @QueryParam("tableName") String tableName){
        if (DBManagerMySQL.isCorrectTable(schemaName, tableName).equals(true)){
        StringBuilder stringBuilder = new StringBuilder("UPDATE "+tableName+" SET ");
        List<Field> fieldList= dbManagerSQL.querySelect(schemaName,tableName);
        List<Field> pKeyList = new ArrayList<>();
        for (Field field: fieldList
                ) {
            if (field.getPrime().equals(true)){
                pKeyList.add(field);
            }
            stringBuilder.append(field.getName() + "=? ");
        }
        stringBuilder.append("WHERE ");
        for (Field field: pKeyList
                ) {
            stringBuilder.append(field.getName() + " = ? ");
        }

        return stringBuilder.toString();
    } else {
            return "Please, enter correct request";
        }
    }

}


