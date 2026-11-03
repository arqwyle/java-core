public record TestStep(String id, String methodName, int order) {
    @Override
    public String toString() {
        return String.format("%d. %s -> %s", order, id, methodName);
    }
}
