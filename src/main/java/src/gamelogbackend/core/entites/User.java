package src.gamelogbackend.core.entites;

import lombok.Builder;
import lombok.NonNull;
import src.gamelogbackend.core.valueobjects.SteamID;

import java.time.LocalDate;

@Builder
public class User {

    @NonNull
    private SteamID id;
    @NonNull
    private String name;
    @NonNull
    private String profileUrl;
    @NonNull
    private LocalDate created;

}
