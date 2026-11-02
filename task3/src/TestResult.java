import java.util.Objects;

public record TestResult(String name, String status) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestResult that = (TestResult) o;
        return Objects.equals(name, that.name) && !Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
