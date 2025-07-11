package design_patterns.factory_method.done.exercise3;

public class JsonFileReaderFactory extends FileReaderFactory {

    @Override
    public FileReader createReader() {
        return new JsonFileReader();
    }

}
