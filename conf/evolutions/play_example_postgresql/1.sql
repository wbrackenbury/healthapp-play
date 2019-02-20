# Users Schema

# --- !Ups

CREATE TABLE IF NOT EXISTS users(
       username VARCHAR(64) PRIMARY KEY,
       password VARCHAR(64) NOT NULL,
       salt VARCHAR(64) NOT NULL
);

# --- !Downs

DROP TABLE Users;
