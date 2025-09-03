package org.example.groworders.domain.chat.repository;

import org.example.groworders.domain.chat.model.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// ChatRoomRepository.java
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByBuyerIdAndFarmerIdOrFarmerIdAndBuyerId(Long buyerId, Long farmerId, Long farmerId2, Long buyerId2);
    Optional<ChatRoom> findByBuyerIdAndFarmerId(Long buyerId, Long farmerId);
    Optional<ChatRoom> findByRoomId(String roomId);
}
