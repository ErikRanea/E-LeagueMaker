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