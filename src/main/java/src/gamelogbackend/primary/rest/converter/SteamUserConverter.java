package src.gamelogbackend.primary.rest.converter;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import src.gamelogbackend.core.entites.SteamUser;
import src.gamelogbackend.core.valueobjects.SteamID;
import src.gamelogbackend.primary.rest.dto.PlayerSummaryDto;
import src.gamelogbackend.primary.rest.dto.SteamUserDto;

@AllArgsConstructor
@Component
public class SteamUserConverter
{
    private UnixTimestampConverter unixTimestampConverter;

    public SteamUser toDomain(@NonNull PlayerSummaryDto dto)
    {
        return SteamUser.builder()
            .steamId(SteamID.fromString(dto.getSteamid()))
            .communityVisibilityState(dto.getCommunityvisibilitystate())
            .profileState(dto.getProfilestate())
            .steamUsername(dto.getPersonaname())
            .lastLogOff(unixTimestampConverter.toLocalDateTime(dto.getLastlogoff()))
            .profileUrl(dto.getProfileurl())
            .avatarUrl(dto.getAvatarfull())
            .build();
    }

    public SteamUserDto toDto(@NonNull SteamUser domain)
    {
        return SteamUserDto.builder()
            .steamId(domain.getSteamUsername())
            .communityVisibilityState(domain.getCommunityVisibilityState())
            .profileState(domain.getProfileState())
            .steamUsername(domain.getSteamUsername())
            .lastLogOff(domain.getLastLogOff().toString())
            .profileUrl(domain.getProfileUrl())
            .avatarUrl(domain.getAvatarUrl())
            .build();
    }
}
