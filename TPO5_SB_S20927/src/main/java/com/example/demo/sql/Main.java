package com.example.demo.sql;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.setProperty("java.net.preferIPv6Addresses", "true");
        //String url = "jdbc:mysql://localhost:3306/dbo?autoReconnect=true&useSSL=false";
        //String url = "jdbc:sqlserver://./;instance=<LOCALDB#4B6030E0>;namedPipe=true";
        //String url = "jdbc:sqlserver://localhost\\MSSQLLocalDB;databaseName=master;";
        //username=rawchor;password='salt123';
        //integratedSecurity=true;
        //String url = "jdbc:sqlserver://localhost:1433;Database=master;instance=MSSQLLocalDB;encrypt=true;TrustServerCertificate=true;integratedSecurity=true;";
        //String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=TestDB;integratedSecurity=true";
        Connection myConn = DriverManager.getConnection("jdbc:oracle:thin:@db-oracle.pjwstk.edu.pl:1521:baza", "s20927", "oracle12");

        String user = "rawchor";
        String password = "salt123";

        //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

        //Connection myConn = DriverManager.getConnection(url);
        System.out.println("Connection successful");

        Statement st = myConn.createStatement();
        //ResultSet rs = st.executeQuery("SELECT * FROM dbo.Cars");
        ResultSet rs = st.executeQuery("SELECT * FROM Cars");

        while (rs.next()) {
            String type = rs.getString("Car");
            System.out.println(type + "\n");
        }
        /*try {

        } catch (Exception e) {
            System.out.println(e);
        }*/
    }
}
