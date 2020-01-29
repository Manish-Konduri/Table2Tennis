package com.tabletennis;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="managerLogin",urlPatterns = "/managerLogin")
public class Manager extends HttpServlet {
    public Manager(){

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        DataBase db = new DataBase();
        Statement statement;
        Connection connection;
        String tournamentName = request.getParameter("Tournament");
        String sets = request.getParameter("sets");
        String playersCount = request.getParameter("playerscount");
        String stridcap = String.valueOf(request.getSession(false).getAttribute("id"));
        int count = Integer.parseInt(playersCount);
        int rounds=0;
        int j;
        String idcap = "";
        for (j = 3; j < stridcap.length(); j++) {
            idcap = idcap + stridcap.charAt(j);
        }
        while(count>0)
        {
            rounds=rounds+1;
            count=count/2;
        }
        int id = Integer.parseInt(idcap);
        try {
            resp.setContentType("text/html");
       //     StringBuilder partQuery= new StringBuilder();
//            for(int i=1;i<=rounds;i++)
//                partQuery.append(",ROUND").append(i).append(" int DEFAULT 0").append(",SET").append(i).append(" int DEFAULT 0");
//           // System.out.println(partQuery.toString());

            ResultSet rs = db.managerPost(tournamentName,id);
                JSONObject userDetails =  new JSONObject();
//            JSONArray js = new JSONArray();
//            //    Gson userDetails = new Gson();
//            String Name="";
//            int i=0;;
            String s = "";
            if(rs.next()) {
                s = rs.getString("tName");
                userDetails.put("Names",rs.getString("tName"));


            }
            if(s.length()>0) {
//            System.out.println(js.toString());
            resp.getWriter().write(userDetails.toString());
            }
            else
                resp.setStatus(401);

       }
        catch (Exception e){
            e.printStackTrace();
        }


    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        DataBase db = new DataBase();
        Statement statement;
        Connection connection;
        String stridcap = String.valueOf(request.getSession(false).getAttribute("id"));
        System.out.println("---->"+stridcap);

        int j;
        String idcap = "";
        int id = Integer.parseInt(stridcap);
        try {
            resp.setContentType("text/html");
            Class.forName("com.mysql.jdbc.Driver");

            // Connects to mysql service through a connection url and credentials
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "manishk", "manish@145#");
            statement = connection.createStatement();

            String query3 = "select tName from tournamentNames where Id="+id+";";
            ResultSet rs = statement.executeQuery(query3);
            JSONObject userDetails =  new JSONObject();
            JSONArray jsonArray = new JSONArray();
            String s = "";
            while(rs.next()) {
                s = rs.getString("tName");
                jsonArray.put(s);
            }
            if(s.length()>0) {
//            System.out.println(js.toString());
                resp.getWriter().write(jsonArray.toString());
            }
            else
                resp.setStatus(401);

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
