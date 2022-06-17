package br.com.odvox.vgames.services.game.service;

import org.springframework.stereotype.Service;

import br.com.odvox.vgames.services.game.dao.JogoRepository;
import br.com.odvox.vgames.services.game.model.Jogo;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;

    public JogoService(JogoRepository jogoRepository){

        this.jogoRepository = jogoRepository;
    }
    public Jogo getJogo(Integer id){
        return  jogoRepository.findJogo(id);
    }
}
