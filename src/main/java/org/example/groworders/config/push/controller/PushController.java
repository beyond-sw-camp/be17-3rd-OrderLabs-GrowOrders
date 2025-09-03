package org.example.groworders.config.push.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.groworders.common.model.BaseResponse;
import org.example.groworders.config.push.model.dto.PushDto;
import org.example.groworders.config.push.service.PushHistoryService;
import org.example.groworders.config.push.service.PushService;
import org.example.groworders.domain.users.model.dto.UserDto;
import org.jose4j.lang.JoseException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("/push")
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
public class PushController {
    private final PushService pushService;
    private final PushHistoryService pushHistoryService;

    // 구독 정보 저장
    @Operation(
            summary = "유저 구독 정보 저장",
            description = "농부 / 구매자 대시보드 접속 시 유저 구독 정보가 자동으로 저장된다."
    )
    @PostMapping("/sub")
    public ResponseEntity sub(@RequestBody PushDto.Subscribe dto,
                              @AuthenticationPrincipal UserDto.AuthUser authUser) {
        pushService.subscribe(dto, authUser.getId());
        return ResponseEntity.status(200).body("구독 성공");
    }

    // 푸시 알림 발송
    @Operation(
            summary = "푸시 알림 발송",
            description = ""
    )
    @PostMapping("/send")
    public ResponseEntity send(@RequestBody PushDto.Send dto) throws JoseException, GeneralSecurityException, IOException, ExecutionException, InterruptedException {
        pushService.send(dto);
        return ResponseEntity.status(200).body("전송 성공");
    }

    // 푸시 히스토리 출력
    @Operation(
            summary = "푸시 히스토리 출력",
            description = ""
    )
    @GetMapping("/history")
    public ResponseEntity<BaseResponse<List<PushDto.PushResponse>>> history(@AuthenticationPrincipal UserDto.AuthUser authUser) {
        List<PushDto.PushResponse> result = pushHistoryService.history(authUser.getId());
        return ResponseEntity.ok(BaseResponse.success(result));
    }
}
