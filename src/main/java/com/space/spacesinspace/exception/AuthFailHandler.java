package com.space.spacesinspace.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;

@Configuration
public class AuthFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMsg = null;

        if (exception instanceof BadCredentialsException) {

            /* 설명. DB에 저장된 인증 정보, 즉 아이디가 존재하지 않거나 비밀번호가 틀린 경우의 에러 메시지 설정 */
            errorMsg = "[Auth-Failed] 아이디가 존재하지 않거나 비밀번호가 일치하지 않습니다.";
        } else if (exception instanceof InternalAuthenticationServiceException) {

            /* 설명. 서버 내부에서 사용자 정보를 검증하는 과정에서 발생하는 에러에 대한 메시지 설정 */
            errorMsg = "[Auth-Failed] 서버에서 오류가 발생되었습니다.";
        } else if (exception instanceof UsernameNotFoundException) {

            /* 설명. 사용자 정보가 DB에 없는 경우의 에러 메시지 설정(여기서 email은 ID에 입력하는 username을 의미) */
            errorMsg = "[Auth-Failed] 존재하지 않는 아이디 입니다.";
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {

            /* 설명. 보안 컨텍스트에 인증 객체가 존재하지 않거나,
             * 인증 정보가 없는 상태에서 보안 처리된 리소스에 접근하는 경우의 에러 메시지 설정 */
            errorMsg = "[Auth-Failed] 인증 요청이 거부되었습니다.";
        } else {

            /* 설명. 그 외의 알 수 없는 오류에 대한 에러 메시지 설정 */
            errorMsg = "[Auth-Failed] 알 수 없는 오류로 로그인 요청을 처리할 수 없습니다.";
        }

        /* 설명. URL을 안전하게 인코딩 하는데 사용되는 util로, 문자열을 URL에 사용 가능한 형식으로 인코딩해준다. */
        errorMsg = URLEncoder.encode(errorMsg, "UTF-8");

        /* 설명. 로그인 실패 시, redirect할 URL을 설정 (실패 메시지를 query string으로 전달) */
        setDefaultFailureUrl("/auth/fail?message=" + errorMsg);

        super.onAuthenticationFailure(request, response, exception);
    }
}
