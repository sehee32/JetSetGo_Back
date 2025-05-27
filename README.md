# **JetSetGo Front​**
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
<templeat>
<v-text-field
  v-model="username"
  :rules="usernameRules"
  label="ID"
  variant="outlined"
  prepend-inner-icon="mdi-account-outline"
  clearable
></v-text-field>
<v-btn @click="checkUsername">중복확인</v-btn>

<div class="phone-container">
          <v-text-field
              v-model="phoneNumber"
              :rules="phoneRules"
              id="phoneNumber"
              label="휴대전화번호"
              variant="outlined"
              prepend-inner-icon="mdi-phone-outline"
              clearable
              class="phone-text-field"
              autocomplete="off"
          ></v-text-field>
          <v-btn
              class="phone-btn"
              color="#00256c"
              size="small"
              @click="verify"
          >
            인증하기
          </v-btn>
        </div>
        <!-- 인증 메시지 -->
        <span v-if="verificationMessage" :class="{'success-message': verificationSuccess, 'error-message': !verificationSuccess}">
         {{ verificationMessage }}
        </span>
        <div>

</templeat>

<script>
methods: {
   checkUsername() {
      // 아이디 중복 확인 기능
      axios.post('http://localhost:8080/api/checkUsername', { username: this.username })
          .then(response => {
            if (response.data.exists) {
              console.log('이미 사용중인 아이디 입니다.');
              this.usernameCheckMessage = '이미 사용중인 아이디 입니다.';
              this.usernameChecked = false; // 중복된 아이디가 있는 경우 false로 설정
            } else {
              console.log('사용할 수 있는 아이디 입니다.');
              this.usernameCheckMessage = '사용할 수 있는 아이디 입니다.';
              this.usernameChecked = true; // 사용 가능한 아이디인 경우 true로 설정
            }
          })
          .catch(error => {
            console.error('아이디 중복 확인에 실패했습니다.', error);
            this.usernameCheckMessage = '아이디 중복 확인에 실패했습니다.';
            this.usernameChecked = false; // 오류 발생 시 false로 설정
          });
    },

async verify() {
      const IMP = window.IMP;
      IMP.init("imp12777257");
      try {
        IMP.certification(
            {
              // param
              pg: "inicis_unified.MIIiasTest", // 본인인증 설정이 2개 이상 되어 있는 경우 필수
              merchant_uid: "ORD20180131-0000011", // 주문 번호
            },
            function (rsp) {
              // callback
              if (rsp.success) {
                // 인증 성공 시
                console.log("인증 성공");
                this.verificationMessage = '본인 인증 요청에 성공했습니다.';
                this.verificationSuccess = true; // 인증 성공 시 변수 값을 true로 설정
              } else {
                // 인증 실패 시 로직
                console.log("본인 인증 요청 실패");
                this.verificationMessage = '본인 인증 요청에 실패했습니다.';
                this.verificationSuccess = false; // 인증 실패 시 변수 값을 false로 설정
              }
            }.bind(this) // 함수 내부에서 this를 사용할 수 있도록 바인딩
        );
      } catch (error) {
        console.error("본인 인증 요청 실패:", error);
        this.verificationMessage = '본인 인증 요청에 실패했습니다.';
        this.verificationSuccess = false; // 인증 실패 시 변수 값을 false로 설정
      }
    }
}
</script>

```
</details>


---


### 로그인 (LoginPage.vue)


<img width="100%" src="https://github.com/user-attachments/assets/8c3171b0-738e-40ac-8dfa-d8a86fc5d3c8"/>

![로그인실패](https://github.com/user-attachments/assets/d33f2f82-ffd3-4a96-ac84-a9df7ab1375d)


<details><summary>주요 코드
</summary>

```
<templeat>
<v-form
          v-model="formValid"
          @submit.prevent="submitlogin"
      >
        <v-text-field
            v-model="username"
            :readonly="loading"
            :rules="[required]"
            class="mb-2"
            label="ID"
            variant="outlined"
            prepend-inner-icon="mdi-account-outline"
            clearable
        ></v-text-field>

        <v-text-field
            v-model="password"
            :readonly="loading"
            :rules="[required]"
            :type="show ? 'text' : 'password'"
            class="mb-2"
            label="Password"
            variant="outlined"
            prepend-inner-icon="mdi-lock-outline"
            clearable
        >
          <!-- 비밀번호 표시/숨김 아이콘 -->
          <template v-slot:append-inner>
            <v-icon @click="show = !show">
              {{ show ? 'mdi-eye' : 'mdi-eye-off' }}
            </v-icon>
          </template>
        </v-text-field>
</templeat>

