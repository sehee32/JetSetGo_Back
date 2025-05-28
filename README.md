# **JetSetGo Back**
이 프로젝트는 Spring Boot를 사용하여 항공권 검색 및 예약을 지원하는 웹 애플리케이션입니다.​


##  🛠기술 스택
- Frontend: Vue.js​ , Axios

- Backend: Spring Boot , MyBatis , MariaDB

- API: Amadeus API , Iamport



## 👥 역할 분담

**[박세희]**

- 회원가입, 로그인 기능 구현
 
- Amadeus API를 활용한 항공권 조회, 예매, 결제 페이지 개발

- Vue.js, Axios, Spring Boot, MyBatis 등을 활용하여 백엔드 API와 연동

- PortOne(구 Iamport) API를 활용한 본인 인증 및 결제 연동

<br><br>


**[이유리]**

- 마이페이지, 문의하기 페이지 구현

- 사용자 예약 내역 조회 및 상세 정보 확인 기능 개발

- 사용자 정보 및 예약 이력 기반 화면 구성

- Vue.js 기반 마이페이지 UI/UX 설계 및 구현


## 🚀 프로젝트 설정 및 실행
### 설치
```
npm install
```

### 실행
```
npm run serve
```

### 컴파일
```
npm run build
```



## 🌟 주요 기능

### 회원가입

<img width="100%" src="https://github.com/user-attachments/assets/2e6643dd-889a-4b21-8f3c-a48259e77aea"/>



<details><summary> 주요 코드
</summary>

```
// [Controller] 회원가입 (SignUpController.java)
@RestController
@RequestMapping("/api")
public class SignUpController {
    @Autowired
    private SignUpMapper signUpMapper;

    // 회원가입
    @PostMapping("/signup")
    public String signUp(@RequestBody TbMembersDto tbMembersDto) {
        signUpMapper.insertMember(tbMembersDto);
        return "회원가입 성공";
    }

    // 아이디(Username) 중복 체크
    @PostMapping("/checkUsername")
    public Map<String, Boolean> checkUsername(@RequestBody Map<String, String> usernameMap) {
        String username = usernameMap.get("username");
        TbMembersDto existingMember = signUpMapper.findByUsername(username);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", existingMember != null);
        return response;
    }
}

// [Mapper] (SignUpMapper.java)
@Mapper
public interface SignUpMapper {
    void insertMember(TbMembersDto tbMembersDto);
    TbMembersDto findByUsername(String username);
}

<!-- [MyBatis XML] 회원 정보 저장 --> (SignUpMapper.xml)
<mapper namespace="kr.co.jetsetgo.dbio.SignUpMapper">
    <insert id="insertMember">
        INSERT INTO MEMBERS (NAME, USERNAME, PASSWORD, BIRTHDATE, PHONENUMBER, AGREETERMS)
        VALUES (#{name}, #{username}, #{password}, #{birthdate}, #{phoneNumber}, #{agreeTerms})
    </insert>

    <select id="findByUsername" resultType="kr.co.jetsetgo.model.TbMembersDto">
        SELECT * FROM MEMBERS WHERE USERNAME = #{username}
    </select>
</mapper>

// 회원 테이블 생성 SQL (MEMBERS 테이블)
CREATE TABLE MEMBERS (
    MEMBERNUM   INT AUTO_INCREMENT PRIMARY KEY,
    NAME        VARCHAR(255),
    USERNAME    VARCHAR(255),
    PASSWORD    VARCHAR(255),
    BIRTHDATE   DATE,
    PHONENUMBER VARCHAR(255),
    AGREETERMS  TINYINT(1) NOT NULL
) COMMENT '회원테이블';

```
</details>

- /api/signup에 회원 정보(JSON) 전달
- DB에 새로운 회원 정보 저장
- 성공 시 "회원가입 성공" 반환

---


### 로그인


<img width="100%" src="https://github.com/user-attachments/assets/8c3171b0-738e-40ac-8dfa-d8a86fc5d3c8"/>

