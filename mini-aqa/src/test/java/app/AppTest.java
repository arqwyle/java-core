package app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    void CorrectName() {
        String s = App.greet("Correct");
        Assertions.assertEquals("Привет, Correct", s);
    }

    @Test
    void EmptyName() {
        String s = App.greet("");
        Assertions.assertEquals("Привет, ", s);
    }

    @Test
    void NullName() {
        String s = App.greet(null);
        Assertions.assertEquals("null", s);
    }
}