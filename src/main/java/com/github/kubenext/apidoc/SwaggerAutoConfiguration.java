package com.github.kubenext.apidoc;

import com.github.kubenext.config.SpringProfiles;
import com.github.kubenext.properties.CommonProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.DispatcherServlet;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.Servlet;
import java.nio.ByteBuffer;
import java.util.*;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author shangjin.li
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({
    ApiInfo.class,
    BeanValidatorPluginsConfiguration.class,
    Servlet.class,
    DispatcherServlet.class
})
@Profile(SpringProfiles.SPRING_PROFILE_SWAGGER)
@AutoConfigureAfter(CommonProperties.class)
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(SwaggerAutoConfiguration.class);

    static final String STARTING_MESSAGE = "Starting Swagger";
    static final String STARTED_MESSAGE = "Started Swagger in {} ms";
    static final String MANAGEMENT_TITLE_SUFFIX = "Management API";
    static final String MANAGEMENT_GROUP_NAME = "management";
    static final String MANAGEMENT_DESCRIPTION = "Management endpoints documentation";

    private final CommonProperties.Swagger swagger;

    public SwaggerAutoConfiguration(CommonProperties commonProperties) {
        this.swagger = commonProperties.getSwagger();
    }

    @Bean
    @ConditionalOnMissingBean(name = "swaggerSpringfoxApiDocket")
    public Docket swaggerSpringfoxApiDocket(List<SwaggerCustomizer> swaggerCustomizers, ObjectProvider<AlternateTypeRule[]> alternateTypeRules) {
        logger.debug(STARTING_MESSAGE);
        StopWatch watch = new StopWatch();
        watch.start();
        Docket docket = createDocket();
        swaggerCustomizers.forEach(customizer -> customizer.customize(docket));
        Optional.ofNullable(alternateTypeRules.getIfAvailable()).ifPresent(docket::alternateTypeRules);
        watch.stop();
        logger.debug(STARTED_MESSAGE, watch.getTotalTimeMillis());
        return docket;
    }

    @Bean
    public CommonSwaggerCustomizer commonSwaggerCustomizer() {
        return new CommonSwaggerCustomizer(swagger);
    }

    @Bean
    @ConditionalOnClass(name = "org.springframework.boot.actuate.autoconfigure.web.server.ManagementServerProperties")
    @ConditionalOnProperty("management.endpoints.web.base-path")
    @ConditionalOnExpression("'${management.endpoints.web.base-path}'.length() > 0")
    @ConditionalOnMissingBean(name = "swaggerSpringfoxManagementDocket")
    public Docket swaggerSpringfoxManagementDocket(@Value("${spring.application.name:application}") String appName, @Value("${management.endpoints.web.base-path}") String managementContextPath) {

        ApiInfo apiInfo = new ApiInfo(
            StringUtils.capitalize(appName) + " " + MANAGEMENT_TITLE_SUFFIX,
            MANAGEMENT_DESCRIPTION,
            swagger.getVersion(),
            "",
            ApiInfo.DEFAULT_CONTACT,
            "",
            "",
            new ArrayList<>()
        );

        return createDocket()
            .apiInfo(apiInfo)
            .useDefaultResponseMessages(swagger.isUseDefaultResponseMessages())
            .groupName(MANAGEMENT_GROUP_NAME)
            .host(swagger.getHost())
            .protocols(new HashSet<>(Arrays.asList(swagger.getProtocols())))
            .forCodeGeneration(true)
            .directModelSubstitute(ByteBuffer.class, String.class)
            .genericModelSubstitutes(ResponseEntity.class)
            .ignoredParameterTypes(Pageable.class)
            .select()
            .paths(regex(managementContextPath + ".*"))
            .build();
    }

    protected Docket createDocket() {
        return new Docket(DocumentationType.SWAGGER_2);
    }

}
