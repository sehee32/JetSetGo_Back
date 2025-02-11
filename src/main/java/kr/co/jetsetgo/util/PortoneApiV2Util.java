package kr.co.jetsetgo.util;

// 필요한 라이브러리 import
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.annotation.Nullable;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;


@Component
public class PortoneApiV2Util {

    private static Logger logger = LoggerFactory.getLogger(PortoneApiV2Util.class);
    private static String PORTONE_API_SECRET;

    @Value("${PORTONE_API_SECRET}")
    public void setApiSecret(String value) { PORTONE_API_SECRET = value;
    }

    /**
     * 토큰 조회
     */
    public static String getToken() throws IOException {

        HttpsURLConnection conn = null;

        URL url = new URL("https://api.portone.io/login/api-secret");

        conn = (HttpsURLConnection) url.openConnection(); // URL 연결

        conn.setRequestMethod("POST"); // HTTP 메소드 설정
        conn.setRequestProperty("Content-type", "application/json"); // 요청 헤더 설정
        conn.setRequestProperty("Accept", "application/json"); // 요청 헤더 설정
        conn.setDoOutput(true); // OutputStream을 사용해 데이터를 전송하도록 설정

        JSONObject json = new JSONObject(); // JSON 객체 생성
        json.put("PORTONE_API_SECRET",PORTONE_API_SECRET);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

        bw.write(json.toString()); // JSON 문자열을 버퍼에 작성
        bw.flush();
        bw.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

        Gson gson = new Gson();

        String response = gson.fromJson(br.readLine(), Map.class).get("response").toString(); // 응답을 Map으로 파싱하여 "response" 필드 추출
        String token = gson.fromJson(response, Map.class).get("access_token").toString(); // "access_token" 필드 추출

        br.close(); // BufferedReader 닫기
        conn.disconnect(); // 연결 해제

        return token; // 토큰 반환
    }

    /**
     * portone api 호출
     * @param url : 호출 api url
     * @param type : GET, POST
     * @param param : 요청 파라미터
     */
    public static String getPortOneApiV2(String url, String type, @Nullable Map<String, String> param) throws IOException {

        HttpsURLConnection conn = null; // HttpsURLConnection 객체 선언

        URL url1 = new URL("https://api.portone.io" + url); // 호출할 API URL 생성

        conn = (HttpsURLConnection) url1.openConnection(); // URL 연결

        conn.setRequestMethod(type); // HTTP 메소드 설정
        conn.setRequestProperty("Content-type", "application/json"); // 요청 헤더 설정
        conn.setRequestProperty("Accept", "application/json"); // 요청 헤더 설정
        conn.setRequestProperty("Authorization", "Bearer " + getToken()); // 인증 헤더 설정
        conn.setDoOutput(true); // OutputStream을 사용해 데이터를 전송하도록 설정

        JSONObject json = new JSONObject(); // JSON 객체 생성

        if(Objects.equals(type, "POST")) { // HTTP 메소드가 POST인 경우
            for (Map.Entry<String, String> data : param.entrySet()) { // 요청 파라미터를 JSON 객체에 추가
                json.put(data.getKey(), data.getValue());
            }
        }

        String uuid = UUID.randomUUID().toString(); // 고유 식별자 생성

        logger.info("===============      PORTONE API S    " + uuid + "    ====================");
        logger.info(json.toString());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

        bw.write(json.toString()); // JSON 문자열을 버퍼에 작성
        bw.flush();
        bw.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

        Gson gson = new GsonBuilder().setLenient().create();

        String responseStr = br.readLine(); // 응답 읽기

        Object response = gson.fromJson(responseStr, Map.class).get("response"); // 응답을 Map으로 파싱하여 "response" 필드 추출

        logger.info(responseStr);
        logger.info("===============      PORTONE API E    " + uuid + "    ====================");

        br.close();
        conn.disconnect(); // 연결 해제

        return gson.toJson(response, Map.class); // 응답을 JSON 문자열로 변환하여 반환
    }

//
//    /**
//     * 결제 내역 단 건 조회 portone API
//     */
//    public static <T> T selectOnePayPortone(String url, String paySeq, Class<T> t) throws Exception {
//
//        String res = getPortOneApi(url + paySeq, "GET", null);
//        JSONObject jsonObject = new JSONObject(res);
//
//        return new ObjectMapper().readValue(jsonObject.toString(), t);
//    }

