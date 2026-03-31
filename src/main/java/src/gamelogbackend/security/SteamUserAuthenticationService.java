package src.gamelogbackend.security;

import java.util.List;

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import src.gamelogbackend.core.valueobjects.SteamID;

@Service
public class SteamUserAuthenticationService
{
    public static final String STEAM_CALLBACK_PATH = "/login/callback";

    public boolean isUserAuthenticated()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication != null;
    }

    public Authentication getAuthentication()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String buildUriForSteamLoginRequest()
    {
        String base = "https://steamcommunity.com/openid/login?";
        String openIdNs = "openid.ns=http://specs.openid.net/auth/2.0&";
        String openIdClaimed = "openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select&";
        String openIdIdentity = "openid.identity=http://specs.openid.net/auth/2.0/identifier_select&";
        String openIdReturn = "openid.return_to=http://localhost:8080" + STEAM_CALLBACK_PATH + "&";
        String openIdRealm = "openid.realm=http://localhost:8080&";
        String openIdSetUp = "openid.mode=checkid_setup";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(
            base + openIdNs + openIdClaimed + openIdIdentity + openIdReturn + openIdRealm + openIdSetUp);
        return uriBuilder.toUriString();
    }

    public String handleSteamCallback(HttpServletRequest request, HttpServletResponse responseForCookie)
    {

        MultiValueMap<String, String> requestParameters = new LinkedMultiValueMap<>();
        request.getParameterMap().forEach((key, values) -> requestParameters.add(key, values[0]));

        requestParameters.set("openid.mode", "check_authentication");

        RestTemplate restTemplate = new RestTemplate();

        String response =
            restTemplate.postForObject("https://steamcommunity.com/openid/login", requestParameters, String.class);

        if (response != null && response.contains("is_valid:true")) {
            String claimedId = request.getParameter("openid.claimed_id");
            String steamId = claimedId.replace("https://steamcommunity.com/openid/id/", "");

            authenticateUser(steamId, request, responseForCookie);

            return "Index";
        }

        return "Failure";
    }

    private void authenticateUser(String steamId,
        HttpServletRequest request,
        HttpServletResponse response)
    {

        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("USER_ROLE");
        SteamAuthenticationToken steamAuthenticationToken =
            new SteamAuthenticationToken(SteamID.fromString(steamId), authorities);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(steamAuthenticationToken);
        SecurityContextHolder.setContext(context);

        HttpSessionSecurityContextRepository repository = new HttpSessionSecurityContextRepository();
        repository.saveContext(context, request, response);
    }
}
