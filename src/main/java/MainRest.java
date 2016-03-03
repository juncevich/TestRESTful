import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class MainRest {

    @GET
    public String sayHello(){
        return "Please enter in your address \" localhost:8080/hello/{yourName}\"";
    }

}
