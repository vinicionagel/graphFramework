package searchGraphCore;
/**
 * 
 * @author Roland e Vinicio.
 *
 */
public enum Color {
	
	BRANCO(1), CINZA(2), PRETO(3);
	 
	private int code;
	 
	private Color(int code) {
		this.setCode(code);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	 
}
