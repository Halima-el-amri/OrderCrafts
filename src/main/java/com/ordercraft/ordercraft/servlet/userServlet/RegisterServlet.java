package com.ordercraft.ordercraft.servlet.userServlet;

import com.ordercraft.ordercraft.dao.UserDao;
import com.ordercraft.ordercraft.model.classes.User;
import com.ordercraft.ordercraft.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final String vue= "/views/register.jsp";
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize the UserService (you can inject it using a framework like Spring)
        userService = new UserService(new UserDao()); // Pass your UserDao instance here
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to the registration form
        request.getRequestDispatcher(vue).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user input from the registration form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check if the username is already taken
        if (userService.isUsernameTaken(username)) {
            request.setAttribute("error", "Username is already taken");
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
        } else {
            // Create a User object
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);

            // Call the UserService to register the user
            userService.addUser(newUser);

            // Redirect to a success page or login page
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
