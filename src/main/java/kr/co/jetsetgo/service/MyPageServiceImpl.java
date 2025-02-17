package kr.co.jetsetgo.service;

import kr.co.jetsetgo.dbio.MyPageMapper;
import kr.co.jetsetgo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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


    //예약 변경 상세 검색
    public List<ReservationDetailDto> selecteReservationChangeDetails(Map<String, Object> ReservationMap) {
        String id = (String) ReservationMap.get("id");
        List<Integer> ids = (List<Integer>) ReservationMap.get("selectedFlightChangeId");
        System.out.println("Received IDs: " + ids);


        // 결과 리스트 초기화
        List<ReservationDetailDto> results = new ArrayList<>();

        if (ids != null && !ids.isEmpty()) {
            // ids가 있는 경우 각각 처리
            for (Integer selectedFlightId : ids) {
                List<ReservationDetailDto> partialResults = myPageMapper.findReservationByReservationIdAndFlightId(id, selectedFlightId);
                if (partialResults != null) {
                    results.addAll(partialResults);
                }
            }
        }
        // 디버그 출력
        System.out.println("Final Results: " + results);


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
            System.out.println("직항여부 " + reservation.getNonstop());
            System.out.println("좌석등급 " + reservation.getTravelclass());
            System.out.println("성인 " + reservation.getAdults());
            System.out.println("아동 " + reservation.getChildren());

        }

        return results;
    }

//    예약 변경 요청
    public boolean selecteReservationChangeDetailsData(List<Map<String, Object>> changeFlights) {
        for (Map<String, Object> flight : changeFlights) {
            Integer flightId = (Integer) flight.get("flightId");
            Map<String, String> changeDetail = (Map<String, String>) flight.get("changeDetail");
            String reservationId = (String) flight.get("reservationId");

            //항공편 도시명 가져오기(임시)
            Map<String, String> currentFlight = myPageMapper.findFlightCityById(flightId);

            TbFlights flights = new TbFlights();
            flights.setDepartureTime(Timestamp.valueOf(changeDetail.get("departureTime")+":00"));
            flights.setArrivalTime(Timestamp.valueOf(changeDetail.get("arrivalTime")+":00"));
            flights.setOriginlocationcode(changeDetail.get("departure"));
            flights.setDestinationlocationcode(changeDetail.get("destination"));
            flights.setDepartureCity(currentFlight.get("DEPARTURE_CITY"));
            flights.setArrivalCity(currentFlight.get("ARRIVAL_CITY"));
            long resultId = myPageMapper.checkAndAddFlight(flights);

            System.out.println("새로운 아이디: " + flights.getId());
            System.out.println("추가 여부: " + resultId);

            boolean result = false;

            if (resultId != 0){
                result = myPageMapper.editReservationByReservationIdAndFlightId(reservationId, flightId, flights.getId(), changeDetail.get("price"));
            }else{
                long flightChangeId = myPageMapper.findFlightId(flights);
                result = myPageMapper.editReservationByReservationIdAndFlightId(reservationId, flightId, flightChangeId, changeDetail.get("price"));
                System.out.println("이미 있음: " + flightChangeId); // 확인용
            }

            System.out.println("결과: " + result); // 확인용

            System.out.println("Reservation ID: " + reservationId); // 확인용
            System.out.println("Flight ID: " + flightId); // 확인용
            System.out.println("Details: " + changeDetail); // 확인용
        }

        return true;
    }

    @PostMapping(value = "/myPageReservationChangeDetailsData", produces = "application/json; charset=utf-8")
    public boolean myPageReservationChangeDetailsData(@RequestBody(required = false) List<Map<String, Object>> changeFlights) {
        boolean result = true;

        for (Map<String, Object> flight : changeFlights) {
            Map<String, String> changeDetail = (Map<String, String>) flight.get("changeDetail");

            TbFlights outgoingFlight = new TbFlights();
            outgoingFlight.setDepartureTime(Timestamp.valueOf(changeDetail.get("departureTime") + ":00"));
            outgoingFlight.setArrivalTime(Timestamp.valueOf(changeDetail.get("arrivalTime") + ":00"));
            outgoingFlight.setOriginlocationcode(changeDetail.get("departure"));
            outgoingFlight.setDestinationlocationcode(changeDetail.get("destination"));
            outgoingFlight.setDepartureCity(changeDetail.get("departureCity"));
            outgoingFlight.setArrivalCity(changeDetail.get("arrivalCity"));

            long outgoingFlightId = myPageMapper.checkAndAddFlight(outgoingFlight);

            // 왕복 예약일 경우 returnFlight 처리
            if ("왕복".equals(flight.get("tripType"))) {
                Map<String, String> returnChangeDetail = (Map<String, String>) flight.get("returnFlight");
                TbFlights returnFlight = new TbFlights();
                returnFlight.setDepartureTime(Timestamp.valueOf(returnChangeDetail.get("departureTime") + ":00"));
                returnFlight.setArrivalTime(Timestamp.valueOf(returnChangeDetail.get("arrivalTime") + ":00"));
                returnFlight.setOriginlocationcode(returnChangeDetail.get("departure"));
                returnFlight.setDestinationlocationcode(returnChangeDetail.get("destination"));
                returnFlight.setDepartureCity(returnChangeDetail.get("departureCity"));
                returnFlight.setArrivalCity(returnChangeDetail.get("arrivalCity"));

                long returnFlightId = myPageMapper.checkAndAddFlight(returnFlight);

                // 예약 정보에 왕복 항공편을 저장
                result &= saveReservation((String) flight.get("reservationId"), outgoingFlightId, returnFlightId);
            } else {
                // 편도일경우
                result &= saveReservation((String) flight.get("reservationId"), outgoingFlightId, 0);
            }
        }

        return result;
    }

    private boolean saveReservation(String reservationId, long outgoingFlightId, long returnFlightId) {
        TbReservation reservation = new TbReservation();
        reservation.setReservation_Id(Long.parseLong(reservationId));
        reservation.setFlight_Id(outgoingFlightId); // 첫 번째 항공편
        myPageMapper.insertReservation(reservation);

        // 왕복 항공편이 있는 경우 두 번째 항공편을 추가
        if (returnFlightId != 0) {
            reservation.setFlight_Id(returnFlightId); // 두 번째 항공편
            myPageMapper.insertReservation(reservation);
        }

        return true;
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
