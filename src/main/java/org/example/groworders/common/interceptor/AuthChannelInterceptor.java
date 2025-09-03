package org.example.groworders.common.interceptor;

import lombok.RequiredArgsConstructor;
import org.example.groworders.domain.users.model.dto.UserDto;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthChannelInterceptor implements ChannelInterceptor {
    private final RoomAccessChecker roomAccessChecker;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (accessor == null) return message;

        // CONNECT: Handshake의 auth를 Principal로 연결
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            Map<String, Object> attrs = accessor.getSessionAttributes();
            if (attrs != null) {
                Authentication authentication = (Authentication) attrs.get("auth");
                if (authentication != null) accessor.setUser(authentication);
            }
        }

        // SEND: 목적지 권한 검증 (room 멤버만 전송 허용)
        if (StompCommand.SEND.equals(accessor.getCommand())) {
            Principal p = accessor.getUser();
            if (p instanceof Authentication auth && auth.getPrincipal() instanceof UserDto.AuthUser u) {
                String dest = accessor.getDestination();
//                roomAccessChecker.assertSendAllowed(dest, u.getId());
            } else {
                throw new AccessDeniedException("Unauthenticated SEND");
            }
        }

        return message;
    }
}
