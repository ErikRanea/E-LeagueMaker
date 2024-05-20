CREATE OR REPLACE PACKAGE Paquete_XML AS
  PROCEDURE GenerarResultadoJornadas; 
  PROCEDURE GenerarClasificacionXML ;
  PROCEDURE GenerarUltimaJornada (p_n_jornada in jornadas.cod%type);
  -- Definir el procedimiento
END Paquete_XML;
/
CREATE OR REPLACE PACKAGE BODY Paquete_XML AS
  PROCEDURE GenerarClasificacionXML IS
    result CLOB;
    
    -- Para almacenar el resultado XML
  BEGIN
    -- Generar el XML a partir de la vista 'clasificacion'
    
    
    SELECT
      XMLELEMENT(
        "clasificacion",  -- Elemento ra�z
        XMLELEMENT(
          "competicion",  -- Competici�n
          XMLATTRIBUTES(c.cod AS "id"),
          XMLFOREST(
                c.nombre AS "nombre_competicion",
                c.fecha_inicio AS "fecha_inicio_competicion",
                c.fecha_fin AS "fecha_fin_competicion",
                (SELECT nombre from juegos j where j.cod= (SELECT cod_juego
                                                                    FROM COMPETICIONES
                                                                    WHERE cod = c.cod)) AS "nombre_juego_competicion"
          ),-- Atributo 'id' para competici�n
          (SELECT
          XMLAGG(
            XMLELEMENT(
              "equipo",  -- Elemento 'equipo'
              XMLATTRIBUTES(cf.cod_equipo AS "id"),  -- Atributo 'id' del equipo
              XMLFOREST(
                cf.nombre AS "nombre",  -- Elemento 'nombre'
                cf.puntos AS "puntos",  -- Elemento 'puntos'
                cf.posicion AS "posicion"  -- Elemento 'posici�n'
              )
            )
            )
            FROM clasificacion cf
            where cf.cod_competicion = c.cod
        )
        )
      ).getClobVal() INTO result  -- Obtener el resultado como CLOB
    FROM competiciones c;  -- Agrupar para evitar duplicados

    -- Insertar el XML en la tabla con fecha de expiraci�n
    INSERT INTO temp_clob_tab (xml_data, fecha_expiracion)
    VALUES (result, SYSTIMESTAMP + INTERVAL '7' DAY);  -- Inserci�n con fecha de expiraci�n

    DBMS_OUTPUT.PUT_LINE('XML generado correctamente');  -- Confirmaci�n de �xito
    
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);  -- Manejo de errores
  END GenerarClasificacionXML; 
  
  -- Procedimiento resultados ultima jornada xml
  

  PROCEDURE GenerarUltimaJornada  (p_n_jornada in jornadas.cod%type) IS
    result CLOB;
    r_xml CLOB;-- Para almacenar el resultado XML
  BEGIN
    -- Generar el XML a partir de la vista 'Resultados_Jornadas'
    SELECT
      XMLELEMENT(
        "resultado_jornada",-- Elemento ra�z
        XMLAGG(
        XMLELEMENT(
        "competicion",
        XMLATTRIBUTES(c.cod AS "id"),
        XMLFOREST(
            c.nombre AS "nombre_competicion"
            ),
            (SELECT
            XMLAGG(
          XMLELEMENT(
            "jornada",
            XMLATTRIBUTES(j.Cod AS "id"),  -- Atributo 'id' para la jornada
            XMLFOREST(
              j.N_Jornada AS "numero_jornada",  -- Elemento del n�mero de la jornada
              j.fecha AS "Fecha_jornada"
            ),
            (SELECT
            XMLAGG(
              XMLELEMENT(
                "enfrentamiento",
                XMLATTRIBUTES(rj.Cod_enfrentamiento AS "id"),  -- Atributo 'id' para enfrentamiento
                XMLFOREST(
                  rj.Cod_Equipo_Local AS "cod_equipo_local",
                  rj.Cod_Equipo_Visitante AS "cod_equipo_visitante",
                  rj.Ganador AS "ganador"
                )
              )
              ORDER BY rj.Cod_enfrentamiento  -- Ordenar por c�digo de enfrentamiento
            )
            FROM resultados_jornadas rj
            WHERE rj.cod_jornada=j.cod
            )
          )
        )
        FROM jornadas j
        WHERE j.n_jornada=p_n_jornada
        AND j.cod_competicion=c.cod
        )
        )
        )
      ).getClobVal() INTO r_xml  -- Obtener el resultado como CLOB
    FROM competiciones c;
    
    result:=  '<?xml version=''1.0'' encoding=''UTF-8'' ?>' || '<!DOCTYPE resultado_jornada SYSTEM "ResultadoUltimaJornada.dtd">' || r_xml;
    
    -- Insertar el XML en la tabla con fecha de expiraci�n
    INSERT INTO temp_clob_tab (xml_data, fecha_expiracion)
    VALUES (result, SYSTIMESTAMP + INTERVAL '7' DAY);  -- Inserci�n con fecha de expiraci�n

    DBMS_OUTPUT.PUT_LINE('XML generado correctamente');
    DBMS_OUTPUT.PUT_LINE(result);
    
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);  -- Manejo de errores
  END GenerarUltimaJornada;
  
  
  
  --procedimiento resultado jornadas xml
  
  
  
PROCEDURE GenerarResultadoJornadas IS
    result CLOB;
    r_xml CLOB;-- Para almacenar el resultado XML
BEGIN
    -- Generar el XML a partir de las tablas
    SELECT
        XMLELEMENT(
            "competiciones",
            XMLAGG(
                XMLELEMENT(
                    "competicion",
                    XMLATTRIBUTES(c.cod AS "id_competicion"),
                    XMLELEMENT("nombre_competicion", c.nombre),
                    (SELECT
                    XMLAGG(
                        XMLELEMENT(
                            "jornada",
                            XMLATTRIBUTES(j.cod AS "id_jornada"),
                            XMLELEMENT("numero_jornada", j.n_jornada),
                            XMLELEMENT("fecha_jornada", j.fecha),
                            (
                                SELECT
                                    XMLAGG(
                                        XMLELEMENT(
                                            "enfrentamiento",
                                            XMLATTRIBUTES(rj.cod_enfrentamiento AS "id"),
                                            XMLFOREST(
                                                rj.cod_equipo_local AS "cod_equipo_local",
                                                rj.cod_equipo_visitante AS "cod_equipo_visitante",
                                                rj.ganador AS "ganador"
                                            )
                                        )
                                    )
                                FROM resultados_jornadas rj
                                WHERE rj.cod_jornada = j.cod
                            )
                        )
                    )
                    FROM jornadas j
                    WHERE j.cod_competicion = c.cod
                    )
                )
            )
        ).getClobVal()
    INTO r_xml
    FROM competiciones c;
    
    result:=  '<?xml version=''1.0'' encoding=''UTF-8'' ?>' || '<!DOCTYPE competiciones SYSTEM "ResultadoJornadas.dtd">' || r_xml;

    -- Insertar el XML en la tabla con fecha de expiraci�n
    INSERT INTO temp_clob_tab (xml_data, fecha_expiracion)
    VALUES (result, SYSTIMESTAMP + INTERVAL '7' DAY);  -- Inserci�n con fecha de expiraci�n

    DBMS_OUTPUT.PUT_LINE('XML generado correctamente');  -- Confirmaci�n de �xito
    DBMS_OUTPUT.PUT_LINE(result);
    
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);  -- Manejo de errores
END GenerarResultadoJornadas;

END Paquete_XML;


