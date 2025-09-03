package org.example.groworders.domain.cart.model.dto;

import lombok.*;
import org.example.groworders.domain.cart.model.entity.Cart;

public class CartDto {

    @Getter
    @Builder
    public static class Status {
        private Long id;
        private Long userId;
        private String userName;
        private String cropType;
        private Long quantity;
        private Long totalPrice;

        public static Status fromEntity(Cart entity) {
            return Status.builder()
                    .id(entity.getId())
                    .userId(entity.getUser().getId())
                    .userName(entity.getUser().getName())
                    .cropType(entity.getCropOrderManagement().getCrop().getType())
                    .quantity(entity.getQuantity())
                    .totalPrice(entity.getPrice())
                    .build();
        }
    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private Long cropOrderManagementId;
        private int quantity;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {   // 응답용 DTO
        private Long Id;
        private String message;
    }
}
