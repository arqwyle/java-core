import java.util.HashSet;
import java.util.Set;

public enum Status { PASSED, FAILED, SKIPPED;
    private static final Set<String> NAMES = new HashSet<>();

    static {
        for (Status status : Status.values()) {
            NAMES.add(status.name());
        }
    }

    public static boolean contains(String value) {
        if (value == null) {
            return false;
        }
        return NAMES.contains(value);
    }
}