<script>
    async submitlogin() {
      if (!this.formValid) return;
      this.loading = true;

      try {
        // Vuex 액션 호출
        await this.login({ username: this.username, password: this.password });

        // 저장된 예약 정보가 있는지 확인
        const bookingData = this.$store.getters.getBookingData;
        if (bookingData) {
          // 예약 정보가 있으면 BookingDetail 페이지로 리다이렉트
          this.$router.push({
            name: 'BookingDetail',
            query: {
              outgoingFlight: JSON.stringify(bookingData.outgoingFlight),
              returnFlight: JSON.stringify(bookingData.returnFlight),
              adults: bookingData.adults,
              children: bookingData.children,
              travelClass: bookingData.travelClass,
              totalPrice: bookingData.totalPrice
            }
          });
          // 사용한 예약 정보 초기화
          this.$store.commit('clearBookingData');
        } else {
          // 예약 정보가 없으면 메인 페이지로 이동
          this.$router.push('/');
        }
      } catch (error) {
        console.error('로그인 오류:', error);
        alert('아이디 또는 비밀번호를 확인해주세요.');
      } finally {
        this.loading = false;
      }
    }
</script>
```
</details>


---


### 마이페이지 (MyPage.vue)

>연락처 정보, 비밀번호 변경, 예약목록 확인![마이페이지1](https://github.com/user-attachments/assets/fbee741d-ca44-44ae-b6f3-df2975876c81)
<details><summary>주요 코드
</summary>

```
<templeat>
<!-- 회원정보 -->
        <div class="profile">
          <v-row>
            <!-- 첫 번째 항목 -->
            <v-col class="costom-profile-col">
              <v-list dense bg-color="#f3f4f8">
                <v-list-item>
                  <v-list-item-content >
                    <v-list-item-title>성명</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
                <v-list-item>
                  <v-list-item-content>
                    <v-list-item-title class="costom-profile-title"><strong>{{ name }}</strong></v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
            </v-col>


<!-- 비밀번호 변경 박스 -->
        <div class="costom-box">
          <h3>비밀번호</h3>
          <p>회원님의 소중한 개인정보 보호를 위해 비밀번호를 주기적으로 변경해 주세요.</p>
          <v-btn :ripple="false" variant="outlined" @click="togglePanel('password')" class="costom-box-btn">변경</v-btn>
          <v-expansion-panels v-model="activePanel">
            <!-- 비밀번호 변경 패널 -->
            <v-expansion-panel value="password">
              <v-expansion-panel-text>
                <div class="changePassword">
                  <h1>비밀번호 변경</h1>
                  <p>새로운 비밀번호를 입력해 주세요.</p>
                  <p>[ <span class="red">*</span> 는 필수 입력 사항입니다.]</p>
                    <v-form validate-on="submit" @submit.prevent="submit">
                      <div class = "inputPassword" >
                        <!-- 현재 비밀번호 -->
                        <p>현재 비밀번호 <span class="red">*</span> </p>
                        <v-text-field
                            class="costom-text-field"
                            v-model="currentPassword"
                            type="password"
                            variant="underlined"
                            maxlength="20"
                            :rules="[rules.required]"
                            @keydown.enter="submit"
                        ></v-text-field>
                        <!-- 신규 비밀번호 -->
                        <p>신규 비밀번호 <span class="red">*</span> </p>
                        <v-text-field
                            class="costom-text-field"
                            v-model="newPassword"
                            type="password"
                            variant="underlined"
                            maxlength="20"
                            :rules="[rules.required, rules.password]"
                            ref="newPasswordField"
                            @input="validateNewPassword"
                            @keydown.enter="submit"
                        ></v-text-field>
                        <!-- 신규 비밀번호 확인 -->
                        <p>신규 비밀번호 확인 <span class="red">*</span> </p>
                        <v-text-field
                            class="costom-text-field"
                            v-model="confirmNewPassword"
                            type="password"
                            variant="underlined"
                            maxlength="20"
                            :rules="[rules.required, rules.matchPassword]"
                            @keydown.enter="submit"
                        ></v-text-field>
                      </div>
                      <!-- 버튼 -->
                      <div class="button-container">
                        <v-btn
                            :loading="loading"
                            class="submitBtn"
                            text="변경하기"
                            type="submit"
                            block
                        ></v-btn>
                      </div>
                    </v-form>
                </div>
              </v-expansion-panel-text>
            </v-expansion-panel>
          </v-expansion-panels>
        </div>
</templeat>

