package com.servlet.resource;

import com.servlet.domain.dto.UserDTO;
import com.servlet.service.AuthService;
import com.servlet.service.UserService;
import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class AuthResource {

    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@QueryParam("login") String login, @QueryParam("psw") String psw,
                          @Context HttpServletRequest req, @Context HttpServletResponse resp) {

        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            return Response.status(HttpStatus.OK_200).build();
        }

        Map<String, Object> result = new HashMap<>();
        try {
            AuthService authService = new AuthService();
            UserDTO user = authService.login(login, psw, req);

            result.put("status", "ok");
            return Response.ok(result).build();
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", "failed");
            result.put("msg", e.getMessage());
            return Response.status(HttpStatus.BAD_REQUEST_400).entity(result).build();
        }
    }

    @POST
    @Path("/logout")
    public void logout(@Context HttpServletRequest req) {
        AuthService.logout(req.getSession());
    }

    @POST
    @Path("/registration")
    public Response registration(@QueryParam("login") String login,
                                 @QueryParam("name") String name,
                                 @QueryParam("psw") String psw,
                                 @Context HttpServletRequest req,
                                 @Context HttpServletResponse resp) {
        try {
            UserService.createUser(login, psw, name);
            return Response.ok().build();
        } catch (Exception e) {
            Map<String, String> res = new HashMap<>();
            res.put("msg", e.getMessage());
            return Response.status(HttpStatus.BAD_REQUEST_400).entity(res).build();
        }
    }
}
