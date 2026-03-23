package src.gamelogbackend.core.valueobjects;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.Objects;

@Value
@Accessors(fluent = true)
public class DiscordID {

    @NonNull
    String value;

    private DiscordID(@NonNull String id) { this.value = id;}

    public static DiscordID fromString(@NonNull String idString) {
        Integer discordIdInt = Integer.getInteger(idString);
        if (Objects.isNull(discordIdInt)) {
            throw new IllegalArgumentException(idString);
        }
        return new DiscordID(idString);
    }
}
