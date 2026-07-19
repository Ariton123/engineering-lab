-- Relational schema used by the library and reading-room coursework project.
-- Cleaned for public portfolio use by removing machine-specific ownership statements.

CREATE TABLE library (
    id VARCHAR(255) PRIMARY KEY,
    counter INTEGER,
    is_reading_room BOOLEAN NOT NULL,
    lat VARCHAR(255),
    lon VARCHAR(255),
    name VARCHAR(255),
    email VARCHAR(100)
);

CREATE TABLE book (
    id BIGSERIAL PRIMARY KEY,
    author_name VARCHAR(255),
    counter INTEGER,
    description VARCHAR(255),
    name VARCHAR(255)
);

CREATE TABLE book_libraries (
    book_id BIGINT NOT NULL REFERENCES book(id),
    libraries_id VARCHAR(255) NOT NULL REFERENCES library(id)
);
