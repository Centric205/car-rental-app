-- v1__init_schema.sql

-- ==============================
-- USERS TABLE
-- ==============================
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL
);

-- ==============================
-- CARS TABLE
-- ==============================
CREATE TABLE cars (
    id BIGSERIAL PRIMARY KEY,
    make VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    year INT NOT NULL CHECK (year >= 1886), -- The first car was invented in 1886
    daily_rate NUMERIC(10, 2) NOT NULL CHECK (daily_rate >= 0),
    available BOOLEAN DEFAULT TRUE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL
);

-- Index for faster search by make/model/year
CREATE INDEX idx_cars_make_model_year ON cars (make, model, year);

-- ==============================
-- CAR_IMAGES TABLE
-- ==============================
CREATE TABLE car_images (
    id BIGSERIAL PRIMARY KEY,
    car_id BIGINT NOT NULL REFERENCES cars(id) ON DELETE CASCADE,
    image_url TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL
);

-- Index for quick lookup of images by car_id
CREATE INDEX idx_car_images_car_id ON car_images (car_id);

-- ==============================
-- BOOKINGS TABLE
-- ==============================
CREATE TABLE bookings (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    car_id BIGINT NOT NULL REFERENCES cars(id) ON DELETE CASCADE,
    start_date TIMESTAMP WITH TIME ZONE NOT NULL,
    end_date TIMESTAMP WITH TIME ZONE NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING'
        CHECK (status IN ('PENDING', 'CONFIRMED', 'CANCELLED')),
    total_price NUMERIC(10, 2) NOT NULL CHECK (total_price >= 0),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL
);

-- Prevent overlapping bookings for the same car
CREATE UNIQUE INDEX idx_bookings_car_date ON bookings (car_id, start_date, end_date);

-- Index for faster lookup of bookings by user_id
CREATE INDEX idx_bookings_user_id ON bookings (user_id);