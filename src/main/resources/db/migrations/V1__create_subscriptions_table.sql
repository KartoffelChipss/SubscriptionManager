-- Subscriptions
CREATE TABLE IF NOT EXISTS subscriptions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    price REAL NOT NULL,
    billing_period TEXT NOT NULL,
    normalized_price REAL NOT NULL,
    start_date BIGINT
)
