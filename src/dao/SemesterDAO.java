package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.Semester;
import util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SemesterDAO {
    public static List<Semester> getAllSemester() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Semester> semesters = null;
        try {
            //Create query
            final String hql =  "select sem from Semester sem";
            Query query = session.createQuery(hql);
            semesters = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return semesters;
    }

    public static List<Semester> getSemestersByName(String semestername) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Semester> semesters = null;
        try {
            //Create query
            final String hql =  "from Semester sem where sem.semestername like :semestername";
            Query query = session.createQuery(hql);
            query.setParameter("semestername", "%" + semestername + "%");
            semesters = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return semesters;
    }

    public static boolean addSemester(Semester semester) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(semester);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }

    public static Semester getSemesterById (int id) {
        Semester semester = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            semester = (Semester) session.get(Semester.class, id);
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return semester;
    }

    public static boolean deleteSemester(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Semester semester = SemesterDAO.getSemesterById(id);
        if (semester == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            //Create query
            transaction = session.beginTransaction();
            session.delete(semester);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }

    public static List<Semester> getCurrentSemester() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Semester> semesters = null;
        try {
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
            Date getToday = java.util.Calendar.getInstance().getTime();
            String today = sdformat.format(getToday);
            final String hql =  "from Semester sem where day(sem.startdate) <= day(:today) and day(sem.enddate) >= day(:today)" +
                    "and month(sem.startdate) <= month(:today) and month(sem.enddate) >= month(:today)" +
                    "and year(sem.startdate) <= year(:today) and year(sem.enddate) >= year(:today)";
            Query query = session.createQuery(hql);
            query.setParameter("today", today);
            semesters = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return semesters;
    }
}
