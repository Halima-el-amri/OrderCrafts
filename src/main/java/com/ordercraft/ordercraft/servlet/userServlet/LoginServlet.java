package com.ordercraft.ordercraft.servlet.userServlet;

import com.ordercraft.ordercraft.dao.UserDao;
import com.ordercraft.ordercraft.model.classes.User;
import com.ordercraft.ordercraft.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;
    private static final String vue = "/views/dash.jsp";
    private static final String form = "/views/login.jsp";

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService(new UserDao());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(form).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isAuthenticated = userService.loginUser(username, password);

        if (isAuthenticated) {
            User user = userService.getUserByUsername(username);
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            System.out.println("************* request ***************");
            System.out.println(request.getServletPath());
            System.out.println("************* response ***************");
            response.getHeaderNames().forEach(System.out::println);
            request.getRequestDispatcher(vue).forward(request, response);
            //response.sendRedirect(vue);
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher(form).forward(request, response);
        }
    }

    @Override
    public void destroy() {

        super.destroy();
    }
}
