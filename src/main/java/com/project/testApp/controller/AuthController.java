
package com.project.testApp.controller;

import com.project.testApp.model.dto.request.userRequest.LoginRequest;
import com.project.testApp.model.dto.request.userRequest.RefreshTokenRequest;
import com.project.testApp.model.dto.response.generalResponse.GeneralResponseWithData;
import com.project.testApp.model.dto.response.generalResponse.SuccessStatus;
import com.project.testApp.model.dto.response.token.AuthToken;
import com.project.testApp.model.dto.response.token.RefreshTokenResponse;
import com.project.testApp.exception.userException.InvalidRefreshTokenException;
import com.project.testApp.exception.userException.RefreshTokenExpiredException;
import com.project.testApp.exception.userException.RefreshTokenNotFoundException;
import com.project.testApp.model.entity.RefreshToken;
import com.project.testApp.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<GeneralResponseWithData<AuthToken>> login(@Valid @RequestBody LoginRequest request) {
		AuthToken tokens = authService.login(request.username(), request.password());
		return ResponseEntity.ok(new GeneralResponseWithData<>(SuccessStatus.getSuccessStatus(), tokens));
	}

	@PostMapping("/refresh")
	public ResponseEntity<GeneralResponseWithData<RefreshTokenResponse>> refresh(@RequestBody RefreshTokenRequest refreshRequest) {

		RefreshToken token = authService.getByUserId(refreshRequest.userId())
				.orElseThrow(RefreshTokenNotFoundException::new);

		RefreshToken validToken = Optional.of(token)
				.filter(t -> t.getToken().equals(refreshRequest.refreshToken()))
				.orElseThrow(InvalidRefreshTokenException::new);

		RefreshToken nonExpiredToken = Optional.of(validToken)
				.filter(t -> !authService.isRefreshExpired(t))
				.orElseThrow(RefreshTokenExpiredException::new);

		RefreshTokenResponse response = authService.generateNewRefreshToken(nonExpiredToken);

		return ResponseEntity.ok(new GeneralResponseWithData<>(SuccessStatus.getSuccessStatus(), response));
	}

}
