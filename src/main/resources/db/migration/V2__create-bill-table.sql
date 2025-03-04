CREATE TABLE bill (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    due DATE NOT NULL,
    payment DATE,
    value NUMERIC NOT NULL,
    description TEXT,
    status TEXT NOT NULL)

