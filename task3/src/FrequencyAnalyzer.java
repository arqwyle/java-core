import java.util.HashMap;
import java.util.Map;

public class FrequencyAnalyzer {
    public static <T> Map<T, Long> frequency(T[] items) {
        Map<T, Long> frequencyMap = new HashMap<>();

        for(T i : items) {
            frequencyMap.merge(i, 1L, Long::sum);
        }

        return frequencyMap;
    }
}
