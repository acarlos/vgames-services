package br.com.odvox.vgames.services.game.dto;

import java.io.Serializable;

public class PerguntaDTO  implements Serializable {
	
	private static final long serialVersionUID = -445270594786681694L;

	private RespostaDTO resposta;
	
	private String pergunta;
	
	private Integer nivel;

	public RespostaDTO getResposta() {
		return resposta;
	}

	public void setResposta(RespostaDTO resposta) {
		this.resposta = resposta;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
}
