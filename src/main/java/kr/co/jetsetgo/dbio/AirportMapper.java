package kr.co.jetsetgo.dbio;

import kr.co.jetsetgo.model.Airport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AirportMapper {
    List<Airport> findByCityNameContainingIgnoreCase(String city); // 도시 이름으로 검색
}
