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
| Name | Method | URL | Request Header | Request Body                                                                                   | Response Header | Response Body                                        |
|---|---|---|-----------------|------------------------------------------------------------------------------------------------|-----------------|------------------------------------------------------|
| 회원가입 | POST | /users/signup | none            | {<br/>"email" : "email@email.com",<br/>"nickname": "nickname",<br/>"password" : "password123"<br/>} | none            | {<br/>"status" : "200",<br/>"message":"회원가입에 성공했습니다"<br/>} |