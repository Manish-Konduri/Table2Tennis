package com.tabletennis;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MatchPlayer {
    public List<Match> matching(String tournamentName, int round) {
        DataBase db = new DataBase();
        round=round-1;
        Statement statement;
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            // Connects to mysql service through a connection url and credentials
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "manishk", "manish@145#");
            statement = connection.createStatement();

            String query3 = "select MemberId from tournamentjoined where tournamentName='"+tournamentName+"'";
            ResultSet rs = statement.executeQuery(query3);
            List<Integer> details = new ArrayList<>();
            List<Match> lol = new ArrayList<>();
            String s = "";
            int id;
            while (rs.next()) {
                s = rs.getString("MemberId");
                id = Integer.parseInt(s);
                details.add(id);
            }
            lol=createMatches(details,round);
            return lol;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Match> createMatches(List<Integer> players, int roundNumber){
        List<Match> matches=new ArrayList<>();
        int numberOfPlayers=players.size();
        int partSize=numberOfPlayers/2;
        for(int index=0;index<partSize;index++){
            Match match=new Match();
            match.setRoundNumber(roundNumber);
            match.setPlayer1(players.get(index));
            match.setPlayer2(players.get(index+partSize));
            matches.add(match);

        }
//        if(numberOfPlayers%2==1){
//            Match match=new Match();
//            match.setRoundNumber(roundNumber);
//            match.setPlayer1(players.get(numberOfPlayers-1));
//            match.setResult(Match.MatchResult.BYE);
//            matches.add(match);
//        }
        return matches;
    }



}