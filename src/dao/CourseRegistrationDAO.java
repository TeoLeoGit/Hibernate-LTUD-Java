package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.Course;

import pojo.Student;
import pojo.Coursesregistration;
import util.HibernateUtil;

import java.util.List;

public class CourseRegistrationDAO {
    public static List<Coursesregistration> getCourseRegistrationById(Student student, Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Coursesregistration> courseregistrations = null;
        try {
            //Create query
            final String hql =  "from Coursesregistration cr where cr.student = :student and cr.course = :course";
            Query query = session.createQuery(hql);
            query.setParameter("student", student);
            query.setParameter("course", course);
            courseregistrations = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return courseregistrations;
    }

    public static boolean addCourseRegistration(Coursesregistration cr) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(cr);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }

    public static List<Coursesregistration> getCrByStudent(Student student) {
        //open session
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Coursesregistration> courseregistrations = null;
        try {
            //Create query
            final String hql =  "from Coursesregistration cr where cr.student = :student";
            Query query = session.createQuery(hql);
            query.setParameter("student", student);
            courseregistrations = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return courseregistrations;
    }

    public static boolean deleteCrs(Student Student, Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Coursesregistration> crs = CourseRegistrationDAO.getCourseRegistrationById(Student, course);
        if (crs.isEmpty()) {
            return false;
        }
        Transaction transaction = null;
        try {
            //Create query

            transaction = session.beginTransaction();
            session.delete(crs.get(0));
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }

}
