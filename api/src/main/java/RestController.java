import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * @author Daulet Zholtayev
 * @since 13.11.2020 - 19:50
 */
@Path("/user")
@RequestScoped
public class RestController {

    @GET
    @Path("find")
    public String findLikeLogin(@QueryParam("param") String param){
        System.out.println(param);
        return "ret";
    }
}
