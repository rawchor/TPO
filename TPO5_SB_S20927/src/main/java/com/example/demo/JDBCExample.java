package com.example.demo;

import java.sql.*;
public class JDBCExample{
    public static void main(String args[]){
        try{
            Connection myConn = DriverManager.getConnection("jdbc:oracle:thin:@db-oracle.pjwstk.edu.pl:1521:baza", "s20927", "oracle12");

            Statement stmt=myConn.createStatement();

            //ResultSet rs=stmt.executeQuery("SELECT * FROM \"Debil\"");
            ResultSet rs=stmt.executeQuery("SELECT * FROM Car");

            while(rs.next()) {
                System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            }
            myConn.close();

        }catch(Exception e){ System.out.println(e);}
    }

    /*
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
        PrintWriter pw=res.getWriter();
        res.setContentType("text/html");
        String tb=req.getParameter("CarType");

        try
        {
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","admin");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@db-oracle.pjwstk.edu.pl:1521:baza", "s20927", "oracle12");
            Statement st=con.createStatement();
            System.out.println("connection established successfully...!!");

            ResultSet rs=st.executeQuery("SELECT * FROM CAR WHERE TYPE='" + tb + "'");

            pw.println("<table border=1>");
            while(rs.next()) {
                pw.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td>"+
                        "<td>"+rs.getString(3)+"</td></tr>");
            }
            pw.println("</table>");
            pw.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }


        out.println("<html>");
        out.println("<body>");
        out.println("<meta charset=\"UTF-8\" />");
        out.println("<h1>Car type chosen: " + carType + "</h1>");
        out.println("</body>");
        out.println("</html>");

    }
    */
}  