package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
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
}
