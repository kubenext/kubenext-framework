package com.github.kubenext.info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author shangjin.li
 */
@Configuration
public class InfoAutoConfiguration {

    @Bean
    public CheckProfilesInfo checkProfilesInfo(Environment env) {
        return new CheckProfilesInfo(env);
    }

}
