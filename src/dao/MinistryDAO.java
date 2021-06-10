package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import pojo.Ministry;
import util.HibernateUtil;

import java.util.List;
public class MinistryDAO {
    public static List<Ministry> getMinistriesByUsername(String username) {
        //open session
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Ministry> ministries = null;
        try {
            //Create query
            final String hql =  "from Ministry mnt where mnt.username = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username);
            ministries = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return ministries;
    }

    public static Ministry getMinistryById (int id) {
        Ministry mnt = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            mnt = (Ministry) session.get(Ministry.class, id);
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return mnt;
    }

    public static boolean updateMinistryAccount(Ministry updateMnt) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (MinistryDAO.getMinistryById(updateMnt.getId()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(updateMnt);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }

    public static List<Ministry> getMinistriesByFirstname(String firstname) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Ministry> ministries = null;
        try {
            //Create query
            final String hql =  "from Ministry mnt where mnt.firstname = :firstname";
            Query query = session.createQuery(hql);
            query.setParameter("firstname", firstname);
            ministries = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return ministries;
    }

    public static List<Ministry> getAllMinistries() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Ministry> ministries = null;
        try {
            //Create query
            final String hql =  "select mnt from Ministry mnt";
            Query query = session.createQuery(hql);
            ministries = query.list();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return ministries;
    }

    public static boolean deleteMinistry(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Ministry mnt = MinistryDAO.getMinistryById(id);
        if (mnt == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            //Create query
            transaction = session.beginTransaction();
            session.delete(mnt);
            transaction.commit();
        } catch (HibernateException e) {
            System.err.println(e);
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean addMinistry(Ministry mnt) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(mnt);
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
