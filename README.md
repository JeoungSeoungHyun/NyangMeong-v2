# 🐶냥도 놀러가고 싶댕 - 강원도 반려동물 여행 플랫폼 

###  **💡Spring Boot를 활용한 반려동물 동반 여행 플랫폼 만들기**

- **팀 프로젝트 기간 : 2022.03.21~2022.04.23**
- **리팩토링 기간: 2022.05.10 ~ 2022.05.22**
- **팀 프로젝트 인원 : 구아현, 이선호, 정성현, 조해윤, 황주원 총 5명**
    
    - 🧑🏻‍💻 프론트엔드 : 정성현, 황주원
    - 🧑🏻‍💻 백엔드 : 구아현, 이선호
    - 👩🏻‍💻 총괄 : 조해윤
    
- **리팩토링 인원: 구아현, 정셩현, 황주원 총 3명**
    - 🧑🏻‍💻 구아현: 사용자(user) 부분 코드 리팩토링 및 추가 기능 구현
    - 🧑🏻‍💻 황주원: 게시판(board) 부분 코드 리팩토링 및 추가 기능 구현
    - 👩🏻‍💻 정성현: 전체 코드 리팩토링 및 추가 기능 구현

<br/>

<details>
<summary>프로젝트 일정 상세 보기</summary>
<div markdown="1">

