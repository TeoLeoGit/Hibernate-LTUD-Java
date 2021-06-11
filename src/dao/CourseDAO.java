package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.Course;
import util.HibernateUtil;

import java.util.List;

public class CourseDAO {
    public static List<Course> getAllCourse() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Course> courses = null;
        try {
            //Create query
            final String hql =  "select course from Course course";
            Query query = session.createQuery(hql);
            courses = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return courses;
    }

    public static boolean addCourse(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(course);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean deleteCourse(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Course course = CourseDAO.getCourseById(id);
        if (course == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            //Create query
            transaction = session.beginTransaction();
            session.delete(course);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }

    public static Course getCourseById(int id) {
        Course course = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            course = (Course) session.get(Course.class, id);
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return course;
    }
}
