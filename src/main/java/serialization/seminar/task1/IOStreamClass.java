package serialization.seminar.task1;

import serialization.seminar.interfaces.IOStreamable;

import java.io.*;

public class IOStreamClass implements IOStreamable {

    @Override
    public void outputObject(Object o, String path) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(o);
            System.out.printf("Объект %s сериализован.\n", o.getClass().getSimpleName());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Object inputObject(String path) {

        try(FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            Object o = objectInputStream.readObject();
            System.out.printf("Объект %s десериализован.\n", o.getClass().getSimpleName());
            return o;
        }catch (IOException e){
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
