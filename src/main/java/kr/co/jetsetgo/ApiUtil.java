package kr.co.jetsetgo;

import com.google.gson.Gson;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.Map;

public class ApiUtil {

    private static String client_id = "SDDRMcSS3MEFhy94eLGkY0OTRpRL4UhD";
    private static String client_secret = "My5F2fQLQcMqRAkc";


    /* 토큰 조회 */
    public static String getToken() throws IOException {

        HttpsURLConnection conn = null;

        URL url = new URL("https://test.api.amadeus.com/v1/security/oauth2/token");

        conn = (HttpsURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("body", "grant_type=client_credentials&client_id="+client_id+"&client_secret="+client_secret);
        conn.setDoOutput(true);

        JSONObject json = new JSONObject();

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
}
