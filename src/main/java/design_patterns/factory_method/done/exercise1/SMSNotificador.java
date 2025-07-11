package design_patterns.factory_method.done.exercise1;

public class SMSNotificador implements Notificador {

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando SMS: " + mensagem);
    }

}
