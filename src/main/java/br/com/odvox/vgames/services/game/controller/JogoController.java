package br.com.odvox.vgames.services.game.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.odvox.vgames.services.game.model.Jogo;
import br.com.odvox.vgames.services.game.service.JogoService;

@RestController
public class JogoController {

    private JogoService jogoService;

    public JogoController(JogoService jogoService) {

        this.jogoService = jogoService;
    }

    @GetMapping(path = "/jogo/{id}")
    public Jogo getJogo(@PathVariable("id") Integer id) {
        return jogoService.getJogo(id);
    }
}
