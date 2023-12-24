package com.ordercraft.ordercraft.servlet.userServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet("/dash")
public class DashboardServlet extends HttpServlet {
    private static final String vue="/views/dash.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to the login form
        request.getRequestDispatcher(vue).forward(request, response);
    }
}