SET SERVEROUTPUT ON;
--**********Comprobar generar_calendario****************************************
--Este PA lo llamaremos desde JAVA en el momento que hagamos el primer inicio de la aplicación
DECLARE
BEGIN
    generar_calendario();
END;
/*
    RESULTADO
----------------

Competicion 1 Jornada 1:
  Enfrentamiento: 3 vs 4
  Enfrentamiento: 1 vs 2
Competicion 1 Jornada 2:
  Enfrentamiento: 3 vs 2
  Enfrentamiento: 4 vs 1
Competicion 1 Jornada 3:
  Enfrentamiento: 3 vs 1
  Enfrentamiento: 2 vs 4
Competicion 2 Jornada 1:
  Enfrentamiento: 7 vs 5
  Enfrentamiento: 6 vs 8
Competicion 2 Jornada 2:
  Enfrentamiento: 7 vs 8
  Enfrentamiento: 5 vs 6
Competicion 2 Jornada 3:
  Enfrentamiento: 7 vs 6
  Enfrentamiento: 8 vs 5
Competicion 3 Jornada 1:
  Enfrentamiento: 9 vs 12
  Enfrentamiento: 10 vs 11
Competicion 3 Jornada 2:
  Enfrentamiento: 9 vs 11
  Enfrentamiento: 12 vs 10
Competicion 3 Jornada 3:
  Enfrentamiento: 9 vs 10
  Enfrentamiento: 11 vs 12
Competicion 4 Jornada 1:
  Enfrentamiento: 14 vs 16
  Enfrentamiento: 15 vs 13
Competicion 4 Jornada 2:
  Enfrentamiento: 14 vs 13
  Enfrentamiento: 16 vs 15
Competicion 4 Jornada 3:
  Enfrentamiento: 14 vs 15
  Enfrentamiento: 13 vs 16
*/


--********COMPROBAR abrir_cerrar_competicion************************************
--Este procedimiento lo llamaremos desde java para abrir y cerrar las competiciones
/
DECLARE
    v_cod_competicion NUMBER := 1;
BEGIN
    abrir_cerrar_competicion(v_cod_competicion);
END;
/

--********COMPROBACIONES PAQUERTE CRUD*************************************
--Comprobacion de crud_Juegos

-- Consultar un juego
CREATE OR REPLACE PROCEDURE pedir_juegos (p_cod IN Juegos.cod%TYPE) AS
    v_cursor crud_Juegos.tipo_cursor;
    v_juego juegos%ROWTYPE;
BEGIN
    v_cursor := crud_Juegos.consultar_juego_cod(p_cod);
    LOOP
        FETCH v_cursor INTO v_juego;
        EXIT WHEN v_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Código: ' || v_juego.cod || ', Nombre: ' || 
        v_juego.nombre || ', Desarrolladora: ' || v_juego.desarrolladora || 
        ', Fecha de lanzamiento: ' || 
        TO_CHAR(v_juego.fecha_lanzamiento, 'DD/MM/YYYY'));
    END LOOP;
    CLOSE v_cursor;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Este juego no existe');
END pedir_juegos;
/

DECLARE
    v_cod juegos.cod%TYPE;
BEGIN
    -- Insertar un juego
    crud_Juegos.insertar_juego('SuperMario', 'Nintendo', 
    TO_DATE('13/09/1985', 'DD/MM/YYYY'));
    SELECT cod INTO v_cod FROM juegos WHERE UPPER(nombre) = 'SUPERMARIO';
    pedir_juegos(v_cod);

    -- Modificar un juego
    crud_Juegos.modificar_juego(v_cod, 'Sonic', 'Nintendo', 
    TO_DATE('01/01/2022', 'DD/MM/YYYY'));
    pedir_juegos(v_cod);
    
    -- Borrar un juego
    crud_Juegos.borrar_juego(v_cod);
END;
/

--------------------------------------------------------------------------------

-- Comprobacion de crud_Equipos

-- Consultar un Equipo
CREATE OR REPLACE PROCEDURE pedir_equipos (p_cod IN Equipos.cod%TYPE) AS
    v_cursor crud_Equipos.tipo_cursor;
    v_equipo equipos%ROWTYPE;
BEGIN
    v_cursor := crud_equipos.consultar_equipo_cod(p_cod);
    LOOP
        FETCH v_cursor INTO v_equipo;
        EXIT WHEN v_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Nombre: ' || v_equipo.nombre || ', 
        Fecha de fundación: ' || TO_CHAR(v_equipo.fecha_fundacion, 
        'DD/MM/YYYY'));
    END LOOP;
    CLOSE v_cursor;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Este equipo no existe');
END pedir_equipos;
/

DECLARE
    v_cod equipos.cod%TYPE;
BEGIN
    -- Insertar un equipo
    crud_Equipos.insertar_equipo('Vodafone Giants', TO_DATE('31/08/2017', 
    'DD/MM/YYYY'));
    SELECT cod INTO v_cod FROM equipos WHERE UPPER(nombre) = 'VODAFONE GIANTS';
    pedir_equipos(v_cod);
    
    -- Modificar un equipo
    crud_Equipos.modificar_equipo(v_cod, 'Movistar Riders', 
    TO_DATE('31/08/2017', 'DD/MM/YYYY'));
    pedir_equipos(v_cod);
    
    -- Borrar un equipo
    crud_Equipos.borrar_equipo(v_cod);
END;
/

--------------------------------------------------------------------------------

-- Comprobacion de crud_Patrocinadores

-- Consultar un Patrocinador
CREATE OR REPLACE PROCEDURE pedir_patrocinadores (p_cod IN 
patrocinadores.cod_patrocinador%TYPE) AS
    v_cursor crud_patrocinadores.tipo_cursor;
    v_patrocinador patrocinadores%ROWTYPE;
BEGIN
    v_cursor := crud_patrocinadores.consultar_patrocinadores_cod(p_cod);
    LOOP
        FETCH v_cursor INTO v_patrocinador;
        EXIT WHEN v_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Nombre: ' || v_patrocinador.nombre);
    END LOOP;
    CLOSE v_cursor;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Este patrocinador no existe');
END pedir_patrocinadores;
/

DECLARE
    v_cod patrocinadores.cod_patrocinador%TYPE;
BEGIN
    -- Insertar un Patrocinadores
    crud_Patrocinadores.insertar_Patrocinadores('Netflix', 01);
    SELECT cod_patrocinador INTO v_cod FROM patrocinadores WHERE UPPER(nombre)
    = 'NETFLIX';
    pedir_patrocinadores(v_cod);
    
    -- Modificar un Patrocinadores
    crud_Patrocinadores.modificar_Patrocinadores(v_cod, 'HBO');
    pedir_patrocinadores(v_cod);

    -- Borrar un Patrocinadores
    crud_Patrocinadores.borrar_Patrocinadores(v_cod);
END;
/

--------------------------------------------------------------------------------

-- Comprobación de crud_Jugadores

-- Consultar un Jugador
CREATE OR REPLACE PROCEDURE pedir_jugadores (p_cod IN jugadores.cod%TYPE) AS
    v_cursor crud_jugadores.tipo_cursor;
    v_jugador jugadores%ROWTYPE;
BEGIN
    v_cursor := crud_jugadores.consultar_jugadores_cod(p_cod);
    LOOP
        FETCH v_cursor INTO v_jugador;
        EXIT WHEN v_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Nombre: ' || v_jugador.nombre || ', Apellido: ' 
        || v_jugador.apellido || ', Rol: ' || v_jugador.rol || ', Salario: ' || 
        v_jugador.salario || ', Nacionalidad: ' || v_jugador.nacionalidad || ', 
        Nickname: ' || v_jugador.nickname || ', Fecha de nacimiento: ' || 
        TO_CHAR(v_jugador.fecha_nacimiento, 'DD/MM/YYYY'));
    END LOOP;
    CLOSE v_cursor;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Este jugador no existe');
END pedir_jugadores;
/

DECLARE
    v_cod JUGADORES.cod%TYPE;
BEGIN
    -- Insertar un Jugadores
    crud_Jugadores.insertar_Jugadores('Manolo', 'Vargas', 'Delantero', 1400, 
    'Hungria', TO_DATE('31/08/1995', 'DD/MM/YYYY'), 'L0leTe', 2);
    SELECT cod INTO v_cod FROM jugadores WHERE UPPER(nombre) = 'MANOLO' AND 
    UPPER(apellido) = 'VARGAS';   
    pedir_jugadores(v_cod);
    
    -- Modificar un Jugadores
    crud_Jugadores.modificar_Jugadores(v_cod, 'Manolo', 'Barajas', 'Delantero', 
    1400, 'Hungria', TO_DATE('31/08/1995', 'DD/MM/YYYY'), 'L0leTe');
    pedir_jugadores(v_cod);

    -- Borrar un Jugadores
    crud_Jugadores.borrar_Jugadores(v_cod);
END;
/

--------------------------------------------------------------------------------

-- Comprobación de crud_Staffs

-- Consultar un Staff
CREATE OR REPLACE PROCEDURE pedir_staffs (p_cod IN staffs.cod%TYPE) AS
    v_cursor crud_staffs.tipo_cursor;
    v_staff staffs%ROWTYPE;
BEGIN
    v_cursor := crud_staffs.consultar_staffs_cod(p_cod);
    LOOP
        FETCH v_cursor INTO v_staff;
        EXIT WHEN v_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Nombre: ' || v_staff.nombre || ', Apellido: ' || 
        v_staff.apellido || ', Puesto: ' || v_staff.puesto || ', Salario: ' || 
        v_staff.salario);
    END LOOP;
    CLOSE v_cursor;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Este miembro del staff no existe');
END pedir_staffs;
/

DECLARE
    v_cod staffs.cod%TYPE;
BEGIN
    -- Insertar un Staffs
    crud_Staffs.insertar_Staffs(25,'Manolo', 'Vargas', 'Entrenador asistente', 
    1400, 01);
    SELECT cod INTO v_cod FROM staffs WHERE UPPER(nombre) = 'MANOLO' AND 
    UPPER(apellido) = 'VARGAS';   
    pedir_staffs(v_cod);
    
    -- Modificar un Staffs
    crud_Staffs.modificar_Staffs(v_cod, 'Manolo', 'Barajas', 
    'Entrenador asistente', 1400);
    pedir_staffs(v_cod);

    -- Borrar un Staffs
    crud_Staffs.borrar_Staffs(v_cod);
END;
/

--------------------------------------------------------------------------------

-- Comprobación de crud_Competiciones

-- Consultar un Competición
CREATE OR REPLACE PROCEDURE pedir_competiciones 
(p_cod IN competiciones.cod%TYPE) AS
    v_cursor crud_competiciones.tipo_cursor;
    v_competicion competiciones%ROWTYPE;
    v_estado VARCHAR2(15);
BEGIN
    v_cursor := crud_competiciones.consultar_competiciones(p_cod);
    LOOP
        FETCH v_cursor INTO v_competicion;
        EXIT WHEN v_cursor%NOTFOUND;
    
        IF (v_competicion.estado_abierto = 1) THEN
            v_estado := 'Abierto';
        ELSE 
            v_estado := 'Cerrado';
        END IF;
        
        DBMS_OUTPUT.PUT_LINE('Nombre: ' || v_competicion.nombre || ', 
        Fecha de Inicio: ' || TO_CHAR(v_competicion.fecha_inicio, 'DD/MM/YYYY') 
        || ', Fecha de Fin: ' || TO_CHAR(v_competicion.fecha_fin, 'DD/MM/YYYY') 
        || ', Estado: ' || v_estado);
    END LOOP;

    CLOSE v_cursor;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Esta competiciÃ³n no existe');
END pedir_competiciones;
/

DECLARE
    v_cod competiciones.cod%TYPE;
BEGIN
    -- Insertar un Competiciones
    crud_Competiciones.insertar_Competiciones('Liga', TO_DATE('31/08/2024', 
    'DD/MM/YYYY'), TO_DATE('31/12/2024', 'DD/MM/YYYY'), 0, 1);
    SELECT cod INTO v_cod FROM competiciones WHERE UPPER(nombre) = 'LIGA';
    pedir_competiciones(v_cod);
    
    -- Modificar un Competiciones
    crud_Competiciones.modificar_Competiciones(v_cod, 'Liga', 
    TO_DATE('31/07/2024', 'DD/MM/YYYY'), TO_DATE('30/12/2024', 'DD/MM/YYYY'), 
    0);
    pedir_competiciones(v_cod);

    -- Borrar un Competiciones
    crud_Competiciones.borrar_Competiciones(v_cod);
END;
/

--------------------------------------------------------------------------------

-- Comprobación de crud_Jornadas

-- Consultar un Jornada
CREATE OR REPLACE PROCEDURE pedir_jornadas (p_cod IN jornadas.cod%TYPE) AS
    v_cursor crud_jornadas.tipo_cursor;
    v_jornada jornadas%ROWTYPE;
BEGIN
    v_cursor := crud_jornadas.consultar_jornadas(p_cod);
    LOOP
        FETCH v_cursor INTO v_jornada;
        EXIT WHEN v_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Número de Jornada: ' || v_jornada.n_jornada || ', 
        Fecha: ' || TO_CHAR(v_jornada.fecha, 'DD/MM/YYYY'));
    END LOOP;

    CLOSE v_cursor;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Esta jornada no existe');
END pedir_jornadas;
/

DECLARE
    v_cod jornadas.cod%TYPE;
BEGIN
    -- Insertar un Jornadas
    crud_Jornadas.insertar_Jornadas('1',  TO_DATE('31/07/2024', 'DD/MM/YYYY'), 
    01);
    SELECT cod INTO v_cod FROM jornadas WHERE fecha = TO_DATE('31/07/2024', 
    'DD/MM/YYYY');
    pedir_jornadas(v_cod);
    
    -- Modificar un Jornadas
    crud_Jornadas.modificar_Jornadas(v_cod, '1',  TO_DATE('31/12/2024', 
    'DD/MM/YYYY'));
    pedir_jornadas(v_cod);

    -- Borrar un Jornadas
    crud_Jornadas.borrar_Jornadas(v_cod);
END;
/

--------------------------------------------------------------------------------

-- Comprobación de crud_Enfrentamientos

-- Consultar un Enfrentamientos
CREATE OR REPLACE PROCEDURE pedir_enfrentamientos 
(p_cod IN enfrentamientos.cod%TYPE) AS
    v_cursor crud_enfrentamientos.tipo_cursor;
    v_enfrentamiento enfrentamientos%ROWTYPE;
    v_ganador VARCHAR2(15);
BEGIN
    v_cursor := crud_enfrentamientos.consultar_enfrentamientos(p_cod);
    LOOP
        FETCH v_cursor INTO v_enfrentamiento;
        EXIT WHEN v_cursor%NOTFOUND;
    
        IF (v_enfrentamiento.gana_local = 1) THEN
            v_ganador := 'Local';
        ELSE 
            v_ganador := 'Visitante';
        END IF;
        
        DBMS_OUTPUT.PUT_LINE('Hora: ' || TO_CHAR(v_enfrentamiento.hora, 
        'HH24:MI') || ', Ganador: ' || v_ganador);
    END LOOP;

    CLOSE v_cursor;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Este enfrentamiento no existe');
END pedir_enfrentamientos;
/

DECLARE
    v_cod enfrentamientos.cod%TYPE;
BEGIN
    --Insertar un Enfrentamientos
    DBMS_OUTPUT.PUT_LINE(seq_jornadas.CURRVAL);
    crud_Enfrentamientos.insertar_Enfrentamientos(TO_DATE('19:00','HH24:MI'), 
    01,  seq_jornadas.CURRVAL, 01, 02);
    SELECT cod INTO v_cod FROM ENFRENTAMIENTOS WHERE gana_local = 1;
    pedir_enfrentamientos(v_cod);
    
    -- Modificar un Enfrentamientos
    crud_Enfrentamientos.modificar_Enfrentamientos(v_cod, TO_DATE('19:00',
    'HH24:MI'), 00);
    pedir_enfrentamientos(v_cod);

    -- Borrar un Enfrentamientos
    crud_Enfrentamientos.borrar_Enfrentamientos(v_cod);
END;
/

--------------------------------------------------------------------------------

-- Comprobación de crud_Usuarios

-- Consultar un Usuarios
CREATE OR REPLACE PROCEDURE pedir_usuarios (p_cod IN usuarios.cod_usuario%TYPE) 
AS
    v_cursor crud_usuarios.tipo_cursor;
    v_usuario usuarios%ROWTYPE;
    v_tipo VARCHAR2(15);
BEGIN
    v_cursor := crud_usuarios.consultar_usuarios(p_cod);
    LOOP
        FETCH v_cursor INTO v_usuario;
        EXIT WHEN v_cursor%NOTFOUND;
    
        IF (v_usuario.es_admin = 1) THEN
            v_tipo := 'Admin';
        ELSE 
            v_tipo := 'Estandar';
        END IF;
        
        DBMS_OUTPUT.PUT_LINE('Nickname: ' || v_usuario.nickname || ', 
        Contraseña: ' || v_usuario.password || ', Permisos: ' || v_tipo);
    END LOOP;

    CLOSE v_cursor;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Este usuario no existe');
END pedir_usuarios;
/

DECLARE
    v_cod usuarios.cod_usuario%TYPE;
BEGIN
    -- Insertar un Usuarios
    crud_Usuarios.insertar_Usuarios('ManoloElFiera', 'Nolete', 01);
    SELECT cod_usuario INTO v_cod FROM usuarios WHERE UPPER(nickname) = 
    'MANOLOELFIERA';
    pedir_usuarios(v_cod);
    
    -- Modificar un Usuarios
    crud_Usuarios.modificar_Usuarios(v_cod, 'ManoloElFiera', 'Nolete', 00);
    pedir_usuarios(v_cod);

    -- Borrar un Usuarios
    crud_Usuarios.borrar_Usuarios(v_cod);
END;
/

--------------------------------------------------------------------------------
-- Comprobación de crud_Puntos_Equipos

-- Consultar un Puntos_Equipos
CREATE OR REPLACE PROCEDURE pedir_puntos_equipos (p_cod_competicion IN 
puntos_equipos.cod_competicion%TYPE, p_cod_equipo IN 
puntos_equipos.cod_equipo%TYPE) AS
    v_cursor crud_puntos_equipos.tipo_cursor;
    v_punto puntos_equipos%ROWTYPE;
BEGIN
    v_cursor := crud_puntos_equipos.consultar_puntos_equipos(p_cod_equipo, 
    p_cod_competicion);
    LOOP
        FETCH v_cursor INTO v_punto;
        EXIT WHEN v_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Puntos: ' || v_punto.puntos);
    END LOOP;

    CLOSE v_cursor;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error al seleccionar este equipo en esta 
        competición');
END pedir_puntos_equipos;
/

DECLARE
    v_cod_competicion puntos_equipos.cod_competicion%TYPE := 1;
    v_cod_equipo puntos_equipos.cod_equipo%TYPE := 10;
BEGIN
    -- Insertar un Puntos_equipos
    crud_Puntos_equipos.insertar_Puntos_equipos( v_cod_equipo, 
    v_cod_competicion, 10);
    SELECT cod_competicion, cod_equipo INTO v_cod_competicion, v_cod_equipo FROM
    puntos_equipos WHERE cod_competicion = v_cod_competicion AND cod_equipo = 
    v_cod_equipo;
    pedir_puntos_equipos(v_cod_competicion, v_cod_equipo);

    -- Modificar un Puntos_equipos
    crud_Puntos_equipos.modificar_Puntos_equipos(v_cod_equipo, 
    v_cod_competicion, 5);
    pedir_puntos_equipos(v_cod_competicion, v_cod_equipo);

    -- Borrar un Puntos_equipos
    crud_Puntos_equipos.borrar_Puntos_equipos(v_cod_equipo, v_cod_competicion);
END;