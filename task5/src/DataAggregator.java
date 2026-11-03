import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class DataAggregator {
    public static ProductInfo aggregateProductInfo(String productName) {
        CompletableFuture<Double> price = CompletableFuture.supplyAsync(() -> {
            imitateFetch();
            return ThreadLocalRandom.current().nextDouble(100, 10000);
        }).exceptionally(_ -> 0.0);

        CompletableFuture<String> description = CompletableFuture.supplyAsync(() -> {
            imitateFetch();
            return "Описание";
        }).exceptionally(_ -> "Нет данных");

        CompletableFuture<Double> rating = CompletableFuture.supplyAsync(() -> {
            imitateFetch();
            return ThreadLocalRandom.current().nextDouble(1, 5);
        }).exceptionally(_ -> 0.0);

        CompletableFuture.allOf(price, description, rating).join();

        return new ProductInfo(productName, price.join(), description.join(), rating.join());
    }

    private static void imitateFetch() {
        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        if (ThreadLocalRandom.current().nextInt(100) < 20) {
            throw new RuntimeException("Пупупууу");
        }
    }
}
