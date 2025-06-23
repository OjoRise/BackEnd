package com.uplus.ojorise.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Component
public class GoogleCredentialInitializer {

    @Value("${GOOGLE_APPLICATION_CREDENTIALS_BASE64}")
    private String credentialsBase64;

    @PostConstruct
    public void setupGoogleCredentials() throws IOException {
        // Render의 쓰기 가능한 temp 디렉터리
        String tempPath = System.getProperty("java.io.tmpdir") + "/credentials.json";

        // 디코딩 후 파일로 저장
        byte[] decoded = Base64.getDecoder().decode(credentialsBase64);
        Files.write(Paths.get(tempPath), decoded);

        // 시스템 환경변수로 설정 (Google SDK가 자동으로 사용함)
        System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", tempPath);
    }
}
