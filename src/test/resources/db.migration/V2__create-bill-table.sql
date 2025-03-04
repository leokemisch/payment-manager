CREATE TABLE bill (
    id VARCHAR(255) PRIMARY KEY,
    due DATE NOT NULL,
    payment DATE,
    bill_value DECIMAL NOT NULL,
    description VARCHAR(255),
    status VARCHAR(255) NOT NULL
);
