-- Creaci�n de Juegos
INSERT INTO Juegos (cod, nombre, desarrolladora, fecha_lanzamiento)
VALUES (1, 'League of Legends', 'Riot Games', TO_DATE('27/10/2009','DD/MM/YYYY'));
INSERT INTO Juegos (cod, nombre, desarrolladora, fecha_lanzamiento)
VALUES (2, 'Counter Strike', 'Valbe', TO_DATE('27/09/2023','DD/MM/YYYY'));
INSERT INTO Juegos (cod, nombre, desarrolladora, fecha_lanzamiento)
VALUES (3, 'Valorant', 'Riot Games', TO_DATE('02/06/2020','DD/MM/YYYY'));
INSERT INTO Juegos (cod, nombre, desarrolladora, fecha_lanzamiento)
VALUES (4, 'Rainbow six siege', 'Ubisoft', TO_DATE('01/12/2015','DD/MM/YYYY'));

commit;

-- Creaci�n de Competiciones
INSERT INTO Competiciones (cod, nombre, fecha_inicio, fecha_fin, estado_abierto, cod_Juego)
VALUES (1, 'LOL_League', TO_DATE('01/06/2024','DD/MM/YYYY') , TO_DATE('01/12/2024','DD/MM/YYYY'), 0,1);
INSERT INTO Competiciones (cod, nombre, fecha_inicio, fecha_fin, estado_abierto, cod_Juego)
VALUES (2, 'CS_go_League', TO_DATE('01/10/2024','DD/MM/YYYY') , TO_DATE('01/02/2025','DD/MM/YYYY'), 0, 2);
INSERT INTO Competiciones (cod, nombre, fecha_inicio, fecha_fin, estado_abierto, cod_Juego)
VALUES (3, 'Valorant_league', TO_DATE('28/12/2023','DD/MM/YYYY') , TO_DATE('09/05/2024','DD/MM/YYYY'), 0, 3);
INSERT INTO Competiciones (cod, nombre, fecha_inicio, fecha_fin, estado_abierto, cod_Juego)
VALUES (4, 'R6_league', TO_DATE('30/03/2024','DD/MM/YYYY') , TO_DATE('01/07/2024','DD/MM/YYYY'), 0, 4);

commit;


-- Creacion de Equipos
-- Para League of Legends
INSERT INTO Equipos (cod, nombre, fecha_fundacion)
VALUES (1, 'Equipo LoL 1', TO_DATE('05/12/2021', 'DD/MM/YYYY'));

INSERT INTO Equipos (cod, nombre, fecha_fundacion)
VALUES (2, 'Equipo LoL 2', TO_DATE('06/12/2021', 'DD/MM/YYYY'));

INSERT INTO Equipos (cod, nombre, fecha_fundacion)
VALUES (3, 'Equipo LoL 3', TO_DATE('07/12/2021', 'DD/MM/YYYY'));

INSERT INTO Equipos (cod, nombre, fecha_fundacion)
VALUES (4, 'Equipo LoL 4', TO_DATE('08/12/2021', 'DD/MM/YYYY'));

-- Para Counter Strike
INSERT INTO Equipos (cod,nombre, fecha_fundacion)
VALUES (5, 'Equipo CS 1', TO_DATE('05/12/2021', 'DD/MM/YYYY'));

INSERT INTO Equipos (cod,nombre, fecha_fundacion)
VALUES (6, 'Equipo CS 2', TO_DATE('06/12/2021', 'DD/MM/YYYY'));

INSERT INTO Equipos (cod,nombre, fecha_fundacion)
VALUES (7, 'Equipo CS 3', TO_DATE('07/12/2021', 'DD/MM/YYYY'));

INSERT INTO Equipos (cod,nombre, fecha_fundacion)
VALUES (8, 'Equipo CS 4', TO_DATE('08/12/2021', 'DD/MM/YYYY'));

-- Para Valorant
INSERT INTO Equipos (cod,nombre, fecha_fundacion)
VALUES (9, 'Equipo Valorant 1', TO_DATE('05/12/2021', 'DD/MM/YYYY'));

INSERT INTO Equipos (cod,nombre, fecha_fundacion)
VALUES (10, 'Equipo Valorant 2', TO_DATE('06/12/2021', 'DD/MM/YYYY'));

INSERT INTO Equipos (cod,nombre, fecha_fundacion)
VALUES (11, 'Equipo Valorant 3', TO_DATE('07/12/2021', 'DD/MM/YYYY'));

