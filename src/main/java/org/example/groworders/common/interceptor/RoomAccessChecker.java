package org.example.groworders.common.interceptor;

import lombok.RequiredArgsConstructor;
import org.example.groworders.domain.chat.service.ChatService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomAccessChecker {
    private final ChatService chatService;
    public void assertSendAllowed(String destination, Long userId) {
        // /app/chat/{roomId} 또는 /topic/chat/{roomId} 형식만 허용
        String roomId = extractRoomId(destination);
        if (roomId == null || userId == null || !chatService.isParticipant(roomId, userId)) {
            throw new AccessDeniedException("Not allowed to send to this room");
        }
    }
    private String extractRoomId(String dest) {
        if (dest == null) return null;
        // 예: /app/chat/{roomId}  또는 /topic/chat/{roomId}
        String[] parts = dest.split("/");
        return parts.length >= 4 && "chat".equals(parts[2]) ? parts[3] : null;
    }
}
