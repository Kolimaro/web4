package service;

import DAO.DailyReportDao;
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

    public void updateDailyReport(Long price) {
        dao.updateDailyReport(price);
    }

    public void addNewReport() {
        dao.addNewReport();
    }

    public void clearDailyReports() {
        dao.clearDailyReports();
    }

}
