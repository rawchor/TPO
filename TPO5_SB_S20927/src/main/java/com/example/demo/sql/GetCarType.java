package com.example.demo.sql;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetCarType", value = "/GetCarType")
public class GetCarType {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //System.out.println("username: " + carType);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String carType = request.getParameter("CarType");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<meta charset=\"UTF-8\" />");
        out.println("<h1>" + carType + "</h1>");
        out.println("</body>");
        out.println("</html>");
    }
}
