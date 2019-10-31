package com.sap.academy.sales.service.configuration;


import com.sap.cloud.security.xsuaa.XsuaaServiceConfiguration;
import com.sap.cloud.security.xsuaa.token.TokenAuthenticationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.core.convert.converter.Converter;

@Configuration
@EnableWebSecurity
@EnableCaching
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private XsuaaServiceConfiguration xsuaaServiceConfiguration;

    // configure Spring Security, demand authentication and specific scopes
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        //@formatter:off
        httpSecurity
        .anonymous()
        .and()
        .csrf().disable()


                // csrf handled by approuter
              /*  .csrf().disable()
                // oauth authorization checks TODO: define common role checks here!
                .authorizeRequests().anyRequest().permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                // session handled by approuter
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .oauth2ResourceServer().jwt().jwtAuthenticationConverter(getJwtAuthenticationConverter())*/
        ;
        //@formatter:on
    }

    /**
     * Customizes how GrantedAuthority are derived from a Jwt
     */
//    Converter<Jwt, AbstractAuthenticationToken> getJwtAuthenticationConverter() {
//        TokenAuthenticationConverter converter = new TokenAuthenticationConverter(xsuaaServiceConfiguration);
//        converter.setLocalScopeAsAuthorities(true);
//        return converter;
//    }


}
