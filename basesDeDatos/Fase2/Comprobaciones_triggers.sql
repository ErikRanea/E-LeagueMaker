---1.----Prueba del trigger de maximo de jugadores----
update jugadores set cod_equipo =2 where cod=5;
INSERT INTO jugadores (cod, nombre,apellido, rol, salario, nacionalidad,
                        fecha_nacimiento, nickname, cod_equipo) 
                        VALUES (2, 'jugador2', 'apellido2', 'entrenador', 3000,
                                'venezuela', '10-11-1999', 'sombra', 1 );
INSERT INTO jugadores (cod, nombre,apellido, rol, salario, nacionalidad,
                        fecha_nacimiento, nickname, cod_equipo) 
                        VALUES (3, 'jugador3', 'apellido3', 'entrenador', 2000,
                                'colombia', '10-10-2020', 'sembra', 1 );
INSERT INTO jugadores (cod, nombre,apellido, rol, salario, nacionalidad, 
                        fecha_nacimiento, nickname, cod_equipo) 
                        VALUES (11, 'jugador4', 'apellido4', 'entrenador', 1000,
                                'irlanda', '10-11-2020', 'shadow', 1 );
INSERT INTO jugadores (cod, nombre,apellido, rol, salario, nacionalidad,
                        fecha_nacimiento, nickname, cod_equipo) 
                        VALUES (5, 'jugador5', 'apellido5', 'entrenador', 7000,
                                'espaï¿½a', '10-11-2002', 'shadow', 1 );
INSERT INTO jugadores (cod, nombre,apellido, rol, salario, nacionalidad, 
                        fecha_nacimiento, nickname, cod_equipo) 
                        VALUES (6, 'jugador6', 'apellido6', 'entrenador', 5000, 
                                'jupiter', '10-11-2005', 'shadow', 1 );
----al inggresar el jugador 7 se dispara el trigger verificar la tabla jugadores
/*Informe de error -
ORA-20001: No se pueden agregar mï¿½s de 6 jugadores a un equipo
ORA-06512: en "EQDAW05.CONTROLAR_MAX_JUGADOR", lï¿½nea 9
ORA-04088: error durante la ejecuciï¿½n del disparador 
'EQDAW05.CONTROLAR_MAX_JUGADOR'*/
INSERT INTO jugador (cod, nombre,apellido, rol, salario, nacionalidad,
                    fecha_nacimiento, nickname, cod_equipo) 
                    VALUES (7, 'jugador7', 'apellido7', 'entrenador', 9000, 
                            'venus', '10-11-2001', 'shadow', 1 );
/*2.COntrolar el min de jugadores en un equipo*/
/*Error que empieza en la lï¿½nea: 35 del comando -
INSERT INTO puntos_equipos (cod_competicion, cod_equipo, puntos) 
VALUES (102, 6, 3)
Error en la lï¿½nea de comandos : 35 Columna : 13
Informe de error -
Error SQL: ORA-20001: Debe haber al menos dos jugadores en un 
        equipo para realizar esta operaciï¿½n
ORA-06512: en "EQDAW05.CONTROLAR_MIN_JUGADOR_EQUIPO", lï¿½nea 9
ORA-04088: error durante la ejecuciï¿½n del disparador 
'EQDAW05.CONTROLAR_MIN_JUGADOR_EQUIPO'*/
INSERT INTO puntos_equipos (cod_competicion, cod_equipo, puntos) 
                            VALUES (1, 6, 3);


/*3. Controlar que cuando empiece la competiciï¿½n no se puedan modificar ni
equipos ni jugadores*/
---nos darï¿½ error de que no se puede insertar por que ya esta iniciado ----
INSERT INTO puntos_equipos (cod_competicion, cod_equipo, puntos) 
                            VALUES(1, 90, 4);


/*4 TRIIGER DE NUMEROS IMPARES*/
INSERT INTO jornadas (cod, n_jornada, fecha, cod_competicion, cod_juego) 
                    VALUES (8, 2, TO_DATE('2024/04/27', 'YYYY/MM/DD'),2, 3 );

/*Error que empieza en la lï¿½nea: 20 del comando -
INSERT INTO jornadas (cod, n_jornada, fecha, cod_competicion, cod_juego)
VALUES (8, 2, TO_DATE('2024/04/27', 'YYYY/MM/DD'),302, 3 )
Error en la lï¿½nea de comandos : 20 Columna : 13
Informe de error -
Error SQL: ORA-20001: No se puede crear una competiciï¿½n
        con un nï¿½mero impar de equipos
ORA-06512: en "EQDAW05.CONTROLAR_EQUIPOS_IMPARES", lï¿½nea 10
ORA-04088: error durante la ejecuciï¿½n del disparador 
'EQDAW05.CONTROLAR_EQUIPOS_IMPARES'*/

