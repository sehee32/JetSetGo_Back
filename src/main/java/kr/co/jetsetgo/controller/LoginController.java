package kr.co.jetsetgo.controller;

import kr.co.jetsetgo.model.LoginDto;
import kr.co.jetsetgo.model.TbMembersDto;
import kr.co.jetsetgo.service.LoginService;
import kr.co.jetsetgo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtUtil jwtUtil;    // jwt 토큰 생성

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody TbMembersDto loginRequest) {
        // 로그인 시도 사용자 인증
        TbMembersDto user = loginService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        // 인증 실패 시 401 응답
        if (user == null) {
            return ResponseEntity.status(401).body("잘못된 정보입니다.");
        }

        // 인증 성공 시 JWT 토큰 생성
        String token = jwtUtil.generateToken(user.getUsername());

        // 토큰 반환
        return ResponseEntity.ok(new LoginDto(token));
    }
}
