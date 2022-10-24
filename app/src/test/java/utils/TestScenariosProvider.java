package utils;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestScenariosProvider {

    public static String getScenario(final String scenarioName, final ScenarioType scenarioType) throws IOException {
        var path = new StringBuilder("./src/test/resources/templates/" + scenarioName);
        switch (scenarioType) {
            case INPUT:
                path.append("_input.html");
                break;
            case EXPECTED:
                path.append("_expected.html");
        }

        try (Stream<String> lines = Files.lines(Path.of(path.toString()))) {
            return lines.collect(Collectors.joining(" "));
        }

    }

    public enum ScenarioType {
        EXPECTED, INPUT
    }
}
