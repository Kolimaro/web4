package servlet;

import model.Car;
import service.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProducerServlet extends HttpServlet {
    CarService carService = new CarService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (carService.addCar(new Car(req.getParameter("brand"), req.getParameter("model"),
                req.getParameter("licensePlate"), Long.parseLong(req.getParameter("price"))))) {
            resp.setStatus(200);
        } else {
            resp.setStatus(430);
        }
    }
}
