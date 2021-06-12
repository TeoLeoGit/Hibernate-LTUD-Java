package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.Courseregistrationsession;
import util.HibernateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CourseRegistrationSessionDAO {
    public static List<Courseregistrationsession> getAllSession() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Courseregistrationsession> sessions = null;
        try {
            //Create query
            final String hql =  "select crs from Courseregistrationsession crs";
            Query query = session.createQuery(hql);
            sessions = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return sessions;
    }

    public static boolean addSession(Courseregistrationsession addSession) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(addSession);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }

    public static List<Courseregistrationsession> getOpeningSession() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Courseregistrationsession> sessions = null;
        try {
            //Create query
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
            Date getToday = java.util.Calendar.getInstance().getTime();
            String today = sdformat.format(getToday);
            final String hql =  "from Courseregistrationsession crs where day(crs.startdate) <= day(:today) and day(crs.enddate) >= day(:today)" +
                    "and month(crs.startdate) <= month(:today) and month(crs.enddate) >= month(:today)" +
                    "and year(crs.startdate) <= year(:today) and year(crs.enddate) >= year(:today)";
            Query query = session.createQuery(hql);
            query.setParameter("today", today);
            sessions = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return sessions;
    }
}
