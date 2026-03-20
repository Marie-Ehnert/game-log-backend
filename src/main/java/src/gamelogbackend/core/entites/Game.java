package src.gamelogbackend.core.entites;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import src.gamelogbackend.core.valueobjects.AppID;
import src.gamelogbackend.core.valueobjects.Playtime;

@Data
@Builder
public class Game {

    @NonNull
    private AppID appid;
    @NonNull
    private String name;
    @NonNull
    private Playtime playtimeTwoWeeks;
    @NonNull
    private Playtime playtimeForever;
    @NonNull
    private String imgLogoUrl;

}
