package src.gamelogbackend.core.valueobjects;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@Accessors(fluent = true)
public class Playtime
{
    @NonNull
    Integer value;

    private Playtime(@NonNull Integer playtimeValueInMinutes)
    {
        this.value = playtimeValueInMinutes;
    }

    public Playtime inMinutes()
    {
        return new Playtime(this.value());
    }

    public Playtime inHours()
    {
        return new Playtime(inMinutes().value() / 60);
    }

    public Playtime inDays()
    {
        return new Playtime(inHours().value() / 24);
    }
}
