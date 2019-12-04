package com.neverpile.eureka.tutorial01.server.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  public SecurityConfig() {
    super(true);
  }

  @Override
  public void configure(final HttpSecurity http) throws Exception {
    // @formatter:off
    http
        .csrf().disable()
        .httpBasic().and()
        .authorizeRequests()
          .antMatchers("/api/**")
            .authenticated()
    ;
    // @formatter:on
  }
}
