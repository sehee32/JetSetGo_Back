package kr.co.jetsetgo.controller;

import kr.co.jetsetgo.model.ReservationDetailDto;
import kr.co.jetsetgo.model.ReservationDto;
import kr.co.jetsetgo.model.TbMembersDto;
import kr.co.jetsetgo.service.MyPageService;
import kr.co.jetsetgo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    //사용자 정보 변경
    @PostMapping(value = "/myPageUserInfoEdit", produces = "application/json; charset=utf-8")
    public boolean myPageUserInfoEdit(@RequestBody(required = false) Map<String, String> userInfoMap){
        boolean result = myPageService.updateUserInfo(userInfoMap);
        return result;
    }

    //사용자 비밀번호 변경
    @PostMapping(value = "/myPageUserPasswordEdit", produces = "application/json; charset=utf-8")
    public boolean myPageUserPasswordEdit(@RequestBody(required = false) Map<String, String> userInfoMap){
        boolean result = myPageService.updateUserPassword(userInfoMap);
        return result;
    }

    //사용자 탈퇴
    @PostMapping(value = "/myPageUserRemove", produces = "application/json; charset=utf-8")
    public boolean myPageUserRemove(@RequestBody(required = false) Map<String, String> userInfoMap){
        boolean result = myPageService.deleteUser(userInfoMap);
        return result;
    }

    //예약 리스트 검색
    @PostMapping(value = "/myPageReservations", produces = "application/json; charset=utf-8")
    public List<ReservationDto> myPageReservations(@RequestBody(required = false) Map<String, String> ReservationMap){
        List<ReservationDto> result = myPageService.selectReservations(ReservationMap);
        System.out.println(result);
        return result;
    }

    //예약 상세 검색
    @PostMapping(value = "/myPageReservationDetails", produces = "application/json; charset=utf-8")
    public List<ReservationDetailDto> myPageReservationDetails(@RequestBody(required = false) Map<String, String> ReservationMap){
        List<ReservationDetailDto> result = myPageService.selectReservationDetails(ReservationMap);
        System.out.println(result);
        return result;
    }

    //예약 취소
    @PostMapping(value = "/myPageCancelReservation", produces = "application/json; charset=utf-8")
    public boolean myPageCancelReservation(@RequestBody(required = false) Map<String, String> reservationMap){
        boolean result = myPageService.updateReservationStatus(reservationMap);
        return result;
    }


}
