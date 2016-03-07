package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class MainRest {
    String greetingMessage = "Please, enter a request.<br>" +
            "For example: <br>" +
            "http://localhost:8080/service/create?schemaName=testDB&tableName=test_table_1<br>" +
            "http://localhost:8080/service/select?schemaName=testDB&tableName=test_table_1<br>" +
            "http://localhost:8080/service/update?schemaName=testDB&tableName=test_table_1";
    @GET
    public String sayHello(){
        return greetingMessage;
    }

}
