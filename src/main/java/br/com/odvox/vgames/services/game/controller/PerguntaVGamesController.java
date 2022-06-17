package br.com.odvox.vgames.services.game.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.odvox.vgames.services.game.model.PerguntaVGames;
import br.com.odvox.vgames.services.game.service.PerguntaVGamesService;

@RestController
public class PerguntaVGamesController {

	private PerguntaVGamesService perguntaVGamesService;

    public PerguntaVGamesController(PerguntaVGamesService perguntaVGamesService) {

        this.perguntaVGamesService = perguntaVGamesService;
    }

    @GetMapping(path = "/perguntaVGames/{id}")
    public PerguntaVGames getPerguntaVGames(@PathVariable("id") Integer id) {
        return perguntaVGamesService.getPerguntaVGames(id);
    }
}
