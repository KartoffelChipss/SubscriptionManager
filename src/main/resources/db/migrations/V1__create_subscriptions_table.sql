-- Tables
CREATE TABLE IF NOT EXISTS profiles (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    pin TEXT,
    created_at BIGINT DEFAULT (strftime('%s', 'now') * 1000), --> Default to current timestamp (in milliseconds)
    UNIQUE(name)
);

CREATE TABLE IF NOT EXISTS subscriptions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    profile_id INTEGER NOT NULL,
    name TEXT NOT NULL,
    price REAL NOT NULL,
    billing_period TEXT NOT NULL,
    normalized_price REAL NOT NULL,
    start_date BIGINT,
    category TEXT NOT NULL,
    FOREIGN KEY (profile_id) REFERENCES profiles(id) ON DELETE CASCADE,
    UNIQUE(profile_id, name)
);

-- Indexes
CREATE INDEX idx_subscriptions_profile_id ON subscriptions(profile_id);
CREATE INDEX idx_subscriptions_name ON subscriptions(name);
CREATE INDEX idx_profiles_created_at ON profiles(created_at);

-- Default profiles
INSERT INTO profiles (name, pin) VALUES ('Profile 1', NULL);
INSERT INTO profiles (name, pin) VALUES ('Profile 2', NULL);
