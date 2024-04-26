INSERT INTO Juegos (cod, nombre, desarrolladora, fecha_lanzamiento)
VALUES (01, 'League of Legends', 'Riot Games', TO_DATE('27/10/2009','DD/MM/YYYY'));
INSERT INTO Juegos (cod, nombre, desarrolladora, fecha_lanzamiento)
VALUES (02, 'Counter Strike', 'Valbe', TO_DATE('27/09/2023','DD/MM/YYYY'));
INSERT INTO Juegos (cod, nombre, desarrolladora, fecha_lanzamiento)
VALUES (03, 'Valorant', 'Riot Games', TO_DATE('02/06/2020','DD/MM/YYYY'));
INSERT INTO Juegos (cod, nombre, desarrolladora, fecha_lanzamiento)
VALUES (04, 'Rainbow six siege', 'Ubisoft', TO_DATE('01/12/2015','DD/MM/YYYY'));

commit;

-- Creación de Competiciones
INSERT INTO Competiciones (cod, nombre, fecha_inicio, fecha_fin, estado_abierto, cod_Juego)
VALUES (0101, 'European League', TO_DATE('01/06/2024','DD/MM/YYYY') , TO_DATE('01/12/2024','DD/MM/YYYY'), 1, 01);
INSERT INTO Competiciones (cod, nombre, fecha_inicio, fecha_fin, estado_abierto, cod_Juego)
VALUES (0102, 'American League', TO_DATE('01/10/2024','DD/MM/YYYY') , TO_DATE('01/02/2025','DD/MM/YYYY'), 1, 01);
INSERT INTO Competiciones (cod, nombre, fecha_inicio, fecha_fin, estado_abierto, cod_Juego)
VALUES (0201, 'Deutsche Liga', TO_DATE('28/12/2023','DD/MM/YYYY') , TO_DATE('09/05/2024','DD/MM/YYYY'), 0, 02);
INSERT INTO Competiciones (cod, nombre, fecha_inicio, fecha_fin, estado_abierto, cod_Juego)
VALUES (0202, 'Ligue Française', TO_DATE('30/03/2024','DD/MM/YYYY') , TO_DATE('01/07/2024','DD/MM/YYYY'), 0, 02);
INSERT INTO Competiciones (cod, nombre, fecha_inicio, fecha_fin, estado_abierto, cod_Juego)
VALUES (0103, 'Liga Española', TO_DATE('12/05/2024','DD/MM/YYYY') , TO_DATE('23/11/2024','DD/MM/YYYY'), 1, 01);
INSERT INTO Competiciones (cod, nombre, fecha_inicio, fecha_fin, estado_abierto, cod_Juego)
VALUES (0301, 'World League', TO_DATE('24/10/2024','DD/MM/YYYY') , TO_DATE('13/04/2025','DD/MM/YYYY'), 1, 03);
INSERT INTO Competiciones (cod, nombre, fecha_inicio, fecha_fin, estado_abierto, cod_Juego)
VALUES (0302, 'American League', TO_DATE('12/04/2024','DD/MM/YYYY') , TO_DATE('11/08/2024','DD/MM/YYYY'), 0, 03);

commit;

-- Creación de Jornadas
INSERT INTO Jornadas (cod, n_jornada, fecha, cod_Juego, cod_Competicion)
VALUES (010101, 01, TO_DATE('01/06/2024','DD/MM/YYYY'), 01, 0101);
INSERT INTO Jornadas (cod, n_jornada, fecha, cod_Juego, cod_Competicion)
VALUES (010102, 02, TO_DATE('08/06/2024','DD/MM/YYYY'), 01, 0101);
INSERT INTO Jornadas (cod, n_jornada, fecha, cod_Juego, cod_Competicion)
VALUES (010203, 03, TO_DATE('15/10/2024','DD/MM/YYYY'), 01, 0102);
INSERT INTO Jornadas (cod, n_jornada, fecha, cod_Juego, cod_Competicion)
VALUES (020201, 01, TO_DATE('30/03/2024','DD/MM/YYYY'), 02, 0202);
INSERT INTO Jornadas (cod, n_jornada, fecha, cod_Juego, cod_Competicion)
VALUES (020106, 06, TO_DATE('01/02/2024','DD/MM/YYYY'), 02, 0201);
INSERT INTO Jornadas (cod, n_jornada, fecha, cod_Juego, cod_Competicion)
VALUES (030101, 01, TO_DATE('24/10/2024','DD/MM/YYYY'), 03, 0301);
INSERT INTO Jornadas (cod, n_jornada, fecha, cod_Juego, cod_Competicion)
VALUES (030204, 04, TO_DATE('03/05/2024','DD/MM/YYYY'), 03, 0302);

commit;

