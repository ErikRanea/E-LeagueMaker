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
        "clasificacion",  -- Elemento ra�z
        XMLELEMENT(
          "competicion",  -- Competici�n
          XMLATTRIBUTES(c.cod_competicion AS "id"),  -- Atributo 'id' para competici�n
          XMLAGG(
            XMLELEMENT(
              "equipo",  -- Elemento 'equipo'
              XMLATTRIBUTES(c.cod_equipo AS "id"),  -- Atributo 'id' del equipo
              XMLFOREST(
                e.nombre AS "nombre",  -- Elemento 'nombre'
                c.puntos AS "puntos",  -- Elemento 'puntos'
                c.posicion AS "posicion"  -- Elemento 'posici�n'
              )
            )
            ORDER BY c.posicion  -- Ordenar equipos por posici�n
          )  -- Fin de XMLAGG para equipos
        )  -- Fin de XMLELEMENT para competici�n
      ).getClobVal() INTO result  -- Obtener el resultado como CLOB
    FROM clasificacion c
    JOIN EQUIPOS e
      ON e.cod = c.cod_equipo  -- Unir con equipos
    WHERE c.cod_competicion = p_cod_competi  -- Filtrar por competici�n espec�fica
    GROUP BY c.cod_competicion;  -- Agrupar para evitar duplicados

    -- Insertar el XML en la tabla con fecha de expiraci�n
    INSERT INTO temp_clob_tab (xml_data, fecha_expiracion)
    VALUES (result, SYSTIMESTAMP + INTERVAL '7' DAY);  -- Inserci�n con fecha de expiraci�n

    DBMS_OUTPUT.PUT_LINE('XML generado correctamente');  -- Confirmaci�n de �xito
    
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);  -- Manejo de errores
  END GenerarClasificacionXML; 
  
  -- Procedimiento resultados ultima jornada xml
  

  PROCEDURE GenerarUltimaJornada (p_cod_jornada in jornadas.cod%type) IS
    result CLOB;  -- Para almacenar el resultado XML
  BEGIN
    -- Generar el XML a partir de la vista 'Resultados_Jornadas'
    SELECT
      XMLELEMENT(
        "resultados_jornadas",  -- Elemento ra�z
        XMLAGG(
          XMLELEMENT(
            "jornada",
            XMLATTRIBUTES(rj.Cod_Jornada AS "id"),  -- Atributo 'id' para la jornada
            XMLFOREST(
              rj.N_Jornada AS "numero_jornada"  -- Elemento del n�mero de la jornada
            ),
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
          )
        )
      ).getClobVal() INTO result  -- Obtener el resultado como CLOB
    FROM Resultados_Jornadas rj
    WHERE rj.Cod_Jornada = p_cod_jornada  -- Filtrar por jornada espec�fica
    GROUP BY rj.Cod_Jornada, rj.N_Jornada, rj.Cod_enfrentamiento, rj.Cod_Equipo_Local, rj.Cod_Equipo_Visitante, rj.Ganador;

    -- Insertar el XML en la tabla con fecha de expiraci�n
    INSERT INTO temp_clob_tab (xml_data, fecha_expiracion)
    VALUES (result, SYSTIMESTAMP + INTERVAL '7' DAY);  -- Inserci�n con fecha de expiraci�n

    DBMS_OUTPUT.PUT_LINE('XML generado correctamente');  -- Confirmaci�n de �xito
    
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);  -- Manejo de errores
  END GenerarUltimaJornada;
  
  
  
  --procedimiento resultado jornadas xml
  
  
  
PROCEDURE GenerarResultadoJornadas IS
    result CLOB;  -- Para almacenar el resultado XML
  BEGIN
    -- Generar el XML a partir de la vista 'Resultados_Jornadas'
    SELECT
          XMLELEMENT(
            "resultado_jornadas",  -- Atributo 'id' para competici�n
            XMLAGG(
              XMLELEMENT(
                "jornada",
                XMLATTRIBUTES(
                rj.cod_competicion AS "id_competicion", 
                rj.Cod_Jornada AS "id_ jornada"
                ),
                XMLFOREST(
                  rj.N_Jornada AS "numero_jornada"  -- N�mero de la jornada
                ),
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
              )
              ORDER BY rj.Cod_Jornada  -- Ordenar por c�digo de jornada
            )
      ).getClobVal() INTO result  -- Obtener el resultado como CLOB
    FROM Resultados_Jornadas rj
    WHERE rj.cod_competicion= 1   -- Filtrar por competici�n v�lida
    GROUP BY rj.cod_competicion, rj.Cod_Jornada, rj.N_Jornada, rj.Cod_enfrentamiento, rj.Cod_Equipo_Local, rj.Cod_Equipo_Visitante, rj.Ganador;

    -- Insertar el XML en la tabla con fecha de expiraci�n
    INSERT INTO temp_clob_tab (xml_data, fecha_expiracion)
    VALUES (result, SYSTIMESTAMP + INTERVAL '7' DAY);  -- Inserci�n con fecha de expiraci�n

    DBMS_OUTPUT.PUT_LINE('XML generado correctamente');  -- Confirmaci�n de �xito
    
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);  -- Manejo de errores
  END GenerarResultadoJornadas;
END Paquete_XML;


