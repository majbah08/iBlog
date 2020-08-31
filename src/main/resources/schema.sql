DROP TABLE IF EXISTS app_users;  
CREATE TABLE app_users (  
user_id INT AUTO_INCREMENT  PRIMARY KEY,  
user_name VARCHAR(50) NOT NULL,  
password_hash VARCHAR(100) NOT NULL,
full_name   VARCHAR(100) NOT NULL,
email   VARCHAR(50) NOT NULL,
is_active INT(1) NOT NULL
);  
