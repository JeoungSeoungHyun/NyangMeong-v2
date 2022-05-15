package spring.project.nyangmong.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import spring.project.nyangmong.handle.ex.CustomApiException;

public class RespScript {

    // response에 스크립트를 담아 돌려주는 메서드
    public static void 스크립트로응답하기(String scriptMsg, HttpServletResponse response) {

        // 인코딩 안 해주면 한글이 깨진다.
        // 주의! PrintWriter을 사용하기 전에 response의 인코딩 타입을 미리 정해줘야 한다.
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // * 스크립트 실행하기
        // response.getWriter() : '쓰기'를 통해 응답하겠다는 메서드 <- 이 메서드를
        // PrintWriter에 담아서 flush 해주면 스크립트를 실행시킬 수 있다. (물론 그냥 텍스트도 가능)
        try {
            PrintWriter out = response.getWriter();
            out.print(scriptMsg);
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new CustomApiException("잘못된 접근입니다.");
        }
    }
}
