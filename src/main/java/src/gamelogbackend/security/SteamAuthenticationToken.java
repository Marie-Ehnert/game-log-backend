package src.gamelogbackend.security;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import src.gamelogbackend.core.valueobjects.SteamID;

import java.util.Collection;

@Getter
@Accessors(fluent = true)
public class SteamAuthenticationToken extends AbstractAuthenticationToken {

    private final SteamID steamID;

    public SteamAuthenticationToken(SteamID steamId, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.steamID = steamId;
        setAuthenticated(true);
    }

    @Override
    public String getPrincipal() {
        return steamID().value();
    }

    // Credentials are not needed here since Steam as our Authorization Provider already validated the users Credentials
    @Override
    public String getCredentials() {
        return null;
    }
}