INSERT INTO Equipos (cod,nombre, fecha_fundacion)
VALUES (12, 'Equipo Valorant 4', TO_DATE('08/12/2021', 'DD/MM/YYYY'));

-- Para Rainbow six siege
INSERT INTO Equipos (cod,nombre, fecha_fundacion)
VALUES (13, 'Equipo Rainbow 1', TO_DATE('05/12/2021', 'DD/MM/YYYY'));

INSERT INTO Equipos (cod,nombre, fecha_fundacion)
VALUES (14, 'Equipo Rainbow 2', TO_DATE('06/12/2021', 'DD/MM/YYYY'));

INSERT INTO Equipos (cod,nombre, fecha_fundacion)
VALUES (15, 'Equipo Rainbow 3', TO_DATE('07/12/2021', 'DD/MM/YYYY'));

INSERT INTO Equipos (cod,nombre, fecha_fundacion)
VALUES (16, 'Equipo Rainbow 4', TO_DATE('08/12/2021', 'DD/MM/YYYY'));


commit;

-- Creacion de Puntos-Equipos
-- Competición 1 (LOL_League)
INSERT INTO Puntos_Equipos (Cod_Competicion, Cod_Equipo, Puntos) VALUES (1, 1, 0);
INSERT INTO Puntos_Equipos (Cod_Competicion, Cod_Equipo, Puntos) VALUES (1, 2, 0);
INSERT INTO Puntos_Equipos (Cod_Competicion, Cod_Equipo, Puntos) VALUES (1, 3, 0);
INSERT INTO Puntos_Equipos (Cod_Competicion, Cod_Equipo, Puntos) VALUES (1, 4, 0);

-- Competición 2 (CS_go_League)
INSERT INTO Puntos_Equipos (Cod_Competicion, Cod_Equipo, Puntos) VALUES (2, 5, 0);
INSERT INTO Puntos_Equipos (Cod_Competicion, Cod_Equipo, Puntos) VALUES (2, 6, 0);
INSERT INTO Puntos_Equipos (Cod_Competicion, Cod_Equipo, Puntos) VALUES (2, 7, 0);
INSERT INTO Puntos_Equipos (Cod_Competicion, Cod_Equipo, Puntos) VALUES (2, 8, 0);

-- Competición 3 (Valorant_league)
INSERT INTO Puntos_Equipos (Cod_Competicion, Cod_Equipo, Puntos) VALUES (3, 9, 0);
INSERT INTO Puntos_Equipos (Cod_Competicion, Cod_Equipo, Puntos) VALUES (3, 10, 0);
INSERT INTO Puntos_Equipos (Cod_Competicion, Cod_Equipo, Puntos) VALUES (3, 11, 0);
INSERT INTO Puntos_Equipos (Cod_Competicion, Cod_Equipo, Puntos) VALUES (3, 12, 0);

-- Competición 4 (R6_league)
INSERT INTO Puntos_Equipos (Cod_Competicion, Cod_Equipo, Puntos) VALUES (4, 13, 0);
INSERT INTO Puntos_Equipos (Cod_Competicion, Cod_Equipo, Puntos) VALUES (4, 14, 0);
INSERT INTO Puntos_Equipos (Cod_Competicion, Cod_Equipo, Puntos) VALUES (4, 15, 0);
INSERT INTO Puntos_Equipos (Cod_Competicion, Cod_Equipo, Puntos) VALUES (4, 16, 0);


commit;


-- Creaci�n de Patrocinadores
INSERT INTO Patrocinadores (nombre, cod_Equipo)
VALUES ('Egibide', 01);
INSERT INTO Patrocinadores (nombre, cod_Equipo)
VALUES ( 'BBVA', 03);
INSERT INTO Patrocinadores (nombre, cod_Equipo)
VALUES ('Ford', 04);
INSERT INTO Patrocinadores (nombre, cod_Equipo)
VALUES ('Digi', 03);
INSERT INTO Patrocinadores (nombre, cod_Equipo)
VALUES ('Adidas', 01);
INSERT INTO Patrocinadores (nombre, cod_Equipo)
VALUES ('Eroski', 02);

commit;

-- Creaci�n de Jugadores
DECLARE
    v_nombre VARCHAR2(50);
    v_apellido VARCHAR2(50);
    v_salario NUMBER := 1400;
    v_nacionalidad VARCHAR2(50);
