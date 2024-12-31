package homework.seminar4.course_hibernate.interfaces;

import homework.seminar4.course_hibernate.model.Course;
import org.hibernate.Session;

import java.util.List;
public interface Repository<T, Id> {
    void create(Session session, T item);
    void createAll(Session session, List<T> items);
    List<T> readAll(Session session);
    Course update(Session session, T item);
    void delete(Session session, Id id);
    T findById(Session session, Id id);
}
