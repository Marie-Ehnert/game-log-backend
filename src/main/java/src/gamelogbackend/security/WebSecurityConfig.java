package src.gamelogbackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    @Autowired
    public GameLogOAuth2UserService gameLogOAuth2UserService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        // @formatter:off
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/login", "/error", "/styles/**").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                // Add Customizer.withDefaults() here:
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(gameLogOAuth2UserService)
                        )
                        .defaultSuccessUrl("/", true)
                )
                // If you need logout, modern Spring often requires this too:
                .logout(l -> l
                        .logoutSuccessUrl("/").permitAll()
                );
        // @formatter:on

        return http.build();
    }

}