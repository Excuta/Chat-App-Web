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
    private String authHeaderName = "Bearer";
    private AuthRepo authRepo;
    private TokenFactory tokenFactory;

    public AuthorizationInterceptor(AuthRepo authRepo, TokenFactory tokenFactory) {
        this.authRepo = authRepo;
        this.tokenFactory = tokenFactory;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = getToken(request);
        if (token != null) {
            UserAuth userAuth = getUserAuth(token);
            if (userAuth != null) {
                if (userAuth.isValid()) return true;
                if (needsRefresh(userAuth)) {
                    refreshAuth(userAuth);
                    authRepo.save(userAuth);
                }
            }
        }
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return false;
    }

    private void refreshAuth(UserAuth userAuth) {
        userAuth.newToken(tokenFactory.createToken());
    }

    private boolean needsRefresh(UserAuth userAuth) {
        return userAuth.isTokenExpired() && !userAuth.isRefreshTokenExpired();
    }

    private UserAuth getUserAuth(String tokenValue) {
        Token token = Token.fromValue(tokenValue);
        return authRepo.findByTokenOrRefreshToken(token, token);
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader(authHeaderName);
        if (header == null) header = request.getHeader(authHeaderName.toLowerCase());
        return header;
    }
}
