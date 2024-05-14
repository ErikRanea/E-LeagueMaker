-- Definir paquete Crud_Juegos
CREATE OR REPLACE PACKAGE crud_Juegos AS 
    TYPE tipo_cursor IS REF CURSOR;
    PROCEDURE insertar_juego(p_nombre IN juegos.nombre%TYPE, p_desarrolladora 
    IN juegos.desarrolladora%TYPE, p_fecha_lanzamiento 
    IN juegos.fecha_lanzamiento%TYPE);
    PROCEDURE borrar_juego(p_cod IN juegos.cod%TYPE);
    PROCEDURE modificar_juego(p_cod IN juegos.cod%TYPE, p_nombre 
    IN juegos.nombre%TYPE, p_desarrolladora IN juegos.desarrolladora%TYPE, 
    p_fecha_lanzamiento IN juegos.fecha_lanzamiento%TYPE);
    FUNCTION consultar_juego(p_cod IN juegos.cod%TYPE) RETURN tipo_cursor;
END crud_Juegos;
/
-- Cuerpo del paquete Crud_Juegos
CREATE OR REPLACE PACKAGE BODY crud_Juegos IS

    -- Alta de juegos
    PROCEDURE insertar_juego (p_nombre IN juegos.nombre%TYPE, p_desarrolladora 
    IN juegos.desarrolladora%TYPE, p_fecha_lanzamiento 
    IN juegos.fecha_lanzamiento%TYPE)
    IS
    BEGIN
        INSERT INTO juegos (cod, nombre, desarrolladora, fecha_lanzamiento)
        VALUES (seq_juegos.nextval, p_nombre, p_desarrolladora, 
        p_fecha_lanzamiento);
    EXCEPTION
        WHEN others THEN
        raise;
    END insertar_juego;
    
    -- Baja de juegos
    PROCEDURE borrar_juego (p_cod IN juegos.cod%TYPE)
    IS
    BEGIN
        DELETE FROM juegos
        WHERE cod = p_cod;
    EXCEPTION
        WHEN others THEN
        raise;
    END borrar_juego;
    
    -- Modificar juegos
    PROCEDURE modificar_juego(p_cod IN juegos.cod%TYPE, p_nombre IN 
    juegos.nombre%TYPE, p_desarrolladora IN juegos.desarrolladora%TYPE, 
    p_fecha_lanzamiento IN juegos.fecha_lanzamiento%TYPE) 
    IS
    BEGIN
        UPDATE juegos
        SET nombre = p_nombre,
            desarrolladora = p_desarrolladora,
            fecha_lanzamiento = p_fecha_lanzamiento
        WHERE cod = p_cod;
    EXCEPTION
        WHEN others THEN
        raise;
    END modificar_juego;

    -- Consultar juegos
    FUNCTION consultar_juego(p_cod IN juegos.cod%TYPE) 
    RETURN tipo_cursor
    IS
        v_cursor tipo_cursor;
    BEGIN
        OPEN v_cursor FOR
            SELECT cod, nombre, desarrolladora, fecha_lanzamiento
            FROM juegos
            WHERE cod = p_cod;
        RETURN v_cursor;
    EXCEPTION
        WHEN others THEN
        raise;
    END consultar_juego;
    
END crud_Juegos;
/
--------------------------------------------------------------------------------

-- Definir paquete Crud_Equipos
CREATE OR REPLACE PACKAGE crud_Equipos AS 
    TYPE tipo_cursor IS REF CURSOR;
    PROCEDURE insertar_equipo(p_nombre IN equipos.nombre%TYPE, p_fecha_fundacion
    IN equipos.fecha_fundacion%TYPE);
    PROCEDURE borrar_equipo(p_cod IN equipos.cod%TYPE);
    PROCEDURE modificar_equipo(p_cod IN equipos.cod%TYPE, p_nombre IN 
    equipos.nombre%TYPE, p_fecha_fundacion IN equipos.fecha_fundacion%TYPE);
    FUNCTION consultar_equipo(p_cod IN equipos.cod%TYPE) RETURN tipo_cursor;
