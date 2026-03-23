package src.gamelogbackend.core.entites;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import src.gamelogbackend.core.valueobjects.DiscordID;

@Builder
@Getter
public class DiscordUser {

    @NonNull
    private DiscordID discordID;
    @NonNull
    private String discordUserName;
    @NonNull
    private String discordEmail;


}
