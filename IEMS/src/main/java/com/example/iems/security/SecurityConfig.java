package com.example.iems.security;


import com.example.iems.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;


    public SecurityConfig(JwtAuthFilter jwtAuthFilter, UserService userService, PasswordEncoder passwordEncoder) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
/*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x ->
                        x.requestMatchers("/**").permitAll()
                )
                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }*/
/*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x ->
                        x.requestMatchers("/appUser/generateToken/**","/login/**","/appSupply/free/**","/appUser/free/**").permitAll()
                )
                .authorizeHttpRequests(x ->
                        x
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/appUser/admin/**").hasRole("ADMIN")
                                .requestMatchers("/appCompany/admin/**").hasRole("ADMIN")
                                .requestMatchers("/appCompany/adMan/**").hasAnyRole("ADMIN","MANAGER")
                                .requestMatchers("/manager/**").hasRole("MANAGER")
                                .requestMatchers("/appCompany/manager/**").hasRole("MANAGER")
                                .requestMatchers("/appProduct/manager/**").hasRole("MANAGER")
                                .requestMatchers("/employee/**").hasRole("EMPLOYEE")
                                .requestMatchers("/appProduct/employee/**").hasRole("EMPLOYEE")
                                .requestMatchers("/appProduct/manEmp/**").hasAnyRole("EMPLOYEE","MANAGER")
                                .requestMatchers("/sendEmail/**").hasAnyRole("EMPLOYEE","MANAGER","SUPPLIER")
                                .requestMatchers("/appSupply/manager/**").hasRole("MANAGER")
                                .requestMatchers("/appSupply/admin/**").hasRole("ADMIN")
                                .requestMatchers("/supplier/**").hasRole("SUPPLIER")
                                .requestMatchers("/appSupply/supplier/**").hasRole("SUPPLIER")
                                .requestMatchers("/profile/**").hasAnyRole("ADMIN","MANAGER","SUPPLIER","EMPLOYEE")


                )
                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .build();
    }*/



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home","/appUser/generateToken/**","/login/**","/appSupply/free/**","/appUser/free/**").permitAll()
                )
                .authorizeHttpRequests(x ->
                        x
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/appUser/admin/**").hasRole("ADMIN")
                                .requestMatchers("/appCompany/admin/**").hasRole("ADMIN")
                                .requestMatchers("/appCompany/adMan/**").hasAnyRole("ADMIN","MANAGER")
                                .requestMatchers("/manager/**").hasRole("MANAGER")
                                .requestMatchers("/appCompany/manager/**").hasRole("MANAGER")
                                .requestMatchers("/appProduct/manager/**").hasRole("MANAGER")
                                .requestMatchers("/employee/**").hasRole("EMPLOYEE")
                                .requestMatchers("/appProduct/employee/**").hasRole("EMPLOYEE")
                                .requestMatchers("/appProduct/manEmp/**").hasAnyRole("EMPLOYEE","MANAGER")
                                .requestMatchers("/sendEmail/**").hasAnyRole("EMPLOYEE","MANAGER","SUPPLIER")
                                .requestMatchers("/appSupply/manager/**").hasRole("MANAGER")
                                .requestMatchers("/appSupply/admin/**").hasRole("ADMIN")
                                .requestMatchers("/supplier/**").hasRole("SUPPLIER")
                                .requestMatchers("/appSupply/supplier/**").hasRole("SUPPLIER")
                                .requestMatchers("/profile/**").hasAnyRole("ADMIN","MANAGER","SUPPLIER","EMPLOYEE")
                                .anyRequest().authenticated()

                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/hello", true)
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .build();
    }



    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();

    }

}
