/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controller.ServiceController;
import controller.UserController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jose_
 */
@WebServlet(name = "ServletListServices", urlPatterns = {"/ServletListServices"})
public class ServletListServices extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ServiceController servController = new ServiceController();
        
        String result = servController.listServices();
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
