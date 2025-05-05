package de.plimplom.gamba.server.config;

import com.vaadin.flow.spring.security.RequestUtil;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import com.vaadin.hilla.route.RouteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends VaadinWebSecurity {

    private final RouteUtil routeUtil;

    private final RequestUtil requestUtil;
    //    private final RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    private final GambaConfig gambaConfig;

    public SecurityConfig(RouteUtil routeUtil, RequestUtil requestUtil, GambaConfig gambaConfig) {
        this.routeUtil = routeUtil;
        this.requestUtil = requestUtil;
        this.gambaConfig = gambaConfig;
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("gamba")
//                .password(passwordEncoder().encode(gambaConfig.getKey()))
//                .authorities("GAMBA");
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        super.configure(http);

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf
                .ignoringRequestMatchers(
                        requestUtil::isFrameworkInternalRequest,  // Vaadin internal
                        AntPathRequestMatcher.antMatcher("/api/**")  // API endpoints
                )
        );
    }

    // Define a user details service or authentication provider
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("gamba")
                .password("{bcrypt}" + new BCryptPasswordEncoder().encode(gambaConfig.getKey()))
                .roles("GAMBA")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

//    @Bean
//    public UserDetailsManager userDetailsService() {
//        // Configure users and roles in memory
//        return new InMemoryUserDetailsManager(
//                // the {noop} prefix tells Spring that the password is not encoded
//                User.withUsername("gamba").password("{noop}" + gambaConfig.getKey()).roles("GAMBA").build()
////                User.withUsername("admin").password("{noop}admin").roles("ADMIN", "USER").build()
//        );
//    }
}
