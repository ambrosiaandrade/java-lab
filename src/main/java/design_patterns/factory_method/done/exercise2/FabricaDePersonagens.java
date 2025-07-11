package design_patterns.factory_method.done.exercise2;

public abstract class FabricaDePersonagens {

    protected abstract Personagem criarPersonagem();

    public void iniciarAventura() {
        Personagem personagem = criarPersonagem();
        personagem.exibirHabilidades();
        personagem.atacar();
        personagem.defender();
        System.out.println("---");
    }

}
