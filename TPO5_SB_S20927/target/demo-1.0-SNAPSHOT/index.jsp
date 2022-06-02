<%--
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>

<h1><%= "GetCarServlet" %></h1>
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Get Car Type</title>
    </head>
    <body>
        <br/>
        <%--<a href="GetCar-Servlet">GetCarServlet</a>--%>
        <form name="ChooseCarType" method="get" action="SetCarType">
            <span style="font-family: verdana; font-size: x-small; ">
                Car Type: <input type="text" name="CarType"/>
                <input type="submit" value="Enter" />
            </span>
            <br/>
        </form>
    </body>
</html>
