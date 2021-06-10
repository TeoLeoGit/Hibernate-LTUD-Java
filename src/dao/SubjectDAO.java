package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.Ministry;
import pojo.Subject;
import util.HibernateUtil;

import java.util.List;

public class SubjectDAO {
    public static List<Subject> getAllSubject() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Subject> subjects = null;
        try {
            //Create query
            final String hql =  "select sj from Subject sj";
            Query query = session.createQuery(hql);
            subjects = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return subjects;
    }

    public static List<Subject> getSubjectsByName(String subjectname) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Subject> subjects = null;
        try {
            //Create query
            final String hql =  "from Subject sj where sj.subjectname like :subjectname";
            Query query = session.createQuery(hql);
            query.setParameter("subjectname", "%" + subjectname + "%");
            subjects = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return subjects;
    }

    public static boolean addSubject(Subject subject) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(subject);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }

    public static Subject getSubjectById (int id) {
        Subject subject = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            subject = (Subject) session.get(Subject.class, id);
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return subject;
    }

    public static boolean updateSubject(Subject updateSubj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (SubjectDAO.getSubjectById(updateSubj.getId()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(updateSubj);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean deleteSubject(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Subject subject = SubjectDAO.getSubjectById(id);
        if (subject == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            //Create query
            transaction = session.beginTransaction();
            session.delete(subject);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }
}
