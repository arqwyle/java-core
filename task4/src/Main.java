void main() {
    List<TestResult> run = List.of(
        new TestResult("LoginTest",        Status.PASSED,  120),
        new TestResult("CreateTaskTest",   Status.PASSED,  940),
        new TestResult("UpdateStatusTest", Status.FAILED,  2100),
        new TestResult("CommentTest",      Status.PASSED,  380),
        new TestResult("DeleteTaskTest",   Status.FAILED,  760),
        new TestResult("StatsTest",        Status.SKIPPED, 0),
        new TestResult("LogoutTest",       Status.PASSED,  90)
    );

    printSummary(run);
}

void printSummary(List<TestResult> run) {
    Map<Status, List<String>> result = run.stream()
        .collect(Collectors.groupingBy(TestResult::status,
            Collectors.mapping(TestResult::name, Collectors.toList())));

    System.out.println("Сводка прогона");
    result.entrySet().stream()
            .sorted(Map.Entry.<Status, List<String>>comparingByValue(Comparator.comparing(List::size)).reversed())
        .forEach(e -> {
            String elements = String.join(", ", e.getValue());
            System.out.println(e.getKey() + " (" + e.getValue().size() + "): " + elements);
        });

    String top3 = run.stream()
        .sorted(Comparator.comparingLong(TestResult::durationMs).reversed())
        .limit(3)
        .map(TestResult::name)
        .collect(Collectors.joining(", "));
    System.out.println("Топ-3 долгих: " + top3);

    Double average = run.stream().collect(Collectors.averagingLong(TestResult::durationMs));
    System.out.println("Среднее время прошедших: " + average);

    System.out.println("\nДни, в которые работало более 1 человека");
    test();
}

void test() {
    var denSheet = new WorkSheet("Денис", List.of(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY, DayOfWeek.SUNDAY));
    var benSheet = new WorkSheet("Бен", List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.SATURDAY));
    var lisSheet = new WorkSheet("Лиза", List.of(DayOfWeek.THURSDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY));
    var sheets = List.of(denSheet, benSheet, lisSheet);

    sheets.stream()
        .flatMap(ws -> ws.workDays().stream())
        .collect(Collectors.groupingBy(item -> item, Collectors.counting()))
        .entrySet().stream()
        .filter(entry -> entry.getValue() > 1)
        .forEach(e -> System.out.println(e.getKey().toString()));
}
