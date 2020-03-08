package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private DataSource dataSource;
  
  private static final String USER_SQL="SELECT"
      +" user_id,"
      +" password,"
      +" true"
      +" FROM"
      +" m_user"
      +" WHERE"
      +" user_id=?";
  
  private static final String ROLE_SQL="SELECT"
      +" user_id,"
      +" role"
      +" FROM"
      +" m_user"
      +" WHERE"
      +" user_id=?";

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/webjars/∗∗", "/css/∗∗");
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
    .authorizeRequests()
    .antMatchers("/webjars/**").permitAll() //webjarsへアクセス許可
    .antMatchers("/css/**").permitAll() //cssへアクセス許可
    .antMatchers("/login").permitAll() //ログインページは直リンクOK
    .antMatchers("/signup").permitAll() //ユーザー登録画面は直リンクOK
    .antMatchers("/admin").hasAuthority("ROLE_ADMIN") //アドミンユーザーに許可
    .anyRequest().authenticated(); //それ以外は直リンク禁止
    
    http
    .formLogin()
      .loginProcessingUrl("/login")
      .loginPage("/login")
      .failureUrl("/login")
      .usernameParameter("userId")
      .passwordParameter("password")
      .defaultSuccessUrl("/home", true);
    
    http.csrf().disable();
  }
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication()
      .dataSource(dataSource)
      .usersByUsernameQuery(USER_SQL)
      .authoritiesByUsernameQuery(ROLE_SQL);
  }

}
