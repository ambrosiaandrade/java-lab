package design_patterns.factory_method.done.exercise1;

public class PushFactory extends NotificadorFactory {

    @Override
    public Notificador criarNotificador() {
        return new PushNotificador();
    }

}
