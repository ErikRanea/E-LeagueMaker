--1. Vista clasificacion-----
CREATE OR REPLACE VIEW Clasificacion AS
SELECT
    Cod_competicion,
    cod_equipo,
    ROW_NUMBER() OVER (PARTITION BY cf.Cod_Competicion ORDER BY cf.Puntos DESC) AS Posicion,
    Puntos
FROM
    PUNTOS_EQUIPOS cf
ORDER BY
    cf.Cod_Competicion,
    cf.Puntos DESC;

---2. Vista para enfrentamientos p

CREATE OR REPLACE VIEW Enfrentamientos_Por_Jornada AS
SELECT
    ruj.Cod_Enfrentamiento,
    ruj.Cod_Jornada,
    ruj.Nombre_Equipo_Local,
    ruj.Nombre_Equipo_Visitante,
    ruj.Nombre_Ganador
FROM
    Resultado_Ultima_Jornada ruj
ORDER BY
    ruj.Cod_Jornada,
    ruj.Cod_Enfrentamiento;





---Sinonimos-----
CREATE SYNONYM COMPETI FOR COMPETICIONES;
CREATE SYNONYM CLASIFI FOR CLASIFICACION_FINAL;
CREATE SYNONYM ENFRE FOR ENFRENTAMIENTOS;
CREATE SYNONYM PATRO FOR PATROCINADORES;



--Secuencias---

CREATE SEQUENCE seq_jornadas
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
CREATE SEQUENCE seq_enfrentamientos
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;






