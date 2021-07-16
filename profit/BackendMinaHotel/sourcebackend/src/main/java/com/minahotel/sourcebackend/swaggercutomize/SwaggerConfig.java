package com.minahotel.sourcebackend.swaggercutomize;

import com.minahotel.sourcebackend.common.customizeexception.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.*;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.util.Map;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        ObjectSchema errorMessage = new ObjectSchema();
        errorMessage.addProperties("time", new DateSchema());
        errorMessage.addProperties("content_error", new StringSchema().example("Error Business App"));
        errorMessage.addProperties("code_error", new StringSchema().example("ES_001"));
        errorMessage.addProperties("help", new StringSchema());

        return new OpenAPI()
                .components(new Components()
                        .addSchemas("ErrorMessage" , errorMessage)
                );
    }

    @Bean
    OperationCustomizer customizeOperation() {
        OperationCustomizer a = new OperationCustomizer() {
            @Override
            public Operation customize(Operation operation, HandlerMethod handlerMethod) {
                Content content = new Content();
                MediaType me = new MediaType();
                Schema schema =new Schema();
                schema.set$ref("#/components/schemas/ErrorMessage");
                me.setSchema(schema);
                content.addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE,me);

                ApiResponse unthorization = new ApiResponse();
                unthorization.setDescription("Authorization");
                unthorization.setContent(content);

                ApiResponse forbidden = new ApiResponse();
                forbidden.setDescription("Forbidden");
                forbidden.setContent(content);

                ApiResponse notfound = new ApiResponse();
                notfound.setDescription("Not Found Path");
                notfound.setContent(content);

                operation.responses(
                        operation.getResponses()
                                .addApiResponse("401", unthorization)
                                .addApiResponse("403", forbidden)
                                .addApiResponse("404", notfound));
                return operation;
            }
        };
        return a;
    }
}
