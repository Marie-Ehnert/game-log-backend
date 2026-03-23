package src.gamelogbackend.secondary.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Table(name= "discord_user")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscordUserDbEntity {

    @Id
    private String discordId;
    @NonNull
    private String discordUserName;
    @NonNull
    private String discordEmail;

}
