package kr.co.jetsetgo;

import kr.co.jetsetgo.util.ApiUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class JetSetGoBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(JetSetGoBackApplication.class, args);
//        try {
//            String token = ApiUtil.getToken();
//            if (token != null) {
//                System.out.println("Token retrieved successfully: " + token);
//
//                // 항공편 검색 예시
//                ApiUtil.searchFlights("LAX", "JFK", "2024-09-01", 1, 0, "ECONOMY", true);
//                System.out.println("Flight search completed.");
//            } else {
//                System.out.println("Failed to retrieve token.");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
