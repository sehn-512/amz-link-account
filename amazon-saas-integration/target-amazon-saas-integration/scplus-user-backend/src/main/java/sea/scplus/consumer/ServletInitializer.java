package sea.scplus.consumer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.annotation.Order;
import org.springframework.core.Ordered;


@Order(Ordered.HIGHEST_PRECEDENCE)
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ScplusUserApplication.class);
	}
	
	@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
		
//		addSpringSecurityFilterChain(servletContext);
    }

//    private void addSpringSecurityFilterChain(ServletContext servletContext) {
//        FilterRegistration.Dynamic registration =
//                servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
//        EnumSet<DispatcherType> dispatcherTypes =
//                EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR, DispatcherType.ASYNC);
//        registration.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
//    }
}
