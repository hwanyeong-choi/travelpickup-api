# TravelPickup Backend
트레블 픽업은 여행중 구매한 물품들이 숙소로 돌아가기까지 짐이되는 문제를 해소하기 위해 기획한 서비스 입니다.
간편하게 웹으로 픽업신청후 쇼핑몰이 밀집된 지역에 위치한 픽업세너로 방문하여 간편하게 큐알코드로 접수하면 
당일 오후 8시까지 안전하게 숙소에서 구매한 물품을 받아보실 수 있습니다.

## WireFrames
Miro Link: [Miro Link](https://miro.com/welcomeonboard/c0lPMHQwY2poM204VXFSQ3ZLOUgxbTZRTVQyRW1WaGE1M0IxYVFBWURJZzZvdmRHSkZHZjM3c09UY1pSQ040UXwzNDU4NzY0NTUxMzI1NTk1NDUzfDI=?share_link_id=922024720079)

## FrontEnd
FrontEnd Github Repository: [travelpickup-front](https://github.com/hwanyeong-choi/travelpickup-front)

## Tech Stack
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/d506e521-b290-492c-9b22-35ac81d0a8f3" alt="Spring" width="58" height="58">
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/9ef38db0-43f8-4fa3-a644-2fb0d8948e94" alt="Spring Boot" width="58" height="58">
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/78ff3945-b252-4e44-bf91-24847295358a" alt="Spring Boot Security" width="58" height="58">
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/04c7be05-da95-4375-bd26-bde1feff2749" alt="swagger" width="58" height="58">
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/21f6def1-3cfa-4700-8051-b3da5110b800" alt="hibernate" width="58" height="58">
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/37d57014-277d-430a-9d63-fb3391dc9d32" alt="mariadb" width="58" height="58">
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/ebac2cd2-b1bc-41f4-a148-ca9592c3f245" alt="kakao" width="58" height="58">


## VCS: Version Control System
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/3163513f-78b6-4906-83ac-ac143cebd0a8" alt="github" width="58" height="58">
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/6afc840d-3d00-4b6d-9d73-3f16132a7218" alt="flyway" width="58" height="58">

## IDE: Integrated Development Environment
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/35a4746b-f92f-407b-88a4-3ac787059239" alt="pedora" width="58" height="58">
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/72761061-6c07-4769-b342-ed4751245a6c" alt="intellijidea" width="58" height="58">
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/157c86e4-4f3f-4163-abee-1546c0850537" alt="datagrip" width="58" height="58">

## Test
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/a6c205b7-b25a-43a0-a079-069738582665" alt="spock" width="58" height="58">

## Code Convention
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/b79da29a-a29f-4969-9c14-2964954a31fb" alt="naver" width="58" height="58">

## Swagger
Local 환경에서만 활성화 경로: http://localhost:8080/swagger-ui/index.html

## Local 개발환경 설정

### Docker
  1. docker, docker-compose 설치 진행
  2. /infra 디렉토리로 이동: cd /infra
  3. mariadb, localstack 컨테이너 생성: docker-compose up -d

### Docker LocalStack Amazon S3 Bucket
  1. Docker 로컬 컨테이너 환경 구현
  2. LocalStack S3 Bucket 생성: aws --endpoint-url=http://localhost:4566 s3 mb s3://pickupimg

## 개선해야하는 부분
  1. Spock Test Code 추가 작성 (커버리지 90%이상 확보 그라운드룰)
  2. CI/CD 구현 GitAction(Build, test), AWS(ECS, ECR, EC2)
  3. RDS master slave 환경 구현
  4. 기능기반 아키텍처에서 -> 헥사고날 아키텍처로 시도가 필요
  5. WebSoket으로 실시간 배송상태 알림 전송 구현
  6. Swagger Restdocs Rest Api 문서화
  7. Spring Security redis를 활용한 refresh token기능 추가
