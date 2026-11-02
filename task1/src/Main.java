void main() {
    List<String> lines = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader("task1/run.log"))) {
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
    } catch (Exception e) {
        System.err.println("Ошибка при чтении файла: " + e.getMessage());
    }

    List<TestResult> results = TestLogParser.parse(lines);

    IO.println("Результаты:");
    for (TestResult result : results) {
        IO.println("\t" + result);
    }

    IO.println("Битые строки:");
    for (Error error : TestLogParser.errors) {
        IO.println("\t" + error);
    }

    IO.println("Первый упавший:");
    Optional<TestResult> firstFail = TestLogParser.firstFailed(results);
    if (firstFail.isPresent()) {
        IO.println("\t" + firstFail.get());
    } else {
        IO.println("\tУпадений нет");
    }

    SimpleStringBuilder b = new SimpleStringBuilder();
    b.append("Hello");     // Hello
    IO.println(b.toString());
    b.append(", ");        // Hello,
    IO.println(b.toString());
    b.append("world");     // Hello, world
    IO.println(b.toString());
    b.undo();              // Hello,
    IO.println(b.toString());
    b.append("AQA!");      // Hello, AQA!
    IO.println(b.toString());
}
