package br.com.odvox.vgames.services.game.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.odvox.vgames.services.game.model.PerguntaVGames;

@Repository
public interface PerguntaVGamesRepository extends JpaRepository<PerguntaVGames, Integer>{
	@Query("select p from PerguntaVGames p where p.id = ?1")
    public PerguntaVGames findPerguntaVGames(Integer id);

}
