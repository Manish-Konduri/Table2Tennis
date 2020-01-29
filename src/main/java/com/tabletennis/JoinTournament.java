package com.tabletennis;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
@WebServlet(name="joinTournament",urlPatterns = "/joinTournament")
public class JoinTournament extends HttpServlet {
    public JoinTournament()
    {
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("text/html");
            DataBase db = new DataBase();
            // Connects to mysql service through a connection url and credentials
            ResultSet rs = db.joinTournamentGet();
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String tournamentName = request.getParameter("Tournament");
        String stridcap = request.getParameter("Id");
        System.out.println("---->"+tournamentName);
        int j;
        String idcap = "";
        for (j = 3; j < stridcap.length(); j++) {
            idcap = idcap + stridcap.charAt(j);
        }
        int id = Integer.parseInt(idcap);
        try {
            resp.setContentType("text/html");
            DataBase db = new DataBase();
            db.joinTournamentPost(id,tournamentName);
        }
        catch (Exception e){
            e.printStackTrace();
            resp.setStatus(401);
        }
    }
}

