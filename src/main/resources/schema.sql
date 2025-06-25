CREATE TABLE tasks (
                       id INT PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       started_on TIMESTAMP NOT NULL,
                       completed_on TIMESTAMP NOT NULL,
                       location VARCHAR(50) NOT NULL
);
