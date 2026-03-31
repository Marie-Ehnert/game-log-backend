package src.gamelogbackend.primary.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SteamResponseWrapper(@JsonProperty("response") SteamPlayerList response)
{
}
