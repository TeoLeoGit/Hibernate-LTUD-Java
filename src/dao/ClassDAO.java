package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.Class;
import util.HibernateUtil;

import java.util.List;

public class ClassDAO {
    public static Class getClassById (int id) {
        Class cl = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            cl = (Class) session.get(Class.class, id);
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return cl;
    }

    public static boolean deleteClass(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Class cl = ClassDAO.getClassById(id);
        if (cl == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            //Create query
            transaction = session.beginTransaction();
            session.delete(cl);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }

    public static List<Class> getAllClasses() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Class> classes = null;
        try {
            //Create query
            final String hql =  "select cl from Class cl";
            Query query = session.createQuery(hql);
            classes = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return classes;
    }

    public static List<Class> getClassesByName(String classname) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Class> classes = null;
        try {
            //Create query
            final String hql =  "from Class cl where cl.classname like :classname";
            Query query = session.createQuery(hql);
            query.setParameter("classname", "%" + classname + "%");
            classes = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return classes;
    }

    public static boolean addClass(Class cl) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(cl);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }

    public static List<Class> getClassHasName(String classname) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Class> classes = null;
        try {
            //Create query
            final String hql =  "from Class cl where cl.classname = :classname";
            Query query = session.createQuery(hql);
            query.setParameter("classname",  classname);
            classes = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return classes;
    }

    public static boolean updateClass(Class updateClass) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (ClassDAO.getClassById(updateClass.getClassId()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(updateClass);
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
