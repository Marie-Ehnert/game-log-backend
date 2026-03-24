package src.gamelogbackend.secondary.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import src.gamelogbackend.core.entites.DiscordUser;
import src.gamelogbackend.core.ports.IDiscordUserRepositoryPort;
import src.gamelogbackend.secondary.dto.DiscordUserDbEntity;
import src.gamelogbackend.secondary.repository.IDiscordUserJpaRepository;

@Slf4j
@RequiredArgsConstructor
@Component
public class DiscordUserRepositoryAdapter implements IDiscordUserRepositoryPort {

    private final IDiscordUserJpaRepository iDiscordUserJpaRepository;

    @Override
    public void registerNewDiscordUser(DiscordUser discordUser) {

        if (iDiscordUserJpaRepository.existsById(discordUser.getDiscordID().value())) {
            log.info("Discord user with id {} is logged in", discordUser.getDiscordID().value());
            return;
        }
        DiscordUserDbEntity discordUserDbEntity = DiscordUserDbEntity.builder()
                .discordUserName(discordUser.getDiscordUserName())
                .discordEmail(discordUser.getDiscordEmail())
                .discordId(discordUser.getDiscordID().value())
                .build();

        iDiscordUserJpaRepository.save(discordUserDbEntity);
    }
}
