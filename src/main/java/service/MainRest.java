package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Клас выводит сообщение для пользователя,
 * с информацией о работе с программой.
 */
@Path("/")
public class MainRest {
    /**
     * Информационное сообщение с примером
     * комманд для ввода.
     */
    private String greetingMessage = "Please, enter a request.<br>"
            + "For example: <br>"
            + "http://localhost:8080/service/create?"
            + "schemaName=testDB&tableName=test_table_1<br>"
            + "http://localhost:8080/service/select?"
            + "schemaName=testDB&tableName=test_table_1<br>"
            + "http://localhost:8080/service/update?"
            + "schemaName=testDB&tableName=test_table_1";

    /**
     * Метод выводит информационное сообщение
     * с примером комманд для ввода.
     * @return информационное сообщение.
     */
    @GET
    public final String sayHello() {
        return greetingMessage;
    }

}
