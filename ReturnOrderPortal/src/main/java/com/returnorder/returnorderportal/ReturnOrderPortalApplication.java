package com.returnorder.returnorderportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
public class ReturnOrderPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReturnOrderPortalApplication.class, args);
	}
	
	@Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.paths(PathSelectors.ant("/"))
                .apis(RequestHandlerSelectors.basePackage("com.returnorder"))
                .build()
                .apiInfo(apiDetails());
    }
    
    @SuppressWarnings("deprecation")
    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Return Order Portal", "This is a local MVC application which communicates with all the microservices and do the processing accordingly.", "1.0", "", " ", "", ""
                );
    }

}
