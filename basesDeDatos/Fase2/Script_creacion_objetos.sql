-- alter session set ddl_lock_timeout = 500;
DROP SEQUENCE seq_equipos;
DROP SEQUENCE seq_competiciones;
DROP SEQUENCE seq_juegos;
DROP SEQUENCE seq_enfrentamientos;
DROP SEQUENCE seq_jornadas;

DROP SYNONYM competi;
DROP SYNONYM enfre;
DROP SYNONYM patro;


DROP TABLE Puntos_Equipos CASCADE CONSTRAINTS;
DROP TABLE Usuarios CASCADE CONSTRAINTS;
DROP TABLE Jugadores CASCADE CONSTRAINTS;
DROP TABLE Staffs CASCADE CONSTRAINTS;
DROP TABLE Equipos CASCADE CONSTRAINTS;
DROP TABLE Enfrentamientos CASCADE CONSTRAINTS;
DROP TABLE Jornadas CASCADE CONSTRAINTS;
DROP TABLE Competiciones CASCADE CONSTRAINTS;
DROP TABLE Juegos CASCADE CONSTRAINTS;
DROP TABLE Temp_clob_tab CASCADE CONSTRAINTS;
DROP TABLE Patrocinadores CASCADE CONSTRAINTS;


DROP SYNONYM  COMPETI;
DROP SYNONYM  ENFRE;
DROP SYNONYM  PATRO;

