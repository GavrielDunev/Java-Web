package bg.softuni.MobiLeLeLe.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfiguration(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // With this line we allow access to all static resources.
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                // This next line allows access to the home page, login page and registration for everyone.
                .antMatchers("/", "/users/login", "/users/register").permitAll()
                // Here we forbid all other pages for unauthenticated users.
                .antMatchers("/**").authenticated()
                .and()
                // Configures login with login HTML form with two input fields.
                .formLogin()
                // Our login page is located at http://<serveraddress>>:<port>/users/login.
                .loginPage("/users/login")
                // This is the name of the <input...> in the login form where the user enters her email/username/etc.
                // The value of this input will be presented to our User details service.
                // Here if we want we can name the input field differently (email for example).
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                // Here is the name of the <input...> HTML field that keeps the password
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                // The place where we should land in case that the login is successful.
                .defaultSuccessUrl("/")
                // the place where we should land if the login is NOT successful
                .failureForwardUrl("/users/login-error")
                .and()
                .logout()
                // This is the URL which spring will implement for us and will log the user out.
                .logoutUrl("/users/logout")
                // This is where to go after the logout.
                .logoutSuccessUrl("/")
                // Removes the session from the server.
                .invalidateHttpSession(true)
                // Deletes the cookie that references our session.
                .deleteCookies("JSESSIONID");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // This gives spring two important components.
        // 1. Our user details service that translates usernames/emails, phone numbers, etc/
        // to UserDetails
        // 2. Password encoder - the component that can decide if the user password matches

        auth.userDetailsService(this.userDetailsService)
                .passwordEncoder(this.passwordEncoder);
    }
}
