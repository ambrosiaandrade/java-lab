package design_patterns.factory_method.done.exercise2;

public class FabricaDeMagos extends FabricaDePersonagens {

    public Personagem criarPersonagem() {
        return new Mago();
    }
    
}
