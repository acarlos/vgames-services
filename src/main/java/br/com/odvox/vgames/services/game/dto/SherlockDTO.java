package br.com.odvox.vgames.services.game.dto;

import java.io.Serializable;

public class SherlockDTO implements Serializable{
	
	private static final long serialVersionUID = 8511299323990418762L;

	private PerguntaDTO perguntaDTO;
	
	private Boolean jogo;
	
	private Integer indice;
	
	private String saudacao;
	
	private String saudacaoVoz;

	public PerguntaDTO getPerguntaDTO() {
		return perguntaDTO;
	}

	public void setPerguntaDTO(PerguntaDTO perguntaDTO) {
		this.perguntaDTO = perguntaDTO;
	}

	public Boolean getJogo() {
		return jogo;
	}

	public void setJogo(Boolean jogo) {
		this.jogo = jogo;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public String getSaudacao() {
		return saudacao;
	}

	public void setSaudacao(String saudacao) {
		this.saudacao = saudacao;
	}

	public String getSaudacaoVoz() {
		return saudacaoVoz;
	}

	public void setSaudacaoVoz(String saudacaoVoz) {
		this.saudacaoVoz = saudacaoVoz;
	}
	
}
