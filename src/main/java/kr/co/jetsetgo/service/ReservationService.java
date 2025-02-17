package kr.co.jetsetgo.service;

import kr.co.jetsetgo.dbio.ReservationMapper;
import kr.co.jetsetgo.model.TbReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReservationService {
    @Autowired
    private ReservationMapper reservationMapper;


    // 예약내역 저장
    public void insertReservation(TbReservation reservation) throws Exception {
        Map<String, String> data = new HashMap<String, String>();

        data.put("merchant_uid", "uid_" + String.valueOf(reservation.getMember_Id()) + reservation.getReservation_Date());
        data.put("amount", String.valueOf(reservation.getPayment_Amount()));
        data.put("currency", String.valueOf(reservation.getPayment_Method()));
        // 포트원 결제 금액 사전 등록 API 호출
        CommonServiceImpl.createOrderParam(data);





        // 희가 작업할 곳 ^^.

        /*
         *  1. 데이터 가져온거 확인
         *
         *  2. for문
         *   2-1. Flights 테이블 데이터 insert
         */
//        TbFlights flights = new TbFlights();
//        flights.setDepartureTime(Timestamp.valueOf(changeDetail.get("departureTime")+":00"));
//        flights.setArrivalTime(Timestamp.valueOf(changeDetail.get("arrivalTime")+":00"));
//        flights.setOriginlocationcode(changeDetail.get("departure"));
//        flights.setDestinationlocationcode(changeDetail.get("destination"));
//        flights.setDepartureCity(currentFlight.get("DEPARTURE_CITY"));
//        flights.setArrivalCity(currentFlight.get("ARRIVAL_CITY"));
//        long resultId = myPageMapper.checkAndAddFlight(flights);


        /*
         *  3. RESERVATION 테이블 데이터 insert
         *
         * */

//        TbReservation reservation = new TbReservation();
//        reservation.setFlight_Id(resultId);
//        reservationMapper.insertReservation(reservation);



    }

}
