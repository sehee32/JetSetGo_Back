package kr.co.jetsetgo.dbio;

import kr.co.jetsetgo.model.PortonePaymentDto;
import kr.co.jetsetgo.model.Reservation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
    void addReservationFlghts(Reservation reservation);
    void addPortonePayment(PortonePaymentDto portonePaymentDto);
}
