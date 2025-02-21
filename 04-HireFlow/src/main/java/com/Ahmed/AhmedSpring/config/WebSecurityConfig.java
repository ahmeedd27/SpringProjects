package com.Ahmed.AhmedSpring.config;

import com.Ahmed.AhmedSpring.services.MyUserDetailsService;
import com.Ahmed.AhmedSpring.util.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final MyUserDetailsService myuserDetailsService;
    private final MyCustomSuccessHandler myCustomSuccessHandler;

    @Autowired
    public WebSecurityConfig( MyUserDetailsService uds , MyCustomSuccessHandler myCustomSuccessHandler){
        this.myuserDetailsService=uds;
        this.myCustomSuccessHandler=myCustomSuccessHandler;

    }

    private final String[] publicUrl = {"/",
            "/global-search/**",
            "/register",
            "/register/**",
            "/webjars/**",
            "/resources/**",
            "/assets/**",
            "/css/**",
            "/summernote/**",
            "/js/**",
            "/*.css",
            "/*.js",
            "/*.js.map",
            "/fonts**", "/favicon.ico", "/resources/**", "/error"}; // urls no need to login to access them

    @Bean
    public SecurityFilterChain sfc(HttpSecurity http) throws Exception {
       return    http.authenticationProvider(authenticationProvider())
               .authorizeHttpRequests( auth ->
           {
               auth.requestMatchers(publicUrl).permitAll();
               auth.requestMatchers("/dashboard/**").hasAnyAuthority("Recruiter", "Candidate");
               auth.anyRequest().authenticated();
           }
          ).formLogin(f -> f.loginPage("/login").permitAll()
                       .successHandler(myCustomSuccessHandler)
               ).logout(l -> {
                   l.logoutUrl("/logout");
                   l.logoutSuccessUrl("/");
               }).cors(Customizer.withDefaults())
               .csrf(AbstractHttpConfigurer::disable)

               .build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuth=new DaoAuthenticationProvider();
        daoAuth.setUserDetailsService(myuserDetailsService);
        daoAuth.setPasswordEncoder(passwordEncoder());
        return daoAuth;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }
}
