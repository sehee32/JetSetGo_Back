package kr.co.jetsetgo.util;

import org.json.JSONArray;
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
            URL url = new URL("https://test.api.amadeus.com/v1/security/oauth2/token");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);

            String requestBody = "grant_type=client_credentials" +
                    "&client_id=" + client_id +
                    "&client_secret=" + client_secret;

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();

                String responseBody = response.toString();
                JSONObject jsonObject = new JSONObject(responseBody);
                token = jsonObject.getString("access_token");
            } else {
                System.out.println("HTTP error code: " + responseCode);
            }
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }

    /* 항공편 검색 */
    public static String searchFlights(String origin, String destination, String departureDate, int adults, int children, String travelClass, boolean nonStop) throws IOException {
        String token = getToken();
        if (token == null) {
            throw new IOException("토큰 가져오기 실패");
        }

        String urlString = String.format(
                "https://test.api.amadeus.com/v2/shopping/flight-offers?" +
                        "originLocationCode=%s&destinationLocationCode=%s&departureDate=%s&adults=%d&children=%d&travelClass=%s&nonStop=%s&max=30&currencyCode=KRW",
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

            // API 응답 JSON에서 필요한 데이터만 추출
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray flightOffers = jsonResponse.getJSONArray("data");
            JSONArray resultArray = new JSONArray();

            for (int i = 0; i < flightOffers.length(); i++) {
                JSONObject offer = flightOffers.getJSONObject(i);
                JSONObject itinerary = offer.getJSONArray("itineraries").getJSONObject(0);  // 여정
                JSONObject segment = itinerary.getJSONArray("segments").getJSONObject(0);   // 구간

                JSONObject flightData = new JSONObject();
                flightData.put("id", offer.getString("id"));    // 항공편 ID
                flightData.put("departureTime", segment.getJSONObject("departure").getString("at"));    // 출발시간
                flightData.put("arrivalTime", segment.getJSONObject("arrival").getString("at"));    // 도착시간
                flightData.put("duration", itinerary.getString("duration"));    // 소요시간
                flightData.put("price", offer.getJSONObject("price").getString("total"));   // 항공편 총 가격
                flightData.put("currency", offer.getJSONObject("price").getString("currency")); // 가격 통화

                resultArray.put(flightData);
            }

            return resultArray.toString();
        } else {
            return "HTTP error code: " + responseCode;
        }
    }

    /* 공항 및 도시 검색 */
    public static String searchAirports(String keyword) throws IOException {
        String token = getToken();
        if (token == null) {
            throw new IOException("토큰 가져오기 실패");
        }

        String urlString = String.format(
                "https://test.api.amadeus.com/v1/reference-data/locations?subType=AIRPORT,CITY&keyword=%s&page[limit]=10",
                keyword);

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

            // API 응답 JSON에서 필요한 데이터만 추출
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray locations = jsonResponse.getJSONArray("data");
            JSONArray resultArray = new JSONArray();

            for (int i = 0; i < locations.length(); i++) {
                JSONObject location = locations.getJSONObject(i);

                JSONObject locationData = new JSONObject();
                locationData.put("name", location.getString("name"));
                locationData.put("iataCode", location.getString("iataCode"));
                locationData.put("subType", location.getString("subType"));

                resultArray.put(locationData);
            }

            return resultArray.toString();

        } else {
            return "HTTP error code: " + responseCode;
        }
    }
}

