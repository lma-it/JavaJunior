package serialization.seminar.interfaces;

public interface IOStreamable {
    void outputObject(Object o, String path);
    Object inputObject(String path);
}
