package br.com.odvox.vgames.services.game.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "JOGO")
public class Jogo {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID")
    private Integer id;

    @Column(name="TIPO")
    private String tipo;

    @Column(name="JOGADOR")
    private String jogador;

    @Column(name="DATA")
    private String date;
    
    @OneToMany(mappedBy = "jogo")
    private Set<PerguntaVGames> perguntas;

    public Jogo(){

    }

    public Jogo(Integer id, String type, String email, String date, Set<PerguntaVGames> perguntas) {
        this.id=id;
        this.tipo = type;
        this.jogador = email;
        this.date = date;
        this.perguntas = perguntas;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getJogador() {
		return jogador;
	}

	public void setJogador(String jogador) {
		this.jogador = jogador;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Set<PerguntaVGames> getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(Set<PerguntaVGames> perguntas) {
		this.perguntas = perguntas;
	}
}