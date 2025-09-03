package org.example.groworders.domain.chat.service;

import lombok.RequiredArgsConstructor;
import org.example.groworders.domain.chat.model.dto.ChatDto;
import org.example.groworders.domain.chat.model.entity.ChatMessage;
import org.example.groworders.domain.chat.model.entity.ChatRoom;
import org.example.groworders.domain.chat.repository.ChatMessageRepository;
import org.example.groworders.domain.chat.repository.ChatRoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository roomRepo;
    private final ChatMessageRepository msgRepo;

    @Transactional
    public ChatDto.RoomRes getOrCreateRoom(Long buyerId, Long farmerId) {
        return roomRepo.findByBuyerIdAndFarmerIdOrFarmerIdAndBuyerId(buyerId, farmerId, farmerId, buyerId)
                .map(this::toRoomRes)
                .orElseGet(() -> {
                    ChatRoom r = ChatRoom.builder()
                            .roomId(UUID.randomUUID().toString())
                            .farmerId(farmerId)
                            .buyerId(buyerId)
                            .build();
                    return toRoomRes(roomRepo.save(r));
                });
    }

    public boolean isParticipant(String roomId, Long userId) {
        return roomRepo.findByRoomId(roomId)
                .map(r -> r.getBuyerId().equals(userId) || r.getFarmerId().equals(userId))
                .orElse(false);
    }

    @Transactional
    public ChatDto.MessageRes saveMessage(String roomId, Long senderId, String content) {
        ChatRoom room = roomRepo.findByRoomId(roomId).orElseThrow(() -> new IllegalArgumentException("room not found"));
        ChatMessage m = msgRepo.save(ChatMessage.builder()
                .chatRoom(room).senderId(senderId).content(content).sentAt(LocalDateTime.now()).build());
        return ChatDto.MessageRes.builder()
                .id(m.getId()).roomId(roomId).senderId(senderId).content(m.getContent()).sentAt(m.getSentAt()).build();
    }

    private ChatDto.RoomRes toRoomRes(ChatRoom r) {
        return ChatDto.RoomRes.builder()
                .roomId(r.getRoomId()).buyerId(r.getBuyerId()).farmerId(r.getFarmerId()).createdAt(r.getCreatedAt()).build();
    }

    @Transactional(readOnly = true)
    public Page<ChatDto.MessageRes> history(String roomId, Pageable pageable) {
        Page<ChatMessage> page = msgRepo.findPageByRoomId(roomId, pageable);

        return page.map(m -> ChatDto.MessageRes.builder()
                .id(m.getId())
                .roomId(roomId)
                .senderId(m.getSenderId())
                .content(m.getContent())
                .sentAt(m.getSentAt())
                .build());
    }

}
