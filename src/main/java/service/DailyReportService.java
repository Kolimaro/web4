package service;

import DAO.DailyReportDao;
import model.Car;
import model.DailyReport;

import java.util.List;

public class DailyReportService {

    private DailyReportDao dao;

    public DailyReportService() {
        dao = new DailyReportDao();
    }

    public List<DailyReport> getAllDailyReports() {
        return dao.getAllDailyReport();
    }

    public DailyReport getLastReport() {
        return dao.getLastReport();
    }

    public void updateDailyReport(Car car) {
        DailyReport dailyReport = getLastReport();
        if (dailyReport == null) {
            addNewReport(car.getPrice(), 1L);
        } else {
            dailyReport.setEarnings(dailyReport.getEarnings() + car.getPrice());
            dailyReport.setSoldCars(dailyReport.getSoldCars() + 1);
            dao.updateDailyReport(dailyReport);
        }
    }

    public void addNewReport(Long price, Long count) {
        dao.addNewReport(price, count);
    }

    public void clearDailyReports() {
        dao.clearDailyReports();
    }

    public DailyReport getYesterdayReport() {
        return dao.getYesterdayReport();
    }

}
