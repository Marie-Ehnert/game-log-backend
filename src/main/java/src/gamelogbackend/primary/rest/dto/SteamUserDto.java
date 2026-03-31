package src.gamelogbackend.primary.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SteamUserDto
{
    @NonNull
    private String steamId;

    @NonNull
    private String steamUsername;

    @NonNull
    private String profileUrl;

    @NonNull
    private String communityVisibilityState;

    @NonNull
    private String profileState;

    @NonNull
    private String lastLogOff;

    @NonNull
    private String avatarUrl;
}
