package src.gamelogbackend.secondary.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.gamelogbackend.secondary.dto.DiscordUserDbEntity;

@Repository
public interface IDiscordUserJpaRepository extends JpaRepository<DiscordUserDbEntity, String> {

}
