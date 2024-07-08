package kr.co.jetsetgo.service;

import kr.co.jetsetgo.model.SupportDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//import org.json.JSONObject;
//import java.sql.*;

@Service
public class SupportServicelmpl implements SupportService{
    public List<SupportDTO> selectSupports(String data) {
//        // JDBC 연결 변수
//        private static Connection connection = null;
//        private static Statement statement = null;
//        private static ResultSet resultSet = null;
//
//        // JDBC 연결 정보
//        private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
//        private static final String DB_USER = "your_username";
//        private static final String DB_PASSWORD = "your_password";
//
//        public static void main(String[] args) {
//            // JSON 문자열
//            String jsonString = "{\"search\":\"value_to_search\", \"category\":\"baggage\"}";
//
//            try {
//                // JSON 파싱
//                JSONObject json = new JSONObject(jsonString);
//                String searchValue = json.getString("search");
//                String categoryValue = json.getString("category");
//
//                // JDBC 드라이버 로드
//                Class.forName("com.mysql.cj.jdbc.Driver");
//
//                // 데이터베이스 연결
//                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//
//                // SQL 쿼리 준비
//                String query = "SELECT * FROM your_table_name WHERE search_column = ? AND category_column = ?";
//
//                // PreparedStatement를 사용하여 SQL 인젝션을 방지
//                PreparedStatement preparedStatement = connection.prepareStatement(query);
//                preparedStatement.setString(1, searchValue);
//                preparedStatement.setString(2, categoryValue);
//
//                // 쿼리 실행 및 결과 가져오기
//                resultSet = preparedStatement.executeQuery();
//
//                // 결과 처리
//                while (resultSet.next()) {
//                    // 결과에서 필요한 작업 수행
//                    int id = resultSet.getInt("id");  // 예시: id 칼럼
//                    String name = resultSet.getString("name");  // 예시: name 칼럼
//                    // 원하는 데이터를 가져와서 처리
//                    System.out.println("ID: " + id + ", Name: " + name);
//                }
//            } catch (ClassNotFoundException | SQLException e) {
//                e.printStackTrace();
//            } finally {
//                // 자원 해제
//                try {
//                    if (resultSet != null) resultSet.close();
//                    if (statement != null) statement.close();
//                    if (connection != null) connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        List<SupportDTO> searchResults = new ArrayList<>();
        searchResults.add(new SupportDTO(1, 1,101, "확인용:"+data, "내용", "작성자1", "2024-05-21", "테스트용 답변입니다.", true,"etc"));
        searchResults.add(new SupportDTO(3, 1, 103, "제목3", "내용", "작성자3","2024-05-24", "", true,"etc"));
        searchResults.add(new SupportDTO(2, 1, 102, "제목2", "내용", "작성자2","2024-05-23", "", false,"etc"));
        searchResults.add(new SupportDTO(4, 1, 104, "제목4", "내용", "작성자4","2024-05-25", "", true,"etc"));
        searchResults.add(new SupportDTO(5, 1, 105, "제목5", "내용", "작성자5","2024-05-26", "테스트용 답변입니다.", false,"etc"));
        searchResults.add(new SupportDTO(6, 1, 106, "제목6", "내용", "작성자6","2024-05-27", "테스트용 답변입니다.", true,"etc"));
        searchResults.add(new SupportDTO(7, 1, 107, "제목7", "내용", "작성자7","2024-05-28", "", false,"etc"));
        searchResults.add(new SupportDTO(8, 1, 108, "제목8", "내용", "작성자8","2024-05-29", "", true,"etc"));
        searchResults.add(new SupportDTO(9, 1, 109, "제목9", "내용", "작성자9","2024-05-30", "", true,"etc"));
        searchResults.add(new SupportDTO(10, 1, 110, "제목10", "내용", "작성자10","2024-06-01", "", false,"etc"));
        searchResults.add(new SupportDTO(11, 1, 111, "제목11", "내용", "작성자11","2024-06-01", "", true,"etc"));

        return searchResults;



    }
}
