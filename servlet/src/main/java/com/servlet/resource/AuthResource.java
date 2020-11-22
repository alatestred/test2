package com.servlet.resource;
import com.servlet.domain.User;
import com.servlet.service.AuthService;
import com.servlet.service.UserService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/")
public class AuthResource {

    String url = "";
    URI uri;

    {
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    //
    @GET
    @Path("/login")
    public Response login(@QueryParam("login") String login, @QueryParam("psw") String psw,
                          @Context HttpServletRequest req, @Context HttpServletResponse resp)
            throws URISyntaxException, ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (session.getAttribute("user") != null) {

            String url = "/";
            URI uri = new URI(url);

            return Response.temporaryRedirect(uri).build();
        }
        if (login == null || psw == null) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
            url = "/";
            uri = new URI(url);

            return Response.temporaryRedirect(uri).build();
        }


        try {
            AuthService authService = new AuthService();
            authService.login(login, psw, req);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(req, resp);
        }

        url = "/info";
        uri = new URI(url);

        return Response.temporaryRedirect(uri).build();
    }


    @GET
    @Path("/logout")
    public Response logout(@Context HttpServletRequest req)
            throws URISyntaxException {
        AuthService.logout(req.getSession());
        url = "/";
        uri = new URI(url);

        return Response.temporaryRedirect(uri).build();
    }

    @POST
    @Path("/registration")
    public String registration(@QueryParam("login") String login, @QueryParam("name") String name,
                               @QueryParam("psw") String psw,
                               @Context HttpServletRequest req, @Context HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService.createUser(login, psw, name);
            resp.sendRedirect("/");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("registration.jsp");
            requestDispatcher.forward(req, resp);
        }
        return null;
    }
}
