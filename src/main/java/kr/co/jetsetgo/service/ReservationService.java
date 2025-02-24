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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservationService {
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private MyPageMapper myPageMapper;


    // 예약내역 저장
    public void insertReservation(TbReservation reservation, List<Map<String, String>> flightData) throws Exception {
        Map<String, String> data = new HashMap<String, String>();

        data.put("merchant_uid", "uid_" + String.valueOf(reservation.getMember_Id()) + reservation.getReservation_Date());
        data.put("amount", String.valueOf(reservation.getPayment_Amount()));
        data.put("currency", String.valueOf(reservation.getPayment_Method()));
        // 포트원 결제 금액 사전 등록 API 호출
        CommonServiceImpl.createOrderParam(data);


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

//        TbReservation reservation = new TbReservation();

        long resultId = saveFlights(flightData);
        reservation.setFlight_Id(resultId);
        reservationMapper.insertReservation(reservation);



    }

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);
    public long saveFlights(List<Map<String, String>> flightData) {
        logger.info("받은 flightData: {}", flightData);

        long resultId = 0;

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


            // 기존 항공편 존재 여부 확인 후 저장하고, ID를 반환
            resultId = myPageMapper.checkAndAddFlight(flight); // 마지막으로 저장된 항공편의 ID
        }

        return resultId;
    }

}
