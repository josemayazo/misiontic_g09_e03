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

@WebServlet(name = "ServletCreateService", urlPatterns = {"/ServletCreateService"})
@MultipartConfig
public class ServletCreateService extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get params from form-data
        int userId = Integer.parseInt(request.getParameter("userId"));
        String serviceName = request.getParameter("serviceName");
        String serviceDesc = request.getParameter("serviceDescription");
        String serviceCat = request.getParameter("serviceCategory");
        String servicePhoneNumber = request.getParameter("servicePhoneNumber");
        String serviceCity = request.getParameter("serviceCity");
        String serviceAddress = request.getParameter("serviceAddress");
        Double serviceValue = Double.parseDouble(request.getParameter("serviceValue"));

        ServiceController serviceController = new ServiceController();
        String result = serviceController.createService(userId, serviceName, serviceDesc,
                serviceCat, servicePhoneNumber, serviceCity, serviceAddress, serviceValue);

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
