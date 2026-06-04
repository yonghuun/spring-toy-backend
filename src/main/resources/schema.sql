CREATE TABLE users (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    username VARCHAR(50) NOT NULL UNIQUE,

    password VARCHAR(255) NOT NULL,

    role VARCHAR(30) NOT NULL,

    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    deleted_at DATETIME NULL
);

CREATE TABLE quests (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(100) NOT NULL,
    memo VARCHAR(255),
    difficulty VARCHAR(20) NOT NULL,
    xp INT NOT NULL,
    completed BOOLEAN NOT NULL DEFAULT FALSE,
    created_at DATETIME NOT NULL,
    completed_at DATETIME NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)    
    
);
