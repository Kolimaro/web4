package DAO;

import model.DailyReport;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.DBHelper;

import java.util.List;

public class DailyReportDao {

    private SessionFactory sessionFactory;
    private static boolean isTablesReset = true;

    public DailyReportDao() {
        this.sessionFactory = DBHelper.getSessionFactory();
    }

    public List<DailyReport> getAllDailyReport() {
        Session session = sessionFactory.openSession();
        List<DailyReport> dailyReportList = session.createQuery("From DailyReport").list();
        session.close();
        return dailyReportList;
    }

    public void updateDailyReport(Long price) {
        if (isTablesReset) {
            addNewReport();
            isTablesReset = false;
        }
        DailyReport lastDailyReport = getAllDailyReport().get(getAllDailyReport().size() - 1);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        lastDailyReport.setEarnings(lastDailyReport.getEarnings() + price);
        lastDailyReport.setSoldCars(lastDailyReport.getSoldCars() + 1);
        session.update(lastDailyReport);
        transaction.commit();
        session.close();
    }

    public void addNewReport() {
        DailyReport report = new DailyReport(0L, 0L);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(report);
        transaction.commit();
        session.close();
    }

    public DailyReport getLastReport() {
        List<DailyReport> list = getAllDailyReport();
        return list.get(list.size() - 2);
    }

    public void clearDailyReports() {
        isTablesReset = true;
        Session session = sessionFactory.openSession();
        List<DailyReport> list = session.createQuery("From DailyReport").list();
        Transaction transaction = session.beginTransaction();
        for (DailyReport x : list) {
            session.delete(x);
        }
        transaction.commit();
        session.close();
    }
}
