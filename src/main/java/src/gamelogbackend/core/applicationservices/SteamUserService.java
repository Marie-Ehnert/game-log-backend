package src.gamelogbackend.core.applicationservices;

import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import src.gamelogbackend.core.applicationservices.exception.SteamUserServiceException;
import src.gamelogbackend.core.entites.SteamUser;
import src.gamelogbackend.primary.rest.converter.SteamUserConverter;
import src.gamelogbackend.primary.rest.dto.PlayerSummaryDto;
import src.gamelogbackend.primary.rest.dto.SteamResponseWrapper;
import src.gamelogbackend.primary.util.EnvLoader;

@Service
public class SteamUserService
{
    private final SteamUserConverter steamUserConverter;

    @Autowired
    public SteamUserService(SteamUserConverter steamUserConverter)
    {
        this.steamUserConverter = steamUserConverter;
    }

    @NonNull
    public SteamUser getSteamUser(Authentication authentication)
    {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SteamUserServiceException("No authenticated Steam user found");
        }

        String steamId = authentication.getName();
        String steamApiKey = EnvLoader.getEnvVariable("STEAM_API_KEY");
        SteamResponseWrapper wrapper = RestClient.create()
            .get()
            .uri(uriBuilder -> uriBuilder
                .scheme("https")
                .host("api.steampowered.com")
                .path("/ISteamUser/GetPlayerSummaries/v2/")
                .queryParam("key", steamApiKey)
                .queryParam("steamids", steamId)
                .build())
            .retrieve()
            .body(SteamResponseWrapper.class);

        if (wrapper == null || wrapper.response() == null || wrapper.response().players().isEmpty()) {
            throw new SteamUserServiceException("Empty Response from Steam API");
        }

        PlayerSummaryDto rawData = wrapper.response().players().getFirst();

        return steamUserConverter.toDomain(rawData);
    }
}
