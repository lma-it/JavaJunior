package serialization.seminar.task1;

public class Program {

    public static void main(String[] args) {
        IOStreamClass ioStreamClass = new IOStreamClass();
        String path = "serialization/database/userdata.bin";
        UserData user = new UserData("Michael", 34, "superSecret");
        ioStreamClass.outputObject(user, path);
        System.out.println((UserData) ioStreamClass.inputObject(path));
    }
}
