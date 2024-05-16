

# TrabelPickup Backend

## Tech Stack
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/d506e521-b290-492c-9b22-35ac81d0a8f3" alt="Spring" width="58" height="58">
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/9ef38db0-43f8-4fa3-a644-2fb0d8948e94" alt="Spring Boot" width="58" height="58">
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/78ff3945-b252-4e44-bf91-24847295358a" alt="Spring Boot Security" width="58" height="58">
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/21f6def1-3cfa-4700-8051-b3da5110b800" alt="hibernate" width="58" height="58">
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/ebac2cd2-b1bc-41f4-a148-ca9592c3f245" alt="kakao" width="58" height="58">
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/37d57014-277d-430a-9d63-fb3391dc9d32" alt="mariadb" width="58" height="58">

## VCS: Version Control System
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/3163513f-78b6-4906-83ac-ac143cebd0a8" alt="github" width="58" height="58">
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/6afc840d-3d00-4b6d-9d73-3f16132a7218" alt="flyway" width="58" height="58">

## IDE: Integrated Development Environment
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/35a4746b-f92f-407b-88a4-3ac787059239" alt="pedora" width="58" height="58">
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/72761061-6c07-4769-b342-ed4751245a6c" alt="intellijidea" width="58" height="58">
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/157c86e4-4f3f-4163-abee-1546c0850537" alt="datagrip" width="58" height="58">

## Test
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/a6c205b7-b25a-43a0-a079-069738582665" alt="spock" width="58" height="58">



## Local 개발환경 설정

### Docker
- 1. docker, docker-compose 설치 진행
- 2. /infra 디렉토리로 이동
- 3. docker-compose up -d 명령어 실행하여 mariadb, localstack 컨테이너 생성

### Bucket
- 1. Docker 로컬 컨테이너 환경 구현
- 2. LocalStack S3 Bucket 생성 aws --endpoint-url=http://localhost:4566 s3 mb s3://pickupimg
