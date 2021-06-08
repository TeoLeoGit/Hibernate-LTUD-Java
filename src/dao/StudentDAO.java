package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
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
}
