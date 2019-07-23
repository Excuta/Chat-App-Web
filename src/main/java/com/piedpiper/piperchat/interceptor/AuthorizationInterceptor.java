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
    private static final String KEY_TOKEN = "User-Token";
    private String tokenHeaderName = "Token";
    private AuthRepo authRepo;

    public AuthorizationInterceptor(AuthRepo authRepo) {
        this.authRepo = authRepo;
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
                boolean tokenValid = userAuth.isTokenValid();
                if (tokenValid) request.getSession().setAttribute(KEY_TOKEN, userAuth.getToken());
                return tokenValid;
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
