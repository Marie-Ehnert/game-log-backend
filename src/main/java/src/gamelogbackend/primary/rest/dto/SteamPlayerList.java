package src.gamelogbackend.primary.rest.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SteamPlayerList(@JsonProperty("players") List<PlayerSummaryDto> players)
{
}
