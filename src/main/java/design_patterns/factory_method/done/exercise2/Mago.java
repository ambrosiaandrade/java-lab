package design_patterns.factory_method.done.exercise2;

public class Mago  implements Personagem {

    @Override
    public void atacar() {
        System.out.println("Mago conjura feitiço de ataque das sombras!");
    }

    @Override
    public void defender() {
        System.out.println("Mago defende feitiço de proteção!");
    }

    @Override
    public void exibirHabilidades() {
        System.out.println("Habilidades do Mago:\n- Ficar invisível\n- Tem 40% a mais de itens que os outros personagens");
    }

}
