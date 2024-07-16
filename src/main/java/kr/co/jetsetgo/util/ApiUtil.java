package kr.co.jetsetgo.util;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiUtil {

    public static String client_id = "SDDRMcSS3MEFhy94eLGkY0OTRpRL4UhD";
    private static String client_secret = "My5F2fQLQcMqRAkc";

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
}
