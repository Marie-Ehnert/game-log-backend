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
public class PlayerSummaryDto
{
    @NonNull
    private String steamid;

    @NonNull
    private String communityvisibilitystate;

    @NonNull
    private String profilestate;

    @NonNull
    private String personaname;

    @NonNull
    private String lastlogoff;

    @NonNull
    private String profileurl;

    @NonNull
    private String avatarfull;
}
