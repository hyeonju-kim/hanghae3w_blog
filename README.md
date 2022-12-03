# hanghae3w_blog
Spring 입문주차 과제 - 스프링 부트로 로그인 기능이 없는 블로그 백엔드 서버 만들기

## **서비스 완성 요구사항**

1.  **전체 게시글 목록 조회 API**
    -   제목, 작성자명, 작성 내용, 작성 날짜를 조회하기
    -   작성 날짜 기준 내림차순으로 정렬하기
2.  **게시글 작성 API**
    -   제목, 작성자명, 비밀번호, 작성 내용을 저장하고
    -   저장된 게시글을 Client 로 반환하기
3.  **선택한 게시글 조회 API**
    -   선택한 게시글의 제목, 작성자명, 작성 날짜, 작성 내용을 조회하기 (검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)
4.  **선택한 게시글 수정 API**
    -   수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
    -   제목, 작성자명, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기
5.  **선택한 게시글 삭제 API**
    -   삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
    -   선택한 게시글을 삭제하고 Client 로 성공했다는 표시 반환하기

## **API 명세서 예시**

| Method | URL | Request | Response |
| --- | --- | --- | --- |
| GET | /api/post | \- | {   {   "**createdAt**": "2022-07-25T12:43:01.226062”,   "**modifiedAt**": "2022-07-25T12:43:01.226062”,   "**id**": 1,   "**title**": "title2",   "**content**": "content2",   "**author**": "author2"   },      { "**createdAt**": "2022-07-25T12:43:01.226062”,   "**modifiedAt**": "2022-07-25T12:43:01.226062”,   "**id**": 2,   "**title**": "title",   "**content**": "content",   "**author**": "author"   }   …   } |
| GET | /api/post/{id} | \- | {   "**createdAt**": "2022-07-25T12:43:01.226062”,   "**modifiedAt**": "2022-07-25T12:43:01.226062”,   "**id**": 1,   "**title**": "title2",   "**content**": "content2",   "**author**": "author2"   } |
| POST | /api/post | {   "**title**" : "title",   "**content**" : "content",   "**author**" : "author",   "**password**" : "password"   } | {   "**createdAt**": "2022-07-25T12:43:01.226062”,   "**modifiedAt**": "2022-07-25T12:43:01.226062”,   "**id**": 1,   "**title**": "title",   "**content**": "content",   "**author**": "author"   } |
| PUT | /api/post/{id} | {   "**title**" : "title2",   "**content**" : "content2",   "**author**" : "author2",   "**password**" :"password2"   } | {   "**createdAt**": "2022-07-25T12:43:01.226062”,   "**modifiedAt**": "2022-07-25T12:43:01.226062”,   "**id**": 1,   "**title**": "title2",   "**content**": "content2",   "**author**": "author2"   } |
| DELETE | /api/post/{id} | {   "**password**" :"password"   } | {   "**success**": true,   } |
