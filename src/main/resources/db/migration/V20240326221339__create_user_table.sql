DROP TABLE IF EXISTS travelpickup_user;
CREATE TABLE travelpickup_user (
    id BIGINT AUTO_INCREMENT COMMENT 'USER ID',
    nick_name VARCHAR(200) NOT NULL COMMENT '닉네임',
    provider VARCHAR(100) NOT NULL COMMENT '프로바이더',
    provider_id BIGINT NOT NULL  COMMENT 'ci',
    role VARCHAR(100) NOT NULL  COMMENT '유저역할',
    create_at datetime(6) NOT NULL COMMENT '생성일',
    modify_at datetime(6) NOT NULL COMMENT '수정일',
    INDEX IDX_PROVIDER_ID (provider_id),
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

DROP TABLE IF EXISTS travelpickup_manager;
CREATE TABLE travelpickup_manager (
   id BIGINT AUTO_INCREMENT COMMENT 'USER ID',
   nick_name VARCHAR(200) NOT NULL COMMENT '닉네임',
   provider VARCHAR(100) NOT NULL COMMENT '프로바이더',
   provider_id BIGINT NOT NULL  COMMENT 'ci',
   role VARCHAR(100) NOT NULL  COMMENT '유저역할',
   create_at datetime(6) NOT NULL COMMENT '생성일',
   modify_at datetime(6) NOT NULL COMMENT '수정일',
   INDEX IDX_PROVIDER_ID (provider_id),
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
