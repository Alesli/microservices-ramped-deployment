--CREATE DATABASE postsdb;
--
--\c postsdb;

CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    text TEXT NOT NULL,
    posted_at DATE NOT NULL
);