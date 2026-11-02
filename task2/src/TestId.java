import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Если поставить CLASS или SOURCE, то аннотации не будет в рантайме
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestId {
    String value();   // номер тест-кейса, например "TC-101"
    int order();      // порядок выполнения в прогоне
}
