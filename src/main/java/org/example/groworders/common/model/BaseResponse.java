package org.example.groworders.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * API 공통 응답 형식
 * @param <T> 응답 데이터 타입
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    private Boolean success; // 요청 성공 여부
    private Integer code;     // 에러 코드 또는 성공 코드
    private String message;  // 메시지
    private T data;          // 응답 데이터

    /**
     * 성공 응답 (기본 메시지, 코드 없음)
     */
    public static <T> BaseResponse<T> success(T data) {
        return BaseResponse.<T>builder()
                .success(true)
                .message("요청이 성공했습니다.")
                .data(data)
                .build();
    }

    /**
     * 성공 응답 (커스텀 메시지, 코드 없음)
     */
    public static <T> BaseResponse<T> successMessage(String message) {
        return BaseResponse.<T>builder()
                .success(true)
                .message(message)
                .build();
    }

    /**
     * 성공 응답 (코드 + 메시지 + 데이터)
     */
    public static <T> BaseResponse<T> success(Integer code, String message, T data) {
        return BaseResponse.<T>builder()
                .success(true)
                .code(code)
                .message(message)
                .data(data)
                .build();
    }

    /**
     * 실패 응답 (기본: 코드 없음, 메시지만)
     */
    public static <T> BaseResponse<T> fail(String message) {
        return BaseResponse.<T>builder()
                .success(false)
                .message(message)
                .build();
    }

    /**
     * 실패 응답 (코드 + 메시지)
     */
    public static <T> BaseResponse<T> fail(Integer code, String message) {
        return BaseResponse.<T>builder()
                .success(false)
                .code(code)
                .message(message)
                .build();
    }
}
