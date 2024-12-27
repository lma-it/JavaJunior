package homework.seminar3.person_hibernate.repo_service;

import ch.qos.logback.classic.Logger;
import homework.seminar3.person_hibernate.model.Person;
import homework.seminar3.person_hibernate.interfaces.Repository;
import homework.seminar3.person_hibernate.util.Connector;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PersonRepository implements Repository {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(PersonRepository.class);
    Connector connector = new Connector();



    @Override
    public void createPerson(Person person) {
        try (Session session = connector.getSession()){
            session.beginTransaction();
            session.save(person);
            logger.info("Сохранение персоны:{}", person);
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error("Ошибка при сохранении в базу данных: {}",e.getMessage());
        }
    }

    @Override
    public void readFromPersons() {
        try(Session session = connector.getSession()){
            String hql = "FROM Person";
            List<Person> persons = session.createQuery(hql, Person.class).getResultList();
            Runnable runnable = () -> persons.forEach(person -> logger.info("Получили персону:{}", person));
            new Thread(runnable).start();
            persons.forEach(System.out::println);
        }catch (Exception e){
            logger.error("Ошибка при чтении из базы данных: {}", e.getMessage());
        }
    }

    @Override
    public void updatePerson(Person person) {
        try(Session session = connector.getSession()){
            logger.info("Измененный объект:{}", person.toString());
            session.beginTransaction();
            session.update(person);
            session.getTransaction().commit();
        }catch (Exception e){
            logger.error("Ошибка при обновлении в базу данных: {}", e.getMessage());
        }

    }

    @Override
    public void deleteById(Long id) {
        try(Session session = connector.getSession()){
            Transaction t = session.beginTransaction();
            List<Person> persons = session.createQuery("FROM Person", Person.class).getResultList();
            if(persons.stream().findFirst().filter(p -> p.getId().equals(id)).isPresent()){
                Person person = persons.stream().findFirst().filter(p -> p.getId().equals(id)).get();
                logger.info("Удаляем персону по id {}: {}", id, person);
                session.delete(person);
            }
            t.commit();
        }catch (Exception e){
            logger.error("Ошибка при удалении из базы данных: {}", e.getMessage());
        }
    }

    @Override
    public Person findById(Long id) {
        try(Session session = connector.getSession()){
            String hql = "FROM Person p WHERE p.id = :id";
            Query<Person> query = session.createQuery(hql, Person.class);
            query.setParameter("id", id);
            logger.info("Выполняем запрос: {}", hql);
            return query.getSingleResult();
        }catch (Exception e){
            logger.error("Ошибка при получении из базы данных по id: {}", e.getMessage());
            return null;
        }
    }
}
