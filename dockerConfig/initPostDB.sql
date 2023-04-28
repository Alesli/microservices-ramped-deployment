SELECT 'CREATE DATABASE postsdb' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'postsdb');

\c postsdb;

CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    text TEXT NOT NULL,
    posted_at DATE NOT NULL
);