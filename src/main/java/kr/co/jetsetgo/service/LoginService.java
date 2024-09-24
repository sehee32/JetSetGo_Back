package kr.co.jetsetgo.service;

import kr.co.jetsetgo.dbio.SignUpMapper;
import kr.co.jetsetgo.model.TbMembersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private SignUpMapper signUpMapper;

    // 사용자 인증 처리
    public TbMembersDto authenticate(String username, String password) {
        // 데이터베이스에서 사용자 조회
        TbMembersDto user = signUpMapper.findByUsername(username);

        // 사용자 존재 여부와 비밀번호 확인
        if (user != null && user.getPassword().equals(password)) {
            return user;    // 인증 성공 시 사용자 리턴
        }

        return null; // 인증 실패 시 null
    }
}
