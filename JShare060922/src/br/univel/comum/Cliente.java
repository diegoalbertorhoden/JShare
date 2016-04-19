package br.univel.comum;

import java.io.Serializable;

/**
 * Identificação do cliente.
 * 
 * @author fernandod
 *
 */
public interface Cliente implements Serializable {

	static final long serialVersionUID = 8998030883019232904L;
	
	String nome;
	String ip;
	private int porta;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}
}
