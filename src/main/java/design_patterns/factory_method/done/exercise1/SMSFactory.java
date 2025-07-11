package design_patterns.factory_method.done.exercise1;

public class SMSFactory extends NotificadorFactory {

    @Override
    public Notificador criarNotificador() {
        return new SMSNotificador();
    }

}
