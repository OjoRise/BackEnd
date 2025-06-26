package com.uplus.ojorise.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vision.v1.ImageAnnotatorSettings;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleApiService {

    public String googleOCR(MultipartFile file) throws IOException {
    System.out.println("📌 현재 GOOGLE_APPLICATION_CREDENTIALS: " + System.getProperty("GOOGLE_APPLICATION_CREDENTIALS"));

    StopWatch totalTime = new StopWatch();
    totalTime.start();

    List<AnnotateImageRequest> requests = new ArrayList<>();
    ByteString imgBytes = ByteString.readFrom(file.getInputStream());

    Image img = Image.newBuilder().setContent(imgBytes).build();
    Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
    AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
            .addFeatures(feat)
            .setImage(img)
            .build();
    requests.add(request);

    StringBuilder result = new StringBuilder();
    List<String> extractedTextList = new ArrayList<>();

    // ✅ 인증 설정 추가
    GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
    ImageAnnotatorSettings settings = ImageAnnotatorSettings.newBuilder()
            .setCredentialsProvider(() -> credentials)
            .build();

    try (ImageAnnotatorClient client = ImageAnnotatorClient.create(settings)) {
        BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
        List<AnnotateImageResponse> responses = response.getResponsesList();

        for (AnnotateImageResponse res : responses) {
            if (res.hasError()) {
                System.out.format("Error: %s%n", res.getError().getMessage());
                return null;
            }

            for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
                extractedTextList.add(annotation.getDescription());
            }
        }

        if (!extractedTextList.isEmpty()) {
            result.append(extractedTextList.get(0));
        }

        totalTime.stop();
        System.out.println("Total Time : " + totalTime.getTotalTimeMillis() + "ms");
        return result.toString();
    } catch (Exception exception) {
        return exception.getMessage();
    }
}
