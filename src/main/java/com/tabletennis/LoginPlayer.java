package com.tabletennis;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

//@WebServlet(name="login",urlPatterns = "/login")
public class LoginPlayer extends HttpServlet {
    public LoginPlayer()
    {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        DataBase db = new DataBase();
        String email = request.getParameter("Email");
        String password = request.getParameter("pwd");
      //  String finalPassword =  PasswordEncryption.encryption(password);
        Statement statement;
        Connection connection;
        try {
            resp.setContentType("text/html");
            Class.forName("com.mysql.jdbc.Driver");

            // Connects to mysql service through a connection url and credentials
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "manishk", "manish@145#");
            statement = connection.createStatement();
            String q = "select * from teamplayer where Email='" + email + "' and password= '" + password + "'";
            ResultSet rs = statement.executeQuery(q);
            JSONObject userDetails =  new JSONObject();
        //    Gson userDetails = new Gson();
            String id="";
            while(rs.next()) {
                id = rs.getString("MemberId");
                userDetails.put("role",rs.getString("role"));
                System.out.println(id);
            }
//            if (rs.next()) {
//                userDetails.put("id", rs.getString("MemberId"));
//                userDetails.put("email", rs.getString("Email"));
////                 arrayList.add(rs.getString("MemberId"));
////                 arrayList.add( rs.getString("Email"));
//            }
//            userDetails.toJson(arrayList);
           // System.out.println(userDetails.length());
            if (id.length()>0) {
            //   resp.getWriter().write(userDetails.toString());
                Cookie c = new Cookie("id",id);

                //resp.getWriter().write(c.getValue());
                resp.addCookie(c);

            }

//            if(userDetails.length()>0)
//                resp.getWriter().write(userDetails.toString());
            else
                resp.setStatus(401);

        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

}
