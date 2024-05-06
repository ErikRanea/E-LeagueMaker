
CREATE OR REPLACE PROCEDURE generar_calendario  AS
    --Competicion
    CURSOR c_cod_competi IS
        SELECT cod_competicion FROM PUNTOS_EQUIPOS;
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
    
    temp PUNTOS_EQUIPOS.cod_equipo%type; --Variable temporal para ayudar en la rotacion
    r NUMBER; --Variable para randomizar 
    
    viejo_cod_competi NUMBER;
    
    todas_jornadas_hechas NUMBER := 0;
BEGIN
    
    OPEN c_cod_competi;
   ---ABRIR CURSOR COMPETIS 
    LOOP 
        FETCH c_cod_competi INTO v_cod_competicion;
    
        EXIT WHEN c_cod_competi%NOTFOUND;
          --Conseguir Fecha de Inicio;
        SELECT fecha_inicio INTO v_fecha_inicio
        FROM COMPETICIONES
        WHERE cod = v_cod_competicion;
        
        fecha_jornada := v_fecha_inicio;
        -- A�adir los equipos a la lista
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
    
        --Contar n�mero de equipos
        v_num_equipos := l_equipos.COUNT;
        --Calcular n�mero de jornadas
        v_num_jornadas := v_num_equipos - 1;
        
        
        
        FOR v_jornada_actual IN 1..v_num_jornadas LOOP--Bucle para crear jornadas
             
    --****CREAR JORNADA***********      
            IF viejo_cod_competi != v_cod_competicion THEN
                todas_jornadas_hechas := 0;
            END IF;
             
             if todas_jornadas_hechas = 0 THEN
                        viejo_cod_competi := v_cod_competicion;
                         DBMS_OUTPUT.PUT_LINE('Competicion '|| v_cod_competicion ||' Jornada ' || v_jornada_actual || ':'); -- Imprimir la jornada actual
                    -- Insertar la jornada en la tabla Jornadas
                    v_cod_jornada := seq_jornadas.NEXTVAL; 
                    fecha_jornada := v_fecha_inicio;
                    /*Al meter el cod jornada en una variable hago que no salte el nextval
                    hasta que lo vuelva a declarar y as� puedo saber cual es el c�digo 
                    de la jornada*/
                    
                    
                             -- Calcular la hora del enfrentamiento
                    IF v_jornada_actual = 1 THEN
                        -- El primer enfrentamiento siempre es a las 19:00
                        fecha_jornada := TRUNC(fecha_jornada) + INTERVAL '19' HOUR;
                    ELSE
                        -- A partir del segundo enfrentamiento de la jornada, a�adir 1 hora
                        fecha_jornada := fecha_jornada + INTERVAL '1' HOUR;
                    END IF;
                    
                    
                    
                    
                    INSERT INTO Jornadas (Cod, N_Jornada, Fecha, Cod_Competicion)
                    VALUES (v_cod_jornada, v_jornada_actual, fecha_jornada, v_cod_competicion);
                    
                    
                    
                    
                    
            --*****Bucle para crear Enfrentamientos       
                     FOR i IN 1..v_num_equipos/2 LOOP--Bucle para crear enfrentamientos
                        equipo_local := i;
                        equipo_visitante := v_num_equipos -i + 1;
            
            
                        INSERT INTO Enfrentamientos (Cod, Hora, Cod_Jornada, cod_equipo_visitante,cod_equipo_local)
                        VALUES (seq_enfrentamientos.NEXTVAL , fecha_jornada, v_cod_jornada,equipo_visitante, equipo_local);
                        
                        DBMS_OUTPUT.PUT_LINE('  Enfrentamiento: ' || l_equipos(equipo_local) || ' vs ' || l_equipos(equipo_visitante));
                     END LOOP;
                 
                     -- Guardar el ?ltimo equipo en la variable temporal
                    temp := l_equipos(v_num_equipos);
                    -- Rotar los equipos hacia arriba en la lista
                    FOR i IN REVERSE 2..v_num_equipos LOOP
                      l_equipos(i) := l_equipos(i-1);
                    END LOOP;
                
                    -- Colocar el ?ltimo equipo en la segunda posici?n para la rotaci?n
                    l_equipos(2) := temp;
                
                if v_jornada_actual = l_equipos.COUNT -1 THEN
                    todas_jornadas_hechas := 1;
                END IF;
             END IF;
             

        END LOOP;--Cierro el for de las jornadas
        
    --CERRAR CURSOR COMPETIS
    END LOOP;




  

EXCEPTION
    WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Error: '|| SQLERRM);
END;