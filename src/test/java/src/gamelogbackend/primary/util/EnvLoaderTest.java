package src.gamelogbackend.primary.util;

import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

class EnvLoaderTest
{
    public final String STEAM_PLAYER_SUMMARIES_BASE_URL = "https://api.steampowered"
        + ".com/ISteamUser/GetPlayerSummaries/v2/";

    @Test
    void getEnvVariableTest()
    {
        String result = EnvLoader.getEnvVariable("TEST");
        assert Objects.equals(result, "test");
    }

    @Test
    void test()
    {
        RestClient restClient = RestClient.create();
        String dto = restClient.get()
            .uri(STEAM_PLAYER_SUMMARIES_BASE_URL + "?key=" + "9341AED8F57E46FA9E1E75DB75B7D8A3" + "&steamids="
                + "76561198132367664")
            .retrieve()
            .body(String.class);
        System.out.println(dto);
    }
}