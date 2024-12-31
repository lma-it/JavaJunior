package databases.seminar.task2;

import ch.qos.logback.classic.Logger;
import databases.seminar.task2.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class Program {

    public static final Logger logger = (Logger) LoggerFactory.getLogger(Program.class);

    public static void main(String[] args) {

        try(SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory()){

            Session session = sessionFactory.openSession();

            try (session) {
                logger.info("Успешно создана Session. Статистика: {}", session.getStatistics().toString());
                session.beginTransaction();

                Student student = Student.createNewStudent();
                session.save(student);
                logger.info("Транзакция произведена успешно. Добавлен новый объект: {}", student);

                Student retrievedStudent = session.get(Student.class, student.getId());
                logger.info("Объект: {} успешно получен из базы данных.", retrievedStudent);

                // Используем такую схему для получения всех объектов из базы данных.
                CriteriaBuilder cb = session.getCriteriaBuilder();
                CriteriaQuery<Student> cq = cb.createQuery(Student.class);
                Root<Student> root = cq.from(Student.class);
                cq.select(root);
                System.out.println("Вывод всех данных из базы.");
                session.createQuery(cq).setCacheable(false).getResultList().forEach(student1 -> {
                    logger.info("Объект: {} успешно получен из базы данных.", student1);
                    System.out.println(student1);
                });

                System.out.println();
                System.out.println("Вывод только тех данных чей id кратный 2:");

                // Схема для выбора из базы данных по специфическим параметрам для чисел, так как функция MOD работает только с числами.
                String hql = "FROM Student s WHERE MOD(s.id, 2) = 0";
                session.createQuery(hql, Student.class).setCacheable(false).getResultList().forEach(student1 -> {
                    logger.info("Объект: {}, чей id кратный 2, успешно получен из базы данных.", student1);
                    System.out.println(student1);
                });


                System.out.println();
                System.out.println("Вывод только тех данных, чей id кратный 3:");
                String hql1 = "FROM Student s WHERE MOD(s.id, 3) = 0";
                session.createQuery(hql1, Student.class).setCacheable(false).getResultList().forEach(student3 -> {
                    logger.info("Объект: {}, чей id кратный 3, успешно получен из базы данных.", student3);
                    System.out.println(student3);
                });

                System.out.println();
                System.out.println("Выбор только тех значений из базы, чей параметр name = \"Lion\"");

                // Схема запроса для писка из базы данных на совпадения по имени.
                // Так же можно заменить s.name = 'Name' на s.name LIKE 'Name%'
                String hql2 = "FROM Student s WHERE s.name = 'Lion'";
                session.createQuery(hql2, Student.class).setCacheable(false).getResultList().forEach(student1 -> {
                    logger.info("Получен объект из базы данных: {}, с именем {}", student1, student1.getName());
                    System.out.println(student1);
                });

                // Запрос на удаление из базы данных осуществляется именно так. Сначала мы получаем данные (все или по определенным критериям),
                // затем проходимся в цикле с этими данными и вызываем метод session.delete(object), для удаления из базы данных.
                String hql3 = "FROM Student s WHERE MOD(s.id, 5) = 0";
                session.createQuery(hql3, Student.class).setCacheable(false).getResultList().forEach(student1 -> {
                    System.out.printf("Удален объект %s из базы данных\n", student1);
                    logger.info("Удален объект: {} из базы данных.", student1);
                    session.delete(student1);
                });


                session.getTransaction().commit();

            }catch (HibernateException ex){
                logger.error("ERROR: Ошибка возникла при попытке транзакции. Причина: {}", ex.getMessage());
            }
        }catch (Exception e){
            StringBuilder sb = new StringBuilder("StackTrace: ");
            for (StackTraceElement element : e.getStackTrace()){
                sb.append(element.toString()).append("\n");
            }
            logger.error("ERROR: Возникла ошибка при создании Session. Причина: {}.\n{}", e.getMessage(), sb);
        }
    }
}
