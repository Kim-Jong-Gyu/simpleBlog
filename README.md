# simpleBlog

## 0. 프로젝트 소개
- 간단한 블로그 서비스 구현
- API 형태로 통신
------
## 1. ERD
![simpleblog](https://github.com/Kim-Jong-Gyu/simpleBlog/assets/62927374/ef37ef6f-fffe-4ee8-af0c-03a3cfcd0777)

------
## 2. API 명세서

### 1. User API
| Name      | Method | URL                                         | Request Header | Request Body                                                                                                                                                                                       | Response Header | Response Body                                                    |
|-----------|--------|---------------------------------------------|-----------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------|------------------------------------------------------------------|
| 회원가입      | POST   | /users/signup                               | none            | {<br/>"email" : "email@email.com",<br/>"nickname": "nickname",<br/>"password" : "password123",<br/>"checkPassword" : "password123",<br/>"email":"email123@gmail.com",<br/>"authNum":"123456"<br/>} | none            | {<br/>"status" : "201",<br/>"message":"회원가입에 성공했습니다"<br/>}       |
| 닉네임 중복 체크 | GET    | /users/validateDuplicateNickname/{nickname} | none            | none                                                                                                                                                                                               | none            | {<br/>"status" : "200",<br/>"message":"닉네임 중복 체크에 성공했습니다."<br/>} |
| 이메일 인증 요청 | POST   | /email/send                                 | none            | {<br/>"email" : "email@email.com"<br/>}                                                                                                                                                            | none            | {<br/>"status" : "200",<br/>"message":"이메일 인증에 성공했습니다."<br/>}    |
| 로그인       | GET    | /users/login                                | none            | {<br/>"nickname" : "nickname",<br/>"password" : "password"<br/>}                                                                                                                                         | none            | {<br/>"status" : "200",<br/>"message":"로그인에 성공했습니다."<br/>}       |
