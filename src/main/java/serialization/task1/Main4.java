package serialization.task1;

import java.io.IOException;
import java.util.ArrayList;

public class Main4 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<String> list = null;
        list = (ArrayList<String>) Main3.deSerialObj("ser1.txt");
        System.out.println(list);
    }
}
