


# TrabelPickup Backend

## Tech Stack
![spring-color](https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/429b02b5-e027-4d9c-a369-b7e5f5c24998)![spring](https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/1603a7c8-4092-4c9d-89ef-8db9538fb0bd)

## Local 개발환경 설정

### Docker
- 1. docker, docker-compose 설치 진행
- 2. /infra 디렉토리로 이동
- 3. docker-compose up -d 명령어 실행하여 mariadb, localstack 컨테이너 생성

### Bucket
- 1. Docker 로컬 컨테이너 환경 구현
- 2. LocalStack S3 Bucket 생성 aws --endpoint-url=http://localhost:4566 s3 mb s3://pickupimg
