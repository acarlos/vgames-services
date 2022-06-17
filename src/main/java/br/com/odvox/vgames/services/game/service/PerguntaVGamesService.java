package br.com.odvox.vgames.services.game.service;

import org.springframework.stereotype.Service;

import br.com.odvox.vgames.services.game.dao.PerguntaVGamesRepository;
import br.com.odvox.vgames.services.game.model.PerguntaVGames;

@Service
public class PerguntaVGamesService {
	private final PerguntaVGamesRepository perguntaVGamesRepository;

    public PerguntaVGamesService(PerguntaVGamesRepository perguntaVGamesRepository){

        this.perguntaVGamesRepository = perguntaVGamesRepository;
    }
    public PerguntaVGames getPerguntaVGames(Integer id){
        return  perguntaVGamesRepository.findPerguntaVGames(id);
    }
}