BEGIN
    FOR i IN 1..16 LOOP
        FOR j IN 1..4 LOOP
            CASE i
                WHEN 1 THEN
                    v_nombre := 'Jugador' || (j + (i - 1) * 4);
                    v_apellido := 'Apellido' || (j + (i - 1) * 4);
                    v_nacionalidad := 'Nacionalidad' || (j + (i - 1) * 4);
                WHEN 2 THEN
                    v_nombre := 'Jugador' || (j + (i - 1) * 4);
                    v_apellido := 'Apellido' || (j + (i - 1) * 4);
                    v_nacionalidad := 'Nacionalidad' || (j + (i - 1) * 4);
                WHEN 3 THEN
                    v_nombre := 'Jugador' || (j + (i - 1) * 4);
                    v_apellido := 'Apellido' || (j + (i - 1) * 4);
                    v_nacionalidad := 'Nacionalidad' || (j + (i - 1) * 4);
                WHEN 4 THEN
                    v_nombre := 'Jugador' || (j + (i - 1) * 4);
                    v_apellido := 'Apellido' || (j + (i - 1) * 4);
                    v_nacionalidad := 'Nacionalidad' || (j + (i - 1) * 4);
                WHEN 5 THEN
                    v_nombre := 'Jugador' || (j + (i - 1) * 4);
                    v_apellido := 'Apellido' || (j + (i - 1) * 4);
                    v_nacionalidad := 'Nacionalidad' || (j + (i - 1) * 4);
                WHEN 6 THEN
                    v_nombre := 'Jugador' || (j + (i - 1) * 4);
                    v_apellido := 'Apellido' || (j + (i - 1) * 4);
                    v_nacionalidad := 'Nacionalidad' || (j + (i - 1) * 4);
                WHEN 7 THEN
                    v_nombre := 'Jugador' || (j + (i - 1) * 4);
                    v_apellido := 'Apellido' || (j + (i - 1) * 4);
                    v_nacionalidad := 'Nacionalidad' || (j + (i - 1) * 4);
                WHEN 8 THEN
                    v_nombre := 'Jugador' || (j + (i - 1) * 4);
                    v_apellido := 'Apellido' || (j + (i - 1) * 4);
                    v_nacionalidad := 'Nacionalidad' || (j + (i - 1) * 4);
                WHEN 9 THEN
                    v_nombre := 'Jugador' || (j + (i - 1) * 4);
                    v_apellido := 'Apellido' || (j + (i - 1) * 4);
                    v_nacionalidad := 'Nacionalidad' || (j + (i - 1) * 4);
                WHEN 10 THEN
                    v_nombre := 'Jugador' || (j + (i - 1) * 4);
                    v_apellido := 'Apellido' || (j + (i - 1) * 4);
                    v_nacionalidad := 'Nacionalidad' || (j + (i - 1) * 4);
                WHEN 11 THEN
                    v_nombre := 'Jugador' || (j + (i - 1) * 4);
                    v_apellido := 'Apellido' || (j + (i - 1) * 4);
                    v_nacionalidad := 'Nacionalidad' || (j + (i - 1) * 4);
                WHEN 12 THEN
                    v_nombre := 'Jugador' || (j + (i - 1) * 4);
                    v_apellido := 'Apellido' || (j + (i - 1) * 4);
                    v_nacionalidad := 'Nacionalidad' || (j + (i - 1) * 4);
                WHEN 13 THEN
                    v_nombre := 'Jugador' || (j + (i - 1) * 4);
                    v_apellido := 'Apellido' || (j + (i - 1) * 4);
                    v_nacionalidad := 'Nacionalidad' || (j + (i - 1) * 4);
                WHEN 14 THEN
                    v_nombre := 'Jugador' || (j + (i - 1) * 4);
                    v_apellido := 'Apellido' || (j + (i - 1) * 4);
                    v_nacionalidad := 'Nacionalidad' || (j + (i - 1) * 4);
                WHEN 15 THEN
                    v_nombre := 'Jugador' || (j + (i - 1) * 4);
                    v_apellido := 'Apellido' || (j + (i - 1) * 4);
                    v_nacionalidad := 'Nacionalidad' || (j + (i - 1) * 4);
                WHEN 16 THEN
                    v_nombre := 'Jugador' || (j + (i - 1) * 4);
                    v_apellido := 'Apellido' || (j + (i - 1) * 4);
                    v_nacionalidad := 'Nacionalidad' || (j + (i - 1) * 4);
            END CASE;

            INSERT INTO Jugadores (nombre, apellido, salario, nacionalidad, fecha_nacimiento, nickname, rol, cod_Equipo)
            VALUES (v_nombre, v_apellido, v_salario, v_nacionalidad, TO_DATE('01/01/1990','DD/MM/YYYY'), 'Nick' || j, 'Rol' || j, i);
        END LOOP;
    END LOOP;
