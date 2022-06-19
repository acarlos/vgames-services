package br.com.odvox.vgames.services.game.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import br.com.odvox.sherlock.SherlockSCE;
import br.com.odvox.sherlock.model.Pergunta;
import br.com.odvox.vgames.services.game.dao.JogoRepository;
import br.com.odvox.vgames.services.game.dto.PerguntaDTO;
import br.com.odvox.vgames.services.game.dto.RespostaDTO;
import br.com.odvox.vgames.services.game.dto.SherlockDTO;
import br.com.odvox.vgames.services.game.model.Jogo;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;
	private SherlockSCE sherlockSCE = new SherlockSCE("pt", "BR");
    private SherlockDTO sherlockDTO;
    private Pergunta pergunta;
    private List<Integer> indices = new ArrayList<Integer>();
    private Integer erros;
    
    @Autowired
    MessageSource messageSource;
	private Locale locale = new Locale("pt", "BR");;

    public JogoService(JogoRepository jogoRepository){

        this.jogoRepository = jogoRepository;
    }
    public Jogo getJogo(Integer id){
        return  jogoRepository.findJogo(id);
    }

	public SherlockDTO createGame() {
		if ((null != this.locale) && (null != messageSource)) {
			this.sherlockSCE = new SherlockSCE(this.locale.getLanguage(), this.locale.getCountry());
		}
		cleanDTOs();
		this.sherlockDTO.setJogo(Boolean.TRUE);
		this.sherlockDTO.setSaudacao(this.messageSource.getMessage("regras", null, this.locale));
		return this.sherlockDTO;
	}
	
	
	private void cleanDTOs() {
		this.sherlockDTO = new SherlockDTO();
		this.indices.clear();
		this.erros = 0;
	}
	public SherlockDTO getPergunta(Integer indice) {
		PerguntaDTO perguntaDTO = new PerguntaDTO();
		if (!this.indices.contains(indice) && this.sherlockDTO.getJogo()) {
			this.indices.add(indice);
			this.pergunta = this.sherlockSCE.getPerguntasDoN1().get(new Random().nextInt(13));
			perguntaDTO.setPergunta(this.pergunta.getPergunta());
			perguntaDTO.setNivel(this.pergunta.getNivel());
			this.sherlockDTO.setJogo(Boolean.TRUE);
		} else {
			perguntaDTO.setPergunta("Pergunta já foi realizada");
		}
		if (indice > 5 || this.erros == 3) {
			perguntaDTO.setPergunta("Jogo encerrado, você errou " + this.erros);
			this.sherlockDTO.setJogo(Boolean.FALSE); 
		}
		this.sherlockDTO.setPerguntaDTO(perguntaDTO);
		this.sherlockDTO.setIndice(indice);
		return this.sherlockDTO;
	}
	
	public SherlockDTO setResposta(String resposta) {
		RespostaDTO respostaDTO = new RespostaDTO();
		if (this.sherlockSCE.responda(resposta, this.pergunta)) {
			respostaDTO.setSentenca(this.messageSource.getMessage("primeiraMensagemRespostaCorreta", null, this.locale) + resposta);
		} else {
			this.erros++;
			respostaDTO.setSentenca(this.messageSource.getMessage("primeiraMensagemRespostaErrada", null, this.locale) + this.pergunta.getResposta().getSentenca());
		}
		this.sherlockDTO.getPerguntaDTO().setResposta(respostaDTO);
		return this.sherlockDTO;
	}
}
