package sea.scplus.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.Locale;

@ServletComponentScan
@SpringBootApplication
@EnableSwagger2
public class ScplusUserApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Value("${spring.web.locale}")
    Locale locale = null;

    @Value("${spring.messages.basename}")
    String messagesBasename = null;

    @Value("${spring.messages.encoding}")
    String messagesEncoding = null;

    @Value("${spring.messages.cache-seconds}")
    int messagesCacheSeconds;

    @Value("${dkms.task-id}")
    String dkmsTaskId;

    public static void main(String[] args) {
        new SpringApplicationBuilder(ScplusUserApplication.class)
                .properties("spring.config.location=" + "classpath:/application.yml").run(args);
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(locale);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    // message source
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(messagesBasename); // "classpath:/messages/message"
        messageSource.setDefaultEncoding(messagesEncoding);
        messageSource.setCacheSeconds(messagesCacheSeconds);
        return messageSource;
    }

    @Bean
    public MessageSourceAccessor getMessageSourceAccessor() {
        ReloadableResourceBundleMessageSource m = messageSource();
        return new MessageSourceAccessor(m);
    }

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/consumer/scplus/*"))
//				.paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("sea.scplus.consumer"))
                .build()
//				.pathMapping("/")
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "CEPOS Front API",
                "Consumer facing API for CE POS",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Consumer facing for API", "http://localhost:8080/", "yisangho717@gmail.com"),
                "API License",
                "http://localhost:8080",
                Collections.emptyList()
        );
    }
}
