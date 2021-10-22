package com.spring.mvc.member.service;

import com.spring.mvc.member.domain.Member;
import com.spring.mvc.member.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;

    //회원가입기능
    public void signUp(Member member) {

        //비밀번호 인코딩
        String rawPw = member.getPassword();//순수한 비밀번호 빼내고
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();//비밀번호 인코딩후
        String encodePw = encoder.encode(rawPw);//암호화된 패스워드 리턴

        member.setPassword(encodePw);

        memberMapper.register(member);

    }

    /**
     * 중복확인 기능
     * @param type - 검사유형(account or email)
     * @param keyword - 검사값
     * @return 중복되면 true, 중복되지 않으면 false
     */
    public boolean isDuplicate(String type, String keyword) {

        Map<String, Object> checkMap = new HashMap<>();
        checkMap.put("type", type);
        checkMap.put("keyword", keyword);

        return memberMapper.isDuplicate(checkMap) ==1;// = return true
    }

    //회원정보 조회기능
    public Member getMember (String account) {
        //중간처리 있으면 해서 보내면 됨
        return memberMapper.getUser(account);
    }

    //로그인 처리기능
    public String login(String inputId, String inputPw) {//리턴이 String인 이유는 문자열로 로그인상태를 알려주기 위해
                                //평문으로 들어옴
        Member member = memberMapper.getUser(inputId);
        if(member != null) {
            String dbPw = member.getPassword();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder.matches(inputPw, dbPw) ? "loginSuccess" : "pwFail";//if else문에 if뒤에 커서 놓고 알트엔터 ..

        }else {//null이다 회원가입 안되어있다.
            return "idFail";
        }
    }


}
