-- Analytical report queries from the database coursework project.
SET search_path TO car_dealership;

-- Monthly number of unapproved orders per owner in the current year.
SELECT
    a.id_sopstvenik,
    EXTRACT(MONTH FROM n.datum) AS mesec,
    COUNT(*) AS broj_neodobreni_naracki
FROM naracka n
JOIN avtomobil a ON n.id_avto = a.id_avto
WHERE n.status = FALSE
  AND EXTRACT(YEAR FROM n.datum) = EXTRACT(YEAR FROM CURRENT_DATE)
GROUP BY a.id_sopstvenik, EXTRACT(MONTH FROM n.datum)
ORDER BY a.id_sopstvenik, mesec;

-- Most frequently sold car brands in the current year.
SELECT
    a.marka,
    COUNT(*) AS broj_prodadeni_avtomobili
FROM naracka n
JOIN avtomobil a ON n.id_avto = a.id_avto
WHERE a.kategorija = 'prodazba'
  AND n.status = TRUE
  AND EXTRACT(YEAR FROM n.datum) = EXTRACT(YEAR FROM CURRENT_DATE)
GROUP BY a.marka
ORDER BY broj_prodadeni_avtomobili DESC;

-- Number of payments processed for each owner during the current month.
SELECT
    a.id_sopstvenik,
    COUNT(p.id_naplata) AS broj_naplati
FROM naplata p
JOIN sopstvenik_potvrduva_naracka potvrda ON p.id_potvrdena = potvrda.id_potvrdena
JOIN naracka n ON potvrda.id_naracka = n.id_naracka
JOIN avtomobil a ON n.id_avto = a.id_avto
WHERE DATE_TRUNC('month', n.datum) = DATE_TRUNC('month', CURRENT_DATE)
GROUP BY a.id_sopstvenik
ORDER BY a.id_sopstvenik;
