package com.tabletennis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name="editForm",urlPatterns = "/editForm")
public class LoginPlayerEdit extends HttpServlet {
    public LoginPlayerEdit()
    {

    }
//UPDATE teamplayer SET Name="KONDURI" WHERE Name="erhgsdf";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String playerName = request.getParameter("Name");
        String phone = request.getParameter("Phone");
        String email = request.getParameter("Email");
        String stridcap = String.valueOf(request.getSession(false).getAttribute("id"));
        System.out.println("----"+playerName+"---");
        try{
            resp.setContentType("text/html");
            DataBase db = new DataBase();
            db.loginPlayerEdit(stridcap,playerName,phone,email);

        } catch (SQLException | ClassNotFoundException e) {
//            resp.setStatus(401);
            e.printStackTrace();
        }


    }
}
