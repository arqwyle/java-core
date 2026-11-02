import java.util.Stack;

public class SimpleStringBuilder {
    private final StringBuilder sb = new StringBuilder();
    private final Stack<String> stack = new Stack<>();

    public void append(String s) {
        if (s == null || s.isEmpty()) return;

        sb.append(s);
        stack.push(s);
    }

    public void undo() {
        if (!stack.isEmpty()) {
            String lastAdded = stack.pop();
            int newLength = sb.length() - lastAdded.length();
            sb.setLength(newLength);
        }
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
