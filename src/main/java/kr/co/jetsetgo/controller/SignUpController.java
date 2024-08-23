package kr.co.jetsetgo.controller;
import kr.co.jetsetgo.dbio.SignUpMapper;
import kr.co.jetsetgo.model.SignUpDto;
import kr.co.jetsetgo.model.TbMembersDto;
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


}

