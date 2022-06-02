package com.example.demo;

import java.io.*;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "SetCarType", value = "/SetCarType")
public class SetCarType extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String carType = request.getParameter("CarType");

        System.out.println("Car Type: " + carType);

        request.setAttribute("CarType", carType);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("GetCarType");

        requestDispatcher.forward(request, response);
    }

    /*
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String carType = request.getParameter("CarType");

        System.out.println("Car Type: " + carType);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        try{
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@db-oracle.pjwstk.edu.pl:1521:baza", "s20927", "oracle12");

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM CAR WHERE TYPE='" + carType + "'");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();


            out.println("<table border=1>");
            out.println("<tr>");
            for (int i = 1; i <= columnCount; i++ ) {
              String name = rsmd.getColumnName(i);
                out.println("<th>" + name + "</th>");
            }
            out.println("<tr>");

            while(rs.next()) {
                out.println("<tr>");
                out.println("<td>"+rs.getInt(1)+"</td>");
                out.println("<td>"+rs.getString(2)+"</td>");
                out.println("<td>"+rs.getString(3)+"</td>");
                out.println("<td>"+rs.getInt(4)+"</td>");
                out.println("<td>"+rs.getInt(5)+"</td>");
                out.println("<td>"+rs.getInt(6)+"</td>");
                out.println("<tr>");
            }
            connection.close();

        }catch(Exception e){ System.out.println(e);}
    }

    public void destroy() {
    }*/
}