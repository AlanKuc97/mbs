-- liquibase formatted sql
-- changeset alanas:1
-- comment: Create schema for MBS
CREATE TABLE `ACCOUNT` (
    `id`                    BIGINT NOT NULL AUTO_INCREMENT,
    `number`                VARCHAR(50) NOT NULL UNIQUE,
    `version_num`           BIGINT NOT NULL,
    `created_by`            VARCHAR(50) NOT NULL,
    `creation_date`         TIMESTAMP NOT NULL,
    `last_modified_by`      VARCHAR(50) NOT NULL,
    `last_modified_date`    TIMESTAMP NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `CUSTOMER` (
    `id`                    BIGINT NOT NULL AUTO_INCREMENT,
    `name`                  VARCHAR(50) NOT NULL,
    `last_name`             VARCHAR(50) NOT NULL,
    `phone_number`          VARCHAR(20) NOT NULL,
    `email`                 VARCHAR(50) NOT NULL,
    `birth_date`            TIMESTAMP NOT NULL,
    `account_id`            BIGINT NOT NULL,
    `version_num`           BIGINT NOT NULL,
    `created_by`            VARCHAR(50) NOT NULL,
    `creation_date`         TIMESTAMP NOT NULL,
    `last_modified_by`      VARCHAR(50) NOT NULL,
    `last_modified_date`    TIMESTAMP NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`account_id`) REFERENCES `ACCOUNT` (id),
    CONSTRAINT customer_unique UNIQUE (`name`, `last_name`, `phone_number`, `email`)
);

CREATE TABLE `ADDRESS` (
    `id`                    BIGINT NOT NULL AUTO_INCREMENT,
    `street`                VARCHAR(50) NOT NULL,
    `city`                  VARCHAR(50) NOT NULL,
    `state`                 VARCHAR(50) NOT NULL,
    `zip_code`              VARCHAR(50) NOT NULL,
    `version_num`           BIGINT NOT NULL,
    `created_by`            VARCHAR(50) NOT NULL,
    `creation_date`         TIMESTAMP NOT NULL,
    `last_modified_by`      VARCHAR(50) NOT NULL,
    `last_modified_date`    TIMESTAMP NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT address_unique UNIQUE (`street`, `city`, `state`, `zip_code`)
);

CREATE TABLE `ADDRESS_CUSTOMER` (
    `id`                    BIGINT NOT NULL AUTO_INCREMENT,
    `customer_id`           BIGINT NOT NULL,
    `address_id`            BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`customer_id`) REFERENCES `CUSTOMER` (id),
    FOREIGN KEY (`address_id`) REFERENCES `ADDRESS` (id),
    CONSTRAINT address_customer_unique UNIQUE (`customer_id`, `address_id`)
);