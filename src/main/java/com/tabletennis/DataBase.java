package com.tabletennis;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class DataBase {
    Statement statement;
    Connection connection;
    DataBase(){
        dbConnection();
    }
    public void dbConnection() {
        try{
            Class.forName("com.mysql.jdbc.Driver");

            // Connects to mysql service through a connection url and credentials
            connection= DriverManager.getConnection( "jdbc:mysql://localhost:3306/testdb","manishk","manish@145#");
            statement = connection.createStatement();
        }
        catch (Exception e){
            System.out.println("No Table");
        }
    }

//    INSERT INTO `teamplayer`(`Name`,`TeamName`,`phone`,`Email`,`password`) VALUES ('"+id+"','"+name+"','"+teamname+"','"+skill+"','"+phone+"','"+Email+"','"+password+"','"+TodayStatus+"');

    public void add(int id, String name, String teamname, int skill, String phone, String Email, String password,String TodayStatus, String Role) throws SQLException {


//        System.out.println("insert into tesdb values ("+id+",'"+name+"','"+description+"','"+dateString+"','"+Initial+"')");
        String q= "INSERT INTO `teamplayer`(`Name`,`TeamName`,`skill`,`phone`,`Email`,`password`,`TodayStatus`) VALUES ('"+name+"','"+teamname+"','"+4+"','"+phone+"','"+Email+"','"+password+"','"+0+"','"+Role+"')";
        statement.executeUpdate(q);
    }
    public JSONArray display() throws SQLException {
        ResultSet rs = statement.executeQuery("select * from teamplayer");
//        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        while(rs.next()) {
            JSONObject record = new JSONObject();
            //Inserting key-value pairs into the json object
            record.put("Name", rs.getString("Name"));
            record.put("Team_Name", rs.getString("TeamName"));
            record.put("skill", rs.getInt("skill"));
            record.put("Phone", rs.getString("phone"));
            record.put("Email", rs.getString("Email"));
            array.put(record);
        }
        return (array);
    }
    public ResultSet joinTournamentGet() throws SQLException, ClassNotFoundException {
        String query = "select tName from tournamentNames";
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }
    public void joinTournamentPost(int id, String tournamentName) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO `tournamentjoined`(`MemberId`,`tournamentName`) VALUES ('"+id+"','"+tournamentName+"')";
        statement.executeUpdate(query);
    }
    public ResultSet loginPlayer(String email,String password) throws ClassNotFoundException, SQLException {
        String q = "select * from teamplayer where Email='" + email + "' and password= '" + password + "'";
        ResultSet rs = statement.executeQuery(q);
        return rs;
    }
    public void loginPlayerEdit(String idcap, String playerName, String email, String phone) throws ClassNotFoundException, SQLException {
        int id = Integer.parseInt(String.valueOf(idcap));
        String q = "UPDATE teamplayer SET Name='" + playerName + "' WHERE MemberId= " + id + "";
        statement.executeUpdate(q);
        q = "UPDATE teamplayer SET Email='" + email + "' WHERE MemberId= " + id + "";
        statement.executeUpdate(q);
        q = "UPDATE teamplayer SET phone='" + phone + "' WHERE MemberId= " + id + "";
        statement.executeUpdate(q);
    }
    public ResultSet managerPost(String tournamentName,int id) throws SQLException {
        String q1 = "insert into tournamentNames values('"+tournamentName+"','"+id+"')";
       // String q="create table "+tournamentName+"(PLAYERNAME varchar(10) PRIMARY KEY"+partQuery.toString()+",TOTAL_SETS int DEFAULT 0)";
//        System.out.println(q);
//        statement.executeUpdate(q);
        statement.executeUpdate(q1);
        String query3 = "select * from tournamentNames where Id="+id+";";
        ResultSet rs = statement.executeQuery(query3);
        return  rs;
    }
    public ResultSet managerGet(int id) throws SQLException {
        String query3 = "select tName from tournamentNames where Id="+id+";";
        ResultSet rs = statement.executeQuery(query3);
        return  rs;
    }
}
