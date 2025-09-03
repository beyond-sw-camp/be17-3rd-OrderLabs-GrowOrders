package org.example.groworders.domain.cart.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.groworders.domain.cart.model.dto.CartDto;
import org.example.groworders.domain.cart.service.CartService;
import org.example.groworders.domain.users.model.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    /**
     * 장바구니 담기
     */
    @PostMapping("/add/{cropMgtId}")
    public ResponseEntity<String> addCart(
            @Valid @RequestBody CartDto.Request request,
            @PathVariable Long cropMgtId,
            @AuthenticationPrincipal String userId
    ) {
        System.out.println(userId);
        Long createdId = cartService.addCart(request, cropMgtId);
        return ResponseEntity.ok("장바구니에 등록되었습니다. cart_id : " + createdId);
    }

    /**
     * 내 장바구니 리스트
     */
    @GetMapping("/{userId}")
    public List<CartDto.Status> getMyCarts(@PathVariable Long userId) {
        return cartService.allCarts(String.valueOf(userId));
    }
}
