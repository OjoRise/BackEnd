package com.uplus.ojorise;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "KAKAO_API_KEY=dummy",
        "KAKAO_CLIENT_SECRET=dummy",
        "KAKAO_REDIRECT_URI=https://localhost",
        "JWT_SECRET=test-secret",
        "GOOGLE_APPLICATION_CREDENTIALS_BASE64 = dummy",
        "GOOGLE_APPLICATION_CREDENTIALS = dummy"
})
class OjoRiseApplicationTests {
    @Test
    void contextLoads() {
    }
}
