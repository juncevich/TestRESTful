package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import dao.DBManagerMySQL;
import entities.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализует необходимые комманды
 * для работы с БД.
 */
@Path("/service")
public class RestExample {
    /**
     * Класс для работы с БД.
     */
    private DBManagerMySQL dbManagerSQL = new DBManagerMySQL();
    /**
     * Поле, которое используется для вывода результата.
     * Хранит результат.
     */
    private String result = " ";

    /**
     * Метод возвращает DDL на создание указанной
     * существующей в БД таблицы. Скрипт должен
     * содержать DDL создания первичного ключа.
     * @param schemaName База данных.
     * @param tableName Таблица
     * @return DDL на создание указанной
     * существующей в БД таблицы.
     */
    @GET
    @Path("/create")
    public final String selectCreate(@QueryParam("schemaName")
                                     final  String schemaName,
                                     @QueryParam("tableName")
                                     final String tableName) {
        if (DBManagerMySQL.isCorrectTable(schemaName, tableName).equals(true)) {
            result = dbManagerSQL.queryCreate(schemaName, tableName);

            return result;
        } else {
            return "Please, enter correct request";
        }
    }
    /**
     * Метод возвращает запрос вида SELECT
     * <список полей через запятую> FROM <указанная таблица>
     * WHERE <поле-первичный ключ> = ?
     * (либо писок таких полей в случае составного ПК).
     * @param schemaName База данных.
     * @param tableName Таблица.
     * @return запрос вида SELECT
     * <список полей через запятую> FROM <указанная таблица>
     * WHERE <поле-первичный ключ> = ?
     * (либо писок таких полей в случае составного ПК).
     */
    @GET
    @Path("/select")
    public final String selectQuery(@QueryParam("schemaName")
                                    final  String schemaName,
                                    @QueryParam("tableName")
                                    final  String tableName) {
        if (DBManagerMySQL.isCorrectTable(schemaName, tableName).equals(true)) {
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

    /**
     * Метод возвращает запрос вида UPDATE
     * <указанная таблица> SET <поле1>=?, <поле2>=?.....
     * WHERE <поле-первичный ключ >=?
     * (либо писок таких полей в случае составного ПК))
     * @param schemaName База данных.
     * @param tableName Таблица.
     * @return запрос вида UPDATE
     * <указанная таблица> SET <поле1>=?, <поле2>=?.....
     * WHERE <поле-первичный ключ >=?
     * (либо писок таких полей в случае составного ПК))
     */
    @GET
    @Path("/update")
    public final String updateQuery(@QueryParam("schemaName")
                                    final String schemaName,
                                    @QueryParam("tableName")
                                    final String tableName) {
        if (DBManagerMySQL.isCorrectTable(schemaName, tableName).equals(true)) {
            StringBuilder stringBuilder = new StringBuilder("UPDATE " + tableName + " SET ");
            List<Field> fieldList = dbManagerSQL.querySelect(schemaName, tableName);
            List<Field> pKeyList = new ArrayList<>();
            for (Field field : fieldList
                    ) {
                if (field.getPrime().equals(true)) {
                    pKeyList.add(field);
                }
                stringBuilder.append(field.getName() + "=? ");
            }
            stringBuilder.append("WHERE ");
            for (Field field : pKeyList
                    ) {
                stringBuilder.append(field.getName() + " = ? ");
            }

            return stringBuilder.toString();
        } else {
            return "Please, enter correct request";
        }
    }

}


