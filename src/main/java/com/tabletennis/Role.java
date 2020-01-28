package com.tabletennis;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import java.sql.*;

public class Role extends HttpServlet {
    public String role(String idstr){
        System.out.println(idstr);
        int i;
//        String idcap = "";
//        for (i = 3; i < idstr.length(); i++) {
//            idcap = idcap + idstr.charAt(i);
//        }
        int id= Integer.parseInt(idstr);
        DataBase db = new DataBase();
        Statement statement;
        Connection connection;
        try{
            Class.forName("com.mysql.jdbc.Driver");

            // Connects to mysql service through a connection url and credentials
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "manishk", "manish@145#");
            statement = connection.createStatement();
            String q = "SELECT * from teamplayer WHERE MemberId= " + id + "";
            ResultSet rs = statement.executeQuery(q);
            JSONObject userDetails =  new JSONObject();
            JSONArray jsonArray = new JSONArray();
            String r="";
            if (rs.next()) {
                r=rs.getString("role");
                return r;
            }
          //  System.out.println(r);

        } catch (SQLException | ClassNotFoundException e) {
//            resp.setStatus(401);
            e.printStackTrace();
        }
    return "";
    }

}