-- Creación de Enfrentamientos
INSERT INTO Enfrentamientos (cod, hora, gana_local, cod_Juego, cod_Competicion, cod_Jornada, cod_equipo_local, cod_equipo_visitante)
VALUES(01010101, TO_DATE('16:00','HH24:MI'), 1, 01, 0101, 010101, 01, 02);
INSERT INTO Enfrentamientos (cod, hora, gana_local, cod_Juego, cod_Competicion, cod_Jornada, cod_equipo_local, cod_equipo_visitante)
VALUES(01010103, TO_DATE('19:00','HH24:MI'), 0, 01, 0101, 010101, 04, 03);
INSERT INTO Enfrentamientos (cod, hora, gana_local, cod_Juego, cod_Competicion, cod_Jornada, cod_equipo_local, cod_equipo_visitante)
VALUES(01020302, TO_DATE('17:00','HH24:MI'), 1, 01, 0102, 010203, 05, 01);
INSERT INTO Enfrentamientos (cod, hora, gana_local, cod_Juego, cod_Competicion, cod_Jornada, cod_equipo_local, cod_equipo_visitante)
VALUES(02010606, TO_DATE('22:00','HH24:MI'), 0, 02, 0201, 020106, 04, 05);
INSERT INTO Enfrentamientos (cod, hora, gana_local, cod_Juego, cod_Competicion, cod_Jornada, cod_equipo_local, cod_equipo_visitante)
VALUES(03010104, TO_DATE('20:00','HH24:MI'), 0, 03, 0301, 030101, 01, 03);
INSERT INTO Enfrentamientos (cod, hora, gana_local, cod_Juego, cod_Competicion, cod_Jornada, cod_equipo_local, cod_equipo_visitante)
VALUES(03020401, TO_DATE('16:00','HH24:MI'), 1, 03, 0302, 030204, 04, 02);

commit;

-- Creación de Puntos-Equipos
INSERT INTO puntos_equipos (cod_Equipo, cod_Competicion, puntos)
VALUES (01, 0101, 5);
INSERT INTO puntos_equipos (cod_Equipo, cod_Competicion, puntos)
VALUES (02, 0101, 3);
INSERT INTO puntos_equipos (cod_Equipo, cod_Competicion, puntos)
VALUES (03, 0101, 7);
INSERT INTO puntos_equipos (cod_Equipo, cod_Competicion, puntos)
VALUES (04, 0101, 1);
INSERT INTO puntos_equipos (cod_Equipo, cod_Competicion, puntos)
VALUES (01, 0102, 9);
INSERT INTO puntos_equipos (cod_Equipo, cod_Competicion, puntos)
VALUES (05, 0102, 3);
INSERT INTO puntos_equipos (cod_Equipo, cod_Competicion, puntos)
VALUES (03, 0202, 9);
INSERT INTO puntos_equipos (cod_Equipo, cod_Competicion, puntos)
VALUES (02, 0202, 4);
INSERT INTO puntos_equipos (cod_Equipo, cod_Competicion, puntos)
VALUES (04, 0202, 7);
INSERT INTO puntos_equipos (cod_Equipo, cod_Competicion, puntos)
VALUES (05, 0202, 1);
INSERT INTO puntos_equipos (cod_Equipo, cod_Competicion, puntos)
VALUES (01, 0301, 2);
INSERT INTO puntos_equipos (cod_Equipo, cod_Competicion, puntos)
VALUES (03, 0301, 0);
INSERT INTO puntos_equipos (cod_Equipo, cod_Competicion, puntos)
VALUES (02, 0302, 1);
INSERT INTO puntos_equipos (cod_Equipo, cod_Competicion, puntos)
VALUES (04, 0302, 3);

commit;

-- Creación de Equipos
INSERT INTO Equipos ( nombre, fecha_fundacion)
VALUES('Koi', TO_DATE('05/12/2021','DD/MM/YYYY'));
INSERT INTO Equipos ( nombre, fecha_fundacion)
VALUES( 'G2', TO_DATE('31/07/2015','DD/MM/YYYY'));
INSERT INTO Equipos ( nombre, fecha_fundacion)
VALUES('Fanatic', TO_DATE('02/01/2017','DD/MM/YYYY'));
INSERT INTO Equipos ( nombre, fecha_fundacion)
VALUES( 'Liquid', TO_DATE('17/10/2012','DD/MM/YYYY'));
INSERT INTO Equipos ( nombre, fecha_fundacion)
VALUES( 'Astralis', TO_DATE('29/08/2020','DD/MM/YYYY'));

commit;

-- Creación de Patrocinadores
INSERT INTO Patrocinadores (cod_patrocinador, nombre, cod_Equipo)
VALUES (01, 'Egibide', 01);
INSERT INTO Patrocinadores (cod_patrocinador, nombre, cod_Equipo)
VALUES (02, 'BBVA', 03);
INSERT INTO Patrocinadores (cod_patrocinador, nombre, cod_Equipo)
VALUES (03, 'Ford', 04);
INSERT INTO Patrocinadores (cod_patrocinador, nombre, cod_Equipo)
VALUES (04, 'Digi', 05);
INSERT INTO Patrocinadores (cod_patrocinador, nombre, cod_Equipo)
VALUES (05, 'Adidas', 01);
INSERT INTO Patrocinadores (cod_patrocinador, nombre, cod_Equipo)
VALUES (06, 'Eroski', 02);

commit;

