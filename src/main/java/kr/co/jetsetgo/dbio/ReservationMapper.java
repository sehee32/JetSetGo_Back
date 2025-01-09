package kr.co.jetsetgo.dbio;

import kr.co.jetsetgo.model.TbReservation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {

    void insertReservation(TbReservation reservation);

}
