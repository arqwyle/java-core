package app;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App {
    static String greet(String name) {
        if(name == null) {
            return "null";
        }
        return "Привет, " + name;
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        try (InputStream input = App.class.getClassLoader().getResourceAsStream("app.properties")) {
            if (input == null) {
                return;
            }

            properties.load(input);

            String name = properties.getProperty("student.name");

            System.out.println(greet(name));

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}