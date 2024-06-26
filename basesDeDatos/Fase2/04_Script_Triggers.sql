-- Updated upstream
DROP TRIGGER CONTROLAR_MAX_JUGADOR;
DROP TRIGGER CONTROLAR_MIN_JUGADOR_EQUIPO;
DROP TRIGGER CONTROLAR_MOD_EQUIPO;
DROP TRIGGER CONTROLAR_EQUIPOS_IMPARES;
DROP TRIGGER CONTROLAR_ENTRENADOR_MIN;
DROP TRIGGER ACTUALIZAR_PUNTOS_EQUIPOS;
DROP TRIGGER CON_SALA_MIN_JUGADORES;
DROP TRIGGER CONTROLAR_SALARIO_MAX_EQUIPO;


----------1. Minimo controlar que no haya mas de 6 jugadores en un equipo.----
CREATE OR REPLACE TRIGGER controlar_max_jugador
FOR INSERT OR UPDATE ON jugadores
COMPOUND TRIGGER
    num_jugadores INTEGER;
    equipo_cod NUMBER;
BEFORE EACH ROW IS
BEGIN
equipo_cod := :NEW.cod_equipo;
END BEFORE EACH ROW;
AFTER STATEMENT IS
    BEGIN
    SELECT COUNT(*) INTO num_jugadores 
    FROM jugadores
    WHERE cod_equipo = equipo_cod;
    
    IF num_jugadores > 6 THEN
        RAISE_APPLICATION_ERROR
        (-20001, 'No se pueden agregar mas de 6 jugadores a un equipo');
    END IF;
    END AFTER STATEMENT;
END;


/

-------2.Controlar que minimo en cada equipo haya dos ---------------
--------jugadores para crear el calendario.--------------------
CREATE OR REPLACE TRIGGER controlar_min_jugador_equipo
BEFORE INSERT OR UPDATE ON puntos_equipos
FOR EACH ROW
DECLARE
    num_jugadores INTEGER;
BEGIN
    SELECT COUNT(cod) INTO num_jugadores 
    FROM jugadores
    WHERE cod_equipo = :NEW.cod_equipo;
    
        IF num_jugadores <= 2 THEN
        RAISE_APPLICATION_ERROR
        (-20001, 'Debe haber al menos dos jugadores en un 
        equipo para realizar esta operacion');
    END IF;
END;

/

---------3. Controlar que cuando empiece la competici?n no se puedan modificar
------------------------ni equipos ni jugadores.--------------------------
----evitar mod de equipo-----
-----el count cuenta 1 o 2 esto me permite saber si esta activado o no
-----de lo contrario caundo lo hice sin el count----
------a pesar de que estaba en 0 el trigger seguia disparandose ----
CREATE OR REPLACE TRIGGER controlar_mod_equipo
BEFORE INSERT OR DELETE ON puntos_equipos
FOR EACH ROW
DECLARE
    comp_iniciada INTEGER;
BEGIN
    IF DELETING THEN
        SELECT count(*) INTO comp_iniciada 
        FROM competiciones 
        WHERE estado_abierto = 1 AND
              :OLD.COD_COMPETICION = cod;
        
        IF comp_iniciada = 0 THEN
            RAISE_APPLICATION_ERROR
            (-20001, 'No se pueden modificar los equipos una vez que la
                        competicion ha comenzado');
        END IF;
    ELSE
        SELECT COUNT(*) INTO comp_iniciada 
        FROM competiciones 
        WHERE estado_abierto = 1 AND
              :NEW.COD_COMPETICION = cod;
        
        IF comp_iniciada = 0 THEN
            RAISE_APPLICATION_ERROR
            (-20001, 'No se pueden modificar los equipos una vez que la
                        competicion ha comenzado');
        END IF;
    END IF;
END;


/


-----4. Controlar competicion no permitir competicion con equipos impares---
CREATE OR REPLACE TRIGGER controlar_equipos_impares
BEFORE INSERT ON jornadas
FOR EACH ROW
DECLARE
    v_cont_equipo INTEGER;
BEGIN
    SELECT COUNT(cod_equipo) INTO v_cont_equipo 
    FROM puntos_equipos
    WHERE cod_competicion = :NEW.cod_competicion;
----------- Ver si es impar----
    IF MOD(v_cont_equipo,2)<>0 THEN
-------si es impar botar error------
        RAISE_APPLICATION_ERROR
        (-20001, 'No se puede crear una competicion
        con un numero impar de equipos');
    END IF;
END;
/




-------5.Sobre la tabla PUNTOS_EQUIPOS. Si se quiere hacer un insert  ------
--el equipo tiene que tener minimo 1 entrenador, de lo contrario no se puede- 
-----------------------------dar de alta.-------------------

CREATE OR REPLACE TRIGGER controlar_entrenador_min
BEFORE INSERT ON puntos_equipos
FOR EACH ROW
DECLARE
    num_entrenadores INTEGER;
BEGIN
------cuenta el numero de entrenadores---------
    SELECT COUNT(puesto) INTO num_entrenadores 
    FROM staffs 
    WHERE cod_equipo = :NEW.cod_equipo;
    
    -- Verificar si hay al menos un entrenador en el equipo
    IF num_entrenadores < 1 THEN
        -- Si no hay al menos un entrenador, lanzar un error
        RAISE_APPLICATION_ERROR
        (-20001, 'El equipo debe tener al menos un entrenador para poder dar
        de alta en la tabla de PUNTOS_EQUIPOS');
    END IF;
END;
/
/*6 .Cuando hay insert de enfrentamiento, condicional if ganalocal suma a local
!ganaLocal suma a visitante en puntos-equipos -------falta verificar--------*/
CREATE OR REPLACE TRIGGER actualizar_puntos_equipos
BEFORE INSERT OR UPDATE ON enfrentamientos
FOR EACH ROW
DECLARE
    v_new_gana_local ENFRENTAMIENTOS.gana_local%type := :NEW.gana_local;  
    v_cod_equipo_ganador equipos.cod%type; -- Variable para el equipo ganador
    v_cod_competicion JORNADAS.cod_competicion%type;
BEGIN
    -- Consulta para obtener una fila espec?fica de enfrentamientos
    --Sacamos la fila actualizada
    
    --Conseguir c�digo competici�n
    SELECT cod_competicion INTO v_cod_competicion
    FROM JORNADAS
    WHERE cod =  :NEW.cod_jornada;
    

    -- Determinar qu? equipo ha ganado usando gana_local desde v_enfre
    IF v_new_gana_local = 1 THEN
        v_cod_equipo_ganador := :NEW.cod_equipo_local;  --Asigna al equipo local
    ELSIF v_new_gana_local= 0 THEN
        v_cod_equipo_ganador := :NEW.cod_equipo_visitante;
    ELSE
        -- Si el resultado no est? definido, no se actualiza
        --DBMS_OUTPUT.PUT_LINE('No se define ganador, no se actualiza');
        RETURN;
    END IF;

    -- Actualizar los puntos del equipo ganador
    UPDATE puntos_equipos
    SET puntos = puntos + 1
    WHERE cod_equipo = v_cod_equipo_ganador
    AND COD_COMPETICION = v_cod_competicion;

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error al actualizar puntos: ' || SQLERRM);  
END;

/
/*7. trigger salario minimo de jugadores 1134   
    falta verificar auqneu mas seguro que est� bien :)*/

CREATE OR REPLACE TRIGGER con_sala_min_jugadores
BEFORE INSERT OR UPDATE ON jugadores 
FOR EACH ROW 
BEGIN 
    IF :NEW.salario < 1134 THEN
        :NEW.salario := 1134;
        RAISE_APPLICATION_ERROR
        (-20001, 'No se puede ingresar un salario inferior a 1134');
    END IF;
END;

/
/*8. Salario maximo de equipo 200000*/ 
CREATE OR REPLACE TRIGGER controlar_salario_max_equipo 
FOR INSERT OR UPDATE ON jugadores
COMPOUND TRIGGER
v_salario_equipo NUMBER;
equipo_cod NUMBER;
salario_nuevo NUMBER;
BEFORE EACH ROW IS
    BEGIN
    equipo_cod := :NEW.cod_equipo;
    salario_nuevo := :NEW.salario;
END BEFORE EACH ROW;
AFTER STATEMENT IS
    
BEGIN
    SELECT SUM(salario) INTO v_salario_equipo
    FROM jugadores
    WHERE cod_equipo = equipo_cod;

    IF NVL(v_salario_equipo, 0) + salario_nuevo > 200000 THEN
        RAISE_APPLICATION_ERROR
        (-20001, 'El salario total del equipo excede el limite de 200,000.');
    END IF;
END AFTER STATEMENT;
END;



/

/*9. Controlar que no se puedan modificar jugadores cuando cuando una 
competicion este cerrada*/
CREATE OR REPLACE TRIGGER controlar_mod_jugadores
BEFORE INSERT OR UPDATE ON jugadores
FOR EACH ROW 
DECLARE 
    comp_iniciada INTEGER;
    cod_competicion INTEGER;
BEGIN 
    -- Obtener la competici�n del equipo del jugador
    SELECT cod_competicion
    INTO cod_competicion
    FROM puntos_equipos
    WHERE cod_equipo = :NEW.cod_equipo; 

    -- Verificar si la competici�n est� iniciada
    SELECT COUNT(*)
    INTO comp_iniciada 
    FROM competiciones 
    WHERE estado_abierto = 1 
      AND cod = cod_competicion;
        
    IF comp_iniciada = 0 THEN
        RAISE_APPLICATION_ERROR(
          -20001, 
          'No se pueden modificar los equipos una vez que la competicion ha 
          comenzado'
        );
    END IF;
END;

/

/*10. Controlar que no se puedan modificar equipos cuando una competicion este
cerrada*/

CREATE OR REPLACE TRIGGER controlar_mod_equipos
BEFORE UPDATE ON equipos
FOR EACH ROW 
DECLARE 
    comp_iniciada INTEGER;
BEGIN 
    -- Verificar si hay alguna competici�n iniciada relacionada con el equipo
    SELECT COUNT(*)
    INTO comp_iniciada
    FROM puntos_equipos pe
    JOIN competiciones c ON pe.cod_competicion = c.cod
    WHERE pe.cod_equipo = :NEW.cod
      AND c.estado_abierto = 1;

    -- Lanzar error si alguna competici�n est� iniciada
    IF comp_iniciada =  0 THEN
        RAISE_APPLICATION_ERROR(
          -20001, 
          'No se pueden modificar los equipos una vez que la competici�n ha
          comenzado'
        );
    END IF;
END;
/

/*11. Controlar que no se puedan modificar miembros del staff cuando la 
competicion ha empezado*/


CREATE OR REPLACE TRIGGER controlar_mod_staffs
BEFORE UPDATE ON staffs
FOR EACH ROW 
DECLARE 
    comp_iniciada INTEGER;
BEGIN 
--Verificar si hay competici�n iniciada relacionada con el equipo del staff
    SELECT COUNT(*)
    INTO comp_iniciada
    FROM puntos_equipos pe
    JOIN competiciones c ON pe.cod_competicion = c.cod
    WHERE pe.cod_equipo = :NEW.cod_equipo
      AND c.estado_abierto = 1;

    -- Lanzar error si alguna competici�n est� iniciada
    IF comp_iniciada = 0 THEN
        RAISE_APPLICATION_ERROR(
          -20001, 
          'No se pueden modificar los staffs una vez que la competici�n ha
          comenzado'
        );
    END IF;
END;

/
