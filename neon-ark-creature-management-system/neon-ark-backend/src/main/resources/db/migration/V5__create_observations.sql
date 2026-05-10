CREATE TABLE observations (
    id SERIAL PRIMARY KEY,
    author VARCHAR(100) NOT NULL,
    creature_id INTEGER NOT NULL REFERENCES creatures(id),
    note TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);