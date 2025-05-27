# **JetSetGo Back**
이 프로젝트는 Vue.js를 사용하여 항공권 검색 및 예약을 지원하는 웹 애플리케이션입니다.​


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


```
</details>


---


### 로그인 (LoginPage.vue)


<img width="100%" src="https://github.com/user-attachments/assets/8c3171b0-738e-40ac-8dfa-d8a86fc5d3c8"/>

![로그인실패](https://github.com/user-attachments/assets/d33f2f82-ffd3-4a96-ac84-a9df7ab1375d)


<details><summary> 주요 코드
</summary>

```


```
</details>


---


### 마이페이지 (MyPage.vue)

>연락처 정보, 비밀번호 변경, 예약목록 확인![마이페이지1](https://github.com/user-attachments/assets/fbee741d-ca44-44ae-b6f3-df2975876c81)
<details><summary> 주요 코드
</summary>

```


```
</details>
<br><br>


>회원 탈퇴 기능![마이페이지2](https://github.com/user-attachments/assets/eba02992-1498-4f94-916a-a1975b06111f)
<details><summary> 주요 코드
</summary>

```


```
</details>
<br><br>

>예약이 변경됨![마이페이지4](https://github.com/user-attachments/assets/608e08dd-5d70-4b35-b28c-7166470a92ab)

<br><br>

>예약 취소![마이페이지5](https://github.com/user-attachments/assets/e44f1bce-945a-4599-af6e-c09fd8ba973f)
<details><summary> 주요 코드
</summary>

```


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
