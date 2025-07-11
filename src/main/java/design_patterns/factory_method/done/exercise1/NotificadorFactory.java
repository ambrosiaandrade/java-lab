package design_patterns.factory_method.done.exercise1;

public abstract class NotificadorFactory {

    public abstract Notificador criarNotificador();

    public void notificarUsuario(String mensagem) {
        Notificador notificador = criarNotificador();
        notificador.enviar(mensagem);
    }

}
