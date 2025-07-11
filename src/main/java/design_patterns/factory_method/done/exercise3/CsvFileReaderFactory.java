package design_patterns.factory_method.done.exercise3;

public class CsvFileReaderFactory extends FileReaderFactory {

    @Override
    public FileReader createReader() {
        return new CsvFileReader();
    }

}
