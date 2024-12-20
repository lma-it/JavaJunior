package serialization.task1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 * Маркерная аннотация, которая нужна для того чтоб можно было сериализовать объекты даже с
 * приватными полями.
 * */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Serializzable {
}
