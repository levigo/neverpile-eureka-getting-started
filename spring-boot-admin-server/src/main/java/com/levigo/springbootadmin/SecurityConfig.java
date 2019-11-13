package com.levigo.springbootadmin;

import java.util.Collections;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import de.codecentric.boot.admin.server.config.AdminServerProperties;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final String adminContextPath;

  public SecurityConfig(final AdminServerProperties adminServerProperties) {
    super(false);
    this.adminContextPath = adminServerProperties.getContextPath();
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
      // @formatter:off
      SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
      successHandler.setTargetUrlParameter("redirectTo");
      http.authorizeRequests()
          .antMatchers(adminContextPath + "/assets/**").permitAll()
          .antMatchers(adminContextPath + "/login").permitAll()
          .anyRequest().authenticated()
          .and()
      .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
      .logout().logoutUrl(adminContextPath + "/logout").and()
      .httpBasic().and()
      .csrf().disable();
      // @formatter:on
  }

  @Override
  public void configure(final WebSecurity web) throws Exception {
    web.ignoring().antMatchers(HttpMethod.OPTIONS);
  }

  @Bean
  public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.applyPermitDefaultValues();
    config.setAllowCredentials(true);
    config.setAllowedOrigins(Collections.singletonList("*"));
    config.setAllowedHeaders(Collections.singletonList("*"));
    config.setAllowedMethods(Collections.singletonList("*"));
    config.setExposedHeaders(Collections.singletonList("content-length"));
    config.setMaxAge(3600L);
    source.registerCorsConfiguration("/**", config);
    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
    bean.setOrder(0);
    return bean;
  }
}
