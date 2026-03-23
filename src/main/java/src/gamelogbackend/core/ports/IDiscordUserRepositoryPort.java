package src.gamelogbackend.core.ports;

import src.gamelogbackend.core.entites.DiscordUser;
import src.gamelogbackend.secondary.dto.DiscordUserDbEntity;

public interface IDiscordUserRepositoryPort {

    DiscordUserDbEntity registerNewDiscordUser(DiscordUser discordUser);
}
