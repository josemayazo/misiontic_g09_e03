/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controller.ServiceController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jose_
 */
@WebServlet(name = "ServletsListMyServices", urlPatterns = {"/ServletsListMyServices"})
@MultipartConfig
public class ServletListMyServices extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int userId = Integer.parseInt(request.getParameter("userId"));
        
        
        
        ServiceController servController = new ServiceController();
        
        String result = servController.listMyServices(userId);
        System.out.println(result);

        
        PrintWriter out = response.getWriter();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(result);
        out.flush();
    
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
