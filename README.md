


# TrabelPickup Backend

## Tech Stack

## Local 개발환경 설정
<img src="https://github.com/hwanyeong-choi/travelpickup-api/assets/47169718/d506e521-b290-492c-9b22-35ac81d0a8f3" alt="Spring" width="76" height="76">


### Docker
- 1. docker, docker-compose 설치 진행
- 2. /infra 디렉토리로 이동
- 3. docker-compose up -d 명령어 실행하여 mariadb, localstack 컨테이너 생성

### Bucket
- 1. Docker 로컬 컨테이너 환경 구현
- 2. LocalStack S3 Bucket 생성 aws --endpoint-url=http://localhost:4566 s3 mb s3://pickupimg
