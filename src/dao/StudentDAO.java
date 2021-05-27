package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.Student;
import util.HibernateUtil;

import java.util.List;

public class StudentDAO {
    public static List<Student> getAllStudent() {
        //open session
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> accounts = null;
        try {
            //Create query
            final String hql =  "select st from Student st";
            Query query = session.createQuery(hql);

            accounts = query.list();
        } catch (HibernateException e){
            System.err.println(e);
        } finally {
            session.close();
        }

        return accounts;
    }
}
