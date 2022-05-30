# TodoList
MVVM 데이터 처리 방법, local, remote data 처리 공부

## Tech-Stack
Hilt, MVVM, Coroutine, Retrofit, Firebase Realtime DB, Room, Navigation

## 실행 화면
<img src="https://user-images.githubusercontent.com/53431177/171041913-e12e7ba2-85a1-405f-8421-b742bb0b9cc6.gif">

## Step 1
- MVVM 이벤트 처리 - LiveData + Event
- Todo data - Json 파일 gson으로 load (load만 구현)

## Step 2 
- MVVM 이벤트 처리 - SingleLiveData
- Todo data - Room 사용 (load, add 가능)

## Step 3
- MVVM 이벤트 처리 - SharedFlow
- Todo data - Firebase Realtime DB 사용 (load, add 가능)

## Step 4
- MVVM 이벤트 처리 - SharedFlow + SEALED CLASS
- Todo data - Firebase Realtime DB 사용 (load, add 가능)

## Step 5
- MVVM 이벤트 처리 - SharedFlow + SEALED CLASS + LIFECYCLE
- Todo data - Firebase Realtime DB 사용 (load, add 가능)

## Step 6
- MVVM 이벤트 처리 - EVENTFLOW + SEALED CLASS + LIFECYCLE
- Todo data - Firebase Realtime DB 사용 (load, add 가능)
