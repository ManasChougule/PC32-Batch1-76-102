package com.cdac.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.cdac.pojo.Product;
import com.cdac.dao.ProductsDAO;
import com.cdac.dao.ProductsDAOImpl;


@WebServlet("/ProductsWeb")
public class ProductsWeb extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductsDAO productsDao;

    @Override
    public void init() throws ServletException {
        try {
            productsDao = new ProductsDAOImpl();
        } catch (ClassNotFoundException e) {
            throw new ServletException("Error initializing DAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String categoryIdStr = request.getParameter("categoryId");
        if (categoryIdStr == null) {
            out.println("Category ID missing!");
            return;
        }

        int categoryId;
        try {
            categoryId = Integer.parseInt(categoryIdStr);
        } catch (NumberFormatException e) {
            out.println("Invalid Category ID!");
            return;
        }

        try {
            List<Product> products = productsDao.getProductsByCategory(categoryId);

            out.println("<head><style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }");
            out.println("table { width: 90%; margin: auto; border-collapse: collapse; background-color: #fff; }");
            out.println("th, td { padding: 12px; border: 1px solid #ccc; text-align: center; }");
            out.println("th { background-color: #2196F3; color: white; }");
            out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
            out.println("img { border-radius: 8px; }");
            out.println("</style></head>");

            out.println("<body>");
            out.println("<table border='1'>");
            out.println("<tr><th>Name</th><th>Description</th><th>Price</th><th>Image</th></tr>");

            for (Product p : products) {
                out.println("<tr>");
                out.println("<td>" + p.getProductName() + "</td>");
                out.println("<td>" + p.getProductDescription() + "</td>");
                out.println("<td>" + p.getProductPrice() + "</td>");
                out.println("<td><img src='Images/" + p.getProductImageUrl() + "' height='60' width='60' /></td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error fetching products: " + e.getMessage());
        }
    }
}
