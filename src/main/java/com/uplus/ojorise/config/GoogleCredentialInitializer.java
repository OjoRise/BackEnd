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
        String tempPath = System.getProperty("java.io.tmpdir") + "/credentials.json";

        byte[] decoded = Base64.getDecoder().decode(credentialsBase64);
        Files.write(Paths.get(tempPath), decoded);

        System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", tempPath);
    }
}
