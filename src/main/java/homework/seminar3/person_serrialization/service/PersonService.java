package homework.seminar3.person_serrialization.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import homework.seminar3.person_serrialization.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonService {

    public static final String FILE_JSON = "src/main/java/homework/seminar3/person_serrialization/personDB/person.json";
    public static final String FILE_BIN = "src/main/java/homework/seminar3/person_serrialization/personDB/person.bin";
    public static final String FILE_XML = "src/main/java/homework/seminar3/person_serrialization/personDB/person.xml";

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();



    public static void savePersonToFile(String fileName, List<Person> persons) {
        try {
            if (fileName.endsWith(".json")) {
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(fileName), persons);
            } else if (fileName.endsWith(".bin")) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                    oos.writeObject(persons);
                }
            } else if (fileName.endsWith(".xml")) {
                xmlMapper.writeValue(new File(fileName), persons);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Person> loadPersonFromFile(String fileName) {
        List<Person> persons = new ArrayList<>();

        File file = new File(fileName);
        if (file.exists()) {
            try {
                if (fileName.endsWith(".json")) {
                    persons = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Person.class));
                } else if (fileName.endsWith(".bin")) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        persons = (List<Person>) ois.readObject();
                    }
                } else if (fileName.endsWith(".xml")) {
                    persons = xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(List.class, Person.class));
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        return persons;
    }

    public static void exit(Scanner scanner, List<Person> persons){
        savePersonToFile(FILE_JSON, persons);
        savePersonToFile(FILE_BIN, persons);
        savePersonToFile(FILE_XML, persons);
        System.out.println("Список персон сохранен.");
        scanner.close();
        System.exit(0);
    }

    public static void showAllPersons(List<Person> persons){
        for (Person person : persons){
            System.out.println(person);
        }
    }

    public static Person createNewPerson() {
        Person person = new Person();
        System.out.println("Введите имя новой персоны: ");
        person.setName(new Scanner(System.in).nextLine());
        System.out.println("Введите возраст новой персоны: ");
        person.setAge(Integer.parseInt(new Scanner(System.in).nextLine()));
        return person;
    }

    public static String deletePersonByName(String name, List<Person> persons){
        for (int i = 0; i < persons.size(); i++){
            if(persons.get(i).getName().equals(name)){
                Person person = persons.get(i);
                persons.remove(i);
                return person.toString();
            }
        }
        return "Персоны с таким именем нет в Базе Данных.";
    }
}
