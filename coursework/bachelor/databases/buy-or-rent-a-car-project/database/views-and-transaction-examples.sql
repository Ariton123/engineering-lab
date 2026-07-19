-- Views and transaction examples for the Buy or Rent a Car coursework project.
SET search_path TO car_dealership;

CREATE OR REPLACE VIEW prikaz_avtomobili_kupuvanje_rentanje AS
SELECT
    a.id_avto,
    a.marka,
    a.model,
    a.kategorija,
    a.cena,
    c.ime AS sopstvenik_ime,
    c.prezime AS sopstvenik_prezime,
    c.id AS id_sopstvenik
FROM avtomobil a
JOIN covek c ON a.id_sopstvenik = c.id;

CREATE OR REPLACE VIEW pregled_naracki AS
SELECT
    n.id_naracka,
    n.status,
    n.datum,
    n.iznos,
    c.ime AS kupuvac_ime,
    c.prezime AS kupuvac_prezime,
    c.telefonski_broj,
    a.marka,
    a.model
FROM naracka n
JOIN avtomobil a ON n.id_avto = a.id_avto
JOIN covek c ON n.id_kupuvac = c.id;

CREATE OR REPLACE VIEW prikaz_avtomobil_full AS
SELECT
    a.id_avto,
    a.marka,
    a.model,
    a.godina_proizvodstvo,
    a.menuvac,
    a.boja,
    a.kilometraza,
    a.tip_gorivo,
    a.kategorija,
    a.cena,
    c.id AS id_sopstvenik,
    c.ime AS sopstvenik_ime,
    c.prezime AS sopstvenik_prezime
FROM avtomobil a
JOIN covek c ON a.id_sopstvenik = c.id;

-- Registration-form transaction example. ROLLBACK keeps the reference dataset unchanged.
BEGIN;
INSERT INTO covek (id, ime, prezime, telefonski_broj, email, adresa)
VALUES (10, 'Test', 'User', '070000010', 'test.user@example.com', 'Example Street 10');
INSERT INTO kupuvac (id_kupuvac) VALUES (10);
ROLLBACK;

-- Order and owner-confirmation transaction example.
BEGIN;
INSERT INTO naracka (id_naracka, datum, iznos, status, id_kupuvac, id_avto)
VALUES (10, CURRENT_DATE, 7000, FALSE, 4, 3);
INSERT INTO sopstvenik_potvrduva_naracka (id_potvrdena, id_naracka)
VALUES (10, 10);
UPDATE naracka SET status = TRUE WHERE id_naracka = 10;
ROLLBACK;