/*5 Si se quiere hacer un insert tiene que haber mï¿½nimo 1 entrenador */

INSERT INTO equipos (nombre, fecha_fundacion) 
                    VALUES ('peruvian',TO_DATE('2014/04/27', 'YYYY/MM/DD'));

INSERT INTO jugadores (nombre, apellido, rol, salario, nacionalidad,
                        fecha_nacimiento, nickname, cod_equipo) 
                        VALUES ('Aitor', 'Rodriguez', 'flex', 1250,
                                'Euskadi',TO_DATE('2000/04/27', 'YYYY/MM/DD'),'Aitorro', 7);

INSERT INTO jugadores (nombre, apellido, rol, salario, nacionalidad, 
                        fecha_nacimiento, nickname, cod_equipo)
                        VALUES ('Remolacha', 'Juarez', 'remo', 1250, 'Francia'
                                ,TO_DATE('2001/04/27', 'YYYY/MM/DD'),'Remojua',
                                7);

INSERT INTO puntos_equipos(cod_competicion, cod_equipo, puntos) 
                            VALUES(2, 7, 3 );
/*Error que empieza en la lï¿½nea: 51 del comando -
INSERT INTO puntos_equipos(cod_competicion, cod_equipo, puntos) 
VALUES(202, 7, 3 )
Error en la lï¿½nea de comandos : 51 Columna : 13
Informe de error -
Error SQL: ORA-20001: El equipo debe tener al menos un entrenador para poder 
dar de alta en la tabla de PUNTOS_EQUIPOS
ORA-06512: en "EQDAW05.CONTROLAR_ENTRENADOR_MIN", lï¿½nea 12
ORA-04088: error durante la ejecuciï¿½n del disparador 
'EQDAW05.CONTROLAR_ENTRENADOR_MIN'*/

/*6. Cuando hay insert de enfrentamiento, condicional if ganalocal suma 
a local !ganaLocal     
suma a visitante en puntos-equipos*/

UPDATE enfrentamientos
SET gana_local = 0
WHERE cod = 3;

/*7. Cuando hacemos una insert de un salario minimo a un jugador nos da el 
error */

INSERT INTO Jugadores ( nombre, apellido, salario, nacionalidad,
                        fecha_nacimiento, nickname, rol, cod_Equipo)
                        VALUES ('Erik', 'Ranea', 500, 'Espaï¿½a',
                                TO_DATE('03/12/2000','DD/MM/YYYY'), 'Dele', 
                                'Suport', 03);


/*Informe de error -
Error SQL: ORA-20001: No se puede ingresar un salario inferior a 1134
ORA-06512: en "EGIBIDE.CONTROLAR_SALARIO_MIN_JUGADORES", lï¿½nea 4
ORA-04088: error durante la ejecuciï¿½n del disparador 
'EGIBIDE.CONTROLAR_SALARIO_MIN_JUGADORES'*/

/*8. Comrpobamos que el salario maximo del equipo no sobrepasa los 200000 */

INSERT INTO Jugadores ( nombre, apellido, salario, nacionalidad,
                        fecha_nacimiento, nickname, rol, cod_Equipo)
                        VALUES ('Fernando', 'Porras', 1200, 'Espaï¿½a', 
                                TO_DATE('03/12/2000','DD/MM/YYYY'), 'Dele', 
                                'Suport', 03);

INSERT INTO Jugadores ( nombre, apellido, salario, nacionalidad,
                        fecha_nacimiento, nickname, rol, cod_Equipo)
                        VALUES ('Fernando', 'Porras', 1200, 'Espaï¿½a', 
                                TO_DATE('03/12/2000','DD/MM/YYYY'), 'Dele', 
                                'Suport', 03);

INSERT INTO Jugadores ( nombre, apellido, salario, nacionalidad, 
                        fecha_nacimiento, nickname, rol, cod_Equipo)
                        VALUES ('Fernando', 'Porras', 1200, 'Espaï¿½a', 
                                TO_DATE('03/12/2000','DD/MM/YYYY'), 'Dele',
                                'Suport', 03);

INSERT INTO Jugadores ( nombre, apellido, salario, nacionalidad, 
                        fecha_nacimiento, nickname, rol, cod_Equipo)
                        VALUES ('Fernando', 'Porras', 1200, 'Espaï¿½a',
                                TO_DATE('03/12/2000','DD/MM/YYYY'), 'Dele', 
                                'Suport', 03);