END crud_Equipos;
/
-- Cuerpo del paquete Crud_Equipos
CREATE OR REPLACE PACKAGE BODY crud_Equipos IS

    -- Alta de equipos
    PROCEDURE insertar_equipo (p_nombre IN equipos.nombre%TYPE, 
    p_fecha_fundacion IN equipos.fecha_fundacion%TYPE)
    IS
    BEGIN
        INSERT INTO equipos (cod, nombre, fecha_fundacion)
        VALUES (seq_equipos.nextval, p_nombre, p_fecha_fundacion);
    EXCEPTION
        WHEN others THEN
        raise;
    END insertar_equipo;
    
    -- Baja de equipos
    PROCEDURE borrar_equipo (p_cod IN equipos.cod%TYPE)
    IS
    BEGIN
        DELETE FROM equipos
        WHERE cod = p_cod;
    EXCEPTION
        WHEN others THEN
        raise;
    END borrar_equipo;
    
    -- Modificar equipos
    PROCEDURE modificar_equipo(p_cod IN equipos.cod%TYPE, p_nombre IN 
    equipos.nombre%TYPE, p_fecha_fundacion IN equipos.fecha_fundacion%TYPE) 
    IS
    BEGIN
        UPDATE equipos
        SET nombre = p_nombre,
            fecha_fundacion = p_fecha_fundacion
        WHERE cod = p_cod;
    EXCEPTION
        WHEN others THEN
        raise;
    END modificar_equipo;

    -- Consultar equipos
    FUNCTION consultar_equipo(p_cod IN equipos.cod%TYPE) 
    RETURN tipo_cursor
    IS
        v_cursor tipo_cursor;
    BEGIN
        OPEN v_cursor FOR
            SELECT cod, nombre, fecha_fundacion
            FROM equipos
            WHERE cod = p_cod;
        RETURN v_cursor;
    EXCEPTION
        WHEN others THEN
        raise;
    END consultar_equipo;
    
END crud_Equipos;
/
--------------------------------------------------------------------------------

-- Definir paquete Crud_Patrocinadores
CREATE OR REPLACE PACKAGE crud_Patrocinadores AS 
    TYPE tipo_cursor IS REF CURSOR;
    PROCEDURE insertar_Patrocinadores(p_nombre IN patrocinadores.nombre%TYPE, 
    p_cod_equipo IN patrocinadores.cod_equipo%TYPE);
    PROCEDURE borrar_Patrocinadores(p_cod IN 
    patrocinadores.cod_patrocinador%TYPE);
    PROCEDURE modificar_Patrocinadores(p_cod IN 
    patrocinadores.cod_patrocinador%TYPE, p_nombre IN
    patrocinadores.nombre%TYPE);
    FUNCTION consultar_Patrocinadores(p_cod IN 
    patrocinadores.cod_patrocinador%TYPE) RETURN tipo_cursor;
END crud_Patrocinadores;
/
-- Cuerpo del paquete Crud_Patrocinadores
CREATE OR REPLACE PACKAGE BODY crud_Patrocinadores IS

    -- Alta de Patrocinadores
    PROCEDURE insertar_Patrocinadores (p_nombre IN patrocinadores.nombre%TYPE, 
    p_cod_equipo IN patrocinadores.cod_equipo%TYPE)
    IS
    BEGIN
        INSERT INTO Patrocinadores (nombre, cod_equipo)
        VALUES (p_nombre, p_cod_equipo);
    EXCEPTION
        WHEN others THEN
        raise;
    END insertar_Patrocinadores;
    
    -- Baja de Patrocinadores
    PROCEDURE borrar_Patrocinadores (p_cod IN 
    patrocinadores.cod_patrocinador%TYPE)
    IS
    BEGIN
        DELETE FROM Patrocinadores
        WHERE cod_patrocinador = p_cod;
    EXCEPTION
        WHEN others THEN
        raise;
    END borrar_Patrocinadores;
    
    -- Modificar Patrocinadores
    PROCEDURE modificar_Patrocinadores(p_cod IN 
    patrocinadores.cod_patrocinador%TYPE, 
    p_nombre IN patrocinadores.nombre%TYPE) 
    IS
    BEGIN
        UPDATE Patrocinadores
        SET nombre = p_nombre
        WHERE cod_patrocinador = p_cod;
    EXCEPTION
        WHEN others THEN
        raise;
    END modificar_Patrocinadores;

    -- Consultar Patrocinadores
    FUNCTION consultar_Patrocinadores(p_cod IN 
    patrocinadores.cod_patrocinador%TYPE) 
    RETURN tipo_cursor
    IS
        v_cursor tipo_cursor;
    BEGIN
        OPEN v_cursor FOR
            SELECT cod_patrocinador, nombre, cod_equipo
            FROM Patrocinadores
            WHERE cod_patrocinador = p_cod;
        RETURN v_cursor;
    EXCEPTION
        WHEN others THEN
        raise;
    END consultar_Patrocinadores;
    
END crud_Patrocinadores;
/
--------------------------------------------------------------------------------

-- Definir paquete Crud_Jugadores
CREATE OR REPLACE PACKAGE crud_Jugadores AS 
    TYPE tipo_cursor IS REF CURSOR;
    PROCEDURE insertar_Jugadores(p_nombre IN jugadores.nombre%TYPE, p_apellido 
    IN jugadores.apellido%TYPE, p_rol IN jugadores.rol%TYPE, p_salario IN 
    jugadores.salario%TYPE, p_nacionalidad IN jugadores.nacionalidad%TYPE, 
    p_fecha_nacimiento IN jugadores.fecha_nacimiento%TYPE, p_nickname IN 
    jugadores.nickname%TYPE, p_cod_equipo IN jugadores.cod_equipo%TYPE);
    PROCEDURE borrar_Jugadores(p_cod IN jugadores.cod%TYPE);
    PROCEDURE modificar_Jugadores(p_cod IN jugadores.cod%TYPE, p_nombre IN 
    jugadores.nombre%TYPE, p_apellido IN jugadores.apellido%TYPE, p_rol IN 
    jugadores.rol%TYPE, p_salario IN jugadores.salario%TYPE, p_nacionalidad IN 
    jugadores.nacionalidad%TYPE, p_fecha_nacimiento IN 
    jugadores.fecha_nacimiento%TYPE, p_nickname IN jugadores.nickname%TYPE);
    FUNCTION consultar_Jugadores(p_cod IN jugadores.cod%TYPE) 
    RETURN tipo_cursor;
END crud_Jugadores;
/
-- Cuerpo del paquete Crud_Jugadores
CREATE OR REPLACE PACKAGE BODY crud_Jugadores IS

    -- Alta de Jugadores
    PROCEDURE insertar_Jugadores (p_nombre IN jugadores.nombre%TYPE, p_apellido 
    IN jugadores.apellido%TYPE, p_rol IN jugadores.rol%TYPE, p_salario IN 
    jugadores.salario%TYPE, p_nacionalidad IN jugadores.nacionalidad%TYPE, 
    p_fecha_nacimiento IN jugadores.fecha_nacimiento%TYPE, p_nickname IN 
    jugadores.nickname%TYPE, p_cod_equipo IN jugadores.cod_equipo%TYPE)
    IS
    BEGIN
        INSERT INTO Jugadores (nombre, apellido, rol, salario, nacionalidad, 
        fecha_nacimiento, nickname, cod_equipo)
        VALUES (p_nombre, p_apellido, p_rol, p_salario, p_nacionalidad, 
        p_fecha_nacimiento, p_nickname, p_cod_equipo);
    EXCEPTION
        WHEN others THEN
        raise;
    END insertar_Jugadores;
    
    -- Baja de Jugadores
    PROCEDURE borrar_Jugadores (p_cod IN jugadores.cod%TYPE)
    IS
    BEGIN
        DELETE FROM Jugadores
        WHERE cod = p_cod;
    EXCEPTION
        WHEN others THEN
        raise;
    END borrar_Jugadores;
    
    -- Modificar Jugadores
    PROCEDURE modificar_Jugadores(p_cod IN jugadores.cod%TYPE, p_nombre IN 
    jugadores.nombre%TYPE, p_apellido IN jugadores.apellido%TYPE, p_rol IN 
    jugadores.rol%TYPE, p_salario IN jugadores.salario%TYPE, p_nacionalidad IN 
    jugadores.nacionalidad%TYPE, p_fecha_nacimiento IN 
    jugadores.fecha_nacimiento%TYPE, p_nickname IN jugadores.nickname%TYPE) 
    IS
    BEGIN
        UPDATE Jugadores
        SET nombre = p_nombre,
            apellido = p_apellido,
            rol = p_rol,
            salario = p_salario,
            nacionalidad = p_nacionalidad,
            fecha_nacimiento = p_fecha_nacimiento,
            nickname = p_nickname
        WHERE cod = p_cod;
    EXCEPTION
        WHEN others THEN
        raise;
    END modificar_Jugadores;

    -- Consultar Jugadores
    FUNCTION consultar_Jugadores(p_cod IN jugadores.cod%TYPE) 
    RETURN tipo_cursor
    IS
        v_cursor tipo_cursor;
    BEGIN
        OPEN v_cursor FOR
            SELECT *
            FROM jugadores
            WHERE cod = p_cod;
        RETURN v_cursor;
    EXCEPTION
        WHEN others THEN
        raise;
    END consultar_Jugadores;
    
END crud_Jugadores;
/
--------------------------------------------------------------------------------

-- Definir paquete Crud_Staffs
CREATE OR REPLACE PACKAGE crud_Staffs AS 
    TYPE tipo_cursor IS REF CURSOR;
    PROCEDURE insertar_Staffs(p_nombre IN Staffs.nombre%TYPE, p_apellido IN 
    Staffs.apellido%TYPE, p_puesto IN Staffs.puesto%TYPE, p_salario IN 
    Staffs.salario%TYPE, p_cod_equipo IN Staffs.cod_equipo%TYPE);
    PROCEDURE borrar_Staffs(p_cod IN Staffs.cod%TYPE);
    PROCEDURE modificar_Staffs(p_cod IN Staffs.cod%TYPE, p_nombre IN 
    Staffs.nombre%TYPE, p_apellido IN Staffs.apellido%TYPE, p_puesto IN 
    Staffs.puesto%TYPE, p_salario IN Staffs.salario%TYPE);
    FUNCTION consultar_Staffs(p_cod IN Staffs.cod%TYPE) RETURN tipo_cursor;
END crud_Staffs;
/
-- Cuerpo del paquete Crud_Staffs
CREATE OR REPLACE PACKAGE BODY crud_Staffs IS

    -- Alta de Staffs
    PROCEDURE insertar_Staffs (p_nombre IN Staffs.nombre%TYPE, p_apellido IN 
    Staffs.apellido%TYPE, p_puesto IN Staffs.puesto%TYPE, p_salario IN 
    Staffs.salario%TYPE,  p_cod_equipo IN Staffs.cod_equipo%TYPE)
    IS
    BEGIN
        INSERT INTO Staffs (nombre, apellido, puesto, salario, cod_equipo)
        VALUES (p_nombre, p_apellido, p_puesto, p_salario, p_cod_equipo);
    EXCEPTION
        WHEN others THEN
        raise;
    END insertar_Staffs;
    
    -- Baja de Staffs
    PROCEDURE borrar_Staffs (p_cod IN Staffs.cod%TYPE)
    IS
    BEGIN
        DELETE FROM Staffs
        WHERE cod = p_cod;
    EXCEPTION
        WHEN others THEN
        raise;
    END borrar_Staffs;
    
    -- Modificar Staffs
    PROCEDURE modificar_Staffs(p_cod IN Staffs.cod%TYPE, p_nombre IN 
    Staffs.nombre%TYPE, p_apellido IN Staffs.apellido%TYPE, p_puesto IN 
    Staffs.puesto%TYPE,  p_salario IN Staffs.salario%TYPE) 
    IS
    BEGIN
        UPDATE Staffs
        SET nombre = p_nombre,
            apellido = p_apellido,
            puesto = p_puesto,
            salario = p_salario
        WHERE cod = p_cod;
    EXCEPTION
        WHEN others THEN
        raise;
    END modificar_Staffs;

    -- Consultar Staffs
    FUNCTION consultar_Staffs(p_cod IN Staffs.cod%TYPE) 
    RETURN tipo_cursor
    IS
        v_cursor tipo_cursor;
    BEGIN
        OPEN v_cursor FOR
            SELECT *
            FROM Staffs
            WHERE cod = p_cod;
        RETURN v_cursor;
    EXCEPTION
        WHEN others THEN
        raise;
    END consultar_Staffs;
    
END crud_Staffs;
/
--------------------------------------------------------------------------------

-- Definir paquete Crud_Competiciones
CREATE OR REPLACE PACKAGE crud_Competiciones AS 
    TYPE tipo_cursor IS REF CURSOR;
    PROCEDURE insertar_Competiciones(p_nombre IN Competiciones.nombre%TYPE, 
    p_fecha_inicio IN Competiciones.fecha_inicio%TYPE, p_fecha_fin IN 
    Competiciones.fecha_fin%TYPE, p_estado_abierto IN 
    Competiciones.estado_abierto%TYPE, p_cod_juego IN 
    Competiciones.cod_juego%TYPE);
    PROCEDURE borrar_Competiciones(p_cod IN Competiciones.cod%TYPE);
    PROCEDURE modificar_Competiciones(p_cod IN Competiciones.cod%TYPE, p_nombre 
    IN Competiciones.nombre%TYPE, p_fecha_inicio IN 
    Competiciones.fecha_inicio%TYPE, p_fecha_fin IN 
    Competiciones.fecha_fin%TYPE, p_estado_abierto IN 
    Competiciones.estado_abierto%TYPE);
    FUNCTION consultar_Competiciones(p_cod IN Competiciones.cod%TYPE) 
    RETURN tipo_cursor;
END crud_Competiciones;
/
-- Cuerpo del paquete Crud_Competiciones
CREATE OR REPLACE PACKAGE BODY crud_Competiciones IS

    -- Alta de Competiciones
    PROCEDURE insertar_Competiciones (p_nombre IN Competiciones.nombre%TYPE, 
    p_fecha_inicio IN Competiciones.fecha_inicio%TYPE, p_fecha_fin IN 
    Competiciones.fecha_fin%TYPE, p_estado_abierto IN 
    Competiciones.estado_abierto%TYPE,  p_cod_juego IN 
    Competiciones.cod_juego%TYPE)
    IS
    BEGIN
        INSERT INTO Competiciones (cod, nombre, fecha_inicio, fecha_fin, 
        estado_abierto, cod_juego)
        VALUES (seq_competiciones.nextval, p_nombre, p_fecha_inicio,
        p_fecha_fin, p_estado_abierto, p_cod_juego);
    EXCEPTION
        WHEN others THEN
        raise;
    END insertar_Competiciones;
    
    -- Baja de Competiciones
    PROCEDURE borrar_Competiciones (p_cod IN Competiciones.cod%TYPE)
    IS
    BEGIN
        DELETE FROM Competiciones
        WHERE cod = p_cod;
    EXCEPTION
        WHEN others THEN
        raise;
    END borrar_Competiciones;
    
    -- Modificar Competiciones
    PROCEDURE modificar_Competiciones(p_cod IN Competiciones.cod%TYPE, p_nombre 
    IN Competiciones.nombre%TYPE, p_fecha_inicio IN 
    Competiciones.fecha_inicio%TYPE, p_fecha_fin IN 
    Competiciones.fecha_fin%TYPE, p_estado_abierto IN 
    Competiciones.estado_abierto%TYPE) 
    IS
    BEGIN
        UPDATE Competiciones
        SET nombre = p_nombre,
            fecha_inicio = p_fecha_inicio,
            fecha_fin = p_fecha_fin,
            estado_abierto = p_estado_abierto
        WHERE cod = p_cod;
    EXCEPTION
        WHEN others THEN
        raise;
    END modificar_Competiciones;

    -- Consultar Competiciones
    FUNCTION consultar_Competiciones(p_cod IN Competiciones.cod%TYPE) 
    RETURN tipo_cursor
    IS
        v_cursor tipo_cursor;
    BEGIN
        OPEN v_cursor FOR
            SELECT *
            FROM Competiciones
            WHERE cod = p_cod;
        RETURN v_cursor;
    EXCEPTION
        WHEN others THEN
        raise;
    END consultar_Competiciones;
    
END crud_Competiciones;
/
--------------------------------------------------------------------------------

-- Definir paquete Crud_Jornadas 
CREATE OR REPLACE PACKAGE crud_Jornadas AS 
    TYPE tipo_cursor IS REF CURSOR;
    PROCEDURE insertar_Jornadas(p_n_jornada IN Jornadas.n_jornada%TYPE, 
    p_fecha IN Jornadas.fecha%TYPE, p_cod_competicion IN
    Jornadas.cod_competicion%TYPE);
    PROCEDURE borrar_Jornadas(p_cod IN Jornadas.cod%TYPE);
    PROCEDURE modificar_Jornadas(p_cod IN Jornadas.cod%TYPE, p_n_jornada IN 
    Jornadas.n_jornada%TYPE, p_fecha IN Jornadas.fecha%TYPE);
    FUNCTION consultar_Jornadas(p_cod IN Jornadas.cod%TYPE) RETURN tipo_cursor;
END crud_Jornadas;
/
-- Cuerpo del paquete Crud_Jornadas
CREATE OR REPLACE PACKAGE BODY crud_Jornadas IS

    -- Alta de Jornadas
    PROCEDURE insertar_Jornadas (p_n_jornada IN Jornadas.n_jornada%TYPE, p_fecha
    IN Jornadas.fecha%TYPE, p_cod_competicion IN Jornadas.cod_competicion%TYPE)
    IS
    BEGIN
        INSERT INTO Jornadas (cod, n_jornada, fecha, cod_competicion)
        VALUES (seq_jornadas.nextval, p_n_jornada, p_fecha, p_cod_competicion);
    EXCEPTION
        WHEN others THEN
        raise;
    END insertar_Jornadas;
    
    -- Baja de Jornadas
    PROCEDURE borrar_Jornadas (p_cod IN Jornadas.cod%TYPE)
    IS
    BEGIN
        DELETE FROM Jornadas
        WHERE cod = p_cod;
    EXCEPTION
        WHEN others THEN
        raise;
    END borrar_Jornadas;
    
    -- Modificar Jornadas
    PROCEDURE modificar_Jornadas(p_cod IN Jornadas.cod%TYPE, p_n_jornada IN 
    Jornadas.n_jornada%TYPE, p_fecha IN Jornadas.fecha%TYPE) 
    IS
    BEGIN
        UPDATE Jornadas
        SET n_jornada = p_n_jornada,
            fecha = p_fecha
        WHERE cod = p_cod;
    EXCEPTION
        WHEN others THEN
        raise;
    END modificar_Jornadas;

    -- Consultar Jornadas
    FUNCTION consultar_Jornadas(p_cod IN Jornadas.cod%TYPE) 
    RETURN tipo_cursor
    IS
        v_cursor tipo_cursor;
    BEGIN
        OPEN v_cursor FOR
            SELECT *
            FROM Jornadas
            WHERE cod = p_cod;
        RETURN v_cursor;
    EXCEPTION
        WHEN others THEN
        raise;
    END consultar_Jornadas;
    
END crud_Jornadas;
/
--------------------------------------------------------------------------------

-- Definir paquete Crud_Enfrentamientos
CREATE OR REPLACE PACKAGE crud_Enfrentamientos AS 
    TYPE tipo_cursor IS REF CURSOR;
    PROCEDURE insertar_Enfrentamientos(p_hora IN Enfrentamientos.hora%TYPE, 
    p_gana_local IN Enfrentamientos.gana_local%TYPE, p_cod_jornada IN 
    Enfrentamientos.cod_jornada%TYPE, p_cod_equipo_visitante 
    Enfrentamientos.cod_equipo_visitante%TYPE, p_cod_equipo_local 
    Enfrentamientos.cod_equipo_local%TYPE);
    PROCEDURE borrar_Enfrentamientos(p_cod IN Enfrentamientos.cod%TYPE);
    PROCEDURE modificar_Enfrentamientos(p_cod IN Enfrentamientos.cod%TYPE, 
    p_hora IN Enfrentamientos.hora%TYPE, p_gana_local IN 
    Enfrentamientos.gana_local%TYPE);
    FUNCTION consultar_Enfrentamientos(p_cod IN Enfrentamientos.cod%TYPE) 
    RETURN tipo_cursor;
END crud_Enfrentamientos;
/
-- Cuerpo del paquete Crud_Enfrentamientos
CREATE OR REPLACE PACKAGE BODY crud_Enfrentamientos IS

    -- Alta de Enfrentamientos
    PROCEDURE insertar_Enfrentamientos (p_hora IN Enfrentamientos.hora%TYPE, 
    p_gana_local IN Enfrentamientos.gana_local%TYPE, p_cod_jornada IN 
    Enfrentamientos.cod_jornada%TYPE, p_cod_equipo_visitante 
    Enfrentamientos.cod_equipo_visitante%TYPE, p_cod_equipo_local 
    Enfrentamientos.cod_equipo_local%TYPE)
    IS
    BEGIN
        INSERT INTO Enfrentamientos (cod, hora, gana_local, cod_jornada, 
        cod_equipo_visitante, cod_equipo_local)
        VALUES (seq_enfrentamientos.nextval, p_hora, p_gana_local, 
        p_cod_jornada, p_cod_equipo_visitante, p_cod_equipo_local);
    EXCEPTION
        WHEN others THEN
        raise;
    END insertar_Enfrentamientos;
    
    -- Baja de Enfrentamientos
    PROCEDURE borrar_Enfrentamientos (p_cod IN Enfrentamientos.cod%TYPE)
    IS
    BEGIN
        DELETE FROM Enfrentamientos
        WHERE cod = p_cod;
    EXCEPTION
        WHEN others THEN
        raise;
    END borrar_Enfrentamientos;
    
    -- Modificar Enfrentamientos
    PROCEDURE modificar_Enfrentamientos(p_cod IN Enfrentamientos.cod%TYPE, 
    p_hora IN Enfrentamientos.hora%TYPE, p_gana_local IN 
    Enfrentamientos.gana_local%TYPE) 
    IS
    BEGIN
        UPDATE Enfrentamientos
        SET hora = p_hora,
            gana_local = p_gana_local
        WHERE cod = p_cod;
    EXCEPTION
        WHEN others THEN
        raise;
    END modificar_Enfrentamientos;

    -- Consultar Enfrentamientos
    FUNCTION consultar_Enfrentamientos(p_cod IN Enfrentamientos.cod%TYPE) 
    RETURN tipo_cursor
    IS
        v_cursor tipo_cursor;
    BEGIN
        OPEN v_cursor FOR
            SELECT *
            FROM Enfrentamientos
            WHERE cod = p_cod;
        RETURN v_cursor;
    EXCEPTION
        WHEN others THEN
        raise;
    END consultar_Enfrentamientos;
    
END crud_Enfrentamientos;
/
--------------------------------------------------------------------------------

-- Definir paquete Crud_Usuarios
CREATE OR REPLACE PACKAGE crud_Usuarios AS 
    TYPE tipo_cursor IS REF CURSOR;
    PROCEDURE insertar_Usuarios(p_nickname IN Usuarios.nickname%TYPE, 
    p_password IN Usuarios.password%TYPE, p_es_admin IN Usuarios.es_admin%TYPE);
    PROCEDURE borrar_Usuarios(p_cod_usuarios IN Usuarios.cod_usuario%TYPE);
    PROCEDURE modificar_Usuarios(p_cod_usuarios IN Usuarios.cod_usuario%TYPE, 
    p_nickname IN Usuarios.nickname%TYPE, p_password IN Usuarios.password%TYPE, 
    p_es_admin IN Usuarios.es_admin%TYPE);
    FUNCTION consultar_Usuarios(p_nickname IN Usuarios.nickname%TYPE) 
    RETURN tipo_cursor;
END crud_Usuarios;
/
-- Cuerpo del paquete Crud_Usuarios
CREATE OR REPLACE PACKAGE BODY crud_Usuarios IS

    -- Alta de Usuarios
    PROCEDURE insertar_Usuarios (p_nickname IN Usuarios.nickname%TYPE, 
    p_password IN Usuarios.password%TYPE, p_es_admin IN Usuarios.es_admin%TYPE)
    IS
    BEGIN
        INSERT INTO Usuarios (nickname, password, es_admin)
        VALUES (p_nickname, p_password, p_es_admin);
    EXCEPTION
        WHEN others THEN
        raise;
    END insertar_Usuarios;
    
    -- Baja de Usuarios
    PROCEDURE borrar_Usuarios (p_cod_usuarios IN Usuarios.cod_usuario%TYPE)
    IS
    BEGIN
        DELETE FROM Usuarios
        WHERE cod_usuario = p_cod_usuarios;
    EXCEPTION
        WHEN others THEN
        raise;
    END borrar_Usuarios;
    
    -- Modificar Usuarios
    PROCEDURE modificar_Usuarios(p_cod_usuarios IN Usuarios.cod_usuario%TYPE, 
    p_nickname IN Usuarios.nickname%TYPE, p_password IN Usuarios.password%TYPE, 
    p_es_admin IN Usuarios.es_admin%TYPE) 
    IS
    BEGIN
        UPDATE Usuarios
        SET nickname = p_nickname,
            password = p_password,
            es_admin = p_es_admin
        WHERE cod_usuario = p_cod_usuarios;
    EXCEPTION
        WHEN others THEN
        raise;
    END modificar_Usuarios;

    -- Consultar Usuarios
    FUNCTION consultar_Usuarios(p_nickname IN Usuarios.nickname%TYPE) 
    RETURN tipo_cursor
    IS
        v_cursor tipo_cursor;
    BEGIN
        OPEN v_cursor FOR
            SELECT *
            FROM Usuarios
            WHERE nickname = p_nickname;
        RETURN v_cursor;
    EXCEPTION
        WHEN others THEN
        raise;
    END consultar_Usuarios;
    
END crud_Usuarios;
/
--------------------------------------------------------------------------------

-- Definir paquete Crud_Puntos_equipos
CREATE OR REPLACE PACKAGE crud_Puntos_equipos AS 
    TYPE tipo_cursor IS REF CURSOR;
    PROCEDURE insertar_Puntos_equipos(p_cod_equipo IN 
    Puntos_equipos.cod_equipo%TYPE, p_cod_competicion IN 
    Puntos_equipos.cod_competicion%TYPE, p_puntos IN 
    Puntos_equipos.puntos%TYPE);
    PROCEDURE borrar_Puntos_equipos(p_cod_equipo IN 
    Puntos_equipos.cod_equipo%TYPE, p_cod_competicion IN 
    Puntos_equipos.cod_competicion%TYPE);
    PROCEDURE modificar_Puntos_equipos(p_cod_equipo IN 
    Puntos_equipos.cod_equipo%TYPE, p_cod_competicion IN 
    Puntos_equipos.cod_competicion%TYPE, p_puntos IN 
    Puntos_equipos.puntos%TYPE);
    FUNCTION consultar_Puntos_equipos(p_cod_equipo IN 
    Puntos_equipos.cod_equipo%TYPE, p_cod_competicion IN 
    Puntos_equipos.cod_competicion%TYPE) RETURN tipo_cursor;
END crud_Puntos_equipos;
/
-- Cuerpo del paquete Crud_Puntos_equipos
CREATE OR REPLACE PACKAGE BODY crud_Puntos_equipos IS

    -- Alta de Puntos_equipos
    PROCEDURE insertar_Puntos_equipos (p_cod_equipo IN 
    Puntos_equipos.cod_equipo%TYPE, p_cod_competicion IN 
    Puntos_equipos.cod_competicion%TYPE, p_puntos IN Puntos_equipos.puntos%TYPE)
    IS
    BEGIN
        INSERT INTO Puntos_equipos (cod_competicion, cod_equipo, puntos)
        VALUES (p_cod_competicion, p_cod_equipo, p_puntos);
    EXCEPTION
        WHEN others THEN
        raise;
    END insertar_Puntos_equipos;
    
    -- Baja de Puntos_equipos
    PROCEDURE borrar_Puntos_equipos (p_cod_equipo IN 
    Puntos_equipos.cod_equipo%TYPE, p_cod_competicion IN 
    Puntos_equipos.cod_competicion%TYPE)
    IS
    BEGIN
        DELETE FROM Puntos_equipos
        WHERE cod_equipo = p_cod_equipo AND cod_competicion = cod_competicion;
    EXCEPTION
        WHEN others THEN
        raise;
    END borrar_Puntos_equipos;
    
    -- Modificar Puntos_equipos
    PROCEDURE modificar_Puntos_equipos(p_cod_equipo IN 
    Puntos_equipos.cod_equipo%TYPE, p_cod_competicion IN 
    Puntos_equipos.cod_competicion%TYPE, p_puntos IN Puntos_equipos.puntos%TYPE) 
    IS
    BEGIN
        UPDATE Puntos_equipos
        SET puntos = p_puntos
    WHERE cod_equipo = p_cod_equipo AND cod_competicion = cod_competicion;
    EXCEPTION
        WHEN others THEN
        raise;
    END modificar_Puntos_equipos;

    -- Consultar Puntos_equipos
    FUNCTION consultar_Puntos_equipos(p_cod_equipo IN 
    Puntos_equipos.cod_equipo%TYPE, p_cod_competicion IN 
    Puntos_equipos.cod_competicion%TYPE) 
    RETURN tipo_cursor
    IS
        v_cursor tipo_cursor;
    BEGIN
        OPEN v_cursor FOR
            SELECT *
            FROM Puntos_equipos
          WHERE cod_equipo = p_cod_equipo AND cod_competicion = cod_competicion;
        RETURN v_cursor;
    EXCEPTION
        WHEN others THEN
        raise;
    END consultar_Puntos_equipos;
    
END crud_Puntos_equipos;
/