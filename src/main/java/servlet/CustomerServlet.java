package servlet;

import com.google.gson.Gson;
import model.Car;
import service.CarService;
import service.DailyReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerServlet extends HttpServlet {
    CarService carService = new CarService();
    DailyReportService dailyReportService = new DailyReportService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String json = gson.toJson(carService.getAllCars());
        resp.getWriter().write(json);
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Car soldCar = carService.buyCar(req.getParameter("brand"), req.getParameter("model"), req.getParameter("licensePlate"));
        dailyReportService.updateDailyReport(soldCar.getPrice());
        carService.sellCar(soldCar);
        Gson gson = new Gson();
        String json = gson.toJson(soldCar);
        resp.getWriter().write(json);
        resp.setStatus(200);
    }
}
