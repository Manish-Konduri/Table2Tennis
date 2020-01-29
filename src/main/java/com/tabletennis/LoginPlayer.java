package com.tabletennis;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
        String email = request.getParameter("Email");
        String password = request.getParameter("pwd");
      //  String finalPassword =  PasswordEncryption.encryption(password);
        try {
            resp.setContentType("text/html");
            DataBase db = new DataBase();
            ResultSet rs = db.loginPlayer(email,password);
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
            Integer visitCount = new Integer(0);
            String visitCountKey = new String("visitCount");
            String userID = id;
            if (id.length()>0) {
               resp.getWriter().write(userDetails.toString());
//                Cookie c = new Cookie("id",id);
                HttpSession session = request.getSession(true);
                if (session.isNew()) {
                    session.setAttribute("id",id);
                } else {
                    visitCount = (Integer)session.getAttribute(visitCountKey);
                    visitCount = visitCount + 1;
                    id = (String)session.getAttribute("id");
                }
                session.setAttribute(visitCountKey,  visitCount);
                //resp.getWriter().write(c.getValue());
//                resp.addCookie(c);

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
