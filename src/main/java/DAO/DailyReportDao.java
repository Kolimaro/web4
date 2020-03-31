package DAO;

import model.DailyReport;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.DBHelper;

import java.util.List;

public class DailyReportDao {

    private SessionFactory sessionFactory;

    public DailyReportDao() {
        this.sessionFactory = DBHelper.getSessionFactory();
    }

    public List<DailyReport> getAllDailyReport() {
        Session session = sessionFactory.openSession();
        Query<DailyReport> query = session.createQuery("SELECT dr FROM DailyReport dr", DailyReport.class);
        List<DailyReport> dailyReportList = query.getResultList();
        session.close();
        return dailyReportList;
    }

    public void updateDailyReport(DailyReport dailyReport) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(dailyReport);
        transaction.commit();
        session.close();
    }

    public void addNewReport(Long price, Long count) {
        DailyReport report = new DailyReport(price, count);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(report);
        transaction.commit();
        session.close();
    }

    public DailyReport getLastReport() {
        Session session = sessionFactory.openSession();
        Query<Long> query = session.createQuery("SELECT MAX (dr.id) FROM DailyReport dr", Long.class);
        Long maxId = query.getSingleResult();
        if (maxId == null) {
            return null;
        }
        DailyReport dailyReport = session.get(DailyReport.class, maxId);
        session.close();
        return dailyReport;
    }

    public DailyReport getYesterdayReport() {
        Session session = sessionFactory.openSession();
        Query<Long> query = session.createQuery("SELECT MAX (dr.id) FROM DailyReport dr", Long.class);
        Long maxId = query.getSingleResult();
        DailyReport dailyReport = session.get(DailyReport.class, maxId - 1);
        session.close();
        return dailyReport;
    }

    public void clearDailyReports() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM DailyReport").executeUpdate();
        transaction.commit();
        session.close();
    }
}
