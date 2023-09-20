package io.github.meucafofo.meucafofo_servico.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class OAuth2ResourceServerSecurityConfiguration {

	@Value("${resourceserver.introspection-uri}")
	String introspectionUri;

	@Value("${resourceserver.client-id}")
	String clientId;

	@Value("${resourceserver.client-secret}")
	String clientSecret;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((authorize) -> authorize
						.anyRequest().authenticated())
				.oauth2ResourceServer(
						(oauth2) -> oauth2.opaqueToken((opaque) -> opaque.introspectionUri(this.introspectionUri)
								.introspectionClientCredentials(this.clientId, this.clientSecret)))
				.cors((cors) -> cors.disable());
		return http.build();
	}
	
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/api/usuarios");
    }
    
    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
