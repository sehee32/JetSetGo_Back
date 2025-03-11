package kr.co.jetsetgo.service;

import kr.co.jetsetgo.dbio.MyPageMapper;
import kr.co.jetsetgo.dbio.ReservationMapper;
import kr.co.jetsetgo.model.TbFlights;
import kr.co.jetsetgo.model.TbReservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservationService {
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private MyPageMapper myPageMapper;

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);


    // 예약내역 저장
    public void insertReservation(Map<String, Object> reservationData, List<Long> flightIds) throws Exception {

        TbReservation reservation = mapToReservation(reservationData);

        // 포트원 결제 금액 사전 등록 API 호출 등 추가 작업 수행 가능
        Map<String, String> data = new HashMap<>();
        data.put("merchant_uid", "uid_" + String.valueOf(reservation.getMember_Id()) + reservation.getReservation_Date());
        data.put("amount", String.valueOf(reservation.getPayment_Amount()));
        data.put("currency", String.valueOf(reservation.getPayment_Method()));
        // 포트원 결제 금액 사전 등록 API 호출
        CommonServiceImpl.createOrderParam(data);


        for (Long flightId : flightIds) {
            // 각 항공편 ID별로 예약 생성 및 저장
            reservation.setFlight_Id(flightId);
            reservationMapper.insertReservation(reservation);
        }
    }


        // 희가 작업할 곳 ^^.

        /*
         *  1. 데이터 가져온거 확인 ㅇㅇㅇ
         *
         *  2. for문
         *   2-1. Flights 테이블 데이터 insert ㅇㅇㅇ
         */
        /*
         *  3. RESERVATION 테이블 데이터 insert ㅜㅜ
         *
         * */


    public List<Long> saveFlights(List<Map<String, String>> flightData) {
        logger.info("받은 flightData: {}", flightData);

        List<Long> flightIds = new ArrayList<>();

        for (Map<String, String> flightDetail : flightData) {
            TbFlights flight = new TbFlights();
            String departureTimeStr = flightDetail.get("departureTime").replace("T", " ");
            flight.setDepartureTime(Timestamp.valueOf(departureTimeStr));
            String arrivalTimeStr = flightDetail.get("arrivalTime").replace("T", " ");
            flight.setArrivalTime(Timestamp.valueOf(arrivalTimeStr));
            flight.setOriginlocationcode(flightDetail.get("originlocationcode"));
            flight.setDestinationlocationcode(flightDetail.get("destinationlocationcode"));
            flight.setDepartureCity(flightDetail.get("departureCity"));
            flight.setArrivalCity(flightDetail.get("arrivalCity"));

            // 기존 항공편 존재 여부 확인 후 저장하고, ID 반환
            myPageMapper.checkAndAddFlight(flight);

            // 방금 삽입된 또는 기존에 존재하는 항공편의 ID 가져오기
            Long savedFlightId = myPageMapper.getFlightIdIfExists(flight);

            if (savedFlightId != null) {
                flightIds.add(savedFlightId);
                logger.info("저장된 Flight ID: {}", savedFlightId);
            } else {
                logger.error("Flight 데이터를 저장하지 못했습니다: {}", flightDetail);
            }
        }

        return flightIds; // 모든 항공편 ID 리스트 반환
    }



    public TbReservation mapToReservation(Map<String, Object> reservationData) {
        TbReservation reservation = new TbReservation();

        //데이터 타입 변환 후 저장
        reservation.setMember_Id(Long.parseLong(reservationData.get("member_Id").toString()));
        reservation.setReservation_Id((String) reservationData.get("reservation_Id"));
        reservation.setStatus((String) reservationData.getOrDefault("status", "예약대기"));
        reservation.setTrip_Type((String) reservationData.get("trip_Type"));

        String dateStr = (String) reservationData.get("reservation_Date");
        Timestamp timestamp = Timestamp.valueOf(dateStr.replace("T", " ").substring(0, 19));
        reservation.setReservation_Date(timestamp);

        reservation.setPassenger_Name((String) reservationData.get("passenger_Name"));
        reservation.setPhone_Number((String) reservationData.get("phone_Number"));
        reservation.setPassport_Number((String) reservationData.get("passport_Number"));
        reservation.setPassport_Expirydate((String) reservationData.get("passport_Expirydate"));
        reservation.setPassport_Issuingcountry((String) reservationData.get("passport_Issuingcountry"));

        Object paymentAmountObj = reservationData.get("payment_Amount");
        if (paymentAmountObj instanceof Number) {
            reservation.setPayment_Amount(((Number) paymentAmountObj).doubleValue());
        } else if (paymentAmountObj != null) {
            reservation.setPayment_Amount(Double.parseDouble(paymentAmountObj.toString()));
        }

        reservation.setPayment_Method((String) reservationData.get("payment_Method"));

        Object nonstopObj = reservationData.get("nonstop");
        if (nonstopObj instanceof Number) {
            reservation.setNonstop(((Number) nonstopObj).longValue());
        } else if (nonstopObj != null) {
            reservation.setNonstop(Long.parseLong(nonstopObj.toString()));
        }

        Object adultsObj = reservationData.get("adults");
        if (adultsObj instanceof Number) {
            reservation.setAdults(((Number) adultsObj).longValue());
        } else if (adultsObj != null) {
            reservation.setAdults(Long.parseLong(adultsObj.toString()));
        }

        Object childrenObj = reservationData.get("children");
        if (childrenObj instanceof Number) {
            reservation.setChildren(((Number) childrenObj).longValue());
        } else if (childrenObj != null) {
            reservation.setChildren(Long.parseLong(childrenObj.toString()));
        }

        String travelClass = (String)reservationData.getOrDefault("travelclass", "ECONOMY");
        reservation.setTravelclass(travelClass);

        return reservation;
    }


}
