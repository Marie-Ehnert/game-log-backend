package src.gamelogbackend.security;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import src.gamelogbackend.core.entites.DiscordUser;
import src.gamelogbackend.core.ports.IDiscordUserRepositoryPort;

import java.util.Objects;

@Service
@AllArgsConstructor
public class GameLogOAuth2UserService extends DefaultOAuth2UserService {

    private IDiscordUserRepositoryPort repositoryPort;

    @Transactional
    public OAuth2User registerFirstTimeDiscordUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        DiscordUser domainEntity = DiscordUser.builder()
                .discordID(Objects.requireNonNull(oAuth2User.getAttribute("id")))
                .discordUserName(Objects.requireNonNull(oAuth2User.getAttribute("username")))
                .discordEmail(Objects.requireNonNull(oAuth2User.getAttribute("email")))
                .build();

        repositoryPort.registerNewDiscordUser(domainEntity);

        return oAuth2User;
    }
}
