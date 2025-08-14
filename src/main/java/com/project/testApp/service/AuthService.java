package com.project.testApp.service;

import com.project.testApp.model.dto.response.token.AuthToken;
import com.project.testApp.model.dto.response.token.RefreshTokenResponse;
import com.project.testApp.model.entity.RefreshToken;
import com.project.testApp.model.entity.User;
import com.project.testApp.exception.userException.UserNotFoundException;
import com.project.testApp.model.repository.RefreshTokenRepository;
import com.project.testApp.model.repository.UserRepository;
import com.project.testApp.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static com.project.testApp.service.ServiceText.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${test.expires.in}")
    private long EXPIRES_IN;

    @Value("${refresh.token.expires.in}")
    private Long refreshExpireSeconds;

    @Transactional
    public AuthToken login(String username, String password) {
        log.info(AUTH_SERVICE_LOGIN_LOG, username);

        User user = userRepository.findByUsername(username).orElseThrow(() -> {
            log.error(USER_NOT_FOUND_LOG, username);
            return new UserNotFoundException(username);
        });

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        String accessToken = "Bearer " + jwtTokenProvider.generateJwtTokenByUserId(user.getUserId());
        String refreshToken = createRefreshToken(user);

        log.info(AUTH_SERVICE_LOGIN_SUCCESS_LOG, username);

        return new AuthToken(accessToken, refreshToken, EXPIRES_IN);
    }

    @Transactional
    public RefreshTokenResponse generateNewRefreshToken(RefreshToken existingToken) {
        log.info(REFRESH_TOKEN_CREATION_LOG, existingToken.getUser().getUserName());

        User user = existingToken.getUser();
        String newToken = createRefreshToken(user);

        log.info(REFRESH_TOKEN_UPDATED_LOG, user.getUserName());

        return new RefreshTokenResponse(newToken);
    }

    @Transactional
    public String createRefreshToken(User user) {
        log.info(REFRESH_TOKEN_CREATION_LOG, user.getUserName());

        RefreshToken token = getOrCreateRefreshToken(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Date.from(Instant.now().plusSeconds(refreshExpireSeconds)));

        String savedToken = refreshTokenRepository.save(token).getToken();
        log.info(REFRESH_TOKEN_UPDATED_LOG, user.getUserName());

        return savedToken;
    }

    private RefreshToken getOrCreateRefreshToken(User user) {
        log.info(CREATE_NEW_REFRESH_TOKEN_LOG, user.getUserName());

        return Optional.ofNullable(refreshTokenRepository.findByUser_UserId(user.getUserId()))
                .orElseGet(() -> {
                    log.info(CREATE_NEW_REFRESH_TOKEN_LOG, user.getUserName());
                    RefreshToken newToken = new RefreshToken();
                    newToken.setUser(user);
                    return newToken;
                });
    }

    public boolean isRefreshExpired(RefreshToken token) {
        boolean expired = token.getExpiryDate().before(new Date());
        if (expired) {
            log.info(REFRESH_TOKEN_EXPIRED_LOG, token.getUser().getUserName());
        }
        return expired;
    }

    public Optional<RefreshToken> getByUserId(Long userId) {
        Optional<RefreshToken> token = Optional.ofNullable(refreshTokenRepository.findByUser_UserId(userId));
        token.ifPresent(t -> log.info(FOUND_REFRESH_TOKEN_LOG, userId));
        return token;
    }
}
