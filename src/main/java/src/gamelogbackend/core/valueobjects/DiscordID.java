package src.gamelogbackend.core.valueobjects;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@Accessors(fluent = true)
public class DiscordID {

    @NonNull
    String value;

    private DiscordID(@NonNull String id) { this.value = id;}

    public static DiscordID fromString(@NonNull String idString) {
        return new DiscordID(idString);
    }
}
