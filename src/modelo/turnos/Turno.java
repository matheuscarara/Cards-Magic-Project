package modelo.turnos;

import modelo.Tabuleiro;

public abstract class Turno {
	
	protected static Integer RODADA = 0;
	
	protected static final Boolean VEZDUELISTA = Boolean.TRUE;
	
	protected static final Boolean VEZBOT = Boolean.TRUE;
	
	public abstract void acao (Tabuleiro tabuleiro);
	
	public abstract void trocaTurno(Tabuleiro tabuleiro);

}
