package design_patterns.factory_method.done.exercise1;

public class PushNotificador implements Notificador{

    @Override
    public void enviar(String mensagem) {
        System.out.println("Enviando push: " + mensagem);
    }

}
