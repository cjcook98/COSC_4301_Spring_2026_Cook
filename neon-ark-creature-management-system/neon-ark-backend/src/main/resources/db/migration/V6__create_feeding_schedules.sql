CREATE TABLE feeding_schedules (
    id SERIAL PRIMARY KEY,
    creature_id INTEGER NOT NULL REFERENCES creatures(id),
    feed_time TIME NOT NULL
);