package serialization.seminar.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static serialization.seminar.task2.ToDoListApp.*;

public class Program {

    public static void main(String[] args) {
        List<ToDo> tasks;
        File f = new File(FILE_JSON);
        if(f.exists() && !f.isDirectory()){
            tasks = loadTasksFromFile(FILE_JSON);
        }else{
            tasks = prepareTasks();
        }
        saveTasksToFile(FILE_JSON, tasks);
        saveTasksToFile(FILE_BIN, tasks);
        saveTasksToFile(FILE_XML, tasks);

        displayTasks(tasks);

        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("Выберите действие: ");
            System.out.println("1. Добавить новую задачу.");
            System.out.println("2. Отметить задачу как выполненную.");
            System.out.println("3. Отобразить список всех задач.");
            System.out.println("4. Выйти.");

            String choice = scanner.nextLine();

            switch (choice){
                case "1" -> addNewTask(scanner, tasks);
                case "2" -> markTaskASDone(scanner, tasks);
                case "3" -> displayTasks(tasks);
                case "4" -> exit(scanner, tasks);
                default -> System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
    }

    static List<ToDo> prepareTasks(){
        ArrayList<ToDo> list = new ArrayList<>();
        list.add(new ToDo("Сходить в магазин за продуктами"));
        list.add(new ToDo("Погулять с собакой"));
        list.add(new ToDo("Заменить лампочку"));
        return list;
    }
}
