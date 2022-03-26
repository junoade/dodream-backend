# dodream-backend

<div align = "center">
<h3>NH 메타버스 기반 핀테크 해커톤 - 학생부 두드림팀</h3>
  🥇 <h4> 장려상(농협은행장상) 입상</h4>
  <h4> 주제 : 핀테크 미술품 거래소 플랫폼 </h4>
  <h4> 개발 기간 : 21.11.27 ~ 21.11.29</h4>
  <h4> 백엔드 서버 레파지토리 </h4>
  최준호( DB 설계, 농협 API 연동, 기타 API 개발) <br/>
  윤승규
  <h4> 프론트엔드팀 레파지토리 </h4>
  https://github.com/nostaljic/dodream-app

### DB 설계
![image](https://user-images.githubusercontent.com/54317409/160241351-6b67d6f1-9628-41f3-aeeb-75696656ea5c.png)
  
</div>

#### 개발 API 목록
- 로그인

| POST           | /api/v1/account/login | 로그인             |
| -------------- | --------------------- | ------------------ |
| Request Header | ResponseHeader        |                    |
| x              | "content-type"        | "application/json" |
| Request Body   | ResponseBody          | Return Type        |
| "id"           | "result"              | boolean            |
| "pwd"          | "accesstoken"         |                    |
|                | "name"                |                    |

 <br/>
 
- 농협 API 연동하여 입금 처리

| POST               | /api/v1/account/transferDeposit | 농협 입금 이체     |
| ------------------ | ------------------------------- | ------------------ |
| **Request Header** | **ResponseHeader**              |                    |
| accesstoken        | "content-type"                  | "application/json" |
| **Request Body**   | **ResponseBody**                | Return Type        |
| "acno"             | "result"                        | boolean값          |
| "tram" (금액)      | "message"                       | "입금성공"         |

 <br/>
 
- 사용자 계좌 생성 

| POST           | /api/v1/dodream/createWallet | 사용자 이미지를 담을 계좌        |
| -------------- | ---------------------------- | -------------------------------- |
| Request Header | ResponseHeader               |                                  |
| "accesstoken"  | "content-type"               | "application/json;charset=utf-8" |
| Request Body   | ResponseBody                 |                                  |
| "id"           | "result"                     | boolean값                        |
|                | "wallet"                     | 0~9a~Z 랜덤하게 10자리           |

 <br/>
 
- 사용자 이미지를 운영 중인 서버에 저장

| POST           | /api/v1/dodream/upload | 사용자 이미지를 운영중인 서버에 저장함 |
| -------------- | ---------------------- | -------------------------------------- |
| Request Header | ResponseHeader         |                                        |
|                | "content-type"         | "application/json"                     |
| Request Body   | ResponseBody           |                                        |
| "file"         | "result"               | boolean값                              |
| "wallet"       | "message"              | 성공시, "File uploaded successfully"   |
| "name"         | "fileName"             | 잘받는지 확인하는 용도에 가까움        |
| "id"           | "wallet"               |                                        |
|                | "name"                 |                                        |
|                | "id"                   |                                        |

 <br/>

- 이미지 경로를 해싱 후 저장

| POST           | /api/v1/dodream/NftUpload | 사용자의 이미지의 file경로를  SHA256방식으로      해싱하여 이 값을 PK로 데이터베이스에 저장함 |
| -------------- | ------------------------- | ------------------------------------------------------------ |
| Request Header | ResponseHeader            |                                                              |
|                | "content-type"            | "application/json;charset=utf-8"                             |
| Request Body   | ResponseBody              |                                                              |
| "file"         | "result"                  | boolean값                                                    |
| "wallet"       | "message"                 | 성공시, "File uploaded successfully"                         |
| "id"           | "fileName"                | 잘받는지 확인하는 용도에 가까움                              |
|                | "wallet"                  |                                                              |
|                | "name"                    |                                                              |
|                | "id"                      |                                                              |

 <br/>

- 계좌 잔고 조회

| GET                              | /api/v1/account/detail |                    |
| -------------------------------- | ---------------------- | ------------------ |
| Request Header                   | ResponseHeader         |                    |
| accesstoken (우리 NH 억세스토큰) | "content-type"         | "application/json" |
| Request Body                     | ResponseBody           |                    |
|                                  | "result"               | boolean값          | 
|                                  | "Ldbl"                 |                    |
|                                  | "RlpmAbamt"            |                    |

 <br/>

- 이미지 반환하기 

| GET                                                          | /api/v1/dodream/display | 서버 시간에 따라      저장되는 Directory에서      FileName으로      파일 이미지 반환 |
| ------------------------------------------------------------ | ----------------------- | ------------------------------------------------------------ |
| Request Header                                               | ResponseHeader          |                                                              |
|                                                              | "content-type"          | 해당 파일에 맞게                                             |
| Request Body                                                 | ResponseBody            |                                                              |
| "filename" (file의 이름값)                                   | resource 그 자체        |                                                              |
| 현재날짜 directory에 등록된  filename의  파일을 해당 파일에 맞는 Content-Type으로 반환 |                         |                                                       


 <br/>

- 이미지 반환하기 

| GET            | /api/v1/dodream/displayAll | DB에 저장되어있는 모든 파일들의  directory+filename을 조합으로 이미지 반환 |
| -------------- | -------------------------- | ------------------------------------------------------------ |
| Request Header | ResponseHeader             |                                                              |
|                | "content-type"             | "application/json"                                           |
| Request Body   | ResponseBody               |                                                              |
|                | "1"                        | "BASE64이미지값"                                             |
|                | "2"                        |                                                              |
|                | ....                       |                                                              |




![215221_215371_5053](https://user-images.githubusercontent.com/54317409/160240666-6f5cb342-b54a-4bb8-b264-19c94422b159.jpeg)
