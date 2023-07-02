package com.kevinpina.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/info")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String name = request.getParameter("name");
        String version = request.getParameter("version");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("     <head>");
        out.println("         <meta charset=\"UTF-8\" />");
        out.println("         <title>Index</title>");
        out.println("     </head>");
        out.println("     <body>");
        out.println("         <h1>Welcome</h1>");
        out.println("<b>Name: </b>" + (name != null ? name : "webapp" ) + "<br/>");
        out.println("<b>Version: </b>" + (version != null ? version : "v0.1.0") +  "<br/>");
        out.println("     </body>");
        out.println("</html>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

}