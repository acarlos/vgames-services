package br.com.odvox.vgames.services.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.odvox.vgames.services.game.dto.SherlockDTO;
import br.com.odvox.vgames.services.game.model.Jogo;
import br.com.odvox.vgames.services.game.service.JogoService;

@RestController
public class JogoController {

	@Autowired
    private JogoService jogoService;

    @GetMapping(path = "/jogo/{id}")
    public Jogo getJogo(@PathVariable("id") Integer id) {
        return jogoService.getJogo(id);
    }
    

    @GetMapping(path = "/start/")
    public SherlockDTO getSherlock() {
        return jogoService.createGame();
    }
    
    @GetMapping(path = "/pergunta/{indice}")
    public SherlockDTO getPergunta(@PathVariable("indice") Integer indice) {
        return jogoService.getPergunta(indice);
    }
    
    @GetMapping(path = "/resposta/{resposta}")
    public SherlockDTO getResposta(@PathVariable("resposta") String resposta) {
        return jogoService.setResposta(resposta);
    }
}
