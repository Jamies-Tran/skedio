package com.skedio.corestarter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "core")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CoreStarterProperties {
    String secretKey;
    List<String> permitAll;
    Long accessExpiredMillis;
    Long refreshExpiredMillis;
}
