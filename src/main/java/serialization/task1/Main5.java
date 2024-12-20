package serialization.task1;

import java.io.IOException;
import java.io.Serializable;

public class Main5 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MyFCs myFCs = new MyFCs("Ivanov", "Ivan", "Ivanovich");
        Main3.serialObj(myFCs, "serMyFCs.txt");

        MyFCs myFCs1 = (MyFCs) Main3.deSerialObj("serMyFCs.txt");
        System.out.println(myFCs1);
    }

    @Serializzable
    static class MyFCs implements Serializable {
        private final String lName;
        private final String fName;
        private final String patronymic;


        public MyFCs(String fName, String lName, String patronymic){
            this.fName = fName;
            this.lName = lName;
            this.patronymic = patronymic;
        }

        @Override
        public String toString() {
            return String.format("%s %s.%s", fName,
                    lName.toUpperCase().charAt(0),
                    patronymic.toUpperCase().charAt(0));
        }
    }
}
