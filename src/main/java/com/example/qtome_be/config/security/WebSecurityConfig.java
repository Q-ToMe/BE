//package com.example.qtome_be.config.security;
//
//import com.example.qtome_be.member.MemberService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.security.config.Customizer;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig {
//
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//    @Autowired
//    private MemberService memberService;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
//                .and()
//                .csrf().disable()
//                .authorizeRequests(auth -> auth
//                        .antMatchers("/member/authenticate", "/swagger-ui/**", "/order/**").permitAll()
//                        .antMatchers(HttpMethod.OPTIONS).permitAll()
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                );
//
//        // 필요한 경우 다른 커스텀 필터를 추가할 수 있습니다.
//        // http.addFilterAfter(new JwtRequestFilter(jwtTokenUtil, memberService), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    // WebSecurityCustomizer 빈을 사용하여 특정 경로를 무시
//    @Bean
//    public Customizer<org.springframework.security.config.annotation.web.builders.WebSecurity> webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/h2-console/**", "/actuator/**", "/article/**");
//    }
//}
