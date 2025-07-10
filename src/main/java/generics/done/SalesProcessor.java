package generics.done;

import java.util.List;

public class SalesProcessor implements ReportProcessor<List<Double>, Double> {

    public SalesProcessor() {
    }

    @Override
    public Double process(List<Double> inputData) {
        return inputData.stream().mapToDouble(Double::doubleValue).sum();
    }

}
