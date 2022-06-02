package com.example.demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@WebServlet(name = "OutCarType", value = "/OutCarType")
public class OutCarType extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String message = request.getAttribute("message").toString();
        System.out.println(message);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<table border=1>");
        out.println("<tr>");
        out.println("<th>CARID</th>");
        out.println("<th>TYPE</th>");
        out.println("<th>BRAND</th>");
        out.println("<th>YEAR</th>");
        out.println("<th>FUELUSAGE</th>");
        out.println("<th>HORSEPOWER</th>");
        out.println("<tr>");

        //String[] words = message.split(",");
        String[] lines = message.split("\n");

        for (int i = 0; i < lines.length; i++) {
            String[] words = lines[i].split(",");
            out.println("<tr>");
            out.println("<td>"+words[0]+"</td>");
            out.println("<td>"+words[1]+"</td>");
            out.println("<td>"+words[2]+"</td>");
            out.println("<td>"+words[3]+"</td>");
            out.println("<td>"+words[4]+"</td>");
            out.println("<td>"+words[5]+"</td>");
            out.println("<tr>");
        }

/*
        Scanner scanner = new Scanner(message);
        System.out.println(scanner);
        while(lines.length) {
            out.println("<tr>");
            out.println("<td>"+words[0]+"</td>");
            out.println("<td>"+words[1]+"</td>");
            out.println("<td>"+words[2]+"</td>");
            out.println("<td>"+words[3]+"</td>");
            out.println("<td>"+words[4]+"</td>");
            out.println("<td>"+words[5]+"</td>");
            out.println("<tr>");
        }*/
    }
}