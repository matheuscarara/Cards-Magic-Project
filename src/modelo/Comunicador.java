package modelo;

import java.util.List;

public interface Comunicador {

	public abstract void perdeu();

	public abstract void ganhou();

	public abstract void campoCheio();

	public abstract void mostraCampo(String campo);

	public abstract List<Integer> selecionaCartas();

	public abstract void configuraTelaCompra(List<Carta> campoBot, List<Carta> campoDuelista, List<Carta> maoDuelista,
			Integer vidaJogador, Integer vidaBot);

	public abstract void cartaColocaNoCampo();

	public abstract void configuraTelaBatalha(List<Carta> campoBot, List<Carta> campoDuelista, Integer pontosDeVida,
			Integer pontosDeVida2, Integer idCartaAtacando);

	public abstract void iniciaTelaBatalha();
}
