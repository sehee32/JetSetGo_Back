package kr.co.jetsetgo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// 웹 애플리케이션 설정 담당 클래스
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        // CORS 설정 추가
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")               // api/**경로 요청에 대한 CORS 설정 추가
                        .allowedOrigins("http://localhost:8000")        // 허용할 도메인 지정
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 허용할 HTTP 메소드 지정
                        .allowedHeaders("*")                            // 허용할 요청 헤더 지정(* 모두허용)
                        .allowCredentials(true);                        // 인증 정보를 포함할 수 있는지 여부 지정 (쿠키,http 인증 헤더)
            }
        };
    }
}
