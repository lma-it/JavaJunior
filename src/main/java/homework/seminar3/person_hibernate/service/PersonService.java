package homework.seminar3.person_hibernate.service;


import homework.seminar3.person_hibernate.model.Person;
import homework.seminar3.person_hibernate.repo_service.PersonRepository;

import java.time.LocalDate;
import java.util.Scanner;

public class PersonService {
    private static final PersonRepository repository = new PersonRepository();
    private static final Scanner scanner = new Scanner(System.in);

    public static void connection(){
        String choice = scanner.nextLine();

        switch (choice){
            case "1" -> createPerson();
            case "2" -> readFromPersons();
            case "3" -> updateById();
            case "4" -> deleteById();
            case "5" -> System.exit(0);
        }
    }


    private static void createPerson(){
        Person person = new Person();
        System.out.println("Введите имя: ");
        person.setName(PersonService.scanner.nextLine());
        System.out.println("Введите отчество: ");
        person.setLastName(PersonService.scanner.nextLine());
        System.out.println("Введите фамилию: ");
        person.setSurname(PersonService.scanner.nextLine());
        System.out.println("Введите дату рождения (формат YYYY-MM-DD): ");
        String date = PersonService.scanner.nextLine();
        person.setBirthDate(LocalDate.parse(date));
        person.setAge(LocalDate.now().getYear() - person.getBirthDate().getYear());
        System.out.println("Введите телефон: ");
        person.setPhone(PersonService.scanner.nextLine());
        System.out.println("Введите email: ");
        person.setEmail(PersonService.scanner.nextLine());
        repository.createPerson(person);
    }

    private static void deleteById(){
        repository.readFromPersons();
        System.out.println("Введите id персоны, которую хотите удалить: ");
        Long id = PersonService.scanner.nextLong();
        repository.deleteById(id);
    }

    private static void readFromPersons(){
        repository.readFromPersons();
    }

    private static void updateById(){
        System.out.println("Введите id персоны, данные которой хотите изменить: ");
        Long id = PersonService.scanner.nextLong();
        Person person = repository.findById(id);
        if(person != null){

            while (true){
                System.out.println("Введите номер параметра, который хотите обновить.");
                System.out.println("1. Обновить имя.");
                System.out.println("2. Обновить фамилию.");
                System.out.println("3. Обновить телефон.");
                System.out.println("4. Обновить email.");
                System.out.println("5. Выход.");

                String choice = scanner.nextLine();

                switch (choice){
                    case "1" -> {
                        System.out.println("Текущее имя персоны: " + person.getName());
                        System.out.println("Введите новое имя: ");
                        String name = scanner.nextLine();
                        if(!name.isEmpty()){
                            person.setName(name);
                        }else {
                            System.out.println("Имя не может быть пустым.");
                        }
                        repository.updatePerson(person);
                    }
                    case "2" -> {
                        System.out.println("Текущая фамилия персоны: " + person.getSurname());
                        System.out.println("Введите новую фамилию: ");
                        String surname = scanner.nextLine();
                        if(!surname.isEmpty()){
                            person.setSurname(surname);
                        }else {
                            System.out.println("Фамилия не может быть пустой.");
                        }
                        repository.updatePerson(person);
                    }
                    case "3" -> {
                        System.out.println("Текущий телефон персоны: " + person.getPhone());
                        System.out.println("Введите новый телефон (может отсутствовать, но должен быть уникальным, если есть): ");
                        String phone = scanner.nextLine();
                        person.setPhone(phone);
                        repository.updatePerson(person);
                    }
                    case "4" -> {
                        System.out.println("Текущий email персоны: " + person.getEmail());
                        System.out.println("Введите новый email (может отсутствовать, но должен быть уникальным, если есть): ");
                        String email = scanner.nextLine();
                        person.setEmail(email);
                        repository.updatePerson(person);
                    }
                    case "5" -> {
                       return;
                    }
                    default -> System.out.println("Введена неверная команда.");
                }
            }
        }
        System.out.println("Введен не правильный Id.");
    }


}
