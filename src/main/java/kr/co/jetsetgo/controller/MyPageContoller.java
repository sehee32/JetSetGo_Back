package kr.co.jetsetgo.controller;

import kr.co.jetsetgo.model.TbMembersDto;
import kr.co.jetsetgo.service.MyPageService;
import kr.co.jetsetgo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MyPageContoller {

    @Autowired
    private MyPageService myPageService;

    @Autowired
    private JwtUtil jwtUtil;

    //로그인된 토큰 ID 값 가져와서 사용자 정보 가져오기
    @PostMapping(value = "/getUserInfos", produces = "application/json; charset=utf-8")
    public TbMembersDto getUserInfos(@RequestBody(required = false) Map<String, String> tokenMap){
        String token = tokenMap.get("token");
        // 사용자 이름 추출
        String username = jwtUtil.extractUsername(token);
        System.out.println("사용자 아이디: " + username);
        // 사용자 로그인 ID 값으로 사용자 정보 가져오기
        TbMembersDto result = myPageService.selectMembers(username);
        System.out.println("사용자 정보 : " + result);
        return result;
    }


}
