package generics.done;

import java.util.List;

public class ErrorLogProcessor implements ReportProcessor<List<String>, Integer> {

    public ErrorLogProcessor() {
    }

    @Override
    public Integer process(List<String> inputData) {
        return (int) inputData.stream().filter(s -> s.contains("ERROR")).count();
    }

}
