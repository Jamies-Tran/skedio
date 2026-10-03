package com.skedio.corestarter;

import com.skedio.corestarter.advice.GlobalRestAdviceController;
import com.skedio.corestarter.auditor.AuditorProvider;
import com.skedio.corestarter.auditor.BaseAuditorConfiguration;
import com.skedio.corestarter.configuration.JacksonMapperConfig;
import com.skedio.corestarter.logs.FunctionLogAspect;
import com.skedio.corestarter.trace.TraceFilter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@AutoConfiguration
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
}
