package design_patterns.factory_method.done;

import config.CustomPrint;
import config.MessageProvider;
import design_patterns.factory_method.done.exercise1.EmailFactory;
import design_patterns.factory_method.done.exercise1.PushFactory;
import design_patterns.factory_method.done.exercise1.SMSFactory;
import design_patterns.factory_method.done.exercise2.*;
import design_patterns.factory_method.done.exercise3.FileReaderFactory;

import java.util.*;

public class FactoryMethodMain {

    static {
        MessageProvider.setModuleName("design_patterns");
        CustomPrint.greeting();
    }

    public static void main(String[] args) {

        CustomPrint.colored("factoryMethod1");
        exercise1();

        CustomPrint.colored("factoryMethod2");
        exercise2();

        CustomPrint.colored("factoryMethod3");
        exercise3();

    }

    private static void exercise1() {
        SMSFactory sms = new SMSFactory();
        EmailFactory email = new EmailFactory();
        PushFactory push = new PushFactory();

        sms.notificarUsuario("Ligações do número (XX)X XXXX-XXXX perdidas");
        email.notificarUsuario("Nova atualização na IDE XYZ");
        push.notificarUsuario("Fulano mandou mensagem");
    }

    private static void exercise2() {
        new FabricaDeGuerreiros().iniciarAventura();
        new FabricaDeMagos().iniciarAventura();
        new FabricaDeArqueiros().iniciarAventura();
    }

    private static void exercise3() {
        List<String> fileNames = List.of("planilha.csv", "dados.json", "script.py", "dados.xml", "tcc.doc");

        fileNames.forEach(name -> {
            String extension = name.substring(name.indexOf("."));
            FileReaderFactory factory = FileReaderFactory.handleFileType(extension);
            if (factory == null) {
                CustomPrint.error("There is no factory for file extension " + extension);
                return;
            }
            System.out.format(":: INPUT :: %s%n", name);
            factory.handleFile();
        });

    }

}
