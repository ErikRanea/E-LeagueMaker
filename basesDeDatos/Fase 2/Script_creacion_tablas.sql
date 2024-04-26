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
        Cod_Juego NUMBER(2),
        CONSTRAINT Jor_Cod_PK PRIMARY KEY (Cod),
        CONSTRAINT Jor_Cod_Juego_FK FOREIGN KEY (Cod_Juego) REFERENCES Juegos(Cod),
        CONSTRAINT Jor_Cod_Competicion_FK FOREIGN KEY (Cod_Competicion) REFERENCES Competiciones(Cod)
    );

    CREATE TABLE Equipos (
        Cod NUMBER(10) GENERATED ALWAYS AS IDENTITY,
        Nombre VARCHAR2(100),
        Fecha_Fundacion DATE,
        CONSTRAINT Equip_Cod_Jornada_PK PRIMARY KEY (Cod)
    );
    
    
    CREATE TABLE Enfrentamientos (
        Cod NUMBER(8),
        Hora DATE,
        Gana_local NUMBER(1),
        Cod_Jornada NUMBER(6),
        Cod_competicion NUMBER(4),
        Cod_juego NUMBER (2),
        Cod_Equipo_Visitante NUMBER(10),
        Cod_Equipo_Local NUMBER(10),
        CONSTRAINT Enfren_Cod_PK PRIMARY KEY (Cod),
        CONSTRAINT Enfren_Cod_Jornada_FK FOREIGN KEY (Cod_Jornada) REFERENCES Jornadas(Cod),
        CONSTRAINT Enfren_Cod_Competicion_FK FOREIGN KEY (Cod_competicion) REFERENCES Competiciones(Cod),
        CONSTRAINT Enfren_Cod_Juego_FK FOREIGN KEY (Cod_juego) REFERENCES Juegos(Cod),
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
        Nacionalidad VARCHAR2(100),
        Fecha_Nacimiento DATE,
        Nickname VARCHAR2(100),
        Cod_Equipo NUMBER(10),
        CONSTRAINT Staff_Cod_PK PRIMARY KEY (Cod),
        CONSTRAINT Staff_Cod_Equipo_FK FOREIGN KEY (Cod_Equipo) REFERENCES Equipos(Cod),
        CONSTRAINT Staff_Puesto_CK CHECK (Puesto IN 
                                        ('Entrenador', 'Entrenador asistente'))
    );

    
    CREATE TABLE Usuarios (
        Cod_Usuario NUMBER(10) GENERATED ALWAYS AS IDENTITY,
        Nickname VARCHAR2(100),
        Contraseña VARCHAR2(100),
        Es_Admin Number(1) DEFAULT(0),
        CONSTRAINT Usu_Cod_Usuario_PK PRIMARY KEY (Cod_Usuario),
        CONSTRAINT Usu_Es_Admin_CK CHECK (Es_Admin IN (0, 1))
    );


    
    CREATE TABLE Clasificacion_Final (
        ID_Expo NUMBER,
        Cod_Competicion NUMBER(4),
        Posicion VARCHAR2(10),
        Equipo VARCHAR(100),
        Puntos NUMBER,
        CONSTRAINT Clasifi_Final_ID_Expo_PK PRIMARY KEY (ID_Expo) 
    );

    
    CREATE TABLE Resultado_Jornadas (
        ID_Expo NUMBER,
        Cod_Jornada NUMBER(6),
        Numero_Jornada VARCHAR2(10),
        Nombre_Equipo_Local VARCHAR2(50),
        Nombre_Equipo_Visitante VARCHAR2(50),
        Nombre_Ganador VARCHAR2(50),
        CONSTRAINT Result_Jor_ID_Expo_PK PRIMARY KEY (ID_Expo)
    );

    
    CREATE TABLE Resultado_Ultima_Jornada (
        ID_Expo NUMBER,
        Cod_Enfrntamiento NUMBER(8),
        Nombre_Equipo_Local VARCHAR2(50),
        Nombre_Equipo_Visitante VARCHAR2(50),
        Nombre_Ganador VARCHAR2(50),
        CONSTRAINT Result_Ult_Jor_ID_Expo_PK PRIMARY KEY (ID_Expo)
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
        Cod_Patrocinador NUMBER(10),
        Nombre VARCHAR2(50),
        Cod_Equipo NUMBER(10),
        CONSTRAINT Patro_Cod_Euqipo_FK FOREIGN KEY (Cod_Equipo) REFERENCES Equipos(Cod),
        CONSTRAINT Patro_Cod_Patrocinador_PK PRIMARY KEY (Cod_Patrocinador)
    );