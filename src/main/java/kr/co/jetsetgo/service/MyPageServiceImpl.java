package kr.co.jetsetgo.service;

import kr.co.jetsetgo.dbio.MyPageMapper;
import kr.co.jetsetgo.model.ReservationDetailDto;
import kr.co.jetsetgo.model.ReservationDto;
import kr.co.jetsetgo.model.TbMembersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class MyPageServiceImpl implements MyPageService{

    @Autowired
    private MyPageMapper myPageMapper;

    //사용자 로그인 ID 값으로 사용자 정보 가져오기
    public TbMembersDto selectMembers(String username) {
        
        TbMembersDto result = myPageMapper.findUserInfoByUserName(username);

        return result;
    }

    //사용자 정보 변경
    public boolean updateUserInfo(Map<String, String> userInfoMap) {
        String id = userInfoMap.get("userId");
        String contact = userInfoMap.get("contact");
        boolean isUpdated = myPageMapper.editUserInfo(id, contact);
        if (isUpdated) {
            System.out.println("Update successful");
        } else {
            System.out.println("Update failed");
        }

        return isUpdated;
    }

    //사용자 비밀번호 변경
    public boolean updateUserPassword(Map<String, String> userInfoMap) {
        String id = userInfoMap.get("userId");
        String password = userInfoMap.get("password");
        boolean isUpdated = myPageMapper.editUserPassword(id, password);

        if (isUpdated) {
            System.out.println("Update successful");
        } else {
            System.out.println("Update failed");
        }

        return isUpdated;
    }

    //사용자 탈퇴
    public boolean deleteUser(Map<String, String> userInfoMap) {
        String id = userInfoMap.get("id");
        myPageMapper.removeUser(id);

        return true;
    }

    //예약 리스트 검색
    public List<ReservationDto> selectReservations(Map<String, String> ReservationMap) {
        String userId = ReservationMap.get("userId");

        List<ReservationDto> results = myPageMapper.findReservationByUserId(userId);

        for (ReservationDto reservation : results) {

            LocalDateTime departureDateTime = reservation.getDeparture_Time();
            LocalDateTime arrivalDateTime = reservation.getArrival_Time();

            if (departureDateTime != null && arrivalDateTime != null) {
                // 날짜와 시간 추출
                reservation.setDepartureDate(departureDateTime.toLocalDate());// 날짜만 추출
                reservation.setDepartureTime(departureDateTime.toLocalTime());// 시간만 추출
                reservation.setArrivalDate(arrivalDateTime.toLocalDate());// 날짜만 추출
                reservation.setArrivalTime(arrivalDateTime.toLocalTime());// 시간만 추출

            } else {
                System.out.println("출발 정보가 없습니다.");
            }
        }

        return results;
    }

    //예약 상세 검색
    public List<ReservationDetailDto> selectReservationDetails(Map<String, String> ReservationMap) {
        String id = ReservationMap.get("id");

        List<ReservationDetailDto> results = myPageMapper.findReservationByReservationId(id);

        for (ReservationDetailDto reservation : results) {

            LocalDateTime departureDateTime = reservation.getDeparture_Time();
            LocalDateTime arrivalDateTime = reservation.getArrival_Time();
            int durationInMinutes = reservation.getDurationInMinutes();

            if (departureDateTime != null && arrivalDateTime != null) {
                // 날짜와 시간 추출
                reservation.setDepartureDate(departureDateTime.toLocalDate());// 날짜만 추출
                reservation.setDepartureTime(departureDateTime.toLocalTime());// 시간만 추출
                reservation.setArrivalDate(arrivalDateTime.toLocalDate());// 날짜만 추출
                reservation.setArrivalTime(arrivalDateTime.toLocalTime());// 시간만 추출

                // 출력 (또는 다른 로직)
                System.out.println("출발 날짜: " + reservation.getDepartureDate());
                System.out.println("출발 시간: " + reservation.getDepartureTime());
                System.out.println("도착 날짜: " + reservation.getArrivalDate());
                System.out.println("도착 시간: " + reservation.getArrivalTime());
            } else {
                System.out.println("출발 정보가 없습니다.");
            }

            if (durationInMinutes != 0) {
                int hours = durationInMinutes / 60; // 총 시간 계산
                int minutes = durationInMinutes % 60; // 남은 분 계산
                reservation.setDuration(String.format("%d시간 %d분", hours, minutes));

                // 출력 (또는 다른 로직)
                System.out.println("소요시간: " + reservation.getDuration());
            }

            //확인용
            System.out.println("항공편 아이디 " + reservation.getFlight_Id());
            System.out.println("출발 도시 " + reservation.getDeparture_City());
            System.out.println("상태 " + reservation.getStatus());
            System.out.println("여권 번호 " + reservation.getPassport_Number());
            System.out.println("여권 만료일 " + reservation.getPassport_ExpiryDate());
            System.out.println("여권 발행국 " + reservation.getPassport_IssuingCountry());
            System.out.println("결제 금액 " + reservation.getPayment_Amount());
            System.out.println("결제 수단 " + reservation.getPayment_Method());
            System.out.println("직항여부 " + reservation.getNonstop());
            System.out.println("좌석등급 " + reservation.getTravelclass());
            System.out.println("성인 " + reservation.getAdults());
            System.out.println("아동 " + reservation.getChildren());

        }

        return results;
    }

    //여권 정보 업데이트
    public boolean updatePassport(Map<String, String> passportMap) {
        String id = passportMap.get("id");
        String passengerName = passportMap.get("passengerName");
        String phoneNumber = passportMap.get("phoneNumber");
        String passportNumber = passportMap.get("passportNumber");
        String passportExpiryDate = passportMap.get("passportExpiryDate");
        String passportIssuingCountry = passportMap.get("passportIssuingCountry");
        System.out.println(id);
        System.out.println(passengerName);
        System.out.println(phoneNumber);
        System.out.println(passportNumber);
        System.out.println(passportExpiryDate);
        System.out.println(passportIssuingCountry);
        boolean isUpdated = myPageMapper.editPassport(id, passengerName, phoneNumber, passportNumber, passportExpiryDate, passportIssuingCountry);

        if (isUpdated) {
            System.out.println("Update successful");
        } else {
            System.out.println("Update failed");
        }

        return isUpdated;
    }

    //예약 취소
    public boolean updateReservationStatus(Map<String, String> reservationMap) {
        String id = reservationMap.get("id");
        boolean isUpdated = myPageMapper.cancelReservation(id);
        System.out.println(id);
        if (isUpdated) {
            System.out.println("Update successful");
        } else {
            System.out.println("Update failed");
        }

        return isUpdated;
    }
}
