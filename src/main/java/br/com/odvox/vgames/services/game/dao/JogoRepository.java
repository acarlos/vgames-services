package br.com.odvox.vgames.services.game.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.odvox.vgames.services.game.model.Jogo;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer> {

    @Query("select j from Jogo j where j.id = ?1")
    public Jogo findJogo(Integer id);

}
