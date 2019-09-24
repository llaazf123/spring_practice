package kr.co.uclick.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration	//configuration 선언

//@EnableWebMvc란놈은 쉽게 얘기하면 web mvc을 이용하는데 있어서 spring container가 가져야할 기본적인 bean component을 등록해서 빠르게 편하게 mvc을 구축할수 있는 configuration 환경을 제공
//예를 들어  @MVC (@RequestMapping, @Requestbody, @ResponseBody) 등의 스타일을 위해서 등록되어야 하는 RequestMappingHandler,RequestMappingHandlerAdapter,ExceptionHandlerExceptionResolver 등의 등록을 자동으로 해줌
@EnableWebMvc	

@ComponentScan("kr.co.uclick.controller")	//Component Scan은 XML에 일일이 빈등록을 하지않고 각 빈 클래스에 @Component를 통해 자동 빈 등록

public class SpringWebConfiguration implements WebMvcConfigurer {	//webMvcConfigurer는 자동구성된 스프링 MVC 구성에 Formatter, MessageConverter 등을 추가등록할 수 있게 함
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false);
		configurer.favorParameter(true);
		configurer.parameterName("mediaType");
		configurer.ignoreAcceptHeader(true);
		configurer.useJaf(false);
		configurer.mediaType("xml", MediaType.APPLICATION_XML);
		configurer.mediaType("json", MediaType.APPLICATION_JSON);
	}

	//특정 url 진입 시 로그인이 된(인가된) 사용자가 접근을 해야 함
	//특정 url 진입 시 Jwt와 같은 토큰을 검사해야 함
	//특정 url의 경우 계정의 권한에 따라 접근을 막아야 함
	//위 요구사항의 특징은 특정 url 진입 시 어떤 작업을 수행해야 하는 것
	//이름의 의미와 같이 무언가를 진행할 때 특정 작업을 수행하는 것이 인터셉터
	@Override
	public void addInterceptors(InterceptorRegistry registry) {	
		registry.addInterceptor(new LocaleChangeInterceptor());
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {	//configureDefaultServletHandling() 에서 설정한 내용으로 고정적인 리소스들에 대한 요청은 DispatcherServlet이 직접 처리하지 않고 서블릿 컨테이너의 디폴트 서블릿 전달을 요청
		configurer.enable();
	}

	@Bean
	public LocaleResolver LocaleResolver() {	//스프링 MVC는 LocaleResolver를 이용해서 웹 요청과 관련된 Locale을 추출하고, 이 Locale 객체를 이용해서 알맞은 언어의 메시지를 선택
		return new CookieLocaleResolver();
	}

	@Bean
	public MessageSource messageSource() {	//제화(i18n)을 제공하는 인터페이스다. 메세지 설정 파일을 모아놓고 각 국가마다 로컬라이징을 함으로서 쉽게 각 지역에 맞춘 메세지를 제공
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setBasename("validate-message.properties");
		return resourceBundleMessageSource;
	}

	@Bean
	public InternalResourceViewResolver InternalResourceViewResolver() {	//웹 어플리케이션의 WAR 파일 내에 포함된 뷰 템플릿을 찾는다. 뷰 템플릿의 경로는 논리적 뷰 이름에 접두어와 접미어를 붙여 구성
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}
}
