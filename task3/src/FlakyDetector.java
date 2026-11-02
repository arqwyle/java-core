import java.util.*;

public class FlakyDetector {
    public static Set<String> failed(List<String> run) {
        return filter(run, "FAILED");
    }

    public static Set<String> passed(List<String> run) {
        return filter(run, "PASSED");
    }

    private static Set<String> filter(List<String> run, String status) {
        Set<String> passed = new HashSet<>();
        for (String s : run) {
            String[] parts = s.split("\\|");
            if(Objects.equals(parts[1], status)) {
                passed.add(parts[0]);
            }
        }
        return passed;
    }

    public static List<String> flaky(List<String> run1, List<String> run2) {
        List<TestResult> run1List = toResultList(run1);
        List<TestResult> run2List = toResultList(run2);

        run1List.retainAll(run2List);

        List<String> resultNames = new ArrayList<>();
        for (TestResult tr : run1List) {
            resultNames.add(tr.name());
        }
        return resultNames;
    }

    private static List<TestResult> toResultList(List<String> run) {
        List<TestResult> list = new ArrayList<>();
        for (String s : run) {
            String[] parts = s.split("\\|");
            list.add(new TestResult(parts[0], parts[1]));
        }
        return list;
    }

    public static Map<String, Long> statusSummary(List<String> run) {
        Map<String, Long> summary = new HashMap<>();

        for(String s : run) {
            if(s.contains("PASSED")) {
                summary.merge("PASSED", 1L, Long::sum);
            }

            else {
                summary.merge("FAILED", 1L, Long::sum);
            }
        }

        return summary;
    }
}
