package org.example.groworders.domain.payment.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.groworders.common.model.BaseResponse;
import org.example.groworders.domain.cart.repository.CartRepository;
import org.example.groworders.domain.orders.model.entity.Order;
import org.example.groworders.domain.orders.repository.OrderRepository;
import org.example.groworders.domain.payment.model.dto.PaymentDto;
import org.example.groworders.domain.payment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;
    private final HttpSession httpSession;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    @PostMapping("/validation")
    public ResponseEntity<BaseResponse<Boolean>> validateOrder(@RequestBody PaymentDto.Validation dto) throws SQLException, IOException {
        boolean ok = paymentService.validation(dto);

        System.out.println(dto);

        if (ok) {
            // 결제 성공 시: 세션 초기화 및 장바구니 비우기
            List<Long> cartIds = (List<Long>) httpSession.getAttribute("cartIds");
            if (cartIds != null) {
                cartIds.forEach(cartRepository::deleteById);
            }
            httpSession.removeAttribute("temporaryOrder");
            httpSession.removeAttribute("cartIds");
        }

        return ResponseEntity.ok(BaseResponse.success(ok));
    }
}
