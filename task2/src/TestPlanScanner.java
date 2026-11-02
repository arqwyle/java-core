import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestPlanScanner {
    public static void buildPlan(Class<?> clazz) {
        List<TestStep> plan = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            TestId annotation = method.getAnnotation(TestId.class);
            if (annotation != null) {
                plan.add(new TestStep(annotation.value(), method.getName(), annotation.order()));
            }
        }

        System.out.println(clazz.getName() + ":");

        if(plan.isEmpty()) {
            System.out.println("\tКейсов нет");
            return;
        }

        plan.sort(Comparator.comparingInt(TestStep::order));

        for (TestStep step : plan) {
            System.out.println("\t" + step);
        }
    }
}