<script>
   export default {
    name: 'MyPage',
    components: {},
    data() {
        return {
          name: '',
          id: '',
          phoneNumber: '',
          birthDate: '',
          userPassword: '',
          activePanel: [], // 현재 열려 있는 패널의 값을 저장
          contact: '',
          currentPasswordInfo: '',
          isPasswordValid: false, // 비밀번호 확인 상태
          currentPassword: '',
          newPassword: '',
          confirmNewPassword: '',
          rules: {
            required : value => !!value || '필수 입력 항목입니다.',
            password: value => this.checkPassword(value),
            matchPassword: value => value === this.newPassword || '비밀번호가 일치하지 않습니다.'
          },
          loading: false
         };
    },

methods: {
      // 비밀번호 변경 상세
      async submit(event) {
        this.loading = true;
        // 비밀번호 확인 로직
        if (this.currentPassword === this.userPassword) {
          const results = await event;
          if(results.valid){
            const response = await axios.post('/api/myPageUserPasswordEdit', {
              userId: this.id,
              password: this.newPassword
            });
            console.log('결과 확인: ' + response.data); // 서버에서 받은 데이터 출력
            if(response.data){
              alert('비밀번호 변경이 완료되었습니다.'); // 비밀번호 변경 성공 시
              this.userPassword = this.newPassword;
            }else{
              alert('비밀번호 변경 불가, 관리자 문의 필요');
            }
            this.activePanel = []; // 모든 패널 닫기
          }else{
            alert(results.errors[0].errorMessages); // 유효성 검사 실패 시
          }
          this.loading = false;
        } else {
          alert('현재 비밀번호가 일치하지 않습니다.'); // 비밀번호가 일치하지 않으면 에러 메시지 표시
          this.loading = false;
        }
      },

 async fetchUserInfos() {
        const token = localStorage.getItem('jwtToken'); // 저장된 토큰 가져오기
        if (token) {
          try {
            const response = await axios.post('/api/getUserInfos', {
              token: token // 토큰을 본문에 포함
            });
            this.name = response.data.name; // 사용자 정보를 변수에 저장
            this.id = response.data.username;
            this.phoneNumber = response.data.phoneNumber;
            this.contact = response.data.phoneNumber;
            this.birthDate = response.data.birthdate;
            this.userPassword = response.data.password;
          } catch (error) {
            console.error('Error fetching user info:', error);
          }
        }
      }
</script>
```
</details>
<br><br>


>회원 탈퇴 기능![마이페이지2](https://github.com/user-attachments/assets/eba02992-1498-4f94-916a-a1975b06111f)
<details><summary>주요 코드
</summary>

```
<script>
   methods: {
    ...mapActions(['logout']),
    async submitPassword() {
      this.loading = true;

      // 비밀번호 확인 로직
      if (this.currentPassword === this.userPassword) {
        this.isPasswordValid = true;
        console.log('비밀번호가 유효합니다. 추가 작업을 수행합니다.');
      } else {
        alert('비밀번호가 일치하지 않습니다.'); // 비밀번호가 일치하지 않으면 에러 메시지 표시
      }
      this.loading = false;
    },
    async withdrawalSubmit() {
      this.loading = true;
      if (this.terms == true) {
        const response = await axios.post('/api/myPageUserRemove', {
          id: this.id
        });
        console.log('결과 확인: ' + response.data); // 서버에서 받은 데이터 출력
        alert('회원탈퇴 성공'); // 정보 변경 성공 시
        await this.logout(); // vuex액션 호출
        this.$router.push('/');
      }
      this.loading = false;
    },
    async fetchUserInfos() {
      const token = localStorage.getItem('jwtToken'); // 저장된 토큰 가져오기
      if (token) {
        try {
          const response = await axios.post('/api/getUserInfos', {
            token: token // 토큰을 본문에 포함
          });
          this.id = response.data.username;
          this.userPassword = response.data.password;
        } catch (error) {
          console.error('Error fetching user info:', error);
        }
      }
    },
    goBack() {
      this.$router.go(-1); // 이전 페이지로 이동
    }
  }
</script>
```
</details>
<br><br>

>여권 정보, 예약 항공편 변경![마이페이지3](https://github.com/user-attachments/assets/ec963116-9217-4590-ae30-e3e0f5917d31)
<details><summary>주요 코드
</summary>

```
<script>
   data() {
  return {
    reservationId: null,           // 예약 ID
    selectedFlightId: '',          // 현재 선택한 항공편 ID
    changeFlight: [],              // 변경 내역 배열
    flights: [],                   // 예약 항공편 목록
  };
},
methods: {
  // 예약 변경 대상 항공편 목록 불러오기
  async getReservationChangeDetail() {
    const response = await axios.post('/api/myPageReservationChangeDetails', {
      id: this.reservationId
    });
    this.flights = response.data;
  },

  // 항공편 선택(변경 검색 화면 이동)
  selectFlight(item) {
    if (this.selectedFlightId === item.flight_Id) {
      this.selectedFlightId = '';
    } else {
      // 변경 검색 화면으로 이동 (선택 정보 쿼리로 전달)
      this.$router.push({
        name: 'TicketChangeSearch',
        query: {
          departure: item.originlocationcode,
          destination: item.destinationlocationcode,
          departureDate: this.formatDate(item.departureDate),
          travelClass: item.travelclass,
          reservationChange: true
        }
      }).then(() => {
        this.selectedFlightId = item.flight_Id;
      });
    }
  },

  // 변경 내역 결제/환불 처리
  async goChange() {
    // 모든 변경 대상이 선택되었는지 확인
    if (this.changeFlight.length === this.flights.length) {
      const response = await axios.post('/api/myPageReservationChangeDetailsData', this.changeFlight);
      if (response.data) {
        alert('변경되었습니다.');
        this.$emit('goDetail');
      } else {
        alert('변경실패 : 관리자에게 문의하세요.');
      }
    } else {
      alert('변경하지 않은 예약이 있습니다.');
    }
  },
},
watch: {
  // 변경 검색 결과가 쿼리로 돌아오면 변경 내역에 반영
  '$route.query.changeFlight': function(newValue) {
    if (newValue && this.selectedFlightId) {
      const existingIndex = this.changeFlight.findIndex(
        (flight) => flight.flightId === this.selectedFlightId
      );
      if (existingIndex === -1) {
        this.changeFlight.push({
          reservationId: this.reservationId,
          flightId: this.selectedFlightId,
          changeDetail: JSON.parse(newValue)
        });
      } else {
        this.changeFlight[existingIndex].changeDetail = JSON.parse(newValue);
      }
      this.selectedFlightId = '';
    }
  }
}
</script>
```
</details>
<br><br>

>예약이 변경됨![마이페이지4](https://github.com/user-attachments/assets/608e08dd-5d70-4b35-b28c-7166470a92ab)

<br><br>

>예약 취소![마이페이지5](https://github.com/user-attachments/assets/e44f1bce-945a-4599-af6e-c09fd8ba973f)
<details><summary>주요 코드
</summary>

```
<script>
   methods: {
  goDialog(action) {
    // '예약취소/환불' 버튼 클릭 시 안내 다이얼로그를 띄움
    if (action === 'cancel') {
      this.dialogTitle = '예약 취소/환불';
      this.dialogDetail = '환불 신청 시 예약과 항공권이 모두 취소되며 취소 이후에는 사용이 불가합니다.<br> 승객 본인의 의사에 따라 신청한 것으로 간주하며 그에 따른 책임은 신청인에게 있습니다.';
      this.showDialog = true;
    }
  },
  async goAction() {
    // 다이얼로그에서 '확인' 버튼을 누르면 실행
    if (this.dialogTitle === '예약 취소/환불') {
      // 서버에 예약 취소 요청
      const response = await axios.post('/api/myPageCancelReservation', {
        id: this.reservationId
      });
      // 취소 성공 시 UI 갱신 및 안내
      this.showDialog = false;
      this.$router.go(0); // 새로고침
      alert('예약 취소 되었습니다.');
    }
  }
}
</script>
```
</details>

---

### 문의하기 (SupportPage.vue)

>문의글 작성, 문의 게시판 카테고리 별 분류, 비공개 글 암호 기능![문의1](https://github.com/user-attachments/assets/325b43eb-451f-4307-a074-3cb5ae321efc)
<details><summary>주요 코드
</summary>

```
<templeat>
<!--유형-->
          <v-row no-gutters>
            <v-col cols="1">
              <v-sheet class="pa-3 fontSize20">
                유형<span class="colRed">*</span>
              </v-sheet>
            </v-col>
            <v-col>
              <v-sheet>
                <v-select
                    v-model="selectedCategory"
                    variant="outlined"
                    placeholder="선택해주세요."
                    :items="categories"
                    item-title="name"
                    item-value="value"
                    :rules="[rules.requiredSelect]"
                ></v-select>
              </v-sheet>
            </v-col>
          </v-row>
          <!--공개여부-->
          <v-row no-gutters>
            <v-col cols="1">
              <v-sheet class="pa-3 fontSize20">
                공개여부
              </v-sheet>
            </v-col>
            <v-col>
              <v-sheet>
                <v-switch
                    v-model="isPublic"
                    :label="switchLabel"
                    color="primary"
                    @change="onSwitchChange"
                    inset
                ></v-switch>
              </v-sheet>
            </v-col>
          </v-row>
</templeat>

<script>
 (SupportInquiry.vue)
 // 데이터 정의
data() {
  return {
    selectedCategory: null,
    isPublic: true,
    categories: [
      { name: '항공권', value: 'ticket' },
      { name: '수하물', value: 'baggage' }
    ]
  }
}

// 문의 등록 메서드
async submit() {
  const response = await axios.post('/api/supportAdd', {
    category: this.selectedCategory,
    public_Status: this.isPublic,
    title: this.title,
    detail: this.detail
  });
  if(response.data) {
    this.$router.push('/support'); // 등록 후 목록 페이지 이동
  }
}


(SupportPage.vue)
// 카테고리 데이터
categories: [
  { name: '전체', value: 'total' },
  { name: '항공권', value: 'ticket' },
  { name: '수하물', value: 'baggage' }
]

// 카테고리 선택 메서드
methods: {
  setCategory(categoryValue) {
    this.category = categoryValue;
    this.search({ 
      page: this.currentPage,
      itemsPerPage: this.itemsPerPage
    });
  }
}

// 서버 검색 요청
async search({ page, itemsPerPage }) {
  const response = await axios.post('/api/supportSearch', {
    category: this.category
  });
  this.searchResults = response.data; // 필터링된 결과 저장
}
</script>
```
</details>
<br><br>

>문의글 수정![문의2](https://github.com/user-attachments/assets/f961cbfd-5d98-4b84-9502-703d30f1268d)

<details><summary>주요 코드
</summary>

```
<templeat>
<!-- 비밀번호 입력 다이얼로그 -->
<v-dialog v-model="passwordDialog" max-width="500px">
  <v-card>
    <v-card-title>비밀번호 입력</v-card-title>
    <v-card-text>
      <v-text-field 
        v-model="password" 
        label="비밀번호" 
        type="password"
        @keyup.enter="submitPassword"></v-text-field>
    </v-card-text>
    <v-card-actions>
      <v-btn color="primary" @click="submitPassword">확인</v-btn>
    </v-card-actions>
  </v-card>
</v-dialog>

<!-- 공개여부 스위치 -->
<v-switch
  v-model="isPublic"
  :label="isPublic ? '공개' : '비공개'"
  color="primary"
  @change="handlePublicChange"
  :disabled="!editable">
</v-switch>
</templeat>

<script>
(SupportPage.vue)
 methods: {
  showDetail(event, { item }) {
    if (!item.public_Status) {
      this.openPasswordDialog(item.support_Id); // 비공개 문의 시 다이얼로그 오픈
    } else {
      this.goDetail(item.support_Id);
    }
  },
  async submitPassword() {
    try {
      const response = await axios.post('/api/supportCheckPassword', {
        supportId: this.selectedSupportNum,
        password: this.password
      });
      if(response.data){
        this.goDetail(this.selectedSupportNum); // 인증 성공 시 상세 페이지 이동
      }
    } catch (error) {
      alert('비밀번호 확인 실패');
    }
  }
}


(SupportDetail.vue)
async submit() {
    await axios.post('/api/supportEdit', {
      public_Status: this.isPublic, // 수정된 공개 상태 전송
      title: this.title,
      detail: this.detail
    });
    alert('수정 완료');
  },
  handlePublicChange() {
    if (!this.isPublic) {
      alert('⚠️ 비공개 시 비밀번호 필요');
    }
  }
},
computed: {
  editable() {
    return this.sessionId === this.writerName; // 작성자만 수정 가능
  }

</script>
```
</details>

---

### 항공권 조회 (BookingPage.vue)

>출발지&도착지 자동완성 기능, 항공편 검색![검색1](https://github.com/user-attachments/assets/cb1916d4-748e-4e83-9558-75f7dd1c3525)
<details><summary>주요 코드
</summary>

```
<templeat>
<!-- 출발지 자동완성 -->
<v-autocomplete
  v-model="departure"
  label="출발지"
  :items="cities"
  item-value="value"
  item-title="label"
  @keyup="searchAirports($event.target.value, 'departure')"
  no-data-text="일치하는 도시가 없습니다."
></v-autocomplete>

<!-- 도착지 자동완성 -->
<v-autocomplete
  v-model="destination"
  label="도착지"
  :items="cities"
  item-value="value"
  item-title="label"
  @keyup="searchAirports($event.target.value, 'destination')"
  no-data-text="일치하는 도시가 없습니다."
></v-autocomplete>
</templeat>

<script>
 methods: {
  // 공항 데이터 로드
  async loadAirports() {
    const response = await axios.get('/api/flights/airports', {
      params: { keyword: this.keyword }
    });
    
    this.cities = response.data.airports.map(airport => ({
      label: `${airport.city} (${airport.code})`, // "서울 (ICN)" 형식
      value: airport.code // 공항 코드
    }));
  },

  // 실시간 검색 필터링
  async searchAirports(keyword) {
      this.keyword = keyword;
      console.log('입력값 : ', keyword);

      if (keyword) {
        let keywordData = '';
        console.log(this.cities);
        keywordData = this.cities.filter(item =>
            item.label.toLowerCase().includes(keyword.toLowerCase())
        );
        console.log(keywordData);
      } else {
        this.keywordData = this.cities;
      }
    },

// 예매 정보 처리 메서드
    bookTicket() {
      if (!this.departure || !this.destination) {
        console.log("출발지 또는 도착지가 비어 있습니다.");
        return;
      }

      console.log('선택된 travelClass: ', this.travelClass);

      const formattedDepartureDate = this.formatDate(this.departureDate);
      const formattedReturnDate = this.returnDate ? this.formatDate(this.returnDate) : null; // 오는 날이 없을 경우 null로 처리

      this.$router.push({
        name: 'TicketSearch',
        query: {
          departure: this.departure,
          departureCity: this.departureCity,
          destination: this.destination,
          destinationCity: this.destinationCity,
          departureDate: formattedDepartureDate,
          returnDate: formattedReturnDate,
          adults: Number(this.adults),
          children: Number(this.children),
          travelClass: this.travelClass,
          nonStop: this.nonStop
        }
      });
    }

</script>
```
</details>
<br><br>


>항공편 목록 정렬, 페이징 기능![검색2](https://github.com/user-attachments/assets/5fb531ee-abea-4b05-b9e0-82a69e4321ee)
<details><summary>주요 코드
</summary>

```
<templeat>
<!-- 정렬 옵션 선택 UI -->
<div class="sort-container">
  <v-select
    v-model="sortOption"
    :items="sortOptions"
    label="정렬 기준"
    dense
    outlined
  ></v-select>
</div>

<!-- 페이지 네비게이션 -->
<v-row v-if="!isLoading">
  <v-col cols="12" class="text-center">
    <v-btn
      v-for="pageNumber in Math.ceil(currentFlights.length / flightsPerPage)"
      :key="pageNumber"
      @click="changePage(pageNumber)"
    >
      {{ pageNumber }}
    </v-btn>
  </v-col>
</v-row>
</templeat>

<script>
 export default {
  data() {
    return {
      sortOption: '출발시간 빠른순',
      sortOptions: [
        '출발시간 빠른순',
        '출발시간 늦은순', 
        '최저가순'
      ],
      flightsPerPage: 5,
      currentPage: 1
    }
  },
  watch: {
    sortOption() {
      this.sortFlights() // 정렬 옵션 변경 시 재정렬
    }
  },
computed: {
    paginatedFlights() {
      const start = (this.currentPage - 1) * this.flightsPerPage
      const end = start + this.flightsPerPage
      return this.currentFlights.slice(start, end) // 현재 페이지 데이터 추출
    }
  },
  methods: {
    sortFlights() {
      if (this.sortOption === '출발시간 빠른순') {
        this.currentFlights.sort((a, b) => 
          new Date(a.departureTime) - new Date(b.departureTime)
        )
      } else if (this.sortOption === '출발시간 늦은순') {
        this.currentFlights.sort((a, b) =>
          new Date(b.departureTime) - new Date(a.departureTime)
        )
      } else if (this.sortOption === '최저가순') {
        this.currentFlights.sort((a, b) => a.price - b.price)
      }
    },

   changePage(pageNumber) {
      this.currentPage = pageNumber // 페이지 변경 핸들러
    }
  }
}
</script>
```
</details>
<br><br>

>항공편 검색 조건 변경 후 재검색![검색3](https://github.com/user-attachments/assets/6a56866f-de94-44ce-a3b8-7d3e5a5d160d)
<details><summary>주요 코드
</summary>

```
<templeat>
(TicketSearch.vue)
<!-- 일정 변경 버튼 -->
<v-btn @click="toggleSchedulePanel">일정 변경</v-btn>

<!-- 일정 변경 패널 -->
<v-expand-transition>
  <div v-show="showSchedulePanel">
    <ScheduleChangePanel 
      @update-schedule="handleScheduleUpdate"
      :initialDeparture="departureMutable"
      :initialDestination="destinationMutable"
      :initialDepartureDate="departureDateMutable"
      :initialReturnDate="returnDateMutable"
      :initialAdults="adults"
      :initialChildren="children"
      :initialTravelClass="travelClass"
      :initialNonStop="nonStop"
    />
  </div>
</v-expand-transition>

(ScheduleChangePanel.vue)
 <v-card>
    <v-form @submit.prevent="updateSchedule">
      <!-- 검색 조건 입력 필드들 -->
      <v-autocomplete v-model="departure" label="출발지"/>
      <v-autocomplete v-model="destination" label="도착지"/>
      <v-text-field v-model="departureDate" type="date"/>
      <v-text-field v-model="returnDate" type="date"/>
      
      <!-- 변경 버튼 -->
      <v-btn @click="updateSchedule">변경하기</v-btn>
    </v-form>
  </v-card>
</templeat>

<script>
(TicketSearch.vue)
 methods: {
  // 일정 변경 패널 토글
  toggleSchedulePanel() {
    this.showSchedulePanel = !this.showSchedulePanel
  },

  // 새 일정 반영 및 재검색
  handleScheduleUpdate(newSchedule) {
    this.departureMutable = newSchedule.departure
    this.destinationMutable = newSchedule.destination
    this.departureDateMutable = newSchedule.departureDate
    this.returnDateMutable = newSchedule.returnDate
    this.adultsMutable = newSchedule.adults
    this.childrenMutable = newSchedule.children
    this.travelClassMutable = newSchedule.travelClass
    this.nonStopMutable = newSchedule.nonStop

    this.journeyStage = 'outgoing' // 여정 단계 초기화
    this.selectedFlightId = null
    this.returnFlightId = null
    
    this.searchFlights() // 변경된 조건으로 재검색 실행
  }
}

(ScheduleChangePanel.vue)
export default {
  methods: {
    updateSchedule() {
      this.$emit('update-schedule', {
        departure: this.departure,
        destination: this.destination,
        departureDate: this.departureDate,
        returnDate: this.returnDate,
        adults: this.adults,
        children: this.children,
        travelClass: this.travelClass,
        nonStop: this.nonStop
      })
    }
  }
}
</script>
```
</details>
<br><br>

>항공편 선택 후 정보 입력 페이지로 데이터 라우팅![검색4](https://github.com/user-attachments/assets/cecc9d71-6576-422f-b5dc-fbba00b14374)
<details><summary>주요 코드
</summary>

```
<templeat>
<!-- 항공편 선택 이벤트 -->
<div 
  v-for="flight in paginatedFlights" 
  :key="flight.id"
  @click="selectFlight(flight.id)">
  <!-- 항공편 정보 표시 -->
</div>
</templeat>

<script>
 methods: {
  // 항공편 선택
  selectFlight(flightId) {
    if (this.journeyStage === 'outgoing') {
      this.selectedFlightId = flightId; // 가는 편 선택
    } else if (this.journeyStage === 'return') {
      this.returnFlightId = flightId; // 오는 편 선택
    }
  },

  // 다음 여정으로 이동
  NextJourney() {
    if (this.selectedFlightId) {
      this.journeyStage = 'return'; // 오는 편 선택 모드로 전환
      this.searchFlights(); // 오는 편 항공편 재검색
    }
  },

async Payment() {
    // 가는 편 정보 저장
    const outgoingFlight = {
      ...this.currentFlights.find(f => f.id === this.selectedFlightId),
      departure: this.departureMutable,
      destination: this.destinationMutable,
      departureDate: this.departureDateMutable
    };

    // 오는 편 정보 저장 (왕복인 경우)
    let returnFlight = null;
    if (this.returnFlightId) {
      returnFlight = {
        ...this.currentFlights.find(f => f.id === this.returnFlightId),
        departure: this.destinationMutable, // 출발지/도착지 스왑
        destination: this.departureMutable,
        departureDate: this.returnDateMutable
      };
    }

    // 총 가격 계산
    const totalPrice = outgoingFlight.price + (returnFlight?.price || 0);

    // BookingDetail 페이지로 이동
    await this.$router.push({
      name: 'BookingDetail',
      query: {
        outgoingFlight: encodeURIComponent(JSON.stringify(outgoingFlight)),
        returnFlight: encodeURIComponent(JSON.stringify(returnFlight || {})),
        adults: this.adultsMutable,
        children: this.childrenMutable,
        travelClass: this.travelClassMutable,
        totalPrice: totalPrice,
        nonStop: this.nonStopMutable
      }
    });
  }
}
</script>


라우터설정(router/index.js)
{
  path: '/booking',
  name: 'BookingDetail',
  component: () => import('@/views/BookingDetail.vue'),
  props: (route) => ({
    outgoingFlight: JSON.parse(decodeURIComponent(route.query.outgoingFlight)),
    returnFlight: JSON.parse(decodeURIComponent(route.query.returnFlight)),
    adults: Number(route.query.adults),
    children: Number(route.query.children),
    travelClass: route.query.travelClass,
    totalPrice: Number(route.query.totalPrice)
  })
}
```
</details>


---

### 항공권 예매 (BookingDetail.vue)

>승객 정보 입력 후 결제![예매1](https://github.com/user-attachments/assets/1cfc4cb1-061a-4dbd-abd0-8b853690ec80)

<details><summary>주요 코드
</summary>

```
<templeat>
 <!-- 승객 정보 폼 동적 생성 -->
  <div v-for="(passenger, index) in passengers" :key="index" class="passenger-form">
    <div class="passenger-header" @click="toggleForm(index)">
      <span class="passenger-number">승객 {{ index + 1 }}</span>
    </div>
    <div v-if="expandedForms[index]" class="form-content">
      <!-- 로그인 사용자 정보 자동 입력 -->
      <input type="text" v-model="passenger.passengerName" placeholder="이름">
      <input type="text" v-model="passenger.birthDate" placeholder="생년월일">
    </div>
  </div>

  <!-- 결제 정보 표시 -->
  <div class="payment-section">
    <span class="amount">{{formatNumberWithCommas(totalPrice)}}원</span>
    <button @click="Payment">결제하기</button>
  </div>
</templeat>

<script>
 export default {
  data() {
    return {
      passengers: [], // 승객 수에 따른 동적 배열
      totalPrice: 0   // TicketSearch.vue에서 전달받은 금액
    }
  },
 mounted() {
    this.fetchUserInfos();
  },
  methods: {
    // 로그인 사용자 정보 조회
    async fetchUserInfos() {
      const token = localStorage.getItem('jwtToken'); // 저장된 토큰 가져오기
      if (token) {
        try {
          const response = await axios.post('/api/getUserInfos', {
            token: token // 토큰을 본문에 포함
          });
          this.name = response.data.name; // 사용자 정보를 변수에 저장
          this.id = response.data.username;
          this.phoneNumber = response.data.phoneNumber;
          this.contact = response.data.phoneNumber;
          this.birthDate = response.data.birthdate;
          this.userPassword = response.data.password;
          this.member_Id = response.data.membernum;

          if (this.passengers.length > 0) {
            this.passengers[0].passengerName = response.data.name;
            this.passengers[0].birthDate = response.data.birthdate;
          }
        } catch (error) {
          console.error('Error fetching user info:', error);
        }
      }
    },

    // 결제 처리 및 페이지 이동
    async Payment() {
      await axios.post('/api/reservation', { /* 예약 데이터 */ });
      this.$router.push({
        path: "/paymentcompleted",
        query: { amount: this.totalPrice }
      });
    }
  },
  created() {
   // 라우터에서 데이터 파싱
    const query = this.$route.query;
    this.outgoingFlight = JSON.parse(decodeURIComponent(query.outgoingFlight || '{}'));
    this.returnFlight = JSON.parse(decodeURIComponent(query.returnFlight || '{}'));

   // 총 승객 수 계산 및 초기화
    this.totalPassenger = this.adults + this.children;

    this.passengers = Array.from({ length: this.totalPassenger }, () => ({
      nationality: "",
      passengerName: "",
      gender: "",
      birthDate: "",
    }));
    this.expandedForms = Array(this.totalPassenger).fill(false);  // expandedForms도 총 승객 수에 맞게 초기화
    this.expandedForms[0] = true; // 첫번째 폼은 펼쳐짐
}

</script>
```
</details>

---

### 항공권 결제 (BookingDetail.vue)

>아임포트(포트원) API 이용하여 결제 ![결제1](https://github.com/user-attachments/assets/06af637d-71de-4e83-9d6a-2aabfecc6507)

<details><summary>주요 코드
</summary>

```
<script>
async Payment() {


      const IMP = window.IMP;
      IMP.init("imp12777257");

try {
   if (this.verificationSuccess) {
          // 결제 진행
          IMP.request_pay({
            // param
            pg: "kakaopay",
            merchant_uid: "uid_" + this.member_Id + date,   // 주문 번호
            channelKey: "channel-key-016f94c1-5b8c-448d-81ee-f544a25da15b",
            paymentId: `payment-${crypto.randomUUID()}`,
            name: "항공권 예약",
            pay_method: "card",
            amount: this.totalPrice
          }, async function (rsp) {
            // callback
            this.verificationSuccess = rsp.success
            console.log("결제 결과: ", rsp)

            for (let i=0; i < this.passengers.length; i++) {
              /* 결제 완료 내역 DB 저장 */
              const response = await axios.post('/api/payment', {
                reservation_Id: `${this.member_Id}_${this.passengers[i].passengerName}_${date}`,
                status: this.verificationSuccess,
                amount: this.totalPrice
              });
              console.log("결제 완료 내역 DB 저장 결과", response.data); // 서버로부터의 응답 확인
            }
            this.$router.push({
              path: "/paymentcompleted",
              query: {
                amount: this.totalPrice,
                paymentMethod: "카드",
                reservationId: `${this.member_Id}_${this.passengers[0].passengerName}_${date}`
              }
            });
          }.bind(this));
        }
      } catch (error) {
        console.error("[ERROR] 결제 실패:", error);
        this.verificationMessage = '[ERROR] 결제가 실패했습니다.';
        this.verificationSuccess = false; // 인증 실패 시 변수 값을 false로 설정
      }
}
</script>
```
</details>
