package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;
import pojo.Account;
import java.util.List;

public class AccountDAO {
    public static List<Account> getAllAccount() {
        //open session
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Account> accounts = null;
        try {
            //Create query
            final String hql =  "select ac from Account ac";
            Query query = session.createQuery(hql);

            accounts = query.list();
        } catch (HibernateException e){
            System.err.println(e);
        } finally {
            session.close();
        }
        return accounts;
    }
    public static List<Account> getAccountsByUsername(String needle) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Account> accounts = null;
        try {
            //Create query
            final String hql =  "FROM Account ac WHERE ac.username = \'" + needle + "\'";
            Query query = session.createQuery(hql);

            accounts = query.list();
        } catch (HibernateException e){
            System.err.println(e);
        } finally {
            session.close();
        }
        return accounts;
    }

    public static Account getAccountByUsername(String username) {
        Account needle = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            //Create query
            needle = (Account) session.get(Account.class, username);
        } catch (HibernateException e){
            System.err.println(e);
        } finally {
            session.close();
        }
        return needle;
    }
}
