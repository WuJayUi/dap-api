package com.tbb.dap.test.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.tbb.dap.common.utils.HttpClientUtil;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    static final String[] ORIGINS = {"GET", "POST", "PUT", "DELETE", "OPTION"};

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods(ORIGINS).allowCredentials(false).allowedHeaders("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.TRADITIONAL_CHINESE);
        return resolver;
    }

    /**
     * <pre>
     * 在程式中要使用 RestTemplate，當通訊協定為 https 時用 @Autowired，
     * 如果通訊協定為 http，直接 new。
     *
     * 行內的系統，走 https 時，可以設定忽略憑證檢查。
     * </pre>
     *
     * @return
     * @throws Exception
     */
    @Scope("prototype")
    public RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        CloseableHttpClient httpClient = HttpClientUtil.acceptsUntrustedCertsHttpClient();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        RestTemplate rest = new RestTemplate(requestFactory);

        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonHttpMessageConverter.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        rest.getMessageConverters().add(jsonHttpMessageConverter);
        rest.setErrorHandler(new CustomErrorHandler());
        return rest;
    }
}
