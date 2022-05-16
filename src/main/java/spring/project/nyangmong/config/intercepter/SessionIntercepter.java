package spring.project.nyangmong.config.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.util.RespScript;
import spring.project.nyangmong.util.Script;

public class SessionIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // System.out.println("preHandle 호출됨");

        HttpSession session = request.getSession();
        User principal = (User) session.getAttribute("principal");

        if (principal == null) {
            // 인증이 필요한 서비스에 인증 없이 접근했을 때 스크립트 처리하기
            String scriptMsg = Script.href("/login-form", "로그인이 필요한 서비스입니다.");
            RespScript.스크립트로응답하기(scriptMsg, response);
            return false;
        } else {
            return true;
        }
    }
}
