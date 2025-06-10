package com.uplus.ojorise.chat.controller;

import com.uplus.ojorise.chat.service.ChatService;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/chat-gpt")
public class ChatController {
    private final ChatgptService chatgptService;
    private final ChatService chatService;

    @PostMapping("")
    public String requestGPT(@RequestBody String question) {
        return chatService.getChatResponse(question);
    }
}
