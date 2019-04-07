package com.github.kubenext.info;

import com.github.kubenext.config.SpringProfiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author shangjin.li
 */
public class CheckProfilesInfo implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(CheckProfilesInfo.class);

    private final Environment env;

    public CheckProfilesInfo(Environment env) {
        this.env = env;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains(SpringProfiles.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(SpringProfiles.SPRING_PROFILE_PRODUCTION)) {
            logger.error("You have misconfigured your application! It should not run " +
                    "with both the 'dev' and 'prod' profiles at the same time.");
        }
    }
}
