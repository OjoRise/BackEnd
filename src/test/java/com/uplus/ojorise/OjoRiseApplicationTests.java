package com.uplus.ojorise;

import com.uplus.ojorise.config.GoogleCredentialInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ComponentScan(basePackages = {
        "com.uplus.ojorise",
        // 필요한 패키지 지정, config 제외
}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = GoogleCredentialInitializer.class)
})
@TestPropertySource(properties = {
        "KAKAO_API_KEY=dummy",
        "KAKAO_CLIENT_SECRET=dummy",
        "KAKAO_REDIRECT_URI=https://localhost",
        "JWT_SECRET=test-secret",
        "GOOGLE_APPLICATION_CREDENTIALS_BASE64=ZHVtbXk=",
        "NEXT_PUBLIC_PYTHON_SERVER_URL=https://localhost"
})
public class OjoRiseApplicationTests {
    @Test
    void contextLoads() {
    }
}

