package vn.dev.clinics.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import vn.dev.clinics.constants.Constants;

@Configuration
public class McvConfigurer implements WebMvcConfigurer, Constants{
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/user/**").addResourceLocations("classpath:/user/");
		registry.addResourceHandler("/admin/**").addResourceLocations("classpath:/admin/");
		
		registry.addResourceHandler("/StorageFolder/**").addResourceLocations("file:" + STORAGE_FOLDER);
	}
}
