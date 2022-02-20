# Cupist

**사용 기술 & 구조**
* `Kotlin`, `MVVM`, `AAC`, `DataBinding`, `Retrofit`, `Coroutine`, `Glide`  

**UI 처리 및 구성**
* MainActivity, MainFragment(메인 화면), ProfileFragment(프로필 화면)
  - MainActivity에서 FragmeLayout을 통해 MainFragment와 ProfileFragment를 트랜젝션 처리.

##

### 1. 화면
**메인 화면**  

<img width="1013" alt="스크린샷 2022-02-20 오후 8 00 04" src="https://user-images.githubusercontent.com/58131221/154839322-28a384d1-5dc4-4c52-9175-5fede0f43334.png">

##

**프로필 화면**  
  
<img width="459" alt="스크린샷 2022-02-19 오후 11 54 19" src="https://user-images.githubusercontent.com/58131221/154806095-e20bf6d5-8c90-4b7c-a643-a1b32256eba2.png">

##

**프로필 다이얼로그**  
  
<img width="463" alt="스크린샷 2022-02-19 오후 11 48 17" src="https://user-images.githubusercontent.com/58131221/154805871-c048de0f-7550-414a-9a49-68a17ab52b33.png">

## 

### 2. 메인 화면 구현

1. 상-하단 네비게이션 탭 구성
    * 상단 TabLayout 처리
    * 하단 Bottom Navigation 처리
    
2. 화면 진입 시, 오늘의 추천 & 추가 추천 API 호출
    * Corountine을 통하여 호출된 API response를 모두 받았을 때, 메인 리스트 처리
    
3. ViewType RecyclerView를 활용하여 리스트 노출
    * `오늘의 추천` - `맞춤 추천` - `추가 추천` 순서대로 리스트 노출
    
4. 리스트 최 하단 도달 시, 다음 페이지의 추가 추천 API 호출
    * 리스트 최 하단 도달 시, 추가 추천 API 호출 후 리스트 하단 노출
    
5. 맞춤 추천 영역 선택 클릭 시, 맞춤 추천 API 호출
    * 기존 리스트를 갱신하여, 맞춤 추천 API 결과를 최상단에 노출
    
6. 소개 카드 X 버튼 혹은 좋아요 버튼 클릭 시 리스트에서 삭제
    * 해당 item을 list에서 삭제하여 데이터 갱신


##  

### 3. 프로필 화면 구현

1. 화면 진입 시, 프로필 API 호출
    * Retrofit을 통한 프로필 API 호출
  
2. 프로필 데이터 관리
    * Glide와 Recyclerview를 활용하여 프로필 사진 GridLayout 처리
    * Preference를 활용하여 프로필 데이터 상태 관리

3. 프로필 수정 항목
    * EditText, Databinding을 통해 viewModel에서 업데이트 되는 text를 관리
  
4. 다이얼 로그
    * 프로필 API의 response meta 데이터를 가공하여 DialogFragment에서 Recyclerview 처리
    * 선택한 item, Dialog 노출 시 글자색 파란색 처리

## 


