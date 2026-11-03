void main() {
    List<String> run1 = List.of("LoginTest|PASSED",
                                "CreateTaskTest|FAILED",
                                "CommentTest|FAILED",
                                "LogoutTest|PASSED");

    List<String> run2 = List.of("LoginTest|PASSED",
                                "CreateTaskTest|PASSED",
                                "CommentTest|FAILED",
                                "LogoutTest|PASSED");


    System.out.println("\n1 прогон:");
    System.out.println("\tУпавшие:");
    for (String s : FlakyDetector.failed(run1)) {
        System.out.println("\t\t" + s);
    }
    System.out.println("\tПрошедшие:");
    for (String s : FlakyDetector.passed(run1)) {
        System.out.println("\t\t" + s);
    }

    System.out.println("\n2 прогон:");
    System.out.println("\tУпавшие:");
    for (String s : FlakyDetector.failed(run2)) {
        System.out.println("\t\t" + s);
    }
    System.out.println("\tПрошедшие:");
    for (String s : FlakyDetector.passed(run2)) {
        System.out.println("\t\t" + s);
    }

    System.out.println("\nFlaky-тесты:");
    for (String s : FlakyDetector.flaky(run1, run2)) {
        System.out.println("\t" + s);
    }

    System.out.println("\nСводка по 1 прогону: " + FlakyDetector.statusSummary(run1));
    System.out.println("\nСводка по 2 прогону: " + FlakyDetector.statusSummary(run2));

    String[] words = {"up", "down", "up", "up", "left"};
    Integer[] numbers = {3, 1, 3, 3, 7, 1};

    System.out.println("\nОбобщённый частотный анализ:");
    System.out.println("\t{\"up\", \"down\", \"up\", \"up\", \"left\"}:");
    System.out.println("\t\t" + FrequencyAnalyzer.frequency(words));
    System.out.println("\t{3, 1, 3, 3, 7, 1}:");
    System.out.println("\t\t" + FrequencyAnalyzer.frequency(numbers));
}
