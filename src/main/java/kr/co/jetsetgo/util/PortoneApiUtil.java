package kr.co.jetsetgo.util;

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
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Component
public class PortoneApiUtil {

    private static Logger logger	= LoggerFactory.getLogger(PortoneApiUtil.class);
    
    private static String imp_key;
    private static String imp_secret;

    @Value("${imp_key}")
    public void setImp_key(String value) {
        imp_key = value;
    }
    @Value("${imp_secret}")
    public void setImp_secret(String value) {
        imp_secret = value;
    }

    /**
     * 토큰 조회
     */
    public static String getToken() throws IOException {

        HttpsURLConnection conn = null;

        URL url = new URL("https://api.iamport.kr/users/getToken");

        conn = (HttpsURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);


        JSONObject json = new JSONObject();

        json.put("imp_key", imp_key);
        json.put("imp_secret", imp_secret);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

        bw.write(json.toString());
        bw.flush();
        bw.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

        Gson gson = new Gson();

        String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();
        String token = gson.fromJson(response, Map.class).get("access_token").toString();

        br.close();
        conn.disconnect();

        return token;
    }

    /**
     *   portone api 호출
     *   @param url : 호출 api url
     *   @param type : GET, POST
     *   @param param : 요청 파라미터
     */
    public static String getPortOneApi(String url, String type, @Nullable Map<String, String> param) throws IOException {

        HttpsURLConnection conn = null;

        URL url1 = new URL("https://api.iamport.kr"+url);

        conn = (HttpsURLConnection) url1.openConnection();

        conn.setRequestMethod(type);
        conn.setRequestProperty("Content-type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + getToken());
        conn.setDoOutput(true);
        JSONObject json = new JSONObject();

        if(Objects.equals(type, "POST")){
            for (Map.Entry<String, String> data : param.entrySet()) {
                json.put(data.getKey(), data.getValue());
            }
        }
        
        String uuid	= UUID.randomUUID().toString();

        logger.info("===============      PORTONE API S    " + uuid + "    ====================");
        logger.info(json.toString());
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

        bw.write(json.toString());
        bw.flush();
        bw.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

        Gson gson = new GsonBuilder().setLenient().create();
        
        String responseStr	= br.readLine();
        
        Object response = gson.fromJson(responseStr, Map.class).get("response");

        logger.info(responseStr);
        logger.info("===============      PORTONE API E    " + uuid + "    ====================");

        br.close();
        conn.disconnect();

        return gson.toJson(response, Map.class);
    }

    /**
     * 결제 내역 단 건 조회 portone API
     */
    public static <T> T selectOnePayPortone(String url, String paySeq, Class<T> t) throws Exception {

        String res = getPortOneApi(url + paySeq, "GET", null);
        JSONObject jsonObject = new JSONObject(res);

        return new ObjectMapper().readValue(jsonObject.toString(), t);
    }
}
