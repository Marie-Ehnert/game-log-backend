package src.gamelogbackend.security;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import src.gamelogbackend.core.entites.DiscordUser;
import src.gamelogbackend.core.ports.IDiscordUserRepositoryPort;
import src.gamelogbackend.core.valueobjects.DiscordID;
import src.gamelogbackend.secondary.exceptions.DiscordUserRepositoryException;

import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class GameLogOAuth2UserService extends DefaultOAuth2UserService {

    private IDiscordUserRepositoryPort repositoryPort;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        try {
            OAuth2User oAuth2User = super.loadUser(userRequest);

            String discordId = oAuth2User.getAttribute("id");
            String username = oAuth2User.getAttribute("username");
            String email = oAuth2User.getAttribute("email");

            System.out.println("ID= " + discordId + " username= " + username + " email= " + email);

            DiscordUser domainEntity = DiscordUser.builder()
                    .discordID(DiscordID.fromString(Objects.requireNonNull(discordId)))
                    .discordUserName(Objects.requireNonNull(username))
                    .discordEmail(Objects.requireNonNull(email))
                    .build();

            repositoryPort.registerNewDiscordUser(domainEntity);

            return oAuth2User;
        } catch (Exception e) {
            throw new DiscordUserRepositoryException("Fehler beim Speichern des neuen Users");
        }

    }
}
