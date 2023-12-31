package com.iceservices.musicdb.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties()
@Getter
@Setter
public class Config
{
    private boolean debug;
}
