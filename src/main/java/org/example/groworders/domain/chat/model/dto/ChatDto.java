package org.example.groworders.domain.chat.model.dto;

import lombok.*;
import org.example.groworders.domain.chat.model.entity.ChatMessage;
import org.example.groworders.domain.chat.model.entity.ChatRoom;
import org.example.groworders.domain.users.model.dto.UserDto;

import java.time.LocalDateTime;

// ChatDtos.java
public class ChatDto {
    @Data
    public static class RoomReq {
        private Long buyerId;
        private Long farmerId;
    }

    @Data
    @Builder
    public static class RoomRes {
        private String roomId;
        private Long buyerId;
        private Long farmerId;
        private LocalDateTime createdAt;
    }

    @Data
    public static class SendReq {
        private String content;
    } // STOMP 수신 페이로드

    @Data @Builder
    public static class MessageRes {
        private Long id;
        private String roomId;
        private Long senderId;
        private String content;
        private LocalDateTime sentAt;
    }
}
