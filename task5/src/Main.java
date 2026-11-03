void main() throws InterruptedException {
    try (ExecutorService fixed = Executors.newFixedThreadPool(8)) {
        List<Callable<TestResult>> tasks = IntStream.rangeClosed(1, 8)
            .mapToObj(i -> fakeTest(
                String.valueOf(i),
                ThreadLocalRandom.current().nextInt(300, 500),
                    Status.values()[ThreadLocalRandom.current().nextInt(Status.values().length)]
            ))
            .toList();

        List<Future<TestResult>> futures;

        long startTime = System.nanoTime();

        try {
            futures = fixed.invokeAll(tasks);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Выполнение тестов прервано", e);
        }

        long endTime = System.nanoTime();

        final LongSummaryStatistics stats = new LongSummaryStatistics();

        List<TestResult> results = futures.stream()
            .map(future -> {
                try {
                    TestResult res = future.get();
                    stats.accept(res.durationMs());
                    return res;
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            })
            .toList();

        results.stream().collect(
            Collectors.groupingBy(
            TestResult::status,
            Collectors.counting()
            )).forEach((status, count) ->
                System.out.println(status + ": " + count));

        Long realTime = (endTime - startTime)/1000000L;
        Long sumTime = stats.getSum();

        System.out.println("Суммарное время прогона: " + sumTime + " мс");
        System.out.println("Реальное время прогона: " + realTime + " мс");
        System.out.println("Параллельность сэкономила: " + (sumTime - realTime) + " мс");
    }

    System.out.println("\nСбор данных о товаре: ");
    System.out.println(DataAggregator.aggregateProductInfo("ноутбук"));

    System.out.println("\nБлокирующая очередь: ");
    SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(1);

    queue.enqueue(1);

    Thread producer = new Thread(() -> {
        try {
            System.out.println("Пробую добавить 2");
            queue.enqueue(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    });

    Thread consumer = new Thread(() -> {
        try {
            Thread.sleep(2000);
            System.out.println("Забираю 1");
            queue.dequeue();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    });

    producer.start();
    consumer.start();
}

static Callable<TestResult> fakeTest(String name, long durationMs, Status status) {
    return () -> {
        Thread.sleep(durationMs);
        return new TestResult(name, status, durationMs);
    };
}
