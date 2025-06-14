package com.uplus.ojorise.controller;

import com.uplus.ojorise.service.GoogleApiService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class OCRController {

    private final GoogleApiService googleApiService;

    @PostMapping(value = "/google/ocr", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Google OCR 실행", description = "이미지 업로드 시, Google OCR 처리 후 결과 반환")
    public ResponseEntity<String> runOCR(@RequestParam("image")MultipartFile file) {
        try {
            String result = googleApiService.googleOCR(file);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("OCR 실패: " + e.getMessage());
        }
    }
}
