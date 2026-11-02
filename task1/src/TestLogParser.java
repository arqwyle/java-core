import java.util.*;

public class TestLogParser {
    public static final List<Error> errors = new ArrayList<>();

    public static List<TestResult> parse(List<String> lines) {
        if (lines == null || lines.isEmpty()) {
            return List.of();
        }

        Map<String, TestResult> resultsMap = new LinkedHashMap<>();

        for (String line : lines) {
            if (line == null || line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\|");
            if (parts.length != 3) {
                errors.add(new Error(line, "Неверное количество разделителей"));
                continue;
            }

            String testName = parts[0].trim();
            String statusStr = parts[1].trim();
            String durationStr = parts[2].trim();

            if (!Status.contains(statusStr)) {
                errors.add(new Error(line, "Неизвестный статус " + statusStr));
                continue;
            }

            int duration;
            try {
                duration = Integer.parseUnsignedInt(durationStr);
            } catch (NumberFormatException e) {
                errors.add(new Error(line, "Неверная длительность " + durationStr));
                continue;
            }

            TestResult currentResult = new TestResult(testName, Status.valueOf(statusStr), duration);

            resultsMap.put(testName, currentResult);
        }

        return List.copyOf(resultsMap.values());
    }

    public static Optional<TestResult> firstFailed(List<TestResult> results) {
        for(TestResult result : results) {
            if (result.status() == Status.FAILED) {
                return Optional.of(result);
            }
        }
        return Optional.empty();
    }
}
