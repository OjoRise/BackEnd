package com.uplus.ojorise.chat.service;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatgptService chatgptService;

    public String getChatResponse(String question) {
        try {
            return chatgptService.sendMessage(question);
        } catch(HttpClientErrorException.TooManyRequests e) {
            log.error(e.getMessage());
            return "요청 한도를 초과했습니다.";
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return "오류가 발생했습니다.";
        }
    }
}
