package com.iceservices.musicdb.content;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@ConfigurationProperties(prefix = "exceptions")
@Getter
@Setter
public class ExceptionMessage
{
    private String defaultMessage;
}
