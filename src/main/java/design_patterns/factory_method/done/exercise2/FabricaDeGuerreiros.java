package design_patterns.factory_method.done.exercise2;

public class FabricaDeGuerreiros extends FabricaDePersonagens {

    public Personagem criarPersonagem() {
        return new Guerreiro();
    }

}
