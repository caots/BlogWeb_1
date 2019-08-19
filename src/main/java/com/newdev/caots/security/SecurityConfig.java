package com.newdev.caots.security;


import com.newdev.caots.service_impl.JWTService;
import com.newdev.caots.service_impl.UserDetailsService_Impl;
import com.newdev.caots.service_impl.admin.AppUserService_Impl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)

public class SecurityConfig  extends WebSecurityConfigurerAdapter {


    private final UserDetailsService_Impl userDetailsService;
    private final AppUserService_Impl appUserService;
    private final JWTService jwtService;

    public SecurityConfig(UserDetailsService_Impl userDetailsService, AppUserService_Impl appUserService, JWTService jwtService) {
        this.userDetailsService = userDetailsService;
        this.appUserService = appUserService;
        this.jwtService = jwtService;
    }

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();

        http.antMatcher("/api/**")
                .authorizeRequests()
                .antMatchers("/api/**/public/**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic().authenticationEntryPoint(restAuthenticationEntryPoint())
                .and()
                .addFilterBefore(new JWTAuthorizationFilter(authenticationManager(), appUserService, jwtService), JWTAuthorizationFilter.class)
                .addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());

        //stateless
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}