DROP TABLE Clasificacion_Final CASCADE CONSTRAINTS;
DROP TABLE Resultado_Jornadas CASCADE CONSTRAINTS;
DROP TABLE Resultado_Ultima_Jornada CASCADE CONSTRAINTS;
DROP TABLE Usuarios CASCADE CONSTRAINTS;
DROP TABLE Jugadores CASCADE CONSTRAINTS;
DROP TABLE Staffs CASCADE CONSTRAINTS;
DROP TABLE Equipos CASCADE CONSTRAINTS;
DROP TABLE Enfrentamientos CASCADE CONSTRAINTS;
DROP TABLE Jornadas CASCADE CONSTRAINTS;
DROP TABLE Competiciones CASCADE CONSTRAINTS;
DROP TABLE Juegos CASCADE CONSTRAINTS;
DROP TABLE Puntos_Equipos CASCADE CONSTRAINTS;
DROP TABLE Patrocinadores CASCADE CONSTRAINTS;

    CREATE TABLE Juegos (
        Cod NUMBER(2) PRIMARY KEY,
        Nombre VARCHAR2(100),
        Desarrolladora VARCHAR2(100),
        Fecha_Lanzamiento DATE
    );

    
    CREATE TABLE Competiciones (
        Cod NUMBER(4) PRIMARY KEY,
        Nombre VARCHAR2(100),
        Fecha_Inicio DATE,
        Fecha_Fin DATE,
        Cod_Juego NUMBER(2),
        Estado_abierto NUMBER(1) DEFAULT(0),
        FOREIGN KEY (Cod_Juego) REFERENCES Juegos(Cod),
        CONSTRAINT CK_Estado_abierto CHECK (Estado_abierto IN (0, 1))
    );

    
    CREATE TABLE Jornadas (
        Cod NUMBER(6) PRIMARY KEY,
        N_Jornada NUMBER,
        Fecha DATE,
        Cod_Competición NUMBER(4),
        Cod_Juego NUMBER(2),
        FOREIGN KEY (Cod_Juego) REFERENCES Juegos(Cod),
        FOREIGN KEY (Cod_Competición) REFERENCES Competiciones(Cod)
    );

    
    CREATE TABLE Enfrentamientos (
        Cod NUMBER(8) PRIMARY KEY,
        Hora VARCHAR2(50),
        Gana_local NUMBER(1),
        Cod_Jornada NUMBER(6),
        Cod_competicion NUMBER(4),
        Cod_juego NUMBER (2),
        FOREIGN KEY (Cod_Jornada) REFERENCES Jornadas(Cod),
        FOREIGN KEY (Cod_competicion) REFERENCES Competiciones(Cod),
        FOREIGN KEY (Cod_juego) REFERENCES Juegos(Cod),
        CONSTRAINT CK_Gana_local CHECK (Gana_local IN (0, 1))
    );

    
    CREATE TABLE Equipos (
        Cod NUMBER(10) PRIMARY KEY,
        Nombre VARCHAR2(100),
        Fecha_Fundación DATE
    );

    
    CREATE TABLE Jugadores (
        Cod NUMBER(10) PRIMARY KEY,
        Nombre VARCHAR2(100),
        Apellido VARCHAR2(100),
        Rol VARCHAR2(100),
        Salario NUMBER,
        Nacionalidad VARCHAR2(100),
        Fecha_Nacimiento DATE,
        Nickname VARCHAR2(100),
        Cod_Equipo NUMBER(10),
        FOREIGN KEY (Cod_Equipo) REFERENCES Equipos(Cod)
    );

    
    CREATE TABLE Staffs (
        Cod NUMBER(10) PRIMARY KEY,
        Nombre VARCHAR2(100),
        Apellido VARCHAR2(100),
        Puesto VARCHAR2(50),
        Salario NUMBER,
        Nacionalidad VARCHAR2(100),
        Fecha_Nacimiento DATE,
        Nickname VARCHAR2(100),
        Cod_Equipo NUMBER(10),
        FOREIGN KEY (Cod_Equipo) REFERENCES Equipos(Cod),
        CONSTRAINT CK_Puesto CHECK (Puesto IN 
                                    ('Entrenador', 'Entrenador asistente'))
    );

    
    CREATE TABLE Usuarios (
        Cod_Usuario NUMBER(10) PRIMARY KEY,
        Nickname VARCHAR2(100),
        Contraseña VARCHAR2(100),
        Es_Admin Number(1) DEFAULT(0),
        CONSTRAINT CK_Es_Admin CHECK (Es_Admin IN (0, 1))
    );


    
    CREATE TABLE Clasificacion_Final (
        ID_Expo NUMBER PRIMARY KEY,
        Cod_Competicion NUMBER(4),
        Posicion VARCHAR2(10),
        Equipo VARCHAR(100),
        Puntos NUMBER
    );

    
    CREATE TABLE Resultado_Jornadas (
        ID_Expo NUMBER PRIMARY KEY,
        Cod_Jornada NUMBER(6),
        Numero_Jornada VARCHAR2(10),
        Nombre_Equipo_Local VARCHAR2(50),
        Nombre_Equipo_Visitante VARCHAR2(50),
        Nombre_Ganador VARCHAR2(50)
    );

    
    CREATE TABLE Resultado_Ultima_Jornada (
        ID_Expo NUMBER PRIMARY KEY,
        Cod_Enfrntamiento NUMBER(8),
        Nombre_Equipo_Local VARCHAR2(50),
        Nombre_Equipo_Visitante VARCHAR2(50),
        Nombre_Ganador VARCHAR2(50)
    );
    
    
    CREATE TABLE Puntos_Equipos (
        Cod_Competicion NUMBER(4),
        Cod_Equipo NUMBER(10),
        Puntos NUMBER,
        FOREIGN KEY (Cod_Equipo) REFERENCES Equipos(Cod),
        FOREIGN KEY (Cod_Competicion) REFERENCES Competiciones(Cod),
        CONSTRAINT PK_Puntos_Equipos PRIMARY KEY (Cod_Competicion, Cod_Equipo)
    );
    
    CREATE TABLE Patrocinadores (
        Cod_Patrocinador NUMBER(10),
        Nombre VARCHAR2(50),
        Cod_Equipo NUMBER(10),
        FOREIGN KEY (Cod_Equipo) REFERENCES Equipos(Cod)
    );