package kr.co.jetsetgo.controller;
import kr.co.jetsetgo.dbio.SignUpMapper;
import kr.co.jetsetgo.model.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8000")  // CORS 설정
public class SignUpController {

    @Autowired
    private SignUpMapper signUpMapper;
//    private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);

    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpDto signUpDto) {
//        logger.info("회원가입 정보: {}", signUpDto);  // 회원가입할 때 입력한 정보를 로그로 출력 //
        signUpMapper.insertMember(signUpDto);
        return "회원가입 성공";
    }


    @PostMapping("/checkUsername")
    public Map<String, Boolean> checkUsername(@RequestBody Map<String, String> usernameMap) {
        String username = usernameMap.get("username");
        SignUpDto existingMember = signUpMapper.findByUsername(username);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", existingMember != null);
        return response;
    }

//    @PostMapping("/identity-verifications")
//    public ResponseEntity<String> verifyIdentity(@RequestBody IdentityVerificationRequest request) {
//        logger.info("본인인증 요청 정보: {}", request);
//
//        try {
//            // PortoneApiV2Util을 사용하여 본인인증 요청 전송
//            String response = PortoneApiV2Util.sendIdentityVerificationRequest(
//                    request.getCustomerId(), request.getName(), request.getBirthDate(),
//                    request.getGender(), request.getCi(), request.getDi());
//
//            logger.info("본인인증 API 응답: {}", response);
//
//            return ResponseEntity.ok("본인인증 요청 성공");
//        } catch (IOException e) {
//            logger.error("본인인증 요청 중 오류 발생", e);
//            return ResponseEntity.status(500).body("본인인증 요청 실패");
//        }
//    }

}

