package com.skedio.corestarter.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonMapperConfig {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            builder
                    .findModulesViaServiceLoader(true)
                    .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                    .serializationInclusion(JsonInclude.Include.NON_NULL);
        };
    }
}
