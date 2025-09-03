package org.example.groworders.domain.chat.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.groworders.common.model.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoom extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String roomId; // UUID

    @Column(nullable = false)
    private Long buyerId;

    @Column(nullable = false)
    private Long farmerId;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}