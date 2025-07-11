package design_patterns.factory_method.done.exercise2;

public class Guerreiro implements Personagem {

    @Override
    public void atacar() {
        System.out.println("Guerreiro ataca com espada!");
    }

    @Override
    public void defender() {
        System.out.println("Guerreiro defende com escudo!");
    }

    @Override
    public void exibirHabilidades() {
        System.out.println("Habilidades do Guerreiro:\n- Correr rápido\n- Carrega 20% a mais de itens que os outros personagens");
    }

}
