package src.gamelogbackend.core.valueobjects;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

import java.util.Objects;

@Value
@Accessors(fluent = true)
public class AppID {

    @NonNull
    String value;

    private AppID(@NonNull String id) { this.value = id;}

    public static AppID fromString(@NonNull String idString) {
        Integer appIdInt = Integer.getInteger(idString);
        if (Objects.isNull(appIdInt)) {
            throw new IllegalArgumentException(idString);
        }
        return new AppID(idString);
    }
}
