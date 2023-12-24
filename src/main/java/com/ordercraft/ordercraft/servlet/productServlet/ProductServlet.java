package com.ordercraft.ordercraft.servlet.productServlet;

import com.ordercraft.ordercraft.dao.ProductDao;
import com.ordercraft.ordercraft.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            productService = new ProductService(new ProductDao());
        } catch (Exception e) {
            throw new ServletException("Error initializing ProductService.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            String productIdParam = request.getParameter("productId");

            if (productIdParam != null && !productIdParam.isEmpty()) {
                int productId = Integer.parseInt(productIdParam);

                if ("edit".equals(action)) {
                    handleUpdateAction(request, response, productId);
                } else if ("delete".equals(action)) {
                    handleDeleteAction(request, response, productId);
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action parameter");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or empty productId parameter");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid productId parameter: " + e.getMessage());
        } catch (RuntimeException e) {
            throw new ServletException("Error processing product action.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            int productId = 0;
            if ("add".equals(action)) {
                handleAddAction(request);
            } else if ("update".equals(action)) {
                handleUpdateAction(request, response, productId);
            } else if ("delete".equals(action)) {
                handleDeleteAction(request, response, productId);
            }

            response.sendRedirect(request.getContextPath() + "/product");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid productId parameter: " + e.getMessage());
        } catch (RuntimeException e) {
            throw new ServletException("Error processing product action.", e);
        }
    }

    private void handleAddAction(HttpServletRequest request) {
        // Implement as needed based on your requirements
    }

    private void handleUpdateAction(HttpServletRequest request, HttpServletResponse response, int productId) {
        // Implement as needed based on your requirements
    }

    private void handleDeleteAction(HttpServletRequest request, HttpServletResponse response, int productId) {
        productId = Integer.parseInt(request.getParameter("productId"));
        productService.deleteProduct(productId);
    }
}
