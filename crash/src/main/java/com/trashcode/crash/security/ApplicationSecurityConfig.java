package com.trashcode.crash.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trashcode.crash.auth.ApplicationUserService;
import com.trashcode.crash.auth.UrlRedirector;
import com.trashcode.crash.userServices.getCurrentUser;
import com.trashcode.crash.userServices.removeCurrentUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    // @Autowired
    // private UserDetailsService userDetailsService;

    @Autowired
    private ApplicationUserService applicationUserService;

    @Autowired
    private getCurrentUser getCurrentUser;

    @Autowired
    private static removeCurrentUser rCurrentUser;

    @Autowired
    private UrlRedirector urlRedirector;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    static Boolean login = false;

    @Bean
    public UserDetailsService userDetailsService() {
        return new ApplicationUserService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(new passwordConfig().passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService());
        return authProvider;
    }

    // @Bean
    // public AuthenticationProvider authProvider() {
    // DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    // provider.setUserDetailsService();
    // // provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
    // // provider.setPasswordEncoder(new BCryptPasswordEncoder());
    // provider.setPasswordEncoder(new passwordConfig().passwordEncoder());
    // return provider;
    // }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests(requests -> requests
                        .antMatchers("/css/*", "/js/*", "/image/*", "/jquery-ui/*", "/jpg").permitAll()
                        // .antMatchers("/productProfile/", "/cart/**",
                        // "/cart/", "/UpdatePassword", "/cart/**", "/createWallet",
                        // "/addMoney", "/buy", "/addOrder/**", "/addPic/**", "/checkoutAll",
                        // "/profile", "/order",
                        // "/cart/", "/UpdatePassword")
                        // .hasAnyAuthority("ADMIN", "USER", "SELLER", "GODFATHER")

                        // .antMatchers("/Seller/*").hasAuthority("SELLER")
                        // // .antMatchers("/u*").hasAnyAuthority("ADMIN", "USER", "SELLER",
                        // "GODFATHER")
                        // .antMatchers("/a/*").hasAnyAuthority("ADMIN")
                        // .anyRequest().authenticated()

                        .antMatchers("/recentItems", "/tempCart",
                                "/login", "/add_user", "/saveUser",
                                "/ProductVerify", "/productProfile/**",
                                "/autocomplete", "/autocomplete/**",
                                "/changeField/**", "/",
                                "/search", "/search/**", "/similarProduct",
                                "/add_Seller", "/FillterProducts/**", "/userConstruction",
                                "/allUser", "/allUser/**", "/electronic/", "/electronic/**",
                                "/productProfile/**",
                                "/similarProduct/**", "/logout")
                        .permitAll()
                        // .antMatchers("/", "/hello/*", "/css/*", "/js/*", "/image/*", "/jquery-ui/*",
                        // "/jpg")
                        // .permitAll()
                        // .anyRequest()
                        // .authenticated()
                        // .antMatchers("/", "/hello/*", "/css/*", "/js/*", "/image/*",
                        // "/jquery-ui/*","/jpg") .permitAll()
                        .antMatchers("/Seller/**").hasAuthority("SELLER")
                        .antMatchers("/a/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/doLogin")
                        .successHandler(urlRedirector)
                        .permitAll())
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/show")
                        .logoutSuccessHandler(new LogoutSuccessHandler() {
                            @Override
                            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                    Authentication authentication) throws IOException, ServletException {

                                System.out.println("Removed Successfully :)");
                                rCurrentUser.removeUser(authentication.getName());
                            }
                        }).permitAll());

    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception
    // {

    // auth.userDetailsService(applicationUserService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    // }

}
