package homework.seminar4.course_hibernate.services;

import homework.seminar4.course_hibernate.interfaces.CourseRepository;
import homework.seminar4.course_hibernate.model.Course;
import homework.seminar4.course_hibernate.util.SessionService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CourseService implements CourseRepository {
    private final Logger logger = (Logger) LoggerFactory.getLogger(CourseService.class);


    @Override
    public void create(Session session, Course item) {
        try(session){
            Transaction transaction = session.beginTransaction();
            session.save(item);
            saveInfoLogs(item, "добавлен", "create");
            transaction.commit();
        }catch (HibernateException e){
            saveErrorLogs(e, "добавить", "create");
        }

    }

    @Override
    public void createAll(Session session, List<Course> items) {
        try(session){
            Transaction transaction = session.beginTransaction();
            items.forEach(session::save);
            logger.info("Класс: {}, метод: {}: Успешно добавлены все {} объектов в базу данных.", this.getClass().getSimpleName(), "createAll", items.size());
            transaction.commit();
        }catch (HibernateException e){
            saveErrorLogs(e, "добавить", "createAll");
        }

    }

    @Override
    public List<Course> readAll(Session session) {
        try(session){
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Course";
            List<Course> courses =  session.createQuery(hql, Course.class).setCacheable(false).getResultList();
            courses.forEach(course -> saveInfoLogs(course, "извлечен", "readAll"));
            transaction.commit();
            return courses;
        }catch (HibernateException e){
            saveErrorLogs(e, "извлечь все", "readAll");
            return null;
        }

    }

    @Override
    public Course update(Session session, Course item) {
        try(session){
            Transaction transaction = session.beginTransaction();
            session.update(item);
            saveInfoLogs(item, "обновлен", "update");
            transaction.commit();
            return item;
        }catch (HibernateException e){
            saveErrorLogs(e, "обновить", "update");
            return null;
        }
    }

    @Override
    public void delete(Session session, Long id) {
        try(session){
            Transaction transaction = session.beginTransaction();
            saveInfoLogs(findById(SessionService.getSession(), id), "удален","delete");
            session.delete(findById(SessionService.getSession(), id));
            transaction.commit();
        }catch (HibernateException e){
            saveErrorLogs(e, "удалить","delete");
        }

    }

    @Override
    public Course findById(Session session, Long id) {
        try(session){
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Course c WHERE c.id = " + id.toString();
            Query<Course> query = session.createQuery(hql, Course.class);
            saveInfoLogs(query.getSingleResult(), "найден", "findById");
            transaction.commit();
            return query.getSingleResult();
        }catch (HibernateException e){
            saveErrorLogs(e, "найти","findById");
            return null;
        }
    }

    private void saveErrorLogs(Exception e, String action, String methodName){
        StringBuilder sb = new StringBuilder("StackTrace: ");
        for(StackTraceElement element : e.getStackTrace()){
            sb.append(element).append("\n");
        }
        logger.error("ERROR: Класс: {}, метод: {}. Ошибка при попытке {} объект в/из БД. Причина: {}.{}", this.getClass().getSimpleName(), methodName, action, e.getMessage(), sb);
    }

    private void saveInfoLogs(Course item, String action ,String methodName){
        logger.info("Класс: {}, метод: {}: Успешно {} объект {} в БД.", this.getClass().getSimpleName(), methodName, action, item);
    }
}
