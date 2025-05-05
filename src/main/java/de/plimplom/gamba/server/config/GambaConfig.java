package de.plimplom.gamba.server.config;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "gamba")
@Getter
@Setter
public class GambaConfig {
    @Nonnull
    private String key;
    @Nonnull
    private String guild;

}
