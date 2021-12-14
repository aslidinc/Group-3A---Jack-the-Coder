package com.jack_the_coder.bilboard_backend.security;

import com.jack_the_coder.bilboard_backend.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * @author Hacı Çakın
 * @date 14.10.2021
 */
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final UserService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity ( UserService userDetailsService , BCryptPasswordEncoder bCryptPasswordEncoder ) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // these url can be accessible in public without auth
    @Override
    protected void configure ( HttpSecurity http ) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers( HttpMethod.POST , SecurityConstants.SIGN_UP_URL )
                .permitAll()
                .antMatchers( HttpMethod.POST , SecurityConstants.SIGN_IN_URL )
                .permitAll()
                .antMatchers( HttpMethod.POST , SecurityConstants.VERIFICATION_EMAIL_URL )
                .permitAll()
                .antMatchers( HttpMethod.POST , SecurityConstants.PASSWORD_RESET_REQUEST_URL )
                .permitAll()
                .antMatchers( HttpMethod.POST , SecurityConstants.PASSWORD_RESET_URL )
                .permitAll()
                .antMatchers( HttpMethod.GET , SecurityConstants.IMAGES )
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter( getAuthenticationFilter() )
                .addFilter( new AuthorizationFilter( authenticationManager() ) )
                .sessionManagement()
                .sessionCreationPolicy( SessionCreationPolicy.STATELESS );

        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure ( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService( userDetailsService ).passwordEncoder( bCryptPasswordEncoder );
    }

    protected AuthenticationFilter getAuthenticationFilter () throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter( authenticationManager() );
        filter.setFilterProcessesUrl( "/auth/signIn" );
        return filter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource () {
        final CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns( Arrays.asList( "*" ) );
        configuration.setAllowedMethods( Arrays.asList( "*" ) );
        configuration.setAllowCredentials( true );
        configuration.setAllowedHeaders( Arrays.asList( "*" ) );

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration( "/**" , configuration );

        return source;
    }

}
