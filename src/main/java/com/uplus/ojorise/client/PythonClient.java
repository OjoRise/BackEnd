package com.uplus.ojorise.client;

import com.uplus.ojorise.domain.Plan;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PythonClient {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${NEXT_PUBLIC_PYTHON_SERVER_URL}")
    private String pythonUrl;

    public void sendPlans(List<Plan> plans) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<Plan>> request = new HttpEntity<>(plans, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(pythonUrl, request, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Python 서버 전송 실패: " + response.getStatusCode());
        }
    }
}
