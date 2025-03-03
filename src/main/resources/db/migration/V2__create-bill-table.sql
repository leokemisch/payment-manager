CREATE TABLE bill (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    due_date DATE NOT NULL,
    payment_date DATE,
    value NUMERIC NOT NULL,
    description TEXT,
    status TEXT NOT NULL,
    user_id TEXT NOT NULL)

