package br.com.odvox.vgames.services.game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PERGUNTA")
public class PerguntaVGames {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private Integer id;
	
	@Column(name = "PERGUNTA")
	private String pergunta;
	
	@Column(name = "RESPOSTA")
	private String resposta;
	
	@ManyToOne
	private Jogo jogo;
	
	public PerguntaVGames() {
		
	}
	
	public PerguntaVGames(Integer id, String pergunrta, String resposta, Jogo jogo) {
		this.id = id;
		this.pergunta = pergunrta;
		this.resposta = resposta;
		this.jogo = jogo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}	
}
