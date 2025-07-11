package design_patterns.factory_method.done;

import config.CustomPrint;
import config.MessageProvider;
import design_patterns.factory_method.done.exercise1.EmailFactory;
import design_patterns.factory_method.done.exercise1.PushFactory;
import design_patterns.factory_method.done.exercise1.SMSFactory;

public class FactoryMethodMain {

    static {
        MessageProvider.setModuleName("design_patterns");
        CustomPrint.greeting();
    }

    public static void main(String[] args) {

        CustomPrint.colored("factoryMethod1");
        exercise1();


    }

    private static void exercise1() {
        SMSFactory sms = new SMSFactory();
        EmailFactory email = new EmailFactory();
        PushFactory push = new PushFactory();

        sms.notificarUsuario("Ligações do número (XX)X XXXX-XXXX perdidas");
        email.notificarUsuario("Nova atualização na IDE XYZ");
        push.notificarUsuario("Fulano mandou mensagem");
    }

    private void out(Object o) {
        System.out.println(o);
    }

}
