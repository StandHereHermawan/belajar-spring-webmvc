package programmerzamannow.webmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import programmerzamannow.webmvc.interceptor.SessionInterceptor;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/user/*");
        // APACHE ANT
        // Ant Path Matcher
        // if the * ( like in this path pattern ),
        //      only works on non-nested directory :    /user/${name-directory},
        //      not works on nested directory :         /user/${name-directory}/${nested-directory}
        // if the ** ,
        //      works on nested directory :             /user/${name-directory}/${nested-directory}
    }
}
