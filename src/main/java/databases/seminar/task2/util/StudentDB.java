package databases.seminar.task2.util;

import java.util.Random;

public class StudentDB {
    private static final Random RANDOM = new Random();

    private static final String[] names = {
            "Adam", "Michael", "Lola", "Anna", "Violetta", "Lion", "Freddy", "Lev", "Dmitriy",
            "Sergey", "Svetlana", "Naomi", "Alex", "Oleg", "Pavel", "Smith", "Omar", "Vlad",
            "Vadim", "Vyacheslav", "Inna", "Maria", "Sara", "Ann", "Chu", "Pu", "Mila",
            "Yakov", "Sam", "Masha", "Olga", "Ola", "Viola", "Vera", "Sonya", "Bella"
    };

    public static String getRandomName(){
        return names[RANDOM.nextInt(0, names.length)];
    }
}
