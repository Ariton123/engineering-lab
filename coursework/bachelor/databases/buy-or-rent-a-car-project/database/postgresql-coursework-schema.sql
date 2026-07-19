-- Buy or Rent a Car - cleaned PostgreSQL coursework schema
-- All sample records are fictional and intended only for local demonstration.

DROP SCHEMA IF EXISTS car_dealership CASCADE;
CREATE SCHEMA car_dealership;
SET search_path TO car_dealership;

CREATE TABLE covek (
    id BIGINT PRIMARY KEY,
    ime VARCHAR(30) NOT NULL,
    prezime VARCHAR(40) NOT NULL,
    telefonski_broj VARCHAR(20),
    email VARCHAR(80) UNIQUE,
    adresa VARCHAR(80),
    CONSTRAINT ck_telefonski_broj CHECK (
        telefonski_broj IS NULL OR telefonski_broj ~ '^[0-9]{9}$'
    ),
    CONSTRAINT ck_email CHECK (
        email IS NULL OR email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'
    )
);

CREATE TABLE sopstvenik (
    id_sopstvenik BIGINT PRIMARY KEY REFERENCES covek(id)
);

CREATE TABLE kupuvac (
    id_kupuvac BIGINT PRIMARY KEY REFERENCES covek(id)
);

CREATE TABLE avtomobil (
    id_avto BIGINT PRIMARY KEY,
    marka VARCHAR(30) NOT NULL,
    model VARCHAR(30) NOT NULL,
    menuvac VARCHAR(20) NOT NULL,
    boja VARCHAR(20),
    kilometraza INTEGER CHECK (kilometraza >= 0),
    godina_proizvodstvo INTEGER NOT NULL,
    tip_gorivo VARCHAR(20),
    kategorija VARCHAR(20) NOT NULL CHECK (kategorija IN ('prodazba', 'rentanje')),
    cena NUMERIC(12,2) NOT NULL CHECK (cena >= 0),
    id_sopstvenik BIGINT NOT NULL REFERENCES sopstvenik(id_sopstvenik)
);

CREATE TABLE naracka (
    id_naracka BIGINT PRIMARY KEY,
    datum DATE NOT NULL,
    iznos NUMERIC(12,2) NOT NULL CHECK (iznos >= 0),
    status BOOLEAN NOT NULL DEFAULT FALSE,
    id_kupuvac BIGINT NOT NULL REFERENCES kupuvac(id_kupuvac),
    id_avto BIGINT NOT NULL REFERENCES avtomobil(id_avto)
);

CREATE TABLE sopstvenik_potvrduva_naracka (
    id_potvrdena BIGINT PRIMARY KEY,
    id_naracka BIGINT UNIQUE NOT NULL REFERENCES naracka(id_naracka)
);

CREATE TABLE naplata (
    id_naplata BIGINT PRIMARY KEY,
    iznos NUMERIC(12,2) NOT NULL CHECK (iznos >= 0),
    id_potvrdena BIGINT UNIQUE NOT NULL REFERENCES sopstvenik_potvrduva_naracka(id_potvrdena)
);

INSERT INTO covek (id, ime, prezime, telefonski_broj, email, adresa) VALUES
    (1, 'Elena', 'Petrova', '070000001', 'elena.owner@example.com', 'Example Street 1'),
    (2, 'Marko', 'Iliev',  '070000002', 'marko.owner@example.com', 'Example Street 2'),
    (3, 'Ana',   'Nikolova','070000003', 'ana.buyer@example.com',  'Example Street 3'),
    (4, 'Bojan', 'Trajkov', '070000004', 'bojan.buyer@example.com','Example Street 4');

INSERT INTO sopstvenik (id_sopstvenik) VALUES (1), (2);
INSERT INTO kupuvac (id_kupuvac) VALUES (3), (4);

INSERT INTO avtomobil (
    id_avto, marka, model, menuvac, boja, kilometraza,
    godina_proizvodstvo, tip_gorivo, kategorija, cena, id_sopstvenik
) VALUES
    (1, 'Audi', 'A6', 'Automatic', 'black', 20000, 2019, 'petrol', 'prodazba', 50000, 1),
    (2, 'Nissan', 'R34', 'Manual', 'purple', 180000, 1998, 'petrol', 'rentanje', 120, 2),
    (3, 'BMW', 'E46', 'Manual', 'silver', 150000, 2002, 'diesel', 'prodazba', 7000, 1);

INSERT INTO naracka (id_naracka, datum, iznos, status, id_kupuvac, id_avto) VALUES
    (1, DATE '2021-12-12', 50000, TRUE, 3, 1),
    (2, DATE '2021-12-15', 120, FALSE, 4, 2);

INSERT INTO sopstvenik_potvrduva_naracka (id_potvrdena, id_naracka) VALUES (1, 1);
INSERT INTO naplata (id_naplata, iznos, id_potvrdena) VALUES (1, 50000, 1);
