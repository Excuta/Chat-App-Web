package com.piedpiper.piperchat.interceptor;

import com.piedpiper.piperchat.bean.security.authorization.token.Token;
import com.piedpiper.piperchat.bean.security.authorization.token.TokenFactory;
import com.piedpiper.piperchat.bean.security.authorization.userauth.UserAuth;
import com.piedpiper.piperchat.bean.security.authorization.userauth.repo.AuthRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created By: Yahia
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    private String tokenHeaderName = "Token";
    private String refreshTokenHeaderName = "Refresh";
    private AuthRepo authRepo;
    private TokenFactory tokenFactory;

    public AuthorizationInterceptor(AuthRepo authRepo, TokenFactory tokenFactory) {
        this.authRepo = authRepo;
        this.tokenFactory = tokenFactory;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (validToken(request)) return true;
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
    }

    private boolean validToken(HttpServletRequest request) {
        String token = getToken(request);
        if (token != null) {
            UserAuth userAuth = getUserAuthByToken(token);
            if (userAuth != null) {
                return userAuth.isTokenValid();
            }
        } else {
            token = getRefreshToken(request);
            if (token != null) {
                UserAuth userAuth = getUserAuthByRefreshToken(token);
                if (userAuth.isRefreshTokenValid()) {
                    refreshAuth(userAuth);
                    authRepo.save(userAuth);
                    return true;
                }
            }
        }
        return false;
    }

    private void refreshAuth(UserAuth userAuth) {
        userAuth.newToken(tokenFactory.createToken());
    }

    private UserAuth getUserAuthByToken(String tokenValue) {
        return authRepo.findByToken(Token.fromValue(tokenValue));
    }

    private UserAuth getUserAuthByRefreshToken(String tokenValue) {
        return authRepo.findByRefreshToken(Token.fromValue(tokenValue));
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader(tokenHeaderName);
        if (header == null) header = request.getHeader(tokenHeaderName.toLowerCase());
        return header;
    }

    private String getRefreshToken(HttpServletRequest request) {
        String header = request.getHeader(refreshTokenHeaderName);
        if (header == null) header = request.getHeader(refreshTokenHeaderName.toLowerCase());
        return header;
    }
}
