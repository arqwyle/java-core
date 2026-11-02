public record TestResult(String name, Status status, long durationMs) {
    @Override
    public String toString() {
        return String.format("%s - %s (%d ms)", name, status, durationMs);
    }
}
