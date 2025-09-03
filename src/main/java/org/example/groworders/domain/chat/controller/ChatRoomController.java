package org.example.groworders.domain.chat.controller;

import lombok.RequiredArgsConstructor;
import org.example.groworders.domain.chat.model.dto.ChatDto;
import org.example.groworders.domain.chat.service.ChatService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat/room")
public class ChatRoomController {
    private final ChatService chatService;

    @PostMapping("/get-or-create")
    public ChatDto.RoomRes getOrCreate(@RequestBody ChatDto.RoomReq req) {
        System.out.println(req.getBuyerId());
        System.out.println(req.getFarmerId());
        return chatService.getOrCreateRoom(req.getBuyerId(), req.getFarmerId());
    }

    // 히스토리 페이지네이션 (옵션)
    @GetMapping("/{roomId}/messages")
    public Page<ChatDto.MessageRes> history(@PathVariable String roomId,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "30") int size) {
        return chatService.history(roomId, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));
    }
}
