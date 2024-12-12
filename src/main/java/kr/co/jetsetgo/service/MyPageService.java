package kr.co.jetsetgo.service;

import kr.co.jetsetgo.model.ReservationDetailDto;
import kr.co.jetsetgo.model.ReservationDto;
import kr.co.jetsetgo.model.TbMembersDto;

import java.util.List;
import java.util.Map;

public interface MyPageService {
    public TbMembersDto selectMembers(String username); //사용자 로그인 ID 값으로 사용자 정보 가져오기
    public boolean updateUserInfo(Map<String, String> userInfoMap); //사용자 정보 변경
    public boolean updateUserPassword(Map<String, String> userInfoMap); //사용자 정보 변경
    public boolean deleteUser(Map<String, String> userInfoMap); //사용자 탈퇴
    public List<ReservationDto> selectReservations(Map<String, String> ReservationMap); //예약 리스트 검색
    public List<ReservationDetailDto> selectReservationDetails(Map<String, String> ReservationMap); //예약 상세 검색
    public boolean updatePassport(Map<String, String> passportMap); //여권 정보 업데이트
    public boolean updateReservationStatus(Map<String, String> reservationMap); //예약 취소
}
