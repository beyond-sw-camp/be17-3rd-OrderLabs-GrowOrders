package org.example.groworders.domain.orders.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.groworders.domain.orders.model.dto.OrderDto;
import org.example.groworders.domain.orders.model.entity.Order;
import org.example.groworders.domain.orders.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final HttpSession httpSession;

    // 임시 주문 테이블 생성
    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody Map<String, Object> payload) {
        List<Integer> cartIdsInteger = (List<Integer>) payload.get("cartIds");
        List<Long> cartIds = cartIdsInteger.stream().map(Long::valueOf).collect(Collectors.toList());
        Order temporaryOrder = orderService.createOrder(cartIds);

        // 세션에 임시 주문 정보를 저장
        httpSession.setAttribute("temporaryOrder", temporaryOrder);
        httpSession.setAttribute("cartIds", cartIds); // 장바구니 id 저장

        return ResponseEntity.ok("주문 임시 저장 완료");
    }

    // 최종 주문 테이블 생성
    @PostMapping("/confirm")
    public ResponseEntity<Object> completeOrder(@Valid @RequestBody OrderDto.Request request) {

        // Request → Confirm 변환
        OrderDto.Confirm confirmDto = request.toConfirm();

        // 세션에서 임시 주문 정보 가져오기
        Order temporaryOrder = (Order) httpSession.getAttribute("temporaryOrder");

        if (temporaryOrder == null) {
            return ResponseEntity.badRequest().body("임시 주문 정보를 찾을 수 없습니다.");
        }

        // 서비스 호출
        Order completedOrder = orderService.orderConfirm(temporaryOrder, confirmDto);

        // Entity → Response 변환
        OrderDto.Response responseDto = OrderDto.Response.fromEntity(completedOrder);

        return ResponseEntity.ok(responseDto);
    }



}