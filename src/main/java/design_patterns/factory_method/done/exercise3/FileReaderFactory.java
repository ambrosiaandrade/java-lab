package design_patterns.factory_method.done.exercise3;

public abstract class FileReaderFactory {

    protected abstract FileReader createReader();

    public void handleFile() {
        FileReader fr = createReader();
        String inputData = fr.read();
        fr.processData(inputData);
    }

    public static FileReaderFactory handleFileType(String extension) {
        return switch(extension.toLowerCase()) {
            case ".csv" -> new CsvFileReaderFactory();
            case ".json" -> new JsonFileReaderFactory();
            case ".xml" -> new XmlFileReaderFactory();
            default -> null;
        };
    }

}
