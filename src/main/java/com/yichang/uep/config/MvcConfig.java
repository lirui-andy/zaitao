package com.yichang.uep.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	Logger logger = LoggerFactory.getLogger(MvcConfig.class);

	@Autowired
	SpringResourceTemplateResolver templateResolver;
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/import-ztry").setViewName("import-ztry");
	}

	//Enable Spring Security taglibs in Thymeleaf
    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setEnableSpringELCompiler(true); // 
        templateEngine.setTemplateResolver(templateResolver);
        //Enable <sec:authentication > tag in the thymeleaf template
        templateEngine.addDialect(new SpringSecurityDialect());
        return templateEngine;
    }
}