-- Creación de Jugadores
INSERT INTO Jugadores ( nombre, apellido, salario, nacionalidad, fecha_nacimiento, nickname, rol, cod_Equipo)
VALUES ('Erik', 'Ranea', 1131, 'España', TO_DATE('03/12/2000','DD/MM/YYYY'), 'Dele', 'Suport', 01);
INSERT INTO Jugadores ( nombre, apellido, salario, nacionalidad, fecha_nacimiento, nickname, rol, cod_Equipo)
VALUES ( 'Oskar', 'Perez', 1600, 'Euskadi', TO_DATE('13/01/2004','DD/MM/YYYY'), 'Osrez', 'Carry', 01);
INSERT INTO Jugadores ( nombre, apellido, salario, nacionalidad, fecha_nacimiento, nickname, rol, cod_Equipo)
VALUES ( 'Joaquin', 'Caparros', 1200, 'Argentina', TO_DATE('13/06/1998','DD/MM/YYYY'), 'Furto', 'Flex', 02);
INSERT INTO Jugadores ( nombre, apellido, salario, nacionalidad, fecha_nacimiento, nickname, rol, cod_Equipo)
VALUES ( 'Dereck', 'Fisher', 1400, 'EEUU', TO_DATE('24/07/1996','DD/MM/YYYY'), 'Date', 'Carry', 03);
INSERT INTO Jugadores ( nombre, apellido, salario, nacionalidad, fecha_nacimiento, nickname, rol, cod_Equipo)
VALUES ( 'Susana', 'Oria', 1427, 'Peru', TO_DATE('23/04/2003','DD/MM/YYYY'), 'Cric', 'Flex', 02);
INSERT INTO Jugadores ( nombre, apellido, salario, nacionalidad, fecha_nacimiento, nickname, rol, cod_Equipo)
VALUES ('Paco', 'Gerla', 1238, 'Chile', TO_DATE('24/07/1996','DD/MM/YYYY'), 'Cogela', 'Carry', 03);
INSERT INTO Jugadores (nombre, apellido, salario, nacionalidad, fecha_nacimiento, nickname, rol, cod_Equipo)
VALUES ('Ainhoa', 'Gaspis', 1612, 'Francia', TO_DATE('24/07/1996','DD/MM/YYYY'), 'Meona', 'Carry', 04);
INSERT INTO Jugadores (nombre, apellido, salario, nacionalidad, fecha_nacimiento, nickname, rol, cod_Equipo)
VALUES ('Elton', 'Tito', 1932, 'España', TO_DATE('24/07/1996','DD/MM/YYYY'), 'Listillo', 'Carry', 04);
INSERT INTO Jugadores (nombre, apellido, salario, nacionalidad, fecha_nacimiento, nickname, rol, cod_Equipo)
VALUES ('Dolores', 'Delano', 1529, 'Venezuela', TO_DATE('24/07/1996','DD/MM/YYYY'), 'Curate', 'Carry', 05);
INSERT INTO Jugadores (nombre, apellido, salario, nacionalidad, fecha_nacimiento, nickname, rol, cod_Equipo)
VALUES ('Zoyla', 'Vaca', 1712, 'Mexico', TO_DATE('24/07/1996','DD/MM/YYYY'), 'LaBurra', 'Carry', 05);

commit;

-- Creación de Staffs
INSERT INTO Staffs (nombre, apellido, salario, puesto, cod_Equipo)
VALUES('Ibai', 'Lopez de Lapuente', '1453', 'Entrenador', 01);
INSERT INTO Staffs (nombre, apellido, salario, puesto, cod_Equipo)
VALUES('Rodrigo', 'Urbina', '1353', 'Entrenador asistente', 01);
INSERT INTO Staffs (nombre, apellido, salario, puesto, cod_Equipo)
VALUES('Sofia', 'Ugarte', '1343', 'Entrenador asistente', 04);
INSERT INTO Staffs (nombre, apellido, salario, puesto, cod_Equipo)
VALUES('Carla', 'Rodriguez', '1567', 'Entrenador', 04);
INSERT INTO Staffs (nombre, apellido, salario, puesto, cod_Equipo)
VALUES('Juan Alberto', 'Domenech', '1197', 'Entrenador', 02);
INSERT INTO Staffs (nombre, apellido, salario, puesto, cod_Equipo)
VALUES('Maria Luisa', 'Garcia', '1837', 'Entrenador', 03);
INSERT INTO Staffs (nombre, apellido, salario, puesto, cod_Equipo)
VALUES('Jorge', 'Cremades', '1934', 'Entrenador', 05);

commit;

-- Creación de Usuarios
INSERT INTO Usuarios (nickname, contraseña, es_admin)
VALUES ('Admin', 'admin123', 1);
INSERT INTO Usuarios (nickname, contraseña, es_admin)
VALUES ('Erik', 'erik123', 1);
INSERT INTO Usuarios (nickname, contraseña, es_admin)
VALUES ('Ibai', 'ibai123', 0);
INSERT INTO Usuarios (nickname, contraseña, es_admin)
VALUES ('Rodrigo', 'rodigo123', 0);
INSERT INTO Usuarios (nickname, contraseña, es_admin)
VALUES ('Oskar', 'oskar123', 0);

commit;
