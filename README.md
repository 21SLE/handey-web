# HANDEY BACK-END
'HANDEY(핸디)'는 직장인과 대학생을 위한 태스크 매니저 웹&앱 서비스입니다.
바쁜 현대인들을 위해 진행 예정인 일, 진행 중인 일, 진행 완료된 일을 체계적으로 정리할 수 있도록 도와드립니다. 
핸디 프로젝트는 백엔드, 프런트엔드, 모바일 앱 파트가 개발되었는데, 이중 백엔드 개발 레포지터리입니다.

'HANDEY' is a task manager service for workers and student which provides web and mobile app. 
We'll help you organize your working process.
<br>
<br>

## 💻 Technologies 
* <strong>Backend: SpringBoot </strong> <br>
* Frontend: React <br>
* Mobile App: Flutter <br>
<br>

## ✏️ Design
* Development Progress <br>
  * [비즈니스 요구사항 및 개발 진행 상황](https://docs.google.com/spreadsheets/d/1_MEKdaJV0bbH2-dRaMY48fHQEZ1tV7XaFVgwaOju_FY/edit?usp=sharing, "Development Progress")
<br>

## 📆 Duration
2021/07 - 2022/01
<br>
<br>

## 🪄 Features
* [<strong>회원가입(Sign Up)/로그인(Sign In)</strong>](https://github.com/hanslelee/handey-be/tree/master/src/main/java/com/handey/web/member)
  * 이메일 중복 확인
  * 회원 탈퇴
  * 닉네임, 비밀번호 변경
* <strong>위클리(Weekly)</strong>
  * [Weekly](https://github.com/hanslelee/handey-be/tree/master/src/main/java/com/handey/web/weekly): 일주일 단위로 해야할 일 정리
  * [Finished Today](https://github.com/hanslelee/handey-be/tree/master/src/main/java/com/handey/web/finishedweekly): Weekly에서 오늘 완료한 일이 자동으로 기록됨
* <strong>[오늘 할 일(ToDo)](https://github.com/hanslelee/handey-be/tree/master/src/main/java/com/handey/web/todo)</strong>
  * 매일 사용자가 지정한 시각(ex. 밤 12시)에 리셋<br>
    이때, 사용자가 모두 완료한 ToDo는 삭제되며 History 화면에서 확인 가능<br>
    [리셋 기능 자세히 보기](https://github.com/hanslelee/handey-be/tree/master/src/main/java/com/handey/web/common/reset)
  * Drag-and-Drop기능이 가능하여 순서를 바꿀 수 있음 
  * 압정 버튼을 누를 시 순서가 고정
* <strong>[메모(Memo)](https://github.com/hanslelee/handey-be/tree/master/src/main/java/com/handey/web/memo)</strong>
  * 메모 기능
* <strong>[히스토리(History)](https://github.com/hanslelee/handey-be/tree/master/src/main/java/com/handey/web/history)</strong>
  * 지난 날들의 ToDo와 Weekly확인 가능
  * [ToDo](https://github.com/hanslelee/handey-be/tree/master/src/main/java/com/handey/web/todohistory)
  * [Weekly](https://github.com/hanslelee/handey-be/tree/master/src/main/java/com/handey/web/finishedweekly)
* [<strong>설정(Settings)</strong>](https://github.com/hanslelee/handey-be/tree/master/src/main/java/com/handey/web/userinfo)
  * 비밀번호 변경
  * 이름(닉네임) 변경
  * 리셋 시간(완료된 ToDo들은 삭제되고, ToDo와 Finished Weekly가 HISTORY로 넘어감) 변경
* [<strong>휴지통(Trash)</strong>](https://github.com/hanslelee/handey-be/tree/master/src/main/java/com/handey/web/trash)
  * 홈화면에서 ToDo삭제시 휴지통 화면으로 이동
  * 일주일이 지나면 휴지통에서 삭제
  * 홈화면으로 복구 버튼을 누르면 홈 화면으로 복구
<br>

## 🔍 Preview
* [웹 프런트엔드 미리보기(Web Preview)](https://github.com/hanslelee/handey-fe)
* [모바일 앱 미리보기(Mobile App Preview)](https://github.com/hanslelee/handey-app)
<br>

## 📑 Future Plans
* DB, Server 변경
* 비밀번호 찾기 기능(이메일 전송)
<br>

## 🎈 Retrospective
백엔드와 프런트엔드, 모바일 앱을 모두 학습해 봄으로써 웹이나 앱 의 전체적인 구조를 구체적인 경험을 통해 공부할 수 있었다. 
이 과정에서 어떤 기능은 백엔드에서 작업해야 프런트엔드에서 개발이 수월한지, 
프런트엔드와 모바일앱에서 동시에 사용해야할 api가 있다면 이를 백엔드에서 어떻게 개발하는 게 나은지 등등의 실질적인 문제들을 직면하였고, 
이를 해결하며 개발자로서 한층 성장할 수 있었다. 
비용적인 측면에서도 실제 배포를 고려하며 개발하다보니, 데이터베이스와 코드 등을 효율적으로 구축하기 위해 최선을 다해 노력하게 되었으며 
이를 통해 어떤 방식이 실무에서도 효과적일지 고민하며 공부할 수 있었다.

<br>
<br>
