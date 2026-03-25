package src.gamelogbackend.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import src.gamelogbackend.core.valueobjects.SteamID;

import java.util.List;

@Service
public class SteamUserAuthenticationService {

    public void authenticateUser(String steamId,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("USER_ROLE");
        SteamAuthenticationToken steamAuthenticationToken = new SteamAuthenticationToken(SteamID.fromString(steamId), authorities);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(steamAuthenticationToken);
        SecurityContextHolder.setContext(context);

        HttpSessionSecurityContextRepository repository = new HttpSessionSecurityContextRepository();
        repository.saveContext(context, request, response);
    }

    public boolean isUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication != null;
    }

}
