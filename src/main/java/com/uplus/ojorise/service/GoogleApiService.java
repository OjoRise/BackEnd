package com.uplus.ojorise.service;

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

    public static String googleOCR(MultipartFile file) throws IOException {
        StopWatch totalTime = new StopWatch();
        totalTime.start();

        List<AnnotateImageRequest> requests = new ArrayList<>();

        //이미지 바이트 추출
        ByteString imgBytes = ByteString.readFrom(file.getInputStream());

        Image img = Image.newBuilder().setContent(imgBytes).build();
        Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
        AnnotateImageRequest request =
                AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
        requests.add(request);

        StringBuilder result = new StringBuilder();
        List<String> extractedTextList = new ArrayList<>();

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
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
                result.append(extractedTextList.get(0)); // 전체 텍스트
            }

            totalTime.stop();
            System.out.println("Total Time : " + totalTime.getTotalTimeMillis() + "ms"); //출력까지 걸리는 시간 확인
            return result.toString();
        } catch (Exception exception) {
            return exception.getMessage();
        }
    }
}
