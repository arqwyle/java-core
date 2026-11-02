public record Error(String line, String message) {
    @Override
    public String toString() {
        return String.format("%s - %s", line, message);
    }
}