package com.github.kubenext.apidoc;

import com.github.kubenext.properties.CommonProperties;
import org.springframework.core.Ordered;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * A swagger customizer to setup (@link Docket) with Kubenext setting.
 *
 * @author shangjin.li
 */
public class CommonSwaggerCustomizer implements SwaggerCustomizer, Ordered {

    public static final int DEFAULT_ORDER = 0;

    private int order = DEFAULT_ORDER;

    private final CommonProperties.Swagger swagger;

    public CommonSwaggerCustomizer(CommonProperties.Swagger swagger) {
        this.swagger = swagger;
    }

    @Override
    public void customize(Docket docket) {
        Contact contact = new Contact(
            swagger.getContactName(),
            swagger.getContactUrl(),
            swagger.getContactEmail()
        );

        ApiInfo apiInfo = new ApiInfo(
            swagger.getTitle(),
            swagger.getDescription(),
            swagger.getVersion(),
            swagger.getTermsOfServiceUrl(),
            contact,
            swagger.getLicense(),
            swagger.getLicenseUrl(),
            new ArrayList<>()
        );

        docket.host(swagger.getHost())
            .protocols(new HashSet<>(Arrays.asList(swagger.getProtocols())))
            .apiInfo(apiInfo)
            .useDefaultResponseMessages(swagger.isUseDefaultResponseMessages())
            .forCodeGeneration(true)
            .directModelSubstitute(ByteBuffer.class, String.class)
            .genericModelSubstitutes(ResponseEntity.class)
            .ignoredParameterTypes(Pageable.class)
            .select()
            .paths(regex(swagger.getDefaultIncludePattern()))
            .build();
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return order;
    }
}
