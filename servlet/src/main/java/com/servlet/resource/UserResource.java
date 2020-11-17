package com.servlet.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.servlet.domain.User;
import com.servlet.domain.dto.*;
import com.servlet.service.ChatService;
import com.servlet.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Daulet Zholtayev
 * @since 11.11.2020 - 20:46
 */

/**
 * @Path
 * @QueryParam
 */
@Path("/user")
public class UserResource {

    @GET
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    public String findLikeLogin(@QueryParam("param") String param, @QueryParam("id") long currentId) {
        System.out.println("param: " + param);
        System.out.println("id: " + currentId);
        try {
            List<UserDTO> users = UserService.findUsersLikeLogin(param);
            if (users.contains(currentId)) {
                int ind = users.indexOf(currentId);
                users.remove(ind);
            }

            return new ObjectMapper().writeValueAsString(users);
        }catch (SQLException | ClassNotFoundException | JsonProcessingException e){
            e.printStackTrace();
            return "FAIL";
        }
    }

    @GET
    @Path("/createChat")
    @Produces(MediaType.APPLICATION_JSON)
    public String createChat(@QueryParam("id") Long id, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            Long chatId = ChatService.createChat(user.getId(), id);
            if(chatId == null) {
                return new ObjectMapper().writeValueAsString(new ResponseDao("FAIL", null));
            }
            return new ObjectMapper().writeValueAsString(new ResponseDao("OK", chatId));
        } catch (SQLException | ClassNotFoundException | JsonProcessingException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @POST
    @Path("/test")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String test(UserDTO testDAO) {
        System.out.println(testDAO);
        return "";
    }

    public class ResponseDao {

        String status;
        Object data;


        public ResponseDao(String status, Object data) {
            this.status = status;
            this.data = data;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }


}
