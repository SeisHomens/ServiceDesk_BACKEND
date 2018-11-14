package br.senai.sp.info.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("br.senai.sp.info.service")
@Import({PersistenceConfig.class, SecurityConfig.class, WebConfig.class})
public class AppConfig implements WebMvcConfigurer {

}
