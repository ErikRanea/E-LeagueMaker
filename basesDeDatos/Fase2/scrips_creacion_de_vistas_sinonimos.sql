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

-- competiciones, juegos, equipos

CREATE OR REPLACE VIEW Enfrentamientos_Por_Jornada AS
SELECT
    enf.Cod,  -- C�digo del enfrentamiento
    enf.Cod_Jornada,  -- Jornada asociada
    jorn.N_Jornada,  -- N�mero de la jornada
    enf.Cod_Equipo_Local,  -- C�digo del equipo local
    enf.Cod_Equipo_Visitante,  -- C�digo del equipo visitante
    CASE
        WHEN enf.Gana_local = 1 THEN 'Local'  -- Si gana local
        ELSE 'Visitante'  -- Si gana visitante
    END AS Ganador  -- Ganador basado en Gana_local
FROM
    Enfrentamientos enf
    JOIN Jornadas jorn  -- Unir con Jornadas por Cod_Jornada
        ON enf.Cod_Jornada = jorn.Cod
ORDER BY
    jorn.N_Jornada,  -- Ordenar por n�mero de jornada
    enf.Cod;  -- Ordenar por c�digo de enfrentamiento






---Sinonimos-----
CREATE SYNONYM COMPETI FOR COMPETICIONES;
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
    CREATE SEQUENCE seq_juegos
    START WITH 100
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
    CREATE SEQUENCE seq_competiciones
    START WITH 100
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;
    CREATE SEQUENCE seq_equipos
    START WITH 100
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;






