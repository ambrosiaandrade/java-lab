package generics.done;

public interface ReportProcessor<T, R> {

    R process(T inputData);

}
