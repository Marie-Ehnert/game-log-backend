package src.gamelogbackend.primary.rest.converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UnixTimestampConverter
{
    public Instant toInstant(String unixTimestampString)
    {
        long unixSeconds = Long.parseLong(unixTimestampString);
        return Instant.ofEpochSecond(unixSeconds);
    }

    public LocalDateTime toLocalDateTime(String unixTimestampString)
    {
        Instant instant = toInstant(unixTimestampString);
        return LocalDateTime.ofInstant(instant, ZoneId.of("CET"));
    }
}
