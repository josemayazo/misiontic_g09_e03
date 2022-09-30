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


@WebServlet(name = "ServletEditService", urlPatterns = {"/ServletEditService"})
@MultipartConfig
public class ServletEditService extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int serviceId = Integer.parseInt(request.getParameter("serviceId"));
        String serviceName = request.getParameter("serviceName");
        String serviceDescription = request.getParameter("serviceDescription");
        String serviceCategory = request.getParameter("serviceCategory");
        String servicePhoneNumber = request.getParameter("servicePhoneNumber");
        String serviceCity = request.getParameter("serviceCity");
        String serviceAddress = request.getParameter("serviceAddress");
        Double serviceValue = Double.parseDouble(request.getParameter("serviceValue"));



        ServiceController servController = new ServiceController();
        String result = servController.editService(serviceId, serviceName, 
                serviceDescription, serviceCategory, servicePhoneNumber, 
                serviceCity, serviceAddress, serviceValue);
        
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
