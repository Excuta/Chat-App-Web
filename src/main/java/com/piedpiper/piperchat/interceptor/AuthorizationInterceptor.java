package com.piedpiper.piperchat.interceptor;

import com.piedpiper.piperchat.data.model.authorization.token.Token;
import com.piedpiper.piperchat.data.model.authorization.userauth.UserAuth;
import com.piedpiper.piperchat.data.repo.AuthRepo;
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
    public static final String sTOKEN_KEY = "TOKEN";
    private static final String KEY_TOKEN = "User-Token";
    private String tokenHeaderName = "Token";
    private AuthRepo authRepo;

    public AuthorizationInterceptor(AuthRepo authRepo) {
        this.authRepo = authRepo;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = getToken(request);
        if (validToken(token)) {
            request.setAttribute(sTOKEN_KEY, token);
            return true;
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
    }

    private boolean validToken(String token) {
        if (token != null) {
            UserAuth userAuth = getUserAuthByToken(token);
            if (userAuth != null) {
                return userAuth.isTokenValid();
            }
        }
        return false;
    }

    private UserAuth getUserAuthByToken(String tokenValue) {
        return authRepo.findByToken(Token.fromValue(tokenValue));
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader(tokenHeaderName);
        if (header == null) header = request.getHeader(tokenHeaderName.toLowerCase());
        return header;
    }
}
