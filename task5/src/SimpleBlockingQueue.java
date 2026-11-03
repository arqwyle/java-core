import java.util.LinkedList;
import java.util.Queue;

public class SimpleBlockingQueue<T> {
    public SimpleBlockingQueue(Integer size) {
        this.size = size;
    }

    int size;
    private final Queue<T> queue = new LinkedList<>();

    public synchronized void enqueue(T item) throws InterruptedException {
        if (queue.size() == size) {
            wait();
        }

        queue.add(item);
        notifyAll();
        System.out.println("Добавлен элемент " + item);
    }

    public synchronized void dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }

        T item = queue.remove();
        notifyAll();
        System.out.println("Удалён элемент " + item);
    }

    public Integer size() {
        return queue.size();
    }
}
