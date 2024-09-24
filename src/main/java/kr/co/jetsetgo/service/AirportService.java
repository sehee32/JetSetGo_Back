package kr.co.jetsetgo.service;

import kr.co.jetsetgo.dbio.AirportMapper;
import kr.co.jetsetgo.model.AirportDTO;
import kr.co.jetsetgo.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportService {
    @Autowired
    private AirportMapper airportMapper;

    public List<AirportDTO> searchAirportsByName(String city) {
        List<Airport> airports = airportMapper.findByCityNameContainingIgnoreCase(city); // 도시 이름으로 검색
        return airports.stream()
                .map(airport -> new AirportDTO(airport.getCode(), airport.getCity())) // cityName 필드 사용
                .collect(Collectors.toList());
    }
}
