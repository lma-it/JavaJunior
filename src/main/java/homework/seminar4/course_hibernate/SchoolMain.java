package homework.seminar4.course_hibernate;

import ch.qos.logback.classic.Logger;
import homework.seminar4.course_hibernate.model.Course;
import homework.seminar4.course_hibernate.services.CourseService;
import homework.seminar4.course_hibernate.util.SessionService;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SchoolMain {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(SchoolMain.class);
    private static final CourseService service = new CourseService();
    public static void main(String[] args) {
        service.createAll(SessionService.getSession(), List.of(new Course("Русский язык", "144 часа"),
                new Course("Математика", "212 часов"),
                new Course("Информатика", "160 часов"),
                new Course("Английский язык", "72 часа"),
                new Course("Технология","32 часа"),
                new Course("Физкультура","32 часа")));


        service.create(SessionService.getSession(), new Course("География","24 часа"));
        service.create(SessionService.getSession(), new Course("Астрономия","12 часов"));
        service.create(SessionService.getSession(), new Course("Физика","64 часа"));
        service.create(SessionService.getSession(), new Course("Химия","64 часа"));
        service.create(SessionService.getSession(), new Course("Геометрия","124 часа"));
        service.create(SessionService.getSession(), new Course("Музыка","16 часов"));
        service.create(SessionService.getSession(), new Course("Биология","32 часа"));
        service.create(SessionService.getSession(), new Course("География","24 часа"));
        service.readAll(SessionService.getSession()).forEach(System.out::println);

        Course course = service.findById(SessionService.getSession(), 66L);
        System.out.printf("Обновляю данные объекта из базы данных: %s\n", course);
        course.setTitle("Изобразительное Искусство");
        course.setDuration("24 часа");
        service.update(SessionService.getSession(), course);
//
        service.readAll(SessionService.getSession()).forEach(course1 -> service.delete(SessionService.getSession(), course1.getId()));
        System.out.println("Проверяем базу данных на наличие элементов после удаления:");
        service.readAll(SessionService.getSession()).forEach(System.out::println);








    }
}
