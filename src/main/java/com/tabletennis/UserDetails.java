package com.tabletennis;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
@WebServlet(name="UserDetails",urlPatterns = "/userDetails")
public class UserDetails extends HttpServlet {
    public UserDetails()
    {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        DataBase db = new DataBase();
        String stridcap = request.getParameter("email");
        int i;
        String idcap="";
        for(i=3;i<stridcap.length();i++)
        {
            idcap=idcap+stridcap.charAt(i);
        }
        int id = Integer.parseInt(idcap);
        System.out.println(id+"---------");
        //  String finalPassword =  PasswordEncryption.encryption(password);
        Statement statement;
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            // Connects to mysql service through a connection url and credentials
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "manishk", "manish@145#");
            statement = connection.createStatement();
            String q = "select * from teamplayer where MemberId='" + id + "'";
            ResultSet rs = statement.executeQuery(q);
            JSONObject userDetails = new JSONObject();
            String role="";
            if (rs.next()) {
                userDetails.put("name", rs.getString("Name"));
                userDetails.put("email", rs.getString("Email"));
                userDetails.put("role",rs.getString("role"));
               userDetails.put("phone", rs.getString("phone"));
               userDetails.put("skill", rs.getString("skill"));
            }

            if(userDetails.length()>0) {
                resp.getWriter().write(userDetails.toString());
                System.out.println(userDetails);
            }
            else
                resp.getWriter().write("Failure");
        }
        catch (Exception e){
            System.out.println("No Table");
        }

    }
}
