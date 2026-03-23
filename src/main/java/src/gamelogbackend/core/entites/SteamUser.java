package src.gamelogbackend.core.entites;

import lombok.Builder;
import lombok.NonNull;
import src.gamelogbackend.core.valueobjects.SteamID;

import java.time.LocalDate;

@Builder
public class SteamUser {

    @NonNull
    private SteamID steamId;
    @NonNull
    private String steamUsername;
    @NonNull
    private String profileUrl;
    @NonNull
    private LocalDate created;

}