DROP SEQUENCE seq_jornadas;
DROP SEQUENCE seq_enfrentamientos;
DROP SEQUENCE seq_juegos;
DROP SEQUENCE seq_competiciones;
DROP SEQUENCE seq_equipos;





    CREATE TABLE temp_clob_tab(
    ID NUMBER GENERATED ALWAYS AS IDENTITY,
    XML_DATA CLOB,
    FECHA_EXPIRACION TIMESTAMP(6),
    CONSTRAINT Temp_Id_Pk PRIMARY KEY (ID)
    );
    
    CREATE TABLE Juegos (
        Cod NUMBER(2),
        Nombre VARCHAR2(100),
        Desarrolladora VARCHAR2(100),
        Fecha_Lanzamiento DATE,
        CONSTRAINT Jueg_Cod_PK PRIMARY KEY (Cod)
    );

    
    CREATE TABLE Competiciones (
        Cod NUMBER(4),
        Nombre VARCHAR2(100),
        Fecha_Inicio DATE,
        Fecha_Fin DATE,
        Cod_Juego NUMBER(2),
        Estado_abierto NUMBER(1) DEFAULT(0),
        CONSTRAINT Comp_Cod_PK PRIMARY KEY (Cod),
        CONSTRAINT Comp_Cod_Juego_FK FOREIGN KEY (Cod_Juego) REFERENCES Juegos(Cod),
        CONSTRAINT Comp_Estado_abierto_CK CHECK (Estado_abierto IN (0, 1))
    );

    
    CREATE TABLE Jornadas (
        Cod NUMBER(6),
        N_Jornada NUMBER,
        Fecha DATE,
        Cod_Competicion NUMBER(4),
        CONSTRAINT Jor_Cod_PK PRIMARY KEY (Cod),
        CONSTRAINT Jor_Cod_Competicion_FK FOREIGN KEY (Cod_Competicion) REFERENCES Competiciones(Cod)
    );

    CREATE TABLE Equipos (
        Cod NUMBER(10),
        Nombre VARCHAR2(100),
        Fecha_Fundacion DATE,
        CONSTRAINT Equip_Cod_Jornada_PK PRIMARY KEY (Cod)
    );
    
    
    CREATE TABLE Enfrentamientos (
        Cod NUMBER(8),
        Hora DATE,
        Gana_local NUMBER(1),
        Cod_Jornada NUMBER(6),
        Cod_Equipo_Visitante NUMBER(10),
        Cod_Equipo_Local NUMBER(10),
        CONSTRAINT Enfren_Cod_PK PRIMARY KEY (Cod),
        CONSTRAINT Enfren_Cod_Jornada_FK FOREIGN KEY (Cod_Jornada) REFERENCES Jornadas(Cod),
        CONSTRAINT Enfren_Gana_local_CK CHECK (Gana_local IN (0, 1)),
        CONSTRAINT Enfren_Cod_Equipo_Visitante_FK FOREIGN KEY (Cod_Equipo_Visitante) REFERENCES Equipos(Cod),
        CONSTRAINT Enfren_Cod_Equipo_Local_FK FOREIGN KEY (Cod_Equipo_Local) REFERENCES Equipos(Cod)
    );

    
    CREATE TABLE Jugadores (
        Cod NUMBER(10) GENERATED ALWAYS AS IDENTITY,
        Nombre VARCHAR2(100),
        Apellido VARCHAR2(100),
        Rol VARCHAR2(100),
        Salario NUMBER,
        Nacionalidad VARCHAR2(100),
        Fecha_Nacimiento DATE,
        Nickname VARCHAR2(100),
        Cod_Equipo NUMBER(10),
        CONSTRAINT Jugad_Cod_PK PRIMARY KEY (Cod),
        CONSTRAINT Jugad_Cod_Equipo_FK FOREIGN KEY (Cod_Equipo) REFERENCES Equipos(Cod)
    );

    
    CREATE TABLE Staffs (
        Cod NUMBER(10) GENERATED ALWAYS AS IDENTITY,
        Nombre VARCHAR2(100),
        Apellido VARCHAR2(100),
        Puesto VARCHAR2(50),
        Salario NUMBER,
        Cod_Equipo NUMBER(10),
        CONSTRAINT Staff_Cod_PK PRIMARY KEY (Cod),
        CONSTRAINT Staff_Cod_Equipo_FK FOREIGN KEY (Cod_Equipo) REFERENCES Equipos(Cod),
        CONSTRAINT Staff_Puesto_CK CHECK (Puesto IN 
                                        ('Entrenador', 'Entrenador asistente'))
    );

    
    CREATE TABLE Usuarios (
        Cod_Usuario NUMBER(10) GENERATED ALWAYS AS IDENTITY,
        Nickname VARCHAR2(100) UNIQUE,
        Password VARCHAR2(100),
        Es_Admin Number(1) DEFAULT(0),
        CONSTRAINT Usu_Cod_Usuario_PK PRIMARY KEY (Cod_Usuario),
        CONSTRAINT Usu_Es_Admin_CK CHECK (Es_Admin IN (0, 1))
    );

    CREATE TABLE Puntos_Equipos (
        Cod_Competicion NUMBER(4),
        Cod_Equipo NUMBER(10),
        Puntos NUMBER,
        CONSTRAINT Punt_Equip_Cod_Equipo_FK FOREIGN KEY (Cod_Equipo) REFERENCES Equipos(Cod),
        CONSTRAINT Punt_Equip_Cod_Competicion_FK FOREIGN KEY (Cod_Competicion) REFERENCES Competiciones(Cod),
        CONSTRAINT Punt_Equip_Puntos_Equipos_PK PRIMARY KEY (Cod_Competicion, Cod_Equipo)
    );
    
    CREATE TABLE Patrocinadores (
        Cod_Patrocinador NUMBER(10) GENERATED ALWAYS AS IDENTITY,
        Nombre VARCHAR2(50),
        Cod_Equipo NUMBER(10),
        CONSTRAINT Patro_Cod_Euqipo_FK FOREIGN KEY (Cod_Equipo) REFERENCES Equipos(Cod),
        CONSTRAINT Patro_Cod_Patrocinador_PK PRIMARY KEY (Cod_Patrocinador)
    );
    
    
    
    --- CREACION VISTAS, SINONIMOS Y SECUENCIAS
    
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

CREATE OR REPLACE VIEW Resultados_Jornadas AS
SELECT

    enf.Cod_Jornada,
    jorn.Cod_competicion,
    enf.Cod AS Cod_Enfrentamiento,  -- Jornada asociada
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
    enf.Cod;
    
-- Ordenar por c�digo de enfrentamiento

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
    
    
    
