package sea.scplus.consumer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import sea.scplus.consumer.common.filter.AuthInterceptor;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

	@Value("${spring.profiles.active}")
	private String server_mode;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		List<String> notLoadList = new ArrayList<String>();
		
		notLoadList.add("/");
		notLoadList.add("/index.html");
		notLoadList.add("/scplus/checkSerialNoValidation");
		notLoadList.add("/scplus/getTax");
		notLoadList.add("/scplus/cancelContract");
		notLoadList.add("/scplus/cancel");
		notLoadList.add("/scplus/resend_email");
		notLoadList.add("/scplus/payment");
		notLoadList.add("/scplus/getContractInfo");
		notLoadList.add("/scplus/cancelRequest");
		notLoadList.add("/scplus/saveFrontError");
		notLoadList.add("/amazon/check-user");
		notLoadList.add("/amazon/fulfillment");

		if ( "local".equals(server_mode) || "dev".equals(server_mode)) {
			notLoadList.add("/swagger-resources/**");
			notLoadList.add("/swagger-ui/**");
			notLoadList.add("/swagger-ui.html");
			notLoadList.add("/webjars/**");
			notLoadList.add("/v2/api-docs");
			
		}
		
		registry.addInterceptor(new AuthInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns(notLoadList)
		;
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index.html");  
		registry.addRedirectViewController("/", "/index.html");
          registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
	
	
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/META-INF/resources/webjars/springfox-swagger-ui/" };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        
    	registry.addResourceHandler("/**")
            .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }
    
}
