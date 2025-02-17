package kr.co.jetsetgo.dbio;

import kr.co.jetsetgo.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MyPageMapper {

    TbMembersDto findUserInfoByUserName(String userName); //사용자 로그인 ID로 검색
    boolean editUserInfo(String id, String contact); // 사용자 정보 수정
    boolean editUserPassword(String id, String password); // 사용자 정보 수정
    void removeUser(String id); // 사용자 삭제
    List<ReservationDto> findReservationByUserId(String userId); //예약 검색
    List<ReservationDetailDto> findReservationByReservationId(String id); //예약 상세 검색
    List<ReservationDetailDto> findReservationByReservationIdAndFlightId(String id, Integer flightId); //예약 취소 상세 검색
    boolean editPassport(String id, String passengerName, String phoneNumber, String passportNumber, String passportExpiryDate, String passportIssuingCountry); // 여권 정보 업데이트
    boolean cancelReservation(String id); // 예약 취소
    Map<String, String> findFlightCityById(Integer id); //항공편 도시명 가져오기(임시)
    long findFlightId(TbFlights flights); //기존 DB에 항공편 ID 검색
    long checkAndAddFlight(TbFlights flights); //기존 DB에 항공편 유무 확인 후 없을 경우 추가
    boolean editReservationByReservationIdAndFlightId(String reservationId, Integer flightId, long changeFlightId, String price); //예약 수정
    void insertReservation(TbReservation reservation);
}
