package serialization.lesson.task1;

import java.io.IOException;
import java.util.ArrayList;

public class Main4 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<String> list = null;
        list = (ArrayList<String>) Main3.deSerialObj("serialization/database/ser1.txt");
        System.out.println(list);
    }
}