![로그인실패](https://github.com/user-attachments/assets/d33f2f82-ffd3-4a96-ac84-a9df7ab1375d)


<details><summary> 주요 코드
</summary>

```
// [Controller] 로그인 요청 및 JWT 토큰 발급 (LoginController.java)
@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody TbMembersDto loginRequest) {
        TbMembersDto user = loginService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        if (user == null) {
            return ResponseEntity.status(401).body("잘못된 정보입니다.");
        }
        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(new LoginDto(token));
    }
}


// [Service] 사용자 인증 (DB에서 사용자 조회 및 비밀번호 검증) (LoginService.java)
@Service
public class LoginService {
    @Autowired
    private SignUpMapper signUpMapper;

    public TbMembersDto authenticate(String username, String password) {
        TbMembersDto user = signUpMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}


// [JwtUtil] JWT 토큰 생성 (JWT토큰 생성 및 파싱) (JwtUtil.java)
@Component
public class JwtUtil {
    private static final String SECRET_KEY = "TestSecretKey";
    private static final long EXPIRATION_TIME = 86400000; // 1일

    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET_KEY));
    }

    public String extractUsername(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(SECRET_KEY)).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getSubject();
    }
}


// 로그인 응답 DTO (토큰 응답용 DTO) (LoginDto.java)
public class LoginDto {
    private String token;
    public LoginDto(String token) { this.token = token; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}

```
</details>

- /api/login에 username, password 전달
- Service에서 DB 조회, 비밀번호 일치하면 인증 성공
- 인증 성공 시 JWT 토큰 생성 후 반환
- 이후 API 호출 시 이 토큰을 Authorization 헤더에 포함



---


### 마이페이지

>연락처 정보, 비밀번호 변경, 예약목록 확인![마이페이지1](https://github.com/user-attachments/assets/fbee741d-ca44-44ae-b6f3-df2975876c81)
<details><summary> 주요 코드
</summary>

```
1. 로그인 중인 사용자 정보 조회
// [Controller] 사용자 정보 조회
@PostMapping("/getUserInfos")
public TbMembersDto getUserInfos(@RequestBody Map<String, String> tokenMap){
    String token = tokenMap.get("token");
    String username = jwtUtil.extractUsername(token);
    return myPageService.selectMembers(username);
}

// [Service] 사용자 정보 조회
public TbMembersDto selectMembers(String username) {
    return myPageMapper.findUserInfoByUserName(username);
}

// [Mapper]
TbMembersDto findUserInfoByUserName(String userName);


2. 연락처 정보(사용자 정보) 변경
// [Controller] 연락처 정보 변경
@PostMapping("/myPageUserInfoEdit")
public boolean myPageUserInfoEdit(@RequestBody Map<String, String> userInfoMap){
    return myPageService.updateUserInfo(userInfoMap);
}

// [Service]
public boolean updateUserInfo(Map<String, String> userInfoMap) {
    String id = userInfoMap.get("userId");
    String contact = userInfoMap.get("contact");
    return myPageMapper.editUserInfo(id, contact);
}

// [Mapper]
boolean editUserInfo(String id, String contact);


3. 비밀번호 변경
// [Controller] 비밀번호 변경
@PostMapping("/myPageUserPasswordEdit")
public boolean myPageUserPasswordEdit(@RequestBody Map<String, String> userInfoMap){
    return myPageService.updateUserPassword(userInfoMap);
}

// [Service]
public boolean updateUserPassword(Map<String, String> userInfoMap) {
    String id = userInfoMap.get("userId");
    String password = userInfoMap.get("password");
    return myPageMapper.editUserPassword(id, password);
}

// [Mapper]
boolean editUserPassword(String id, String password);



4. 예약목록 확인
// [Controller] 예약 목록 조회
@PostMapping("/myPageReservations")
public List<ReservationDto> myPageReservations(@RequestBody Map<String, String> ReservationMap){
    return myPageService.selectReservations(ReservationMap);
}

// [Service]
public List<ReservationDto> selectReservations(Map<String, String> ReservationMap) {
    String userId = ReservationMap.get("userId");
    List<ReservationDto> results = myPageMapper.findReservationByUserId(userId);
    // 출발/도착 일시 → 날짜/시간 분리
    for (ReservationDto reservation : results) {
        LocalDateTime dep = reservation.getDeparture_Time();
        LocalDateTime arr = reservation.getArrival_Time();
        if (dep != null && arr != null) {
            reservation.setDepartureDate(dep.toLocalDate());
            reservation.setDepartureTime(dep.toLocalTime());
            reservation.setArrivalDate(arr.toLocalDate());
            reservation.setArrivalTime(arr.toLocalTime());
        }
    }
    return results;
}

// [Mapper]
List<ReservationDto> findReservationByUserId(String userId);

```
</details>

- JWT 토큰으로 로그인 사용자를 식별해 개인정보, 예약 내역 등 조회/수정
- Map<String, String> 형태로 필요한 데이터(아이디, 연락처, 비밀번호 등) 전달
- MyBatis Mapper를 통해 DB 연동
<br><br>


>회원 탈퇴 기능![마이페이지2](https://github.com/user-attachments/assets/eba02992-1498-4f94-916a-a1975b06111f)
<details><summary> 주요 코드
</summary>

```
// [Controller] 회원 탈퇴
@PostMapping("/myPageUserRemove")
public boolean myPageUserRemove(@RequestBody Map<String, String> userInfoMap){
    return myPageService.deleteUser(userInfoMap);
}

// [Service] 회원 탈퇴
public boolean deleteUser(Map<String, String> userInfoMap) {
    String id = userInfoMap.get("id");
    myPageMapper.removeUser(id);
    return true;
}

// [Mapper] 회원 탈퇴
void removeUser(String id);

```
</details>


- 컨트롤러에서 deleteUser 서비스 메소드 호출
- 서비스에서 myPageMapper.removeUser(id)로 DB에서 해당 회원 삭제
- 성공 시 true 반환
<br><br>

>여권 정보, 예약 항공편 변경![마이페이지3](https://github.com/user-attachments/assets/ec963116-9217-4590-ae30-e3e0f5917d31)
<details><summary> 주요 코드
</summary>

```
// [여권 정보 업데이트] - MyPageServiceImpl.java
public boolean updatePassport(Map<String, String> passportMap) {
    String id = passportMap.get("id");
    String passengerName = passportMap.get("passengerName");
    String phoneNumber = passportMap.get("phoneNumber");
    String passportNumber = passportMap.get("passportNumber");
    String passportExpiryDate = passportMap.get("passportExpiryDate");
    String passportIssuingCountry = passportMap.get("passportIssuingCountry");
    return myPageMapper.editPassport(id, passengerName, phoneNumber, passportNumber, passportExpiryDate, passportIssuingCountry);
}

// [예약 항공편 변경 상세 조회] - MyPageServiceImpl.java
public List<ReservationDetailDto> selecteReservationChangeDetails(Map<String, Object> ReservationMap) {
    String id = (String) ReservationMap.get("id");
    List<Integer> ids = (List<Integer>) ReservationMap.get("selectedFlightChangeId");
    List<ReservationDetailDto> results = new ArrayList<>();
    if (ids != null && !ids.isEmpty()) {
        for (Integer selectedFlightId : ids) {
            List<ReservationDetailDto> partialResults = myPageMapper.findReservationByReservationIdAndFlightId(id, selectedFlightId);
            if (partialResults != null) {
                results.addAll(partialResults);
            }
        }
    }
    // (날짜/시간 분리 및 기타 정보 가공 로직 생략)
    return results;
}

// [예약 항공편 변경 요청] - MyPageServiceImpl.java
public boolean selecteReservationChangeDetailsData(List<Map<String, Object>> changeFlights) {
    for (Map<String, Object> flight : changeFlights) {
        Integer flightId = (Integer) flight.get("flightId");
        Map<String, String> changeDetail = (Map<String, String>) flight.get("changeDetail");
        String reservationId = (String) flight.get("reservationId");

        // 항공편 정보 생성 및 DB 처리
        Map<String, String> currentFlight = myPageMapper.findFlightCityById(flightId);
        TbFlights flights = new TbFlights();
        flights.setDepartureTime(Timestamp.valueOf(changeDetail.get("departureTime")+":00"));
        flights.setArrivalTime(Timestamp.valueOf(changeDetail.get("arrivalTime")+":00"));
        flights.setOriginlocationcode(changeDetail.get("departure"));
        flights.setDestinationlocationcode(changeDetail.get("destination"));
        flights.setDepartureCity(currentFlight.get("DEPARTURE_CITY"));
        flights.setArrivalCity(currentFlight.get("ARRIVAL_CITY"));
        long resultId = myPageMapper.checkAndAddFlight(flights);

        boolean result;
        if (resultId != 0){
            result = myPageMapper.editReservationByReservationIdAndFlightId(reservationId, flightId, flights.getId(), changeDetail.get("price"));
        } else {
            long flightChangeId = myPageMapper.findFlightId(flights);
            result = myPageMapper.editReservationByReservationIdAndFlightId(reservationId, flightId, flightChangeId, changeDetail.get("price"));
        }
    }
    return true;
}

```
</details>

**여권 정보 업데이트**
- updatePassport(Map<String, String> passportMap)
- 전달받은 여권 관련 정보로 DB 업데이트

**예약 항공편 변경 상세 조회**
- selecteReservationChangeDetails(Map<String, Object> ReservationMap)
- 예약ID, 변경할 항공편 ID 리스트로 상세정보 조회

**예약 항공편 변경 요청**

- selecteReservationChangeDetailsData(List<Map<String, Object>> changeFlights)
- 변경 요청된 항공편 정보를 DB에 반영 (신규 항공편 추가/기존 항공편 변경)

>예약이 변경됨![마이페이지4](https://github.com/user-attachments/assets/608e08dd-5d70-4b35-b28c-7166470a92ab)

<br><br>

>예약 취소![마이페이지5](https://github.com/user-attachments/assets/e44f1bce-945a-4599-af6e-c09fd8ba973f)
<details><summary> 주요 코드
</summary>

```
// [Service] 예약 취소 기능 - MyPageServiceImpl.java
public boolean updateReservationStatus(Map<String, String> reservationMap) {
    String id = reservationMap.get("id");
    boolean isUpdated = myPageMapper.cancelReservation(id);
    System.out.println(id);
    if (isUpdated) {
        System.out.println("Update successful");
    } else {
        System.out.println("Update failed");
    }
    return isUpdated;
}

// [Mapper] 예약 취소 - MyPageMapper.java
boolean cancelReservation(String id);

<!-- [Mapper XML] 예약 취소 예시 -->
<update id="cancelReservation">
    UPDATE RESERVATIONS
    SET STATUS = '취소'
    WHERE RESERVATION_ID = #{id}
</update>

```
</details>

- updateReservationStatus(Service): 예약 ID를 받아 예약 상태를 '취소'로 변경
- cancelReservation(Mapper): DB에서 해당 예약의 상태 업데이트
- Mapper XML에서 실제 SQL 실행


---

### 문의하기

>문의글 작성, 문의 게시판 카테고리 별 분류, 비공개 글 암호 기능![문의1](https://github.com/user-attachments/assets/325b43eb-451f-4307-a074-3cb5ae321efc)

<details><summary> 주요 코드
</summary>

```
1. 문의글 작성 (Create)
// [Controller] 문의글 등록
@PostMapping("/supportAdd")
public boolean supportAdd(@RequestBody TbSupport support) {
    return supportService.insertSupport(support);
}

// [Service] 작성자 ID 조회 및 문의글 저장
public boolean insertSupport(TbSupport support) {
    long writerId = supportMapper.findIdByWriterName(support.getWriter_Name());
    support.setWriter_Id(writerId);
    supportMapper.addSupport(support);
    return true;
}

// [Mapper] 
void addSupport(TbSupport support);

<!-- [MyBatis XML] 문의글 추가 -->
<insert id="addSupport">
    INSERT INTO SUPPORT (WRITER_ID, WRITER_NAME, TITLE, DETAIL, CREATED_DATE, PUBLIC_STATUS, CATEGORY)
    VALUES (#{writer_Id}, #{writer_Name}, #{title}, #{detail}, NOW(), #{public_Status}, #{category});
</insert>


2. 문의 게시판 + 카테고리 분류 (Read)
// [Controller] 문의 목록 조회
@PostMapping("/supportSearch")
public List<TbSupport> supportSearch(@RequestBody Map<String, String> SearchMap) {
    return supportService.selectSupports(SearchMap);
}

// [Service] 조건별 검색 처리
public List<TbSupport> selectSupports(Map<String, String> SearchMap) {
    String search = SearchMap.get("search");
    String category = SearchMap.get("category");
    
    if (search.isEmpty()) {
        return category.equals("total") 
            ? supportMapper.findAll() 
            : supportMapper.findByCategory(category);
    } else {
        return supportMapper.findBySearchQuery(search);
    }
}

// [Mapper]
List<TbSupport> findAll();
List<TbSupport> findByCategory(String category);
List<TbSupport> findBySearchQuery(String search);


<!-- [MyBatis XML] 전체/카테고리/검색어별 조회 -->
<select id="findAll" resultType="kr.co.jetsetgo.model.TbSupport">
    SELECT * FROM SUPPORT ORDER BY SUPPORT_ID DESC
</select>
<select id="findBycategory" resultType="kr.co.jetsetgo.model.TbSupport">
    SELECT * FROM SUPPORT WHERE CATEGORY = #{category} ORDER BY SUPPORT_ID DESC
</select>
<select id="findBySearchQuery" resultType="kr.co.jetsetgo.model.TbSupport">
    SELECT * FROM SUPPORT WHERE TITLE LIKE CONCAT('%', #{search}, '%') ORDER BY SUPPORT_ID DESC
</select>

3. 비공개 글 암호 확인
// [Controller] 비밀번호 검증
@PostMapping("/supportCheckPassword")
public boolean supportCheckPassword(@RequestBody Map<String, String> supportIdMap) {
    return supportService.selectSupportPassword(supportIdMap);
}

// [Service] DB 비밀번호 비교
public boolean selectSupportPassword(Map<String, String> supportIdMap) {
    String supportId = supportIdMap.get("supportId");
    String inputPassword = supportIdMap.get("password");
    
    TbSupport support = supportMapper.findById(supportId);
    String dbPassword = supportMapper.findPasswordByWriterId(support.getWriter_Id());
    return dbPassword.equals(inputPassword);
}

// [Mapper]
String findPasswordByWriterId(long writerId);

<!-- [MyBatis XML] 작성자 비밀번호 조회 -->
<select id="findPasswordByWriterId" resultType="String">
    SELECT PASSWORD FROM MEMBERS WHERE MEMBERNUM = #{writerId}
</select>


4. 문의 상세 조회
// [Controller] 상세 조회
@PostMapping("/enterSupport")
public TbSupport enterSupport(@RequestBody Map<String, String> supportIdMap) {
    return supportService.selectSupport(supportIdMap);
}

// [Service] 문의글 단건 조회
public TbSupport selectSupport(Map<String, String> supportIdMap) {
    return supportMapper.findById(supportIdMap.get("supportId"));
}

// [Mapper]
TbSupport findById(String supportId);

<!-- [MyBatis XML] 문의글 단건 조회 -->
<select id="findById" resultType="kr.co.jetsetgo.model.TbSupport">
    SELECT * FROM SUPPORT WHERE SUPPORT_ID = #{id}
</select>

```
</details>

- 카테고리 분류: total(전체)/ticket(항공권)/baggage(수하물) 등 카테고리별 필터링
- 작성자 식별: 작성자 이름(writer_Name) → DB에서 writer_Id로 변환해 저장
- 비공개 글 처리: 비밀번호는 사용자 계정 비밀번호와 연동 (로그인 사용자 전용)
- REST API: 프론트엔드에서 JSON 요청으로 모든 기능 연동 가능
<br><br>

>문의글 수정![문의2](https://github.com/user-attachments/assets/f961cbfd-5d98-4b84-9502-703d30f1268d)

<details><summary> 주요 코드
</summary>

```
// [Controller] 문의글 수정
@PostMapping("/supportEdit")
public boolean supportEdit(@RequestBody TbSupport support) {
    return supportService.updateSupport(support);
}

// [Service] 문의글 정보 갱신
public boolean updateSupport(TbSupport support) {
    supportMapper.editSupport(support);
    return true;
}

// [Mapper]
void editSupport(TbSupport support);

<!-- [MyBatis XML] 문의글 수정 쿼리 -->
<update id="editSupport">
    UPDATE SUPPORT
    SET TITLE = #{title}, DETAIL = #{detail}, PUBLIC_STATUS = #{public_Status}, CATEGORY = #{category}, ANSWER = #{answer}
    WHERE SUPPORT_ID = #{support_Id}
</update>

```
</details>

- 클라이언트가 수정할 문의글의 id와 새 데이터(title, detail 등)를 전달
- 컨트롤러에서 서비스의 updateSupport 호출
- 서비스에서 매퍼의 editSupport 실행 → DB에서 해당 SUPPORT_ID의 데이터 수정
- 성공 시 true 반환

---

### 항공권 조회

>출발지&도착지 자동완성 기능, 항공편 검색![검색1](https://github.com/user-attachments/assets/cb1916d4-748e-4e83-9558-75f7dd1c3525)
<details><summary> 주요 코드
</summary>

```
1. 출발지&도착지 자동완성
// [Controller] 공항 검색 API
@GetMapping("/airports")
public Map<String, List<AirportDTO>> searchAirports(@RequestParam String keyword) {
    List<AirportDTO> airportDTOs = airportService.searchAirportsByName(keyword);
    return Map.of("airports", airportDTOs);
}

// [Service] 도시 이름으로 공항 검색
public List<AirportDTO> searchAirportsByName(String city) {
    List<Airport> airports = airportMapper.findByCityNameContainingIgnoreCase(city);
    return airports.stream()
        .map(a -> new AirportDTO(a.getCode(), a.getCity()))
        .collect(Collectors.toList());
}

<!-- [MyBatis] 도시 이름 검색 쿼리 -->
<select id="findByCityNameContainingIgnoreCase" resultType="Airport">
    SELECT * FROM IATA_AIRPORT 
    WHERE LOWER(CITY) LIKE LOWER(CONCAT('%', #{city}, '%'))
</select>


2. 항공편 검색
// [Controller] 항공편 검색 API
@GetMapping("/search")
public String searchFlights(@RequestParam String origin,
                           @RequestParam String destination,
                           @RequestParam String departureDate,
                           @RequestParam int adults,
                           @RequestParam int children,
                           @RequestParam String travelClass,
                           @RequestParam boolean nonStop) throws IOException {
    return ApiUtil.searchFlights(origin, destination, departureDate, 
                               adults, children, travelClass, nonStop);
}

// [API Util] Amadeus API 호출
public static String searchFlights(...) throws IOException {
    String token = getToken(); // OAuth 2.0 토큰 발급
    String url = String.format(
        "https://test.api.amadeus.com/v2/shopping/flight-offers?originLocationCode=%s&...",
        origin, destination, departureDate, adults, children, travelClass, nonStop
    );
    
    // API 응답 처리
    JSONArray resultArray = new JSONArray();
    for (flight in 응답데이터) {
        resultArray.put({
            "id": "A12345",
            "departureTime": "2025-01-01T12:00:00",
            "arrivalTime": "2025-01-05T15:00:00",
            "price": "250000"
        });
    }
    return resultArray.toString();
}

// 검색 파라미터
origin: 출발지 공항 코드 (ICN)
destination: 도착지 공항 코드 (NRT)
departureDate: 출발일 (YYYY-MM-DD)
adults/children: 승객 수
travelClass: 좌석 등급 (ECONOMY, BUSINESS)
nonStop: 직항 여부 (true/false)

```
</details>

**자동완성**
- 사용자가 입력창에 서 입력 → 프론트에서 /api/flights/airports?keyword=서 요청
- DB에서 CITY 컬럼에 서가 포함된 공항 검색 (예: 서울, 청주, 부산)
- 결과를 [{code: "ICN", city: "서울"}, ...] 형태로 반환
- 프론트엔드에서 자동완성 UI에 표시

**항공편 검색**
- 선택된 공항 코드로 Amadeus API 호출
- 최대 30개 항공편 정보 반환
- 가격, 출발/도착 시간, 소요시간 포함

<br><br>


>항공편 목록 정렬, 페이징 기능![검색2](https://github.com/user-attachments/assets/5fb531ee-abea-4b05-b9e0-82a69e4321ee)

- 프론트엔드에서 작성

<br><br>

>항공편 검색 조건 변경 후 재검색![검색3](https://github.com/user-attachments/assets/6a56866f-de94-44ce-a3b8-7d3e5a5d160d)
<details><summary> 주요 코드
</summary>

```
// [Controller] 항공편 검색 API
@GetMapping("/search")
public String searchFlights(
    @RequestParam String origin,
    @RequestParam String destination,
    @RequestParam String departureDate,
    @RequestParam int adults,
    @RequestParam int children,
    @RequestParam String travelClass,
    @RequestParam boolean nonStop
) throws IOException {
    // 변경된 조건을 파라미터로 받아 Amadeus API 재호출
    return ApiUtil.searchFlights(origin, destination, departureDate, adults, children, travelClass, nonStop);
}


// [API Util] Amadeus API 호출
public static String searchFlights(
    String origin, String destination, String departureDate,
    int adults, int children, String travelClass, boolean nonStop
) throws IOException {
    // ... (토큰 발급 및 API URL 조립)
    String urlString = String.format(
        "https://test.api.amadeus.com/v2/shopping/flight-offers?" +
        "originLocationCode=%s&destinationLocationCode=%s&departureDate=%s" +
        "&adults=%d&children=%d&travelClass=%s&nonStop=%s&max=30&currencyCode=KRW",
        origin, destination, departureDate, adults, children, travelClass, nonStop
    );
    // ... (API 호출 및 결과 반환)
}
```
</details>

- 사용자가 검색 조건(출발지, 도착지, 날짜, 인원 등)을 변경
- 프론트엔드에서 새로운 조건으로 /api/flights/search 엔드포인트에 요청
- 백엔드 컨트롤러에서 변경된 파라미터를 받아 Amadeus API를 다시 호출
- 변경된 조건에 맞는 항공편 목록을 반환
<br><br>

>항공편 선택 후 정보 입력 페이지로 데이터 라우팅![검색4](https://github.com/user-attachments/assets/cecc9d71-6576-422f-b5dc-fbba00b14374)

- 프론트엔드에서 작성


---

### 항공권 예매

>승객 정보 입력 후 결제![예매1](https://github.com/user-attachments/assets/1cfc4cb1-061a-4dbd-abd0-8b853690ec80)

<details><summary> 주요 코드
</summary>

```
[Controller] 예약 생성 요청 처리
@PostMapping
public ResponseEntity<String> createReservation(@RequestBody Map<String, Object> reservationData) {
    // 프론트에서 전달받은 flightIds(항공편 ID 배열) 추출
    List<?> flightIdObjects = (List<?>) reservationData.get("flightIds");
    List<Long> flightIds = new ArrayList<>();
    for(Object id : flightIdObjects) {
        flightIds.add(Long.parseLong(id.toString()));
    }
    // 예약 저장 서비스 호출
    reservationService.insertReservation(reservationData, flightIds);
    return ResponseEntity.ok("Reservation 성공");
}

[Service] 예약 및 항공편 저장 처리
public void insertReservation(Map<String, Object> reservationData, List<Long> flightIds) {
    // 1. 예약 데이터 DTO로 변환 (status는 프론트에서 "예약대기"로 전송)
    TbReservation reservation = mapToReservation(reservationData);

    // 2. 각 항공편 ID별로 예약 저장
    for (Long flightId : flightIds) {
        reservation.setFlight_Id(flightId);
        reservationMapper.insertReservation(reservation); // RESERVATION 테이블에 저장
    }
}

// 항공편 저장 (결제 전 단계에서 별도 호출)
public List<Long> saveFlights(List<Map<String, String>> flightData) {
    List<Long> flightIds = new ArrayList<>();
    for (Map<String, String> flightDetail : flightData) {
        TbFlights flight = new TbFlights();
        // 출발/도착 시간 등 필드 매핑
        // ... (생략)
        myPageMapper.checkAndAddFlight(flight); // 중복 없으면 insert
        Long savedFlightId = myPageMapper.getFlightIdIfExists(flight);
        flightIds.add(savedFlightId);
    }
    return flightIds;
}

[Mapper] 항공편/예약 저장 쿼리
<!-- FLIGHTS 테이블: 항공편 중복 체크 후 저장 -->
<insert id="checkAndAddFlight" parameterType="kr.co.jetsetgo.model.TbFlights" useGeneratedKeys="true" keyProperty="id">
    INSERT INTO FLIGHTS (DEPARTURE_TIME, ARRIVAL_TIME, ORIGINLOCATIONCODE, DESTINATIONLOCATIONCODE, DEPARTURE_CITY, ARRIVAL_CITY)
    SELECT #{departureTime}, #{arrivalTime}, #{originlocationcode}, #{destinationlocationcode}, #{departureCity}, #{arrivalCity}
    FROM dual
    WHERE NOT EXISTS (
        SELECT ID FROM FLIGHTS
        WHERE DEPARTURE_TIME = #{departureTime}
          AND ARRIVAL_TIME = #{arrivalTime}
          AND ORIGINLOCATIONCODE = #{originlocationcode}
          AND DESTINATIONLOCATIONCODE = #{destinationlocationcode}
          AND DEPARTURE_CITY = #{departureCity}
          AND ARRIVAL_CITY = #{arrivalCity}
    );
</insert>

<!-- RESERVATION 테이블: 예약 데이터 저장 -->
<insert id="insertReservation" parameterType="kr.co.jetsetgo.model.TbReservation">
    INSERT INTO RESERVATION
    (MEMBER_ID, RESERVATION_ID, FLIGHT_ID, STATUS, TRIP_TYPE, RESERVATION_DATE,
     PASSENGER_NAME, PHONE_NUMBER, PASSPORT_NUMBER, PASSPORT_EXPIRYDATE,
     PASSPORT_ISSUINGCOUNTRY, PAYMENT_AMOUNT, PAYMENT_METHOD, NONSTOP,
     TRAVELCLASS, ADULTS, CHILDREN)
    VALUES
    (#{member_Id}, #{reservation_Id}, #{flight_Id}, #{status}, #{trip_Type}, #{reservation_Date},
     #{passenger_Name}, #{phone_Number}, #{passport_Number}, #{passport_Expirydate},
     #{passport_Issuingcountry}, #{payment_Amount}, #{payment_Method}, #{nonstop},
     #{travelclass}, #{adults}, #{children})
</insert>

```
</details>

**결제하기 버튼 클릭 시**
- 프론트엔드에서 항공편 정보(FLIGHT)와 예약 정보(RESERVATION), 그리고 status="예약대기" 포함해서 전송
- 컨트롤러에서 flightIds와 예약 데이터 분리
- 서비스에서 항공편 데이터는 FLIGHTS 테이블에, 예약 데이터는 각 항공편별로 RESERVATION 테이블에 저장
- status 컬럼은 프론트에서 받은 "예약대기" 그대로 저장
<br><br><br>

- 항공편 데이터는 중복 저장 방지 로직(checkAndAddFlight)으로 처리
- 예약 데이터는 각 승객/항공편별로 여러 건 생성될 수 있음

---

### 항공권 결제

>아임포트(포트원) API 이용하여 결제 ![결제1](https://github.com/user-attachments/assets/06af637d-71de-4e83-9d6a-2aabfecc6507)

<details><summary> 주요 코드
</summary>

```


```
</details>
