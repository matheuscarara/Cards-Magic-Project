package modelo;

public interface Comunicador {
	
	public abstract void perdeu();
	
	public abstract void ganhou();

	public abstract Integer indiceCartaDaMaoParaColocaNoCampo(String campo, String mao);
	
	public abstract void campoCheio();

	public abstract void indiceInvalido();

	public abstract void mostraCampo(String campo);

}
