package src.gamelogbackend.core.entites;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import src.gamelogbackend.core.valueobjects.SteamID;

@Builder
@Getter
public class SteamUser
{
    @NonNull
    private SteamID steamId;
    @NonNull
    private String steamUsername;
    @NonNull
    private String profileUrl;
    @NonNull
    private String communityVisibilityState;

    @NonNull
    private String profileState;

    @NonNull
    private LocalDateTime lastLogOff;

    @NonNull
    private String avatarUrl;
}