    /**
     * 본인인증 단건 조회
     * @param identityVerificationId : 본인인증 고유 ID
     * @param t : 반환할 클래스 타입
     */
    public static <T> T getIdentityVerification(String identityVerificationId, Class<T> t) throws Exception {

        String url = "/identity-verifications/" + identityVerificationId; // 본인인증 조회 API URL 생성
        String res = getPortOneApiV2(url, "GET", null); // 본인인증 조회 API 호출
        JSONObject jsonObject = new JSONObject(res); // 응답을 JSON 객체로 변환

        return new ObjectMapper().readValue(jsonObject.toString(), t); // JSON 객체를 지정된 클래스 타입으로 변환하여 반환
    }

    /**
     * 본인인증 요청 전송
     *
     * @param identityVerificationId : 본인인증 고유 ID
     * @param storeId                : 상점 아이디 (선택적)
     * @param channelKey             : 채널 키
     * @param customerId             : 고객 ID
     * @param customerName           : 고객 이름
     * @param phoneNumber            : 전화번호
     * @param identityNumber         : 주민등록번호 앞 7자리 (SMS 방식일 경우 필수)
     * @param ipAddress              : IP 주소
     * @param customData             : 사용자 지정 데이터 (선택적)
     * @param operator               : 본인인증 통신사
     * @param method                 : 본인인증 방식 (SMS 또는 APP)
     * @return API 응답 결과를 JSON 형태로 반환
     * @throws IOException           : HTTP 요청 중 발생할 수 있는 예외
     */
    public static String sendIdentityVerificationRequest(String identityVerificationId, String storeId, String channelKey, String customerId,
                                                         String customerName, String phoneNumber, String identityNumber,
                                                         String ipAddress, String customData, String operator, String method) throws IOException {

        String url = "/identity-verifications/" + identityVerificationId + "/send"; // 본인인증 요청 API URL 생성

        JSONObject json = new JSONObject(); // JSON 객체 생성
        json.put("storeId", storeId);
        json.put("channelKey", channelKey);

        JSONObject customer = new JSONObject(); // 고객 정보 JSON 객체 생성
        customer.put("id", customerId);
        customer.put("name", customerName);
        customer.put("phoneNumber", phoneNumber);
        if (identityNumber != null) {
            customer.put("identityNumber", identityNumber); // SMS 방식일 경우 필수
        }

        json.put("customer", customer);
        json.put("ipAddress", ipAddress);
        json.put("customData", customData);
        json.put("operator", operator);
        json.put("method", method);

        // JSONObject를 Map으로 변환
        Map<String, String> paramMap = new HashMap<>();
        for (String key : json.keySet()) {
            paramMap.put(key, String.valueOf(json.get(key)));
        }

        return getPortOneApiV2(url, "POST", paramMap);
    }

    /**
     * 본인인증 확인 요청 전송
     *
     * @param identityVerificationId 본인인증 고유 ID
     * @param storeId                상점 아이디 (선택적)
     * @param otp                    OTP (SMS 방식에서만 사용됨, 선택적)
     * @return API 응답 결과를 JSON 형태로 반환
     * @throws IOException           HTTP 요청 중 발생할 수 있는 예외
     */
    public static String confirmIdentityVerification(String identityVerificationId, String storeId, String otp) throws IOException {
        String url = "/identity-verifications/" + identityVerificationId + "/confirm";

        JSONObject json = new JSONObject();
        if (storeId != null) {
            json.put("storeId", storeId);
        }
        if (otp != null) {
            json.put("otp", otp);
        }

        // JSONObject를 Map으로 변환
        Map<String, String> paramMap = new HashMap<>();
        for (String key : json.keySet()) {
            paramMap.put(key, String.valueOf(json.get(key)));
        }

        return getPortOneApiV2(url, "POST", paramMap);
    }

    /**
     * SMS 본인인증 요청 재전송
     *
     * @param identityVerificationId : 본인인증 고유 ID
     * @param storeId                : 상점 아이디 (선택적)
     */
    public static String resendSmsIdentityVerification(String identityVerificationId, String storeId) throws IOException {
        String url = "/identity-verifications/" + identityVerificationId + "/resend"; // 본인인증 요청 재전송 API URL 생성

        JSONObject json = new JSONObject(); // JSON 객체 생성
        json.put("storeId", storeId);

        Map<String, String> paramMap = new HashMap<>();
        for (String key : json.keySet()) {
            paramMap.put(key, String.valueOf(json.get(key)));
        }

        return getPortOneApiV2(url, "POST", paramMap);

    }




}
