DROP DATABASE IF EXISTS petDB;
CREATE DATABASE petDB CHARACTER SET utf8 COLLATE utf8_general_ci;
use petDB;

CREATE TABLE IF NOT EXISTS User (
                                    user_id INT AUTO_INCREMENT PRIMARY KEY,
                                    username VARCHAR(255) NOT NULL UNIQUE,
                                    email VARCHAR(255) NOT NULL UNIQUE,
                                    password VARCHAR(255) NOT NULL,
                                    role ENUM('USER', 'ADMIN') DEFAULT 'USER',
                                    profile_picture_url VARCHAR(255),
                                    contact_info VARCHAR(255),
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Pet (
                                   pet_id INT AUTO_INCREMENT PRIMARY KEY,
                                   owner_id INT NOT NULL,
                                   name VARCHAR(255) NOT NULL,
                                   type VARCHAR(100),
                                   breed VARCHAR(100),
                                   age INT,
                                   description TEXT,
                                   health_information TEXT,
                                   vaccinations TEXT,
                                   photo_url VARCHAR(255),
                                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                   FOREIGN KEY (owner_id) REFERENCES User(user_id)
);

CREATE TABLE IF NOT EXISTS Post (
                                    post_id INT AUTO_INCREMENT PRIMARY KEY,
                                    user_id INT NOT NULL,
                                    title VARCHAR(255) NOT NULL,
                                    content TEXT NOT NULL,
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                    pet_id INT,
                                    FOREIGN KEY (user_id) REFERENCES User(user_id),
                                    FOREIGN KEY (pet_id) REFERENCES Pet(pet_id)
);

CREATE TABLE IF NOT EXISTS Transaction (
                                           transaction_id INT AUTO_INCREMENT PRIMARY KEY,
                                           seller_id INT NOT NULL,
                                           buyer_id INT NOT NULL,
                                           pet_id INT NOT NULL,
                                           price DECIMAL(10, 2),
                                           transaction_date DATE,
                                           status ENUM('PENDING', 'COMPLETED', 'CANCELLED') DEFAULT 'PENDING',
                                           FOREIGN KEY (seller_id) REFERENCES User(user_id),
                                           FOREIGN KEY (buyer_id) REFERENCES User(user_id),
                                           FOREIGN KEY (pet_id) REFERENCES Pet(pet_id)
);

CREATE TABLE IF NOT EXISTS Message (
                                       message_id INT AUTO_INCREMENT PRIMARY KEY,
                                       sender_id INT NOT NULL,
                                       receiver_id INT NOT NULL,
                                       content TEXT NOT NULL,
                                       timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       FOREIGN KEY (sender_id) REFERENCES User(user_id),
                                       FOREIGN KEY (receiver_id) REFERENCES User(user_id)
);

