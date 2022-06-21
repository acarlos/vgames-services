package br.com.odvox.vgames.services.game.dto;

import java.io.Serializable;

public class RespostaDTO implements Serializable{

	private static final long serialVersionUID = -4849476661747303796L;
	public String sentenca;
	public String sentencaVoz;
	
	public String getSentenca() {
		return sentenca;
	}
	public void setSentenca(String sentenca) {
		this.sentenca = sentenca;
	}
	public String getSentencaVoz() {
		return sentencaVoz;
	}
	public void setSentencaVoz(String sentencaVoz) {
		this.sentencaVoz = sentencaVoz;
	}
	
}
