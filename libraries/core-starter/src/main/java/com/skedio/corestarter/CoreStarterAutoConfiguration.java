package com.skedio.corestarter;

import com.skedio.corestarter.advice.GlobalRestAdviceController;
import com.skedio.corestarter.auditor.AuditorProvider;
import com.skedio.corestarter.auditor.BaseAuditorConfiguration;
import com.skedio.corestarter.auth.*;
import com.skedio.corestarter.configuration.JacksonMapperConfig;
import com.skedio.corestarter.logs.FunctionLogAspect;
import com.skedio.corestarter.trace.TraceFilter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@AutoConfiguration
@EnableConfigurationProperties(CoreStarterProperties.class)
public class CoreStarterAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public AuditorProvider auditorProvider() {
        return  () -> Optional.of("System");
    }

    @Bean
    @ConditionalOnMissingBean
    public BaseAuditorConfiguration baseAuditorConfiguration(AuditorProvider auditorProvider) {
        return new BaseAuditorConfiguration(auditorProvider);
    }

    @Bean
    @ConditionalOnMissingBean
    public GlobalRestAdviceController globalRestControllerAdvice() {
        return new GlobalRestAdviceController();
    }

    @Bean
    @ConditionalOnMissingBean
    public DateTimeProvider dateTimeProvider() {
        return  () -> Optional.of(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));
    }

    @Bean
    @ConditionalOnMissingBean
    public TraceFilter traceFilter() {
        return new TraceFilter();
    }

    @Bean
    @ConditionalOnMissingBean
    public FunctionLogAspect functionLogAspect() {
        return new FunctionLogAspect();
    }

    @Bean
    @ConditionalOnMissingBean
    public JacksonMapperConfig jacksonMapperConfig() {
        return new JacksonMapperConfig();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtProvider jwtProvider(CoreStarterProperties properties) {
        return new JwtProvider(properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtProvider jwtProvider) {
        return new JwtAuthenticationFilter(jwtProvider);
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityFilterChain securityConfiguration(
            JwtAuthenticationFilter filter,
            AccessDeniedHandler accessDeniedHandler,
            AuthenticationEntryPoint entryPoint,
            HttpSecurity http,
            CoreStarterProperties properties
    ) throws Exception {
        return new SecurityConfiguration(filter, accessDeniedHandler, entryPoint, properties).securityFilterChain(http);
    }

    @Bean
    @ConditionalOnMissingBean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
