-- liquibase formatted sql
-- changeset alanas:2
-- comment: Insert initial data
INSERT INTO ACCOUNT (number, version_num, created_by, creation_date, last_modified_by, last_modified_date) VALUES
('FR7630004000031234567890143', 1, 'user1', '2022-05-01 07:09:27', 'user1', '2022-05-01 07:09:27'),
('DE89370400440532013000', 1, 'user2', '2023-02-12 10:15:45', 'user2', '2023-02-12 10:15:45'),
('GB29NWBK60161331926819', 1, 'user3', '2021-08-19 05:30:10', 'user3', '2021-08-19 05:30:10');

INSERT INTO CUSTOMER (name, last_name, phone_number, email, birth_date, account_id, version_num, created_by, creation_date, last_modified_by, last_modified_date) VALUES
('John', 'Doe', '123-456-7890', 'john.doe@example.com', '1985-03-22', 1, 1, 'user1', '2022-05-01 07:09:27', 'user1', '2022-05-01 07:09:27'),
('Jane', 'Smith', '234-567-8901', 'jane.smith@example.com', '1990-11-15', 2, 1, 'user2', '2023-02-12 10:15:45', 'user2', '2023-02-12 10:15:45'),
('Michael', 'Johnson', '345-678-9012', 'michael.johnson@example.com', '1978-07-09', 3, 1, 'user3', '2021-08-19 05:30:10', 'user3', '2021-08-19 05:30:10');

INSERT INTO ADDRESS (street, city, state, zip_code, version_num, created_by, creation_date, last_modified_by, last_modified_date) VALUES
('123 Main St', 'New York', 'NY', '10001', 1, 'user1', '2022-05-01 07:09:27', 'user1', '2022-05-01 07:09:27'),
('456 Elm St', 'Los Angeles', 'CA', '90001', 1, 'user2', '2023-02-12 10:15:45', 'user2', '2023-02-12 10:15:45'),
('789 Oak St', 'Chicago', 'IL', '60601', 1, 'user3', '2021-08-19 05:30:10', 'user3', '2021-08-19 05:30:10');
