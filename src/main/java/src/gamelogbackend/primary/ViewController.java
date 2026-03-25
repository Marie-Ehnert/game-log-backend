package src.gamelogbackend.primary;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import src.gamelogbackend.security.SteamUserAuthenticationService;

@Controller
@AllArgsConstructor
public class ViewController {

    private SteamUserAuthenticationService steamUserAuthenticationService;

    public static final String STEAM_CALLBACK_PATH = "/login/callback";

    @GetMapping("/")
    public String home() {
        if (steamUserAuthenticationService.isUserAuthenticated()) {
            return "redirect:/success";
        }
        return "Login";
    }

    @GetMapping("/success")
    public String success() { return "Success"; }

    @GetMapping("/failure")
    public String failure() {
        return "Failure";
    }

    @GetMapping("/login")
    public String login() { return "Login"; }

    @GetMapping("/login/steam")
    public String loginSteam() {
        String base = "https://steamcommunity.com/openid/login?";
        String openIdNs = "openid.ns=http://specs.openid.net/auth/2.0&";
        String openIdClaimed = "openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select&";
        String openIdIdentity = "openid.identity=http://specs.openid.net/auth/2.0/identifier_select&";
        String openIdReturn = "openid.return_to=http://localhost:8080" + STEAM_CALLBACK_PATH + "&";
        String openIdRealm = "openid.realm=http://localhost:8080&";
        String openIdSetUp = "openid.mode=checkid_setup";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(base + openIdNs + openIdClaimed + openIdIdentity + openIdReturn + openIdRealm + openIdSetUp);

        return "redirect:" + uriBuilder.toUriString();
    }

    @GetMapping("/login/callback")
    public String handleSteamCallBack(HttpServletRequest request, HttpServletResponse responseForCookie) {

        MultiValueMap<String, String> requestParameters = new LinkedMultiValueMap<>();
        request.getParameterMap().forEach((key, values) -> requestParameters.add(key, values[0]));

        requestParameters.set("openid.mode", "check_authentication");

        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.postForObject("https://steamcommunity.com/openid/login", requestParameters, String.class);

        if (response != null && response.contains("is_valid:true")) {
            String claimedId = request.getParameter("openid.claimed_id");
            String steamId = claimedId.replace("https://steamcommunity.com/openid/id/", "");

            steamUserAuthenticationService.authenticateUser(steamId, request, responseForCookie);

            return "Success";
        }

        return "Failure";
    }
}
