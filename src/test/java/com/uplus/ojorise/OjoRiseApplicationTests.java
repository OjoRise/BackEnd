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
        "GOOGLE_APPLICATION_CREDENTIALS_BASE64=ZHVtbXk=",
        "GOOGLE_APPLICATION_CREDENTIALS=dummy",
        "NEXT_PUBLIC_PYTHON_SERVER_URL=https://localhost"
})
class OjoRiseApplicationTests {
    @Test
    void contextLoads() {
    }
}
