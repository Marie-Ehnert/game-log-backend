package src.gamelogbackend.primary;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import src.gamelogbackend.core.applicationservices.SteamUserService;
import src.gamelogbackend.core.entites.SteamUser;
import src.gamelogbackend.security.SteamUserAuthenticationService;

@Controller
@AllArgsConstructor
public class ViewController {

    private SteamUserAuthenticationService steamUserAuthenticationService;

    private SteamUserService steamUserService;

    @GetMapping("/")
    public String home() {
        if (steamUserAuthenticationService.isUserAuthenticated()) {
            return "redirect:/index";
        }
        return "Login";
    }

    @GetMapping("/index")
    public String index()
    {
        return "Index";
    }

    @GetMapping("/success")
    public String success() { return "Success"; }

    @GetMapping("/failure")
    public String failure() {
        return "Failure";
    }

    @GetMapping("/login")
    public String login() { return "Login"; }

    @GetMapping("/profile")
    public String profile(Model model)
    {
        SteamUser steamUser = steamUserService.getSteamUser(steamUserAuthenticationService.getAuthentication());
        model.addAttribute("steamUser", steamUser);
        return "Profile";
    }

    @GetMapping("/login/steam")
    public String loginSteam() {
        return "redirect:" + steamUserAuthenticationService.buildUriForSteamLoginRequest();
    }

    @GetMapping("/login/callback")
    public String loginCallback(HttpServletRequest request, HttpServletResponse responseForCookie)
    {
        return steamUserAuthenticationService.handleSteamCallback(request, responseForCookie);
    }
}
