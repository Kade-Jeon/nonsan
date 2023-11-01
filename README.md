# MSA 구조를 활용한 포탈 서비스 프로젝트
팀명 : 논산
서비스명 : 호국요람
컨셉 : 톰캣 내장 서버를 활용하여 MSA 구조를 구현한 포탈 서비스.

# 개발환경
Springboot 2.5.6
Java opnejdk 11
Maira DB
Redis 3.0.504

# 내가 담당한 부분
- User 정보가진 Rest Server 전체 개발
  * 각 서버에서 유저 정보가 필요할 때, 이 서버로 api 요청을 보내고 리턴을 받습니다.
- Main Homepage 서비스 개발
  * 유저가 접속하는 메인 페이지이며, 이 페이지를 통해 각 서비스로 연결합니다.
  * 외부 api 활용한 날씨 데이터 노출
  * 로그인/회원가입 및 로그인정보 세션처리
- 실시간 오픈 채팅 서비스
  * 스포츠 경기에 대한 오픈 채팅 서비스
  * 관리자가 해당 경기에 대한 채팅방을 개설하면, 유저가 접속하여 서비스 활용
- 전체 프로젝트 통합

# 프로젝트 중 느낀 어려웠던 점
1. 각 서버가 내장 톰캣으로 구현되어 A 컨트롤러(localhost:1001)에서 다른 서버의 B 컨트롤러(localhost:1002)로 이동이 불가능.
2. 내장 톰캣으로 각 서버에 세션 정보를 넘기는 것이 불가능.
  [문제 원인]
  * 일반적으로 로그인 세션을 구현하면 해당 정보는 톰캣 내부에 저장된다. 따라서 다른 서버로 이동할 때 세션 정보가 유지되지 않는다.
  * 다른 서비스로 연결할 때에 A 서비스 컨트롤러에서 B 서비스 컨트롤러로 연결하게 되면 localhost:1001 뒤에 또다시 http://localhost:1002~ 이런 방식으로 이동됨
    예) http://localhost:1001/http://localhost:1002/serviceB
  [1차 해결 방안]
  * 이를 해결하기 위해, 인덱스 페이지를 통해 ajax로 다른 서버로 유저 정보를 넘기고 홈페이지 이동을 구현하였다.
  * 해당 페이지에는 ajax를 위한 JavaScript만 구현되어있고, 파라미터 요청에 따라서 각 유저가 원하는 서비스로 이동시켜준다.
  * 넘긴 유저 정보는 Map에 담아두고 이동된 서버에서 파라미터로 넘어온 유저아이디를 이용해 Map에서 값을 꺼내어 세션을 만들어 구현했다.
  * ajax로 값을 넘긴 이후에는 우리가 원하는 각 서비스로 이동하도록 구현했다.
  [1차 해결 방안의 문제점]
  * 파라미터에 다른 아이디를 임의로 넣으면 다른 아이디로 서버에 접근할 수 있는 보안상의 이슈가 생겼다.

  [2차 해결 방안]
  * 세션 정보를 넘기기 위해, 방법을 알아보던 중 Redis라는 기술을 알게 되었고 이를 접목하기로 하였다.
  * Redis를 통해 메인 서비스에서 유저 객체를 세션으로 저장하여, 다른 서버에서 Redis에서 세션을 꺼내와 사용하도록 했다.
  [2차 해결 방안의 문제점]
  * Redis에 유저 객체를 세션으로 저장할 때는 문제가 생기지 않았으나, 다른 서버에서 세션 정보를 유저 객체로 꺼내올 때 직렬화/역직렬화 이슈가 발생되었다.
  * 해당 이슈를 해결하여 처리하고 싶었으나, 프로젝트 제출 마감 직전으로 다른 방법을 찾아야만 했다.
  [3차 해결 방안]
  * Redis에 세션 정보를 유저 객체로 생기는 에러가 단순히 String으로 유저 아이디를 넣었을 때는 문제 없이 작동되는 것을 확인했다.
  * 따라서 이를 이용하여 아이디를 가져와서 세션에 아이디 정보를 저장할 수 있었다.
  * 만약, 각 서비스에서 아이디 이외에 정보가 필요한 경우 Rest 서버에 아이디를 넘겨서 다른 정보를 조회하여 이용할 수 있도록 구현했다.
  * 이를 통해, 인덱스 페이지가 사라지고 각 메인 홈페이지에서 직접 해당 서버로 연결할 수 있도록 바꿀 수 있었다.

# 프로젝트를 통해 아쉬웠던 점
  * 실시간 채팅 구현을 위한 웹 소켓에 대해서 더욱 시간을 투자하지 못한 점.
  * 3차 해결방안에서 유저 아이디 값을 Rest로 넘기고 값을 리턴 받는데, 일반 유저가 해당 restAPI 주소를 안다면 다른 유저의 정보를 빼갈 수 있는 보안이슈.
  * MSA를 구현하기 위해서는 컨테이너 기술이나 쿠버네티스 등 많은 기술들이 필요한데, 그런 기술들이 없이 구현한 점이 아쉬웠다. 그럼에도 불구하고 전반적인 MSA 구조를 조금은 이해할 수 있었던 프로젝트였다.






