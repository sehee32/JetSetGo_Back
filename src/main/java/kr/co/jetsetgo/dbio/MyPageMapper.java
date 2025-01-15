package kr.co.jetsetgo.dbio;

import kr.co.jetsetgo.model.ReservationDetailDto;
import kr.co.jetsetgo.model.ReservationDto;
import kr.co.jetsetgo.model.TbMembersDto;
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
//    String findFlightId(String departureTime, String arrivalTime, String departure, String destination, String departureCity, String arrivalCity); //기존 DB에 항공편 유무 확인하기
    String checkAndAddFlight(String departureTime, String arrivalTime, String departure, String destination, String departureCity, String arrivalCity); //항공편 추가하기
    boolean editReservationByReservationIdAndFlightId(String reservationId, Integer flightId, String changeFlightId, String price); //예약 수정
}
