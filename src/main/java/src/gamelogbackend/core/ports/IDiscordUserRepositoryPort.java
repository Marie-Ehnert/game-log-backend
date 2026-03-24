package src.gamelogbackend.core.ports;

import src.gamelogbackend.core.entites.DiscordUser;

public interface IDiscordUserRepositoryPort {

    void registerNewDiscordUser(DiscordUser discordUser);
}
