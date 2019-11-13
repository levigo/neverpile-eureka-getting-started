package com.neverpile.eureka.tutorial01.server.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  public SecurityConfig() {
    super(true);
  }

  @Override
  public void configure(final HttpSecurity http) throws Exception {
    // @formatter:off
    http
      .addFilter(new WebAsyncManagerIntegrationFilter())
      .securityContext() // Sets up management of the SecurityContext on the SecurityContextHolder between request's.
      .and().headers() // Adds the Security headers to the response.
      .and().exceptionHandling() // Allows configuring exception handling.
      .and().sessionManagement() //Allows configuring of Session Management.
      .and().requestCache() // Allows configuring the Request Cache.
      .and().anonymous() // Allows configuring how an anonymous user is represented.
      .and().authorizeRequests() // Allows restricting access based upon the HttpServletRequest using
        .antMatchers("/api/**") // List of path patterns with any http method
          .authenticated() // Specify that URLs are allowed by any authenticated user.
    ;
    // @formatter:on
  }
}
