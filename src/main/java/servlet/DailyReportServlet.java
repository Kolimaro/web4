package servlet;

import com.google.gson.Gson;
import service.CarService;
import service.DailyReportService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DailyReportServlet extends HttpServlet {
    DailyReportService dailyReportService = new DailyReportService();
    CarService carService = new CarService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String json;
        if (req.getPathInfo().contains("all")) {
            json = gson.toJson(dailyReportService.getAllDailyReports());
            resp.getWriter().write(json);
            resp.setStatus(200);
        } else if (req.getPathInfo().contains("last")) {
            json = gson.toJson(dailyReportService.getLastReport());
            resp.getWriter().write(json);
            resp.setStatus(200);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dailyReportService.clearDailyReports();
        carService.clearCarList();
    }
}
