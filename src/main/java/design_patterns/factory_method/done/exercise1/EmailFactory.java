package design_patterns.factory_method.done.exercise1;

public class EmailFactory extends NotificadorFactory {

    public Notificador criarNotificador() {
        return new EmailNotificador();
    }

}
