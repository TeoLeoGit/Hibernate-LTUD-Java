package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.Student;
import util.HibernateUtil;

import java.util.List;

public class StudentDAO {
    public static List<Student> getStudentsByUsername(String username) {
        //open session
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = null;
        try {
            //Create query
            final String hql =  "from Student st where st.username = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            students = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return students;
    }

    public static Student getStudentById (int id) {
        Student std = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            std = (Student) session.get(Student.class, id);
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return std;
    }

    public static boolean updateStudentAccount(Student updateStd) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (StudentDAO.getStudentById(updateStd.getId()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(updateStd);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }

    public static List<Student> getStudentsByStudentId(int studentId) {
        //open session
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = null;
        try {
            //Create query
            final String hql =  "from Student st where st.studentId = :studentId";
            Query query = session.createQuery(hql);
            query.setParameter("studentId", studentId);
            students = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return students;
    }

    public static List<Student> getAllStudent() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = null;
        try {
            //Create query
            final String hql =  "select std from Student std";
            Query query = session.createQuery(hql);
            students = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return students;
    }

    public static boolean addStudent(Student std) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(std);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }

}