![프로젝트_일정](https://user-images.githubusercontent.com/97711663/170500135-528d2bc0-446e-4701-bb38-3cceae650e39.jpg)

</div>
</details>
    
<br/>
<br />

## 🐱기획 의도

📌 댕냥이와 같이 여행 할 수 있는 장소를 서로 공유하여 <br/> 
반려동물 동반 여행 지도를 함께 만들어 가는 여행 플랫폼! <br /> 
냥도놀러가고싶댕은 강원도 여행을 좋아하고 반려동물을 💖사랑하는 모두에게💖 열려 있습니다. <br />
<br/>
<br/>


## 🐱설계 과정 
<details>
<summary>더보기</summary>
<div markdown="1">
  
### ERD
![ERD_v2](https://user-images.githubusercontent.com/97711663/170499707-eb1fe59c-3118-4621-a4c7-9fac8031f1ea.png)
<br/>

###  기능 구성도

![기능구성도_220423_수정본](https://user-images.githubusercontent.com/97711663/170500123-5e8a1cd2-79fe-4e4d-9ad7-ec9764a26a0c.jpg)
<br/>

### 화면설계

| 메인화면 | 검색화면 |
|------|------|
|![냥도놀러가고싶댕_화면구성](https://user-images.githubusercontent.com/97711663/170501585-81efa2c6-463b-4dba-a4ba-81bb312bd842.png)|![냥도놀러가고싶댕_화면구성_검색](https://user-images.githubusercontent.com/97711663/170501590-fd8eba79-8dbb-4c72-a4c0-729b4d4c1568.png)|
| 관광정보 상세보기 | 회원정보 상세보기 |
|![냥도놀러가고싶댕_상세보기](https://user-images.githubusercontent.com/97711663/170501564-e9a66d57-6abd-4bf5-a74c-9fb194cdaa89.png)|![냥도놀러가고싶댕_화면구성_내정보보기](https://user-images.githubusercontent.com/97711663/170501597-8af91773-2e47-4229-9be1-2c69cdcf0080.png)|
| 회원가입 페이지 | 서비스 소개 페이지 |
|![냥도놀러가고싶댕_화면구성_회원가입](https://user-images.githubusercontent.com/97711663/170501600-350101ae-1294-42de-8c44-6f4c153bcb65.png)|![냥도놀러가고싶댕_화면구성_서비스소개](https://user-images.githubusercontent.com/97711663/170501598-d17eb9f9-8eaa-4015-b19f-e3d6d0f2c1f1.png)|

<br>
</div>
</details>
<br/>
<br/>

 ## 🐱 사용 기술

<img src="https://img.shields.io/badge/-Java-007396"/>  <img src="https://img.shields.io/badge/-Spring-6DB33F"/>  <img src="https://img.shields.io/badge/-Apach%20Tomcat-F8DC75"/> <img src="https://img.shields.io/badge/-MariaDB-071D49"/> 
<img src="https://img.shields.io/badge/-HTML5-E34F26"/> <img src="https://img.shields.io/badge/-CSS-1572B6"/> <img src="https://img.shields.io/badge/-JavaScript-F7DF1E"/> <img src="https://img.shields.io/badge/-JQuery-0769AD"/> 
<img src="https://img.shields.io/badge/-Github-181717"/> <img src="https://img.shields.io/badge/-Git-F05032"/> <img src="https://img.shields.io/badge/-FontAwesome-528DD7"/> <img src="https://img.shields.io/badge/-BootStrap-7952B3"/> 

- **개발 언어**: Java 11, HTML 5, CSS, JavaScript
- **DataBase**: MariaDB 10.6
- **API**
    
    강원도 반려동물 동반관광 Open API([http://pettravel.kr/petapi](http://pettravel.kr/petapi)) 
    
    네이버 지도 API([https://developers.naver.com/main/](https://developers.naver.com/main/))
    
    Google Font API([https://fonts.google.com/](https://fonts.google.com/))
    
- **Library**
- ***Front***
    - Bootstrap 5.1.3, jQuery 3.5.1, Summernote, Mustache, FontAwesome 5.15.2
- ***Back***
    - Spring Web, Spring Boot Devtools, Lombok, MariaDB Driver, Spring Data JPA, Validation
- **개발 환경** : VS Code, SpringBoot 2.5.12, Gradle, Lombok, JPA
- **협업 툴** : GitHub, Trello, Discord, Kakaotalk, GoogleDrive, Slack

<details>
<summary>협업 툴 사용 사례 상세보기</summary>
<div markdown="1">

### Trello
<img width="1046" alt="Screenshot_86" src="https://user-images.githubusercontent.com/97711663/170505791-7e1c9372-eba3-46c7-b5d8-c25ba7100f07.png">

### Slack
<img width="907" alt="Screenshot_87" src="https://user-images.githubusercontent.com/97711663/170505803-a8ef520e-81b4-4c3c-b7df-bc6a575a02ad.png">
<img width="909" alt="Screenshot_88" src="https://user-images.githubusercontent.com/97711663/170505806-624732f1-34ac-435e-b1eb-b2120bd7219c.png">

### Discord
<img width="699" alt="Screenshot_89" src="https://user-images.githubusercontent.com/97711663/170506580-215f0512-7877-4ff3-820b-ea34654ed67b.png">
<br/>


</div>
</details>

<br/>
<br/>
  
## 🐱****페이지별 상세 기능****

### 💡 ****관광정보 관련 기능****
- 관광정보와 네이버 지도API 연계 기능 
- 카테고리 별 관광정보 리스트 제공 
- 관광정보 상세보기 기능 
- 관광정보 좋아요, 즐겨찾기 기능 

| 관광정보와 네이버 지도API 연계 기능 | 카테고리 별 관광정보 리스트 제공 |
|------|------|
|![map](https://user-images.githubusercontent.com/97711663/170491895-d6c51722-c769-4823-8f05-44a2160586d5.gif)|![place-outline](https://user-images.githubusercontent.com/97711663/170491949-5591f95b-6f79-404c-ae05-b2614ddd7baa.gif)|
| 관광정보 상세보기 기능 | 관광정보 검색 기능  |
|![place-detail](https://user-images.githubusercontent.com/97711663/170492295-db803e9d-bd7b-4c15-9db7-d7fbf3c3de34.gif)|![find-place](https://user-images.githubusercontent.com/97711663/170491824-4753708c-10b9-40f3-a2a6-8b6e310bfd05.gif)|

<br/>

### 💡 ****유저 관련 기능****
- 회원가입 시 유저 네임 중복 체크 기능
- 로그인 시 쿠키에 username 기억 기능
- 로그아웃 기능
- 회원정보 수정 기능
- 회원 탈퇴 기능
- 아이디/비밀번호 찾기 : 입력받은 이메일과 핸드폰번호로 회원 정보 찾는 기능

| 회원가입 시 유저 네임 중복 체크 기능 | 로그인 시 쿠키에 username 기억 기능 |
|------|------|
|![usernamecheck](https://user-images.githubusercontent.com/97711663/170496361-4c44e47b-8094-40cb-a02e-46dca57022bc.gif)|![remember-id](https://user-images.githubusercontent.com/97711663/170492868-859f56bd-80af-4f1c-927c-52e2aec5f5ab.gif)|
| 로그아웃 기능 | 회원정보 수정기능 |
|![logout](https://user-images.githubusercontent.com/97711663/170496374-3dd16d88-8e06-4631-ba26-1c7e1e9a57b7.gif)|![userchange](https://user-images.githubusercontent.com/97711663/170496397-d4c85654-bfda-47ed-99c0-b81423a3bb0e.gif)|
| 회원탈퇴 기능 | 아이디/비밀번호 찾기 |
|![userdelete](https://user-images.githubusercontent.com/97711663/170496420-759d8963-d371-4658-8b52-dfaf59efc76b.gif)|![findid](https://user-images.githubusercontent.com/97711663/170496444-122d40ec-a8a1-4d40-9048-0ffaddb9a965.gif)|
<br/>

### 💡 ****게시판 관련 기능****
- 글 쓰기, 글 목록, 글 상세보기, 글 수정하기, 글 삭제하기, 글 좋아요
- 이미지 업로드 및 썸네일 등록
- 댓글 쓰기, 댓글 삭제하기 기능 제공

| 글 쓰기 | 글 목록 |
|------|------|
|![board-write](https://user-images.githubusercontent.com/97711663/170490633-6f6afccf-fc34-4706-938e-40bb53775b96.gif)|![boardlist](https://user-images.githubusercontent.com/97711663/170496473-0e18e514-30c5-4562-abf3-e37c7dd4d8d2.gif)|
| 글 상세보기 | 글 수정 |
|![boarddetail](https://user-images.githubusercontent.com/97711663/170496486-a31aa49e-9ef3-4078-8fbb-977ff729600e.gif)|![update-board](https://user-images.githubusercontent.com/97711663/170492401-832bdc7b-2ccc-45b9-aac9-c4e76f24bee9.gif)|
| 글 삭제하기 | 댓글 기능 |
|![board-delete](https://user-images.githubusercontent.com/97711663/170496515-aaee6209-99b2-4a4a-a7f5-26db7f609052.gif)|![comment-write](https://user-images.githubusercontent.com/97711663/170490623-6bced2ec-9e4f-498a-a0be-52933ac47de2.gif)|

<br/>

### 💡 ****관리자 관련 기능****
- 관리자가입코드를 만들어 관리자가입코드 입력 시 관리자로 회원가입 가능
- 회원 게시글 삭제 및 회원 댓글 삭제 기능
- 전체 데이터 받기 기능

| 관리자 회원가입 | 회원 게시글 삭제 |
|------|------|
|![admin-join](https://user-images.githubusercontent.com/97711663/170496542-fc722e55-94ae-4b34-9f5e-25ed6b15172c.gif)|![admin-delete](https://user-images.githubusercontent.com/97711663/170496568-eebbde59-e105-4807-b10c-cd166bc26781.gif)|
| 관리자 공지사항 작성 | 전체 데이터 받기 |
|![admin-notice](https://user-images.githubusercontent.com/97711663/170490134-bdd75d47-7430-4ce9-952c-10197b400aef.gif)|![admindata](https://user-images.githubusercontent.com/97711663/170498805-b0b20f44-043c-40b4-b7c9-f41c9e8beba4.gif)|
<br/>
<br/>


## 🐱구현 결과

[<img width="814" alt="Screenshot_90" src="https://user-images.githubusercontent.com/97711663/170508765-59abc2d9-21f5-4f3a-ae52-43e8223c52b3.png">](https://www.youtube.com/watch?v=EEdzvkBXGnE)

<br/>
<br/>

## 🐱****프로젝트 및 리팩토링 후기****

- 좋았던 점
    - 1차 프로젝트 때는 시간이 부족하여 계획했던 기능을 다 구현하지 못했지만 리팩토링 기간동안 추가 기능을 다 구현해 낼 수 있어서 뿌듯했다.
    - 프론트도 함께 맡아 구현하니 새로운 개념들을 알아가며 더 배울 수 있었다. 
    - 리팩토링 기간동안, 프론트엔드 구성원이 백엔드에 같이 참여하면서 더 다양한 경험을 할 수 있게 되어 좋았다.
    - 배웠던 기술을 프로젝트에 응용하여 팀원들과 함께 개발하면서 개념을 재정립할 수 있었던 시간이었다.
    - 다양한 오류를 접하면서 원인을 찾고, 해결하면서 다시 한 번 공부하고 이해하는 계기가 되었다.
    - 1차 프로젝트 기간동안 해결하지 못했던 오류를 리팩토링 기간에 발견하고 고칠 수 있었던 점이 가장 좋았다. 

- 보완할 점
    - 프로젝트를 같이 모의하기에 앞서 팀원 개인의 실력과 수준을 정확하게 알고 역할 분담하는 것이 중요하다고 느꼈다.
    - 세부적인 기능 설정을 구현하면서 정하였고, 일정을 세세하게 관리하지 않아 후반에는 시간이 촉박했다. 이번 프로젝트를 통해 프로젝트 일정 관리와 기획 및 설계 단계에서 명확한 설계가 필요하다는 것을 느꼈다.
    - 중복되는 코드가 많아 코드가 깔끔하지 않았다.
    - 자신이 만드는 코드에 주석을 통해 간략한 정보나 기능을 명시하지 않아  전체적인 코드를 파악 힘들어 수정에 어려움이 있었다.
    - 협업툴(github)에 대한 이해도와 사용법에 대한 지식이 부족하여 버전관리 하기 힘들었다.
    - 하지만 리팩토링 기간동안 협업툴 사용법을 제대로 숙지하여 훨씬 깔끔한 커밋 정리를 할 수 있었다. 
    - 의사소통 미흡으로 각자 진행/변동 사항 파악이 제대로 이루어지지 않아 전체적인 일정에 차질이 발생했다. 주기적인 회의가 필요한 것 같다.
