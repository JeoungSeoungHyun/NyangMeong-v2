package spring.project.nyangmong.config.intercepter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import spring.project.nyangmong.domain.user.User;
import spring.project.nyangmong.util.Script;

public class SessionIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // System.out.println("preHandle 호출됨");

        HttpSession session = request.getSession();
        User principal = (User) session.getAttribute("principal");

        if (principal == null) {
            // 스크립트 모듈
            String scriptMsg = Script.href("/login-form", "로그인이 필요한 서비스입니다!!");

            // 인코딩 안 해주면 한글이 깨진다.
            // 주의! PrintWriter을 사용하기 전에 response의 인코딩 타입을 미리 정해줘야 한다.
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");

            // * 스크립트 실행하기
            // response.getWriter() : '쓰기'를 통해 응답하겠다는 메서드 <- 이 메서드를
            // PrintWriter에 담아서 flush 해주면 스크립트를 실행시킬 수 있다. (물론 그냥 텍스트도 가능)
            PrintWriter out = response.getWriter();
            out.print(scriptMsg);
            out.flush();
            out.close();
            return false;
        } else {
            return true;
        }
    }
}