INSERT INTO Jugadores ( nombre, apellido, salario, nacionalidad, 
                        fecha_nacimiento, nickname, rol, cod_Equipo)
                        VALUES ('Fernando', 'Porras', 1200, 'Espaï¿½a', 
                                TO_DATE('03/12/2000','DD/MM/YYYY'), 'Dele', 
                                'Suport', 03);

INSERT INTO Jugadores ( nombre, apellido, salario, nacionalidad, 
                        fecha_nacimiento, nickname, rol, cod_Equipo)
                        VALUES ('Fernando', 'Porras', 1200, 'Espaï¿½a', 
                                TO_DATE('03/12/2000','DD/MM/YYYY'), 'Dele',
                                'Suport', 03);

INSERT INTO Jugadores ( nombre, apellido, salario, nacionalidad, 
                        fecha_nacimiento, nickname, rol, cod_Equipo)
                        VALUES ('Fernando', 'Porras', 1200, 'Espaï¿½a', 
                                TO_DATE('03/12/2000','DD/MM/YYYY'), 'Dele', 
                                'Suport', 03);

INSERT INTO Jugadores ( nombre, apellido, salario, nacionalidad, 
                        fecha_nacimiento, nickname, rol, cod_Equipo)
                        VALUES ('Fernando', 'Porras', 1200, 'Espaï¿½a', 
                                TO_DATE('03/12/2000','DD/MM/YYYY'), 'Dele', 
                                'Suport', 03);

INSERT INTO Jugadores ( nombre, apellido, salario, nacionalidad,
                        fecha_nacimiento, nickname, rol, cod_Equipo)
                        VALUES ('Fernando', 'Porras', 1200, 'Espaï¿½a',
                                TO_DATE('03/12/2000','DD/MM/YYYY'), 'Dele', 
                                'Suport', 03);




/*Informe de error -
Error SQL: ORA-20001: El salario total del equipo excede el lï¿½mite de 200,000.
ORA-06512: en "EGIBIDE.CONTROLAR_SALARIO_MAX_EQUIPO", lï¿½nea 9
ORA-04088: error durante la ejecuciï¿½n del disparador 
'EGIBIDE.CONTROLAR_SALARIO_MAX_EQUIPO'*/



/*9. Controlar que no se puedan modificar jugadores cuando cuando una 
competicion este cerrada*/


UPDATE jugadores
SET salario = 2000
WHERE cod_equipo = 8;

/*La competicion en la que esta el equipo 8 esta cerrada por lo que da este 
error:
Informe de error -
Error SQL: ORA-20001: No se pueden modificar los equipos una vez que 
la competicion ha comenzado
ORA-06512: en "EQDAW05.CONTROLAR_MOD_JUGADORES", línea 19
ORA-04088: error durante la ejecución del disparador 
'EQDAW05.CONTROLAR_MOD_JUGADORES'
*/

UPDATE jugadores
SET salario = 2000
WHERE cod_equipo = 9;

/*Por otro lado, la competicion en la que esta el equipo 9 esta abierta, asi
que deja actualizarlo:
4 filas actualizadas.
*/




/*10. Controlar que no se puedan modificar equipos cuando una competicion este
cerrada*/

UPDATE equipos
SET nombre='equipo LOL 10'
WHERE cod=8;

/*La competicion en la que esta el equipo 8 esta cerrada por lo que da este 
error:
Informe de error -
Error SQL: ORA-20001: No se pueden modificar los equipos una vez que
la competición ha comenzado
ORA-06512: en "EQDAW05.CONTROLAR_MOD_EQUIPOS", línea 14
ORA-04088: error durante la ejecución del disparador 
'EQDAW05.CONTROLAR_MOD_EQUIPOS'
*/

UPDATE equipos
SET nombre='equipo LOL 10'
WHERE cod=9;

/*Por otro lado, la competicion en la que esta el equipo 9 esta abierta, asi
que deja actualizarlo:
1 fila actualizadas.
*/


/*11. Controlar que no se puedan modificar miembros del staff cuando la 
competicion ha empezado*/

UPDATE STAFFS
SET SALARIO =2000
WHERE COD_EQUIPO=8;

/*La competicion en la que esta el equipo 8 esta cerrada por lo que da este 
error:
Informe de error -
Error SQL: ORA-20001: No se pueden modificar los staffs una vez que la competición ha
          comenzado
ORA-06512: en "EQDAW05.CONTROLAR_MOD_STAFFS", línea 14
ORA-04088: error durante la ejecución del disparador 'EQDAW05.CONTROLAR_MOD_STAFFS'
*/

UPDATE STAFFS
SET SALARIO =2000
WHERE COD_EQUIPO=9;

/*Por otro lado, la competicion en la que esta el equipo 9 esta abierta, asi
que deja actualizarlo:
1 fila actualizadas.
*/