package com.example.demo;


import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "GetCarType", value = "/GetCarType")
public class GetCarType extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String carType = (String) request.getAttribute("CarType");

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


            StringBuilder message = new StringBuilder();
            /*for (int i = 1; i <= columnCount; i++ ) {
                message.append(rsmd.getColumnName(i)+",");
            }

            message.append("\n");*/

            while(rs.next()) {
                message.append(rs.getInt(1)+",");
                message.append(rs.getString(2)+",");
                message.append(rs.getString(3)+",");
                message.append(rs.getInt(4)+",");
                message.append(rs.getInt(5)+",");
                message.append(rs.getInt(6)+",");
                message.append("\n");
            }
            connection.close();

            System.out.println(message);
            request.setAttribute("message", message);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("OutCarType");

            requestDispatcher.forward(request, response);

            /*out.println("<table border=1>");
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
            connection.close();*/

        }catch(Exception e){ System.out.println(e);}
    }

    public void destroy() {
    }
}
