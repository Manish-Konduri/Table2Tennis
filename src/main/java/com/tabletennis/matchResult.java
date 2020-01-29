package com.tabletennis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@WebServlet(name="matchResult",urlPatterns = "/matchResult")
public class matchResult extends HttpServlet {


    public matchResult(){

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBase db = new DataBase();
        Statement statement;
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            // Connects to mysql service through a connection url and credentials
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "manishk", "manish@145#");
            statement = connection.createStatement();

            int playerA = Integer.parseInt(req.getParameter("playerA"));
            int playerB = Integer.parseInt(req.getParameter("playerB"));
            int matchResult = Integer.parseInt(req.getParameter("matchresult"));
            int winsetPlayerA = Integer.parseInt(req.getParameter("winsets_a"));
            int winsetPlayerB = Integer.parseInt(req.getParameter("winsets_b"));
            int totalSets = winsetPlayerA+winsetPlayerB;
            System.out.println(playerB+" "+playerA+" "+matchResult);

            String query = "update pointstable set result="+matchResult+",TotalSets="+totalSets+",winset_Player1="+winsetPlayerA+",winset_Player2="+winsetPlayerB+"  where player1="+playerA+" and player2="+playerB+"";
            statement.executeUpdate(query);

        }
        catch (Exception e){
                e.printStackTrace();
        }
    }
}
