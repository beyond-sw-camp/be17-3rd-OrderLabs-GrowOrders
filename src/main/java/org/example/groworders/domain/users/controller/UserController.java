package org.example.groworders.domain.users.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.groworders.common.model.BaseResponse;
import org.example.groworders.domain.users.model.dto.UserDto;
import org.example.groworders.domain.users.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "회원 기능")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "회원가입 기능",
            description = "회원 가입 기능"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 성공")

    })
    @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BaseResponse<Void>> signup(
            @RequestPart(value = "signupInfo") @Valid UserDto.SignUp dto,
            @Parameter(description = "업로드할 프로필 이미지", required = false)
            @RequestPart(value = "profileImageUrl", required = false) MultipartFile profileImageUrl
    ) throws MessagingException, IOException, SQLException {
        userService.signup(dto, profileImageUrl);
        return ResponseEntity.ok(BaseResponse.successMessage("등록 성공"));
    }

    @Operation(
            summary = "E-mail 검증 API",
            description = "E-mail 검증 API"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email 검증 성공")

    })
    @GetMapping("/verify")
    public ResponseEntity<BaseResponse<Void>> verify(
            @Parameter(description = "이메일 인증용 UUID", required = true, example = "a1b2c3d4-1234-5678-9abc-def123456789")@RequestParam String uuid) {
        userService.verify(uuid);
        return ResponseEntity.ok(BaseResponse.successMessage("등록 성공"));
    }
}
