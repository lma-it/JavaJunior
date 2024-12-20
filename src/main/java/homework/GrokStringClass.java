package homework;

import java.lang.reflect.Method;

public class GrokStringClass {
    public static void main(String[] args) {
        System.out.println(showAllStringClassMethods(String.class));
    }


    private static String showAllStringClassMethods(Class<?> clazz){
        StringBuilder stringMethods = new StringBuilder();

        Method[] methods = clazz.getDeclaredMethods();
        for(Method method : methods){
            stringMethods.append("Метод: ").append(method.toString()).append("\n");
        }

        return stringMethods.toString();
    }
}
