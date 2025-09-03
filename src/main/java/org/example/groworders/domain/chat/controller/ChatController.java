package org.example.groworders.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import org.example.groworders.domain.chat.model.dto.ChatDto;
import org.example.groworders.domain.chat.model.entity.ChatMessage;
import org.example.groworders.domain.chat.service.ChatService;
import org.example.groworders.domain.users.model.dto.UserDto;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate template;
    private final ChatService chatService;

    // 클라이언트 SEND: /app/chat/{roomId}
    @MessageMapping("/chat/{roomId}")
    public void sendMessage(Principal principal,
                            @DestinationVariable String roomId,
                            ChatDto.SendReq payload) {
        Authentication authentication = (Authentication) principal;
        UserDto.AuthUser au = (UserDto.AuthUser) authentication.getPrincipal();

        // 영속화
        ChatDto.MessageRes saved = chatService.saveMessage(roomId, au.getId(), payload.getContent());

        // 브로드캐스트: /topic/chat/{roomId}로 통일
        template.convertAndSend("/topic/chat/" + roomId, saved);
    }

    // 에러 프레임 내려보내기 (선택)
    @MessageExceptionHandler
    @SendToUser(destinations = "/queue/errors", broadcast = false)
    public String handleException(Exception e) { return e.getMessage(); }
}
