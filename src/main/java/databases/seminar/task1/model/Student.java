package databases.seminar.task1.model;

import databases.seminar.task1.util.StudentDB;

import java.util.Random;

public class Student {

    private int id;
    private String name;
    private int age;

    public Student(int id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Student(String name, int age){
        this.name = name;
        this.age = age;
    }

    public Student(){}


    public static Student createNewStudent(){
        return new Student(updateName(), updateAge());
    }

    public static int updateAge(){
        return new Random().nextInt(20, 29);
    }

    public static String updateName(){
        return StudentDB.getRandomName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Id: %s, Имя: %s, Возраст: %s", id, name, age);
    }
}
