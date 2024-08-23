package kr.co.jetsetgo.util;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiUtil {

    public static String client_id = "JLqIJGs7WzJNlFr2mTUhNzUKGHT5MHNu";
    private static String client_secret = "VIxVAatQMd3yMYTC";

    /* 토큰 조회 */
    public static String getToken() throws IOException {
        String token = null;
        try {
            // API 요청을 위한 URL
            URL url = new URL("https://test.api.amadeus.com/v1/security/oauth2/token");

            // HTTP 연결 객체 생성
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);

            // 요청 body 설정
            String requestBody = "grant_type=client_credentials" +
                    "&client_id=" + client_id +
                    "&client_secret=" + client_secret;

            // 요청 body 전송
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // 응답 코드 확인
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 응답 body 읽기
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();

                // JSON 응답 파싱
                String responseBody = response.toString();
                JSONObject jsonObject = new JSONObject(responseBody);
                token = jsonObject.getString("access_token");

                // access_token 사용 예시
                System.out.println("Access Token: " + token);
            } else {
                System.out.println("HTTP error code: " + responseCode);
            }
            // 연결 종료
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }

    /* API 호출 */
    public static String getApiInfo() throws IOException {

        return "";
    }

    /* 항공편 검색 */
    public static String searchFlights(String origin, String destination, String departureDate, int adults, int children, String travelClass, boolean nonStop) throws IOException {
        String token = getToken();
        if (token == null) {
            throw new IOException("토큰 가져오기 실패");
        }

        String urlString = String.format(
                "https://test.api.amadeus.com/v2/shopping/flight-offers?" +
                        "originLocationCode=%s&destinationLocationCode=%s&departureDate=%s&adults=%d&children=%d&travelClass=%s&nonStop=%s&max=5",
                origin, destination, departureDate, adults, children, travelClass, nonStop);

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + token);
        conn.setRequestProperty("Content-Type", "application/json");

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            // API 응답 출력
            return response.toString();

        } else {
            return "HTTP error code: " + responseCode;
        }

    }

}
