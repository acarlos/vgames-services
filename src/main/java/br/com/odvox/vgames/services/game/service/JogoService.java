package br.com.odvox.vgames.services.game.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
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
    private List<Pergunta> perguntasFeitas = new ArrayList<Pergunta>();
    private List<Integer> indices = new ArrayList<Integer>();
    private Integer erros;
    
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private VoiceService voiceService;
    
	private Locale locale = new Locale("pt", "BR");

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
		PerguntaDTO perguntaDTO = new PerguntaDTO();
		RespostaDTO respostaDTO = new RespostaDTO();
		respostaDTO.setSentenca("");
		perguntaDTO.setPergunta("");
		perguntaDTO.setResposta(respostaDTO);
		this.sherlockDTO.setPerguntaDTO(perguntaDTO);
		this.sherlockDTO.setSaudacao(this.messageSource.getMessage("regras", null, this.locale));
		try {
			this.sherlockDTO.setSaudacaoVoz(this.voiceService.getVozURL(this.messageSource.getMessage("regras", null, this.locale)));
		} catch (UnsupportedEncodingException | NoSuchMessageException e) {
			e.printStackTrace();
		}
		return this.sherlockDTO;
	}
	
	
	private void cleanDTOs() {
		this.perguntasFeitas = new ArrayList<Pergunta>();
		this.sherlockDTO = new SherlockDTO();
		this.indices.clear();
		this.erros = 0;
	}
	public SherlockDTO getPergunta(Integer indice) {
		PerguntaDTO perguntaDTO = new PerguntaDTO();
		RespostaDTO respostaDTO = new RespostaDTO();
		respostaDTO.setSentenca("");
		perguntaDTO.setResposta(respostaDTO);
		if (!this.indices.contains(indice) && this.sherlockDTO.getJogo()) {
			this.indices.add(indice);
			this.pergunta = getPerguntaRandomUnico();
			this.perguntasFeitas.add(this.pergunta);
			perguntaDTO.setPergunta(this.pergunta.getPergunta());
			try {
				perguntaDTO.setPerguntaVoz(this.voiceService.getVozURL(this.pergunta.getPergunta()));
			} catch (UnsupportedEncodingException | NoSuchMessageException e) {
				e.printStackTrace();
			}
			perguntaDTO.setNivel(this.pergunta.getNivel());
			this.sherlockDTO.setJogo(Boolean.TRUE);
		} else {
			perguntaDTO.setPergunta("Pergunta já foi realizada");
		}
		if (indice > 5 || this.erros == 3) {
			String jogoEncerrado = "Jogo encerrado, você errou " + this.erros;
			perguntaDTO.setPergunta(jogoEncerrado);
			try {
				perguntaDTO.setPerguntaVoz(this.voiceService.getVozURL(jogoEncerrado));
			} catch (UnsupportedEncodingException | NoSuchMessageException e) {
				e.printStackTrace();
			}
			this.sherlockDTO.setJogo(Boolean.FALSE); 
		}
		this.sherlockDTO.setPerguntaDTO(perguntaDTO);
		this.sherlockDTO.setIndice(indice);
		return this.sherlockDTO;
	}
	private Pergunta getPerguntaRandomUnico() {
		Pergunta pergunta = this.getPerguntaRandom();
		if (!this.perguntasFeitas.contains(pergunta)) {
			return pergunta;
		} else {
			return this.getPerguntaRandomUnico();
		}
	}
	private Pergunta getPerguntaRandom() {
		return this.sherlockSCE.getPerguntasDoN1().get(new Random().nextInt(13));
	}
	
	public SherlockDTO setResposta(String resposta) {
		RespostaDTO respostaDTO = new RespostaDTO();
		String sentenca = "";
		if (this.sherlockSCE.responda(resposta, this.pergunta)) {
			sentenca = this.messageSource.getMessage("primeiraMensagemRespostaCorreta", null, this.locale) + resposta;
		} else {
			this.erros++;
			sentenca = this.messageSource.getMessage("primeiraMensagemRespostaErrada", null, this.locale) + this.pergunta.getResposta().getSentenca();
		}
		respostaDTO.setSentenca(sentenca);
		try {
			respostaDTO.setSentencaVoz(this.voiceService.getVozURL(sentenca));
		} catch (UnsupportedEncodingException | NoSuchMessageException e) {
			e.printStackTrace();
		}
		this.sherlockDTO.getPerguntaDTO().setResposta(respostaDTO);
		return this.sherlockDTO;
	}
}
