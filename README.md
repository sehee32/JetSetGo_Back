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

### 회원가입 (SignUp.vue)

<img width="100%" src="https://github.com/user-attachments/assets/2e6643dd-889a-4b21-8f3c-a48259e77aea"/>



<details><summary> 주요 코드
</summary>

```
// 회원가입 REST API 컨트롤러 (SignUpController.java)
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

// MyBatis Mapper 인터페이스 (SignUpMapper.java)
@Mapper
public interface SignUpMapper {
    void insertMember(TbMembersDto tbMembersDto);
    TbMembersDto findByUsername(String username);
}

// MyBatis Mapper XML (SignUpMapper.xml)
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



---


### 로그인 (LoginPage.vue)


<img width="100%" src="https://github.com/user-attachments/assets/8c3171b0-738e-40ac-8dfa-d8a86fc5d3c8"/>

![로그인실패](https://github.com/user-attachments/assets/d33f2f82-ffd3-4a96-ac84-a9df7ab1375d)


<details><summary> 주요 코드
</summary>

```
// 로그인 컨트롤러: 인증 및 JWT 토큰 발급 (LoginController.java)
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


// 로그인 서비스: 사용자 인증 (DB에서 사용자 조회 및 비밀번호 검증) (LoginService.java)
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


// JWT 토큰 생성 유틸리티 (JWT토큰 생성 및 파싱) (JwtUtil.java)
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



---


### 마이페이지 (MyPage.vue)

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


---

### 문의하기 (SupportPage.vue)

>문의글 작성, 문의 게시판 카테고리 별 분류, 비공개 글 암호 기능![문의1](https://github.com/user-attachments/assets/325b43eb-451f-4307-a074-3cb5ae321efc)
<details><summary> 주요 코드
</summary>

```


```
</details>
<br><br>

>문의글 수정![문의2](https://github.com/user-attachments/assets/f961cbfd-5d98-4b84-9502-703d30f1268d)

<details><summary> 주요 코드
</summary>

```


```
</details>

---

### 항공권 조회 (BookingPage.vue)

>출발지&도착지 자동완성 기능, 항공편 검색![검색1](https://github.com/user-attachments/assets/cb1916d4-748e-4e83-9558-75f7dd1c3525)
<details><summary> 주요 코드
</summary>

```


```
</details>
<br><br>


>항공편 목록 정렬, 페이징 기능![검색2](https://github.com/user-attachments/assets/5fb531ee-abea-4b05-b9e0-82a69e4321ee)
<details><summary> 주요 코드
</summary>

```


```
</details>
<br><br>

>항공편 검색 조건 변경 후 재검색![검색3](https://github.com/user-attachments/assets/6a56866f-de94-44ce-a3b8-7d3e5a5d160d)
<details><summary> 주요 코드
</summary>

```


```
</details>
<br><br>

>항공편 선택 후 정보 입력 페이지로 데이터 라우팅![검색4](https://github.com/user-attachments/assets/cecc9d71-6576-422f-b5dc-fbba00b14374)
<details><summary> 주요 코드
</summary>

```


```
</details>


---

### 항공권 예매 (BookingDetail.vue)

>승객 정보 입력 후 결제![예매1](https://github.com/user-attachments/assets/1cfc4cb1-061a-4dbd-abd0-8b853690ec80)

<details><summary> 주요 코드
</summary>

```


```
</details>

---

### 항공권 결제 (BookingDetail.vue)

>아임포트(포트원) API 이용하여 결제 ![결제1](https://github.com/user-attachments/assets/06af637d-71de-4e83-9d6a-2aabfecc6507)

<details><summary> 주요 코드
</summary>

```


```
</details>
