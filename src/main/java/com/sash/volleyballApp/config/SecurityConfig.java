//package com.sash.volleyballApp.config;
//
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import java.util.Optional;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity(debug = true)
//@AllArgsConstructor
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(withDefaults())
//                .cors(withDefaults())
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/", "/login", "/register", "/chat/websocket").permitAll()
//                        .requestMatchers("/admin/dashboard").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/admin/dashboard", true)
//                        .failureUrl("/login?error=true")
//                        .permitAll()
//                )
//                .logout((logout) -> logout.logoutSuccessUrl("/"));
//
//        return http.build();
//    }
//
////    @Bean
////    public UserDetailsService userDetailsService() {
////        // Create an admin user with the username "admin" and the password "admin"
////        org.springframework.security.core.userdetails.UserDetails admin =
////                org.springframework.security.core.userdetails.User
////                        .builder()
////                        .username("admin")
////                        .password(passwordEncoder().encode("admin")) // Use BCrypt to encode the password
////                        .roles("ADMIN") // Assign the "ADMIN" role
////                        .build();
////
////        // In-memory user details manager with the admin user
////        return new InMemoryUserDetailsManager(admin);
////    }
//
//
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//
//    //@TODO THIS IS FOR TESTING PURPOSES ONLY, NEED TO CHANGE LATER
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//
//}
//
