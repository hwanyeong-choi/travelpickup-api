
DROP TABLE IF EXISTS pickup;
CREATE TABLE pickup (
    pickup_id BIGINT AUTO_INCREMENT COMMENT '픽업 아이디',
    user_id BIGINT COMMENT '유저 아이디',
    state VARCHAR(100) NOT NULL COMMENT '픽업 상태',
    create_at datetime(6) NOT NULL COMMENT '픽업 생성일',
    modify_at datetime(6) NOT NULL COMMENT '픽업 수정일',
    PRIMARY KEY (pickup_id),
    INDEX IDX_USER_ID (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS pickup_location;
CREATE TABLE pickup_location(
    pickup_location_id BIGINT AUTO_INCREMENT,
    pickup_id BIGINT COMMENT '픽업 아이디',
    description TEXT COMMENT '픽업 위치 상세내역',
    latitude DOUBLE NOT NULL COMMENT '픽업위치 위도',
    longitude DOUBLE NOT NULL COMMENT '핍업위치 경도',
    INDEX IDX_PICKUP_ID (pickup_id),
    PRIMARY KEY (pickup_location_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS destination_location;
CREATE TABLE destination_location(
    destination_location_id BIGINT AUTO_INCREMENT,
    pickup_id BIGINT NOT NULL COMMENT '픽업 아이디',
    description TEXT COMMENT '픽업 위치 상세내역',
    latitude DOUBLE NOT NULL COMMENT '픽업위치 위도',
    longitude DOUBLE NOT NULL COMMENT '핍업위치 경도',
    INDEX IDX_PICKUP_ID (pickup_id),
    PRIMARY KEY (destination_location_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS pickup_product;
CREATE TABLE pickup_product (
    pickup_product_id BIGINT AUTO_INCREMENT COMMENT '픽업 물건 아이디',
    pickup_id BIGINT NOT NULL COMMENT '픽업 아이디',
    name VARCHAR(255) NOT NULL COMMENT '물품 이름',
    quantity BIGINT NOT NULL COMMENT '물품개수',
    INDEX IDX_PICKUP_ID (pickup_id),
    PRIMARY KEY (pickup_product_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS pickup_product_img;
CREATE TABLE pickup_product_img (
    pickup_product_img_id BIGINT AUTO_INCREMENT COMMENT '픽업 물건 사진 아이디',
    pickup_id BIGINT NOT NULL COMMENT '픽업 아이디',
    path VARCHAR(255) NOT NULL COMMENT '물품 사진 경로',
    INDEX IDX_PICKUP_ID (pickup_id),
    PRIMARY KEY (pickup_product_img_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;