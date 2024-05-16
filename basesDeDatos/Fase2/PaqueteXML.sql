CREATE OR REPLACE PACKAGE Paquete_XML AS
  PROCEDURE GenerarResultadoJornadas; 
  PROCEDURE GenerarClasificacionXML (p_cod_competi in competiciones.cod%type);
  PROCEDURE GenerarUltimaJornada (p_cod_jornada in jornadas.cod%type);
  -- Definir el procedimiento
END Paquete_XML;
/
CREATE OR REPLACE PACKAGE BODY Paquete_XML AS
  PROCEDURE GenerarClasificacionXML (p_cod_competi in competiciones.cod%type) IS
    result CLOB;  -- Para almacenar el resultado XML
  BEGIN
    -- Generar el XML a partir de la vista 'clasificacion'
    SELECT
      XMLELEMENT(
        "clasificacion",  -- Elemento raíz
        XMLELEMENT(
          "competicion",  -- Competición
          XMLATTRIBUTES(c.cod_competicion AS "id"),  -- Atributo 'id' para competición
          XMLAGG(
            XMLELEMENT(
              "equipo",  -- Elemento 'equipo'
              XMLATTRIBUTES(c.cod_equipo AS "id"),  -- Atributo 'id' del equipo
              XMLFOREST(
                e.nombre AS "nombre",  -- Elemento 'nombre'
                c.puntos AS "puntos",  -- Elemento 'puntos'
                c.posicion AS "posicion"  -- Elemento 'posición'
              )
            )
            ORDER BY c.posicion  -- Ordenar equipos por posición
          )  -- Fin de XMLAGG para equipos
        )  -- Fin de XMLELEMENT para competición
      ).getClobVal() INTO result  -- Obtener el resultado como CLOB
    FROM clasificacion c
    JOIN EQUIPOS e
      ON e.cod = c.cod_equipo  -- Unir con equipos
    WHERE c.cod_competicion = p_cod_competi  -- Filtrar por competición específica
    GROUP BY c.cod_competicion;  -- Agrupar para evitar duplicados

    -- Insertar el XML en la tabla con fecha de expiración
    INSERT INTO temp_clob_tab (xml_data, fecha_expiracion)
    VALUES (result, SYSTIMESTAMP + INTERVAL '7' DAY);  -- Inserción con fecha de expiración

    DBMS_OUTPUT.PUT_LINE('XML generado correctamente');  -- Confirmación de éxito
    
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);  -- Manejo de errores
  END GenerarClasificacionXML; 
  
  -- Procedimiento resultados ultima jornada xml
  

  PROCEDURE GenerarUltimaJornada  (p_cod_jornada in jornadas.cod%type) IS
    result CLOB;
    r_xml CLOB;-- Para almacenar el resultado XML
  BEGIN
    -- Generar el XML a partir de la vista 'Resultados_Jornadas'
    SELECT
      XMLELEMENT(
        "resultado_jornada",  -- Elemento raíz
        XMLAGG(
          XMLELEMENT(
            "jornada",
            XMLATTRIBUTES(j.Cod AS "id"),  -- Atributo 'id' para la jornada
            XMLFOREST(
              j.N_Jornada AS "numero_jornada",  -- Elemento del número de la jornada
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
              ORDER BY rj.Cod_enfrentamiento  -- Ordenar por código de enfrentamiento
            )
            FROM resultados_jornadas rj
            WHERE rj.cod_jornada=j.cod
            )
          )
        )
      ).getClobVal() INTO r_xml  -- Obtener el resultado como CLOB
    FROM jornadas j
    WHERE j.cod=p_cod_jornada;
    
    result:=  '<?xml version=''1.0'' encoding=''UTF-8'' ?>' || '<!DOCTYPE resultado_jornada SYSTEM "ResultadoUltimaJornada.dtd">' || r_xml;
    
    -- Insertar el XML en la tabla con fecha de expiración
    INSERT INTO temp_clob_tab (xml_data, fecha_expiracion)
    VALUES (result, SYSTIMESTAMP + INTERVAL '7' DAY);  -- Inserción con fecha de expiración

    DBMS_OUTPUT.PUT_LINE('XML generado correctamente');  -- Confirmación de éxito
    
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

    -- Insertar el XML en la tabla con fecha de expiración
    INSERT INTO temp_clob_tab (xml_data, fecha_expiracion)
    VALUES (result, SYSTIMESTAMP + INTERVAL '7' DAY);  -- Inserción con fecha de expiración

    DBMS_OUTPUT.PUT_LINE('XML generado correctamente');  -- Confirmación de éxito
    DBMS_OUTPUT.PUT_LINE(result);
    
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);  -- Manejo de errores
END GenerarResultadoJornadas;

END Paquete_XML;


