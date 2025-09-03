package org.example.groworders.domain.chat.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.groworders.common.model.BaseEntity;

import java.time.LocalDateTime;

// ChatMessage.java
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoom chatRoom;

    @Column(nullable = false) private Long senderId;
    @Column(nullable = false, length = 2000) private String content;
    @Column(nullable = false) private LocalDateTime sentAt;
}
