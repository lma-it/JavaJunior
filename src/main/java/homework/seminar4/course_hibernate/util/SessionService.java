package homework.seminar4.course_hibernate.util;


import homework.seminar4.course_hibernate.model.Course;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionService {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Course.class)
                    .buildSessionFactory();

        }catch (HibernateException e){
            throw new HibernateException(e.getMessage());
        }
    }


    public static Session getSession(){
        return sessionFactory.openSession();
    }

    public static void shutdown(){
        if(sessionFactory != null){
            sessionFactory.close();
        }
    }

}
