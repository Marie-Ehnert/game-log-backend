package src.gamelogbackend.primary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import src.gamelogbackend.core.applicationservices.SteamUserService;
import src.gamelogbackend.core.entites.SteamUser;
import src.gamelogbackend.primary.rest.converter.SteamUserConverter;
import src.gamelogbackend.primary.rest.dto.SteamUserDto;

@RestController
@RequestMapping("/api/steam-user")
public class SteamUserController
{
    private final SteamUserService steamUserService;

    private final SteamUserConverter steamUserConverter;

    @Autowired
    public SteamUserController(SteamUserService steamUserService, SteamUserConverter steamUserConverter)
    {
        this.steamUserService = steamUserService;
        this.steamUserConverter = steamUserConverter;
    }

    @GetMapping("/player-summary")
    public ResponseEntity<SteamUserDto> getPlayerSummary(Authentication authentication)
    {
        SteamUser user = steamUserService.getSteamUser(authentication);
        SteamUserDto steamUserDto = steamUserConverter.toDto(user);
        return new ResponseEntity<>(steamUserDto, HttpStatus.OK);
    }
}
