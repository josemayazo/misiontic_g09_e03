
package servlets;

import controller.ServiceController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dgalean1
 */
@WebServlet(name = "ServletServiceRegister", urlPatterns = {"/ServletServiceRegister"})
public class ServletServiceCreate extends HttpServlet {

    public ServletServiceCreate() {
        super();
    }

    
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ServiceController serviceController = new ServiceController();

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        String phoneNumber = request.getParameter("phoneNumber");
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String value = request.getParameter("value");
        

        System.out.println(id);
        System.out.println(name);
        System.out.println(description);
        System.out.println(category);
        System.out.println(phoneNumber);
        System.out.println(city);
        System.out.println(address);
        System.out.println(value);

        String result = serviceController.create(id, name,  description,  category,
             phoneNumber,  city,   address ,  value);
        PrintWriter out = response.getWriter();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(result);
        out.flush();
        }
    

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

