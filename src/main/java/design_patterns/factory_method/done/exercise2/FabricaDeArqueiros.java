package design_patterns.factory_method.done.exercise2;

public class FabricaDeArqueiros extends FabricaDePersonagens {

    public Personagem criarPersonagem() {
        return new Arqueiro();
    }

}
