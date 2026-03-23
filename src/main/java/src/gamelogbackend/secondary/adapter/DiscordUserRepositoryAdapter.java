package src.gamelogbackend.secondary.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import src.gamelogbackend.core.entites.DiscordUser;
import src.gamelogbackend.core.ports.IDiscordUserRepositoryPort;
import src.gamelogbackend.secondary.dto.DiscordUserDbEntity;
import src.gamelogbackend.secondary.exceptions.DiscordUserRepositoryException;
import src.gamelogbackend.secondary.repository.IDiscordUserJpaRepository;

@RequiredArgsConstructor
@Component
public class DiscordUserRepositoryAdapter implements IDiscordUserRepositoryPort {

    private final IDiscordUserJpaRepository iDiscordUserJpaRepository;

    @Override
    public DiscordUserDbEntity registerNewDiscordUser(DiscordUser discordUser) {

        if (iDiscordUserJpaRepository.existsById(discordUser.getDiscordID().value())) {
            throw new DiscordUserRepositoryException("Discord user is already registered!");
        }
        DiscordUserDbEntity discordUserDbEntity = DiscordUserDbEntity.builder()
                .discordUserName(discordUser.getDiscordUserName())
                .discordEmail(discordUser.getDiscordEmail())
                .discordId(discordUser.getDiscordID().value())
                .build();

        iDiscordUserJpaRepository.save(discordUserDbEntity);

        return discordUserDbEntity;
    }
}
