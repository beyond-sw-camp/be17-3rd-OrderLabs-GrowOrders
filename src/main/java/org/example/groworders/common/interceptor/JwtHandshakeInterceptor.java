package org.example.groworders.common.interceptor;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.example.groworders.domain.users.model.dto.UserDto;
import org.example.groworders.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;
import java.util.Map;

@Component
public class JwtHandshakeInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler ws, Map<String, Object> attributes) {
        if (request instanceof ServletServerHttpRequest sreq) {
            HttpServletRequest http = sreq.getServletRequest();
            String jwt = null;
            if (http.getCookies() != null) {
                for (Cookie c : http.getCookies()) {
                    if ("OL_AT".equals(c.getName())) { jwt = c.getValue(); break; }
                }
            }
            if (jwt != null) {
                Claims claims = JwtUtil.getClaims(jwt); // 만료/서명 검증 포함 권장
                if (claims != null && !JwtUtil.isExpired(claims)) {
                    Long id = Long.parseLong(JwtUtil.getValue(claims, "id"));
                    String email = JwtUtil.getValue(claims, "email");
                    UserDto.AuthUser authUser = UserDto.AuthUser.builder().id(id).email(email).build();
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            authUser, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
                    attributes.put("auth", authentication);
                }
            }
        }
        return true;
    }
    @Override public void afterHandshake(ServerHttpRequest r, ServerHttpResponse s, WebSocketHandler h, Exception e) {}
}
