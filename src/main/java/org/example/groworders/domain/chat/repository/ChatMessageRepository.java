package org.example.groworders.domain.chat.repository;


import org.example.groworders.domain.chat.model.entity.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    @Query("select m from ChatMessage m join m.chatRoom r where r.roomId=:roomId order by m.id desc")
    Page<ChatMessage> findPageByRoomId(@Param("roomId") String roomId, Pageable pageable);
}
