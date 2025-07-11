package design_patterns.factory_method.done.exercise3;

public class XmlFileReader implements FileReader {

    @Override
    public String read() {
        return "Dados XML simulados";
    }

    @Override
    public void processData(String data) {
        System.out.println("Dado recebido ["+data+"]\nProcessando dados...");
    }

}

