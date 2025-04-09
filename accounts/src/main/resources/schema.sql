CREATE TABLE IF NOT EXISTS `accounts` (
customer_id int NOT NULL,
account_number int AUTO_INCREMENT PRIMARY KEY,
account_type varchar(100) NOT NULL,
branch_address varchar(100) NOT NULL,
created_at DATE NOT NULL,
created_by VARCHAR(20) NOT NULL,
updated_at date DEFAULT NULL,
updated_by varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    mobile_number VARCHAR(15) NOT NULL,
    created_at DATE DEFAULT CURRENT_DATE,
    created_by VARCHAR(20) DEFAULT 'system',
    updated_at DATE DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL
);