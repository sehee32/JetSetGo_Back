package kr.co.jetsetgo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "TestSecretKey";
    private static final long EXPIRATION_TIME = 86400000; // jwt 토큰 만료시간 (1일)

    // JWT 토큰 생성
    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)  // 서브젝트 사용자id로 설정
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET_KEY));
    }

    // JWT 토큰에서 사용자 이름 추출 ( 사용시 메소드 호출 )
    public String extractUsername(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(SECRET_KEY))
                .build();   // jwt 검증자 생성
        DecodedJWT jwt = verifier.verify(token);    // 토큰 검증
        return jwt.getSubject();    // 사용자 이름 반환
    }
}
