package homework.seminar3.person_serrialization;


import homework.seminar3.person_serrialization.service.PersonService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PersonMain {

    public static void main(String[] args) {
        List<Person> persons;

        File f = new File(PersonService.FILE_JSON);
        if(f.exists() && !f.isDirectory()){
            persons = PersonService.loadPersonFromFile(PersonService.FILE_JSON);
        }else{
            persons = preparePersons();
        }

        PersonService.savePersonToFile(PersonService.FILE_JSON, persons);
        PersonService.savePersonToFile(PersonService.FILE_BIN, persons);
        PersonService.savePersonToFile(PersonService.FILE_XML, persons);

        PersonService.showAllPersons(persons);
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Выберите действие");
            System.out.println("1. Добавить персону.");
            System.out.println("2. Удалить персону по имени.");
            System.out.println("3. Отобразить всех персон.");
            System.out.println("4. Выход.");

            String choice = scanner.nextLine();

            switch (choice){
                case "1" -> persons.add(PersonService.createNewPerson());
                case "2" -> {
                    System.out.println("Введите имя персоны, которую хотите удалить: ");
                    String name = new Scanner(System.in).nextLine();
                    System.out.println(PersonService.deletePersonByName(name, persons));
                }
                case "3" -> PersonService.showAllPersons(persons);
                case "4" -> PersonService.exit(scanner, persons);
                default -> System.out.println("Вы ввели неверную команду.");
            }

        }

    }


    private static List<Person> preparePersons(){
        return new ArrayList<>(List.of(
                new Person("Some", 19),
                new Person("One", 21),
                new Person("New", 22),
                new Person("Persona", 24),
                new Person("Grata", 26)
        ));
    }
}
