
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.ServiceController;


@WebServlet(name = "ServletServiceUpdate", urlPatterns = {"/ServletServiceUpdate"})
public class ServletServiceUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletServiceUpdate() {
    }

    
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServiceController servicio = new ServiceController();
		
		int id = Integer.getInteger(request.getParameter("id"));
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String category = request.getParameter("category");
                String phoneNumber = request.getParameter("phoneNumber");
                String city = request.getParameter("city");
                String address = request.getParameter("address");
                double value = Double.parseDouble(request.getParameter("value"));
		
		
		
		String servicioStr = servicio.update(id,name,description,category,
                        phoneNumber,city,address,value);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(servicioStr);
		out.flush();
		out.close();
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    
   

}
