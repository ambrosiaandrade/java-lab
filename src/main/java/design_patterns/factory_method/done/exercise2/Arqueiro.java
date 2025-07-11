package design_patterns.factory_method.done.exercise2;

public class Arqueiro  implements Personagem {

    @Override
    public void atacar() {
        System.out.println("Arqueiro lança flexa com fogo!");
    }

    @Override
    public void defender() {
        System.out.println("Arqueiro se esconde nas rochas!");
    }

    @Override
    public void exibirHabilidades() {
        System.out.println("Habilidades do Arqueiro:\n- Visão de águia\n- Tem 30% a mais de vida que os outros personagens");
    }

}
