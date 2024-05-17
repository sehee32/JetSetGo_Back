package kr.co.jetsetgo.controller;
import kr.co.jetsetgo.model.SignUpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SignUpController {

    private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest) {
        logger.info("회원가입 정보: {}", signUpRequest);  // 회원가입할 때 입력한 정보를 로그로 출력 //
        return ResponseEntity.ok("회원가입 성공");
    }


    @PostMapping("/checkUsername")
    // 아이디 중복확인 메소드
    public ResponseEntity<Map<String, Boolean>> checkUsername(@RequestBody Map<String, String> usernameRequest) {
        String username = usernameRequest.get("username");
        // 아이디 중복 여부 확인 코드 추가
        boolean exists = false; // 일단 false로 설정

        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }
}