END;
/



commit;

-- Creaci�n de Staffs
INSERT INTO Staffs (cod, nombre, apellido, salario, puesto, cod_Equipo)
VALUES(1,'Ibai', 'Lopez de Lapuente', '1453', 'Entrenador', 1);
INSERT INTO Staffs (cod, nombre, apellido, salario, puesto, cod_Equipo)
VALUES(2,'Rodrigo', 'Urbina', '1353', 'Entrenador asistente', 2);
INSERT INTO Staffs ( cod, nombre, apellido, salario, puesto, cod_Equipo)
VALUES(3,'Sofia', 'Ugarte', '1343', 'Entrenador asistente', 3);
INSERT INTO Staffs ( cod, nombre, apellido, salario, puesto, cod_Equipo)
VALUES(4,'Carla', 'Rodriguez', '1567', 'Entrenador', 4);
INSERT INTO Staffs ( cod, nombre, apellido, salario, puesto, cod_Equipo)
VALUES(5,'Juan Alberto', 'Domenech', '1197', 'Entrenador', 5);
INSERT INTO Staffs ( cod, nombre, apellido, salario, puesto, cod_Equipo)
VALUES(6,'Maria Luisa', 'Garcia', '1837', 'Entrenador', 6);
INSERT INTO Staffs ( cod, nombre, apellido, salario, puesto, cod_Equipo)
VALUES(7,'Jorge', 'Cremades', '1934', 'Entrenador', 7);
INSERT INTO Staffs ( cod, nombre, apellido, salario, puesto, cod_Equipo)
VALUES(8,'Ibai', 'Lopez de Lapuente', '1453', 'Entrenador', 8);
INSERT INTO Staffs ( cod, nombre, apellido, salario, puesto, cod_Equipo)
VALUES(9,'Rodrigo', 'Urbina', '1353', 'Entrenador asistente', 9);
INSERT INTO Staffs ( cod, nombre, apellido, salario, puesto, cod_Equipo)
VALUES(10,'Sofia', 'Ugarte', '1343', 'Entrenador asistente', 10);
INSERT INTO Staffs ( cod, nombre, apellido, salario, puesto, cod_Equipo)
VALUES(11,'Carla', 'Rodriguez', '1567', 'Entrenador', 11);
INSERT INTO Staffs ( cod, nombre, apellido, salario, puesto, cod_Equipo)
VALUES(12,'Juan Alberto', 'Domenech', '1197', 'Entrenador', 12);
INSERT INTO Staffs ( cod, nombre, apellido, salario, puesto, cod_Equipo)
VALUES(13,'Maria Luisa', 'Garcia', '1837', 'Entrenador', 13);
INSERT INTO Staffs ( cod, nombre, apellido, salario, puesto, cod_Equipo)
VALUES(14,'Jorge', 'Cremades', '1934', 'Entrenador', 14);
INSERT INTO Staffs ( cod, nombre, apellido, salario, puesto, cod_Equipo)
VALUES(15,'Maria Luisa', 'Garcia', '1837', 'Entrenador', 15);
INSERT INTO Staffs ( cod, nombre, apellido, salario, puesto, cod_Equipo)
VALUES(16,'Jorge', 'Cremades', '1934', 'Entrenador', 16);

commit;

-- Creaci�n de Usuarios
INSERT INTO Usuarios ( nickname, password, es_admin)
VALUES ( 'Admin', 'admin123', 1);
INSERT INTO Usuarios ( nickname, password, es_admin)
VALUES ( 'Erik', 'erik123', 1);
INSERT INTO Usuarios (nickname, password, es_admin)
VALUES ( 'Ibai', 'ibai123', 0);
INSERT INTO Usuarios (nickname, password, es_admin)
VALUES ( 'Rodrigo', 'rodigo123', 0);
INSERT INTO Usuarios (nickname, password, es_admin)
VALUES ( 'Oskar', 'oskar123', 0);

commit;