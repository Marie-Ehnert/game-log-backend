package src.gamelogbackend.core.valueobjects;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@Accessors(fluent = true)
public class SteamID {

    @NonNull
    String value;

    private SteamID(@NonNull String id) { this.value = id;}

    public static SteamID fromString(@NonNull String idString) {
        return new SteamID(idString);
    }

}
