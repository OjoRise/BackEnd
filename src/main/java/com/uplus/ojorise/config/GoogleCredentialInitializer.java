package com.uplus.ojorise.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Component
public class GoogleCredentialInitializer {

    String credentialsBase64 = System.getenv("GOOGLE_APPLICATION_CREDENTIALS_BASE64");

    @PostConstruct
    public void setupGoogleCredentials() throws IOException {
        System.out.println("🚀 PostConstruct 진입");

        if (credentialsBase64 == null || credentialsBase64.trim().isEmpty()) {
            System.out.println("❌ credentialsBase64 비어있음");
            throw new IllegalStateException("GOOGLE_APPLICATION_CREDENTIALS_BASE64 is not set or empty");
        }

        byte[] decoded = Base64.getDecoder().decode(credentialsBase64);
        String tempPath = System.getProperty("java.io.tmpdir") + File.separator + "credentials.json";
        Files.write(Paths.get(tempPath), decoded);

        System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", tempPath);
        System.out.println("✅ GOOGLE_APPLICATION_CREDENTIALS 설정 완료: " + tempPath);
    }
}
