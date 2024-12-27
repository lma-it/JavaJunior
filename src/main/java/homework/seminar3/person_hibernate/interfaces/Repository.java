package homework.seminar3.person_hibernate.interfaces;

import homework.seminar3.person_hibernate.model.Person;

public interface Repository {
    void createPerson(Person person);
    void readFromPersons();
    void updatePerson(Person person);
    void deleteById(Long id);
    Person findById(Long id);


}