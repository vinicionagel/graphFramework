package searchGraphCore;
/**
 * 
 * @author Roland e Vinicio.
 *
 */
public class DFSATribute extends Atribute {
	
	private int tempoDescoberta;
	private int tempoFechamento;
	
	public DFSATribute() {
	}
	
	public int getTempoDescoberta() {
		return tempoDescoberta;
	}
	
	public void setTempoDescoberta(int tempoDescoberta) {
		this.tempoDescoberta = tempoDescoberta;
	}
	
	public int getTempoFechamento() {
		return tempoFechamento;
	}
	
	public void setTempoFechamento(int tempoFechamento) {
		this.tempoFechamento = tempoFechamento;
	}
}
