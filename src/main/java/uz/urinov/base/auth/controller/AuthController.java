package uz.urinov.base.auth.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.urinov.base.auth.dto.*;
import uz.urinov.base.auth.enums.Language;
import uz.urinov.base.auth.service.AuthService;
import uz.urinov.base.profile.dto.ProfileCreateDTO;
import uz.urinov.base.profile.dto.ProfileResponseDTO;
import uz.urinov.base.util.Result;

import static uz.urinov.base.constant.BaseApiUrls.*;


@Slf4j
@RestController
@RequestMapping(AUTH_URL)
@Tag(name = "Auth Controller", description = "Api list for authorization, registration and other ... ")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // Check User Phone Request (Profileni phone statusini tekshirish)
    @PostMapping(CHECK_URL)
    public ResponseEntity<CheckUserPhoneResponse> checkUserPhone(@Valid @RequestBody CheckUserPhoneRequest dto) {
        CheckUserPhoneResponse result = authService.checkUserPhone(dto);
        return ResponseEntity.ok().body(result);
    }

    // Profile registration Sms
    @PostMapping(REGISTER_URL)
    public ResponseEntity<Result> registrationSms(@Valid @RequestBody ProfileCreateDTO dto,
                                                  @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        Result result = authService.registrationSms(dto, lang);
        return ResponseEntity.status(result.isSuccess() ? 200 : 409).body(result);
    }

    // Profile verifySms (Sms kode orqali tekshiruvdan o'tkazish)
    @PostMapping(VERIFY_URL)
    public ResponseEntity<Result> verifySms(@Valid @RequestBody VerifyDto dto,
                                            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        Result result = authService.verifySms(dto, lang);
        return ResponseEntity.status(result.isSuccess() ? 200 : 409).body(result);
    }

    // Resent sms code (Parol esdan chiqib qolganda)
    @PostMapping(RESEND_URL)
    public ResponseEntity<Result> verificationResendSms(@PathVariable String phone,
                                                        @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        Result result = authService.verificationResendSms(phone, lang);
        return ResponseEntity.status(result.isSuccess() ? 200 : 409).body(result);
    }

    // Profile login
    @PostMapping(LOGIN_URL)
    public ResponseEntity<ProfileResponseDTO> loginUser(@RequestBody LoginDto loginDto,
                                                        @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        ProfileResponseDTO result = authService.loginProfile(loginDto, lang);
        return ResponseEntity.ok().body(result);
    }

    // Profile login
    @PostMapping(REFRESH_TOKEN_URL)
    public ResponseEntity<TokenResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        TokenResponse result = authService.refreshToken(request);
        return ResponseEntity.ok().body(result);
    }

    // Forget User password Request (Parol esdan chiqib qolganda  )
    @PostMapping(FORGET_URL)
    public ResponseEntity<Result> forget(@Valid @RequestBody CheckUserPhoneRequest dto,
                                         @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        Result result = authService.forget(dto, lang);
        return ResponseEntity.status(result.isSuccess() ? 200 : 409).body(result);
    }

    // Forget User password update Request (Parol esdan chiqib qolganda yangi parol o'rnatish)
    @PostMapping(UPDATE_PASSWORD_URL)
    public ResponseEntity<Result> forgetUpdatePassword(@Valid @RequestBody ForgetPasswordDto dto,
                                                       @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        Result result = authService.forgetUpdatePassword(dto, lang);
        return ResponseEntity.status(result.isSuccess() ? 200 : 409).body(result);
    }


}
