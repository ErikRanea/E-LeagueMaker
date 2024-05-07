SET SERVEROUTPUT ON;
--**********Comprobar generar_calendario****************************************

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
/
DECLARE
    v_cod_competicion NUMBER := 1;
BEGIN
    abrir_cerrar_competicion(v_cod_competicion);
END;