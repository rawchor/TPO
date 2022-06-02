package com.example.demo.sql;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class GetCar extends HttpServlet {
    // SQL query
    private static final String SELECT_STUDENT_QUERY =
            "SELECT * FROM CAR WHERE TYPE = ?";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // variables
        PrintWriter pw = null;
        int sno = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;

        // set content type
        res.setContentType("text/html");
        // get Writer
        pw = res.getWriter();

        // get form data
        sno = Integer.parseInt(req.getParameter("F1"));

        try {
            // register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // create JDBC connection
            con = DriverManager.getConnection(
                    "jdbc:mysql:///knowprogram", "root", "Know9@Program");
            //jdbc:jtds:sqlserver://./;instance=<pipe name>;namedPipe=true
            // compile SQL query and store it in
            // PreparedStatement object
            if (con != null)
                ps = con.prepareStatement(SELECT_STUDENT_QUERY);
            // set input value to query parameter
            if (ps != null)
                ps.setInt(1, sno);
            // execute the query
            if (ps != null)
                rs = ps.executeQuery();

            // process the result
            if (rs != null) {
                while(rs.next()) {
                    // display result
                    flag = true;
                    pw.println("<h1>Car Details, </h1>"
                            + "Name: " + rs.getString("SNAME") + "<br>"
                            + "Address: " + rs.getString("SADD") + "<br>"
                            + "Average: " + rs.getDouble("AVG") + "<br>");
                }
            }

            // Student not found
            if(!flag) {
                pw.println("<h1>Student Not Found.</h1>");
            }

        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("Error Occured");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("Unknown Exception Occured");
        } finally {
            // close JDBC connection
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

            // Link to home
            pw.println("<h3><a href='input.html'>Home</a></h3>");
            // close stream
            pw.close();
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doGet(req, res);
    }

}