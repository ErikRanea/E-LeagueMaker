--SCRIPT PARA GENERAR LOS PROCEDIMIENTOS ALMACENADOS
--***************** PA Resetear secuencias de jornadas y enfrentamientos********
SET SERVEROUTPUT ON;
CREATE OR REPLACE PROCEDURE reset_seq_jornadas_enfrentamientos
AS
BEGIN
        -- Eliminar las secuencias existentes
    EXECUTE IMMEDIATE 'DROP SEQUENCE seq_enfrentamientos';
    EXECUTE IMMEDIATE 'DROP SEQUENCE seq_jornadas';
    
    -- Crear de nuevo la secuencia seq_jornadas
    EXECUTE IMMEDIATE '
        CREATE SEQUENCE seq_jornadas
        START WITH 1
        INCREMENT BY 1
        NOCACHE
        NOCYCLE';
        
    -- Crear de nuevo la secuencia seq_enfrentamientos
    EXECUTE IMMEDIATE '
        CREATE SEQUENCE seq_enfrentamientos
        START WITH 1
        INCREMENT BY 1
        NOCACHE
        NOCYCLE';

END;
/

--****************reset secuencias enfren jor  FINAL****************************

--***************** PA Generar Calendario **************************************
CREATE OR REPLACE PROCEDURE generar_calendario  AS
    --Competicion
    CURSOR c_cod_competi IS
        SELECT p.cod_competicion FROM PUNTOS_EQUIPOS p
        JOIN COMPETICIONES c
        ON p.cod_competicion = c.cod
        WHERE c.estado_abierto = 0;
    --Hacemos un where que solo generé de las competiciones cerradas
    v_cod_competicion PUNTOS_EQUIPOS.cod_competicion%type;


    -- Declaraci�n de tipos y variables
    TYPE EquipoArray IS VARRAY(100) OF NUMBER;
    l_equipos EquipoArray; -- Variable para almacenar los equipos
    v_num_equipos  NUMBER; --calcular total equipos
    v_num_jornadas NUMBER; --calcular jornadas
    
    v_jornada_actual NUMBER; -- N�mero de la jornada actual
    v_cod_jornada JORNADAS.cod%TYPE;
    fecha_jornada DATE;
    v_fecha_inicio COMPETICIONES.fecha_inicio%TYPE;
    
    v_cod_enfrentamiento ENFRENTAMIENTOS.cod%type;
    
    equipo_local ENFRENTAMIENTOS.cod_equipo_local%TYPE;
    equipo_visitante ENFRENTAMIENTOS.cod_equipo_visitante%TYPE;
    
    temp PUNTOS_EQUIPOS.cod_equipo%type; 
    --Variable temporal para ayudar en la rotacion
    r NUMBER; --Variable para randomizar 
    
    viejo_cod_competi NUMBER;
    
    todas_jornadas_hechas NUMBER := 0;
BEGIN

    RESET_SEQ_JORNADAS_ENFRENTAMIENTOS;
    DELETE ENFRENTAMIENTOS;
    DELETE JORNADAS;
    

        
    
    OPEN c_cod_competi;
   ---ABRIR CURSOR COMPETICIONES 
   /*Creamos un cursor para poder tener el código de todas las copeticiones
     dadas de alta en PUNTOS_EQUIPOS*/
    LOOP 
--*******************COMIENZO DE BUCLE DE COD_COMPETICION***********************    
        FETCH c_cod_competi INTO v_cod_competicion; --Recorremos y asignamos 
    
        EXIT WHEN c_cod_competi%NOTFOUND;
        
        
        UPDATE PUNTOS_EQUIPOS 
        SET 
        puntos = 0
        WHERE cod_competicion = v_cod_competicion;
        
        
          --Conseguir Fecha de Inicio;
        SELECT fecha_inicio INTO v_fecha_inicio
        FROM COMPETICIONES
        WHERE cod = v_cod_competicion;
        
        fecha_jornada := v_fecha_inicio;
        
        -- Add los equipos a la lista
        SELECT Cod_Equipo
        BULK COLLECT INTO l_equipos
        FROM Puntos_Equipos
        WHERE Cod_Competicion = v_cod_competicion;
        
        
        --Mezclo los equipos
        FOR i IN REVERSE 2..l_equipos.COUNT LOOP
            -- Generar un índice aleatorio entre 1 e i
            r := DBMS_RANDOM.VALUE(1, i);
            
     -- Intercambiar el elemento actual con el elemento en la posición aleatoria
            temp := l_equipos(i);
            l_equipos(i) := l_equipos(r);
            l_equipos(r) := temp;
            
        END LOOP;
    
    
    
        --Contar numero de equipos
        v_num_equipos := l_equipos.COUNT;
        --Calcular n�mero de jornadas
        v_num_jornadas := v_num_equipos - 1;
        
        /*Con el numero de jornadas y enfrentamientos
        crearemos sus respetivos bucles para que hagan la cantidad
        correspondiente a cada una de las competiciones*/
--*******************Comienzo bucle de jornadas*********************************              
        FOR v_jornada_actual IN 1..v_num_jornadas LOOP
             

    
            /*Al repetirse el cod_competicion debido a que se repite en funcion
            de la cantidad de equipos que haya en Puntos_Equipos. 
            4 Equipos = 4 veces cod_competicion correspondiente
            
            Por lo tanto creamos la variable todas_jornadas_hechas para 
            determinar que si ya ha creado todas las jornadas correspondientes
            a esa competicion no entre pueda crear más jornadas*/
            IF viejo_cod_competi != v_cod_competicion THEN
                todas_jornadas_hechas := 0;
            END IF;
             
            /*Al poner una condicional para hacer la creacion de las jornadas
            y de los enfrentamientos nos evitamos que se repitan sin parar
            las mismas jornadas x la cantidad de equipos que hay en una 
            competicion*/
             
             if todas_jornadas_hechas = 0 THEN
                        viejo_cod_competi := v_cod_competicion;
                         -- Imprimir la jornada actual
                         DBMS_OUTPUT.PUT_LINE('Competicion '|| v_cod_competicion 
                         ||' Jornada ' || v_jornada_actual || ':'); 
                         

                    -- Insertar la jornada en la tabla Jornadas
                    v_cod_jornada := seq_jornadas.NEXTVAL; 
                    /*Al meter el cod jornada en una variable hago que no 
                    salte el nextval hasta que lo vuelva a declarar y asi puedo 
                    saber cual es el codigo de la jornada*/
                    
                    
                             -- Calcular la hora del enfrentamiento
                    IF v_jornada_actual = 1 THEN
                        -- El primer enfrentamiento siempre es a las 19:00
                        fecha_jornada := 
                        TRUNC(fecha_jornada) + INTERVAL '19' HOUR;
                    ELSE
            -- A partir del segundo enfrentamiento de la jornada, aniadir 1 hora
                        fecha_jornada := fecha_jornada + INTERVAL '1' HOUR;
                    END IF;
                    
                    
                    
                    
                    INSERT INTO Jornadas (Cod, N_Jornada, Fecha, 
                    Cod_Competicion)
                    VALUES (v_cod_jornada, v_jornada_actual, fecha_jornada,
                    v_cod_competicion);

                    
                    
                    
--*******************Bucle para crear Enfrentamientos***************************       
                     FOR i IN 1..v_num_equipos/2 LOOP
                        equipo_local := i;
                        equipo_visitante := v_num_equipos -i + 1;
            
            
                        INSERT INTO Enfrentamientos (Cod, Hora, Cod_Jornada, 
                        cod_equipo_visitante,cod_equipo_local)
                        VALUES (seq_enfrentamientos.NEXTVAL , fecha_jornada, 
                        v_cod_jornada,l_equipos(equipo_visitante),
                        l_equipos(equipo_local));
                        
                        DBMS_OUTPUT.PUT_LINE('  Enfrentamiento: ' ||
                        l_equipos(equipo_local) || ' vs ' || 
                        l_equipos(equipo_visitante));
                     END LOOP; 
--*******************FIN DEL BUCLE DE ENFRENTAMIENTOS***************************


                     -- Guardar el ultimo equipo en la variable temporal
                    temp := l_equipos(v_num_equipos);
                    -- Rotar los equipos hacia arriba en la lista
                    FOR i IN REVERSE 2..v_num_equipos LOOP
                      l_equipos(i) := l_equipos(i-1);
                    END LOOP;
                
             -- Colocar el ultimo equipo en la segunda posicion para la rotacion
                    l_equipos(2) := temp;
            
            --Saltar de semana para la siguiente jornada 
                     fecha_jornada := fecha_jornada + INTERVAL '7' DAY;
                
                if v_jornada_actual = l_equipos.COUNT -1 THEN
                    todas_jornadas_hechas := 1;
                END IF;
             END IF;
             

        END LOOP;
--*******************FIN BUCLE JORNADAS*****************************************
    END LOOP;
--*******************FIN BUCLE COD_COMPETICIONES********************************


EXCEPTION
    WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Error: '|| SQLERRM);
END;

--****************************Fin PA Generar calendario*************************



--
/







--***************PA_abrir_cerrar_competicion************************************
CREATE OR REPLACE PROCEDURE abrir_cerrar_competicion(
    p_cod_competicion IN COMPETICIONES.cod%TYPE)
AS
    v_estado_actual COMPETICIONES.ESTADO_ABIERTO%TYPE;
BEGIN

--Conseguimos el estado actual de la competicion
    SELECT estado_abierto INTO v_estado_actual
    FROM COMPETICIONES
    WHERE cod = p_cod_competicion;

--Si esta cerrado abrelo
    IF v_estado_actual = 0 THEN 
        UPDATE COMPETICIONES
        SET estado_abierto = 1
        WHERE cod = p_cod_competicion;
        
        DBMS_OUTPUT.put_line('competicion '|| p_cod_competicion || ' abierta');
        
    END IF;
--Si esta abierto cierralo
    IF v_estado_actual = 1 THEN
        UPDATE COMPETICIONES
        SET estado_abierto = 0
        WHERE cod = p_cod_competicion;    
        DBMS_OUTPUT.put_line('competicion '|| p_cod_competicion || ' cerrada');
        
    END IF;
    
END abrir_cerrar_competicion;
--***********************Fin abrir cerrar competiciones*************************
/
--******************************************************************************
CREATE OR REPLACE PROCEDURE CONSULTAR_ENFRENTAMIENTOS_SIN_RESULTADOS
(
    p_cod_jornada IN JORNADAS.cod%TYPE,
    c_enfrentamientos OUT SYS_REFCURSOR
)
AS

BEGIN
     OPEN c_enfrentamientos FOR
        SELECT *
        FROM ENFRENTAMIENTOS 
        WHERE gana_local IS NULL
        AND cod_jornada = p_cod_jornada;
END;
/
--******************************************************************************

CREATE OR REPLACE PROCEDURE insertar_resultado
(
    p_cod_enfrentamiento IN ENFRENTAMIENTOS.cod%TYPE,
    p_v_gana_local IN ENFRENTAMIENTOS.gana_local%type
)
AS

BEGIN
    UPDATE ENFRENTAMIENTOS
    SET
        gana_local = p_v_gana_local
    WHERE cod = p_cod_enfrentamiento;
END;
--******************************************************************************
/
--******************************************************************************
CREATE OR REPLACE PROCEDURE consultar_ultima_jornada
(
    c_ultima_jornada OUT SYS_REFCURSOR
)
AS

BEGIN
     OPEN c_ultima_jornada FOR
      SELECT 
        cod,
        n_jornada,
        fecha,
        cod_competicion
    FROM 
        (SELECT 
            cod,
            n_jornada,
            fecha,
            cod_competicion
        FROM 
            jornadas
        ORDER BY n_jornada DESC)
    WHERE ROWNUM = 1;
END;

/
--******************************************************************************

CREATE OR REPLACE PROCEDURE con_enfre_ulti_jornada (
    c_ultima_jornada OUT SYS_REFCURSOR
) AS
    v_cod_jornada jornadas.cod%TYPE;
BEGIN
    SELECT cod
    INTO v_cod_jornada
    FROM jornadas
    WHERE ROWNUM = 1
    ORDER BY n_jornada DESC;

    OPEN c_ultima_jornada FOR
    SELECT 
        cod,
        cod_equipo_local,
        cod_equipo_visitante,
        hora,
        gana_local 
    FROM enfrentamientos
    WHERE cod_jornada = v_cod_jornada;
END;
--******************************************************************************
