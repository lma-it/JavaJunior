package serialization.task1;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Main3 {
    public static void main(String[] args) throws IOException {

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            list.add(Character.getName(i));
        }
        serialObj(list, "ser1.txt");

    }

    public static void serialObj(Object o, String file) throws IOException {
        Class<?> clazz = o.getClass();
        if(clazz.isAnnotationPresent(Serializzable.class)){
            Field[] fields = clazz.getDeclaredFields();
            for(Field field : fields){
                field.setAccessible(true);
            }
        }
        FileOutputStream fileInputStream = new FileOutputStream(file);
        ObjectOutputStream objectInputStream = new ObjectOutputStream(fileInputStream);
        objectInputStream.writeObject(o);
        objectInputStream.close();
    }

    public static Object deSerialObj(String file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return objectInputStream.readObject();
    }
}
