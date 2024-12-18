package jp.co.sysystem.training.guide.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMncConfig implements WebMvcConfigurer {

  @Autowired
  private LoginCheckInterceptor loginCheckInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loginCheckInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns(
            "/",
            "/homepage",
            "/login",
            "/login/process",
            "/static/**",
            "/content/**",
            "/resources/**",  
            "/webjars/**",   
            "/markdown/**",
            "/css/**",
            "/template/**",
            "/js/**",
            "/images/**",
            "/error",
            "/**/*.css",
            "/**/*.md", 
            "/**/*.js",     
            "/**/*.png",   
            "/**/*.jpg",    
            "/**/*.jpeg");
  }
}
