/* Populate tabla clientes */

INSERT INTO regiones (id, name) VALUES (1, 'Queretaro');
INSERT INTO regiones (id, name) VALUES (2, 'Leon');
INSERT INTO regiones (id, name) VALUES (3, 'Guanajuato');
INSERT INTO regiones (id, name) VALUES (4, 'Irapuato');
INSERT INTO regiones (id, name) VALUES (5, 'Celaya');
INSERT INTO regiones (id, name) VALUES (6, 'CDMX');
INSERT INTO regiones (id, name) VALUES (7, 'San Luis Potosi');
INSERT INTO regiones (id, name) VALUES (8, 'Guadalajara');

INSERT INTO clientes (region_id, name, apellido, email, create_at) VALUES(1, 'John', 'Lennon', 'profesor@gmail.com', '2018-01-01');
INSERT INTO clientes (region_id, name, apellido, email, create_at) VALUES(2, 'Mr. John', 'Doe', 'john.doe@gmail.com', '2018-01-02');
INSERT INTO clientes (region_id, name, apellido, email, create_at) VALUES(4, 'Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2018-01-03');
INSERT INTO clientes (region_id, name, apellido, email, create_at) VALUES(4, 'Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2018-01-04');
INSERT INTO clientes (region_id, name, apellido, email, create_at) VALUES(4, 'Erich', 'Gamma', 'erich.gamma@gmail.com', '2018-02-01');
INSERT INTO clientes (region_id, name, apellido, email, create_at) VALUES(3, 'Richard', 'Helm', 'richard.helm@gmail.com', '2018-02-10');
INSERT INTO clientes (region_id, name, apellido, email, create_at) VALUES(3, 'Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2018-02-18');
INSERT INTO clientes (region_id, name, apellido, email, create_at) VALUES(3, 'John', 'Vlissides', 'john.vlissides@gmail.com', '2018-02-28');
INSERT INTO clientes (region_id, name, apellido, email, create_at) VALUES(3, 'Dr. James', 'Gosling', 'james.gosling@gmail.com', '2018-03-03');
INSERT INTO clientes (region_id, name, apellido, email, create_at) VALUES(5, 'Magma', 'Lee', 'magma.lee@gmail.com', '2018-03-04');
INSERT INTO clientes (region_id, name, apellido, email, create_at) VALUES(6, 'Tornado', 'Roe', 'tornado.roe@gmail.com', '2018-03-05');
INSERT INTO clientes (region_id, name, apellido, email, create_at) VALUES(7, 'Jade', 'Doe', 'jane.doe@gmail.com', '2018-03-06');

/* Creamos algunos usuarios con sus roles */
INSERT INTO `usuarios` (username, password, enabled, name, apellido, email) VALUES ('olivier','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq',1, 'Olivier', 'Sanchez','profesor@gmail.com');
INSERT INTO `usuarios` (username, password, enabled, name, apellido, email) VALUES ('admin','$2a$10$RmdEsvEfhI7Rcm9f/uZXPebZVCcPC7ZXZwV51efAvMAp1rIaRAfPK',1, 'Admin', 'Uno','jhon.doe@gmail.com');

INSERT INTO `roles` (name) VALUES ('ROLE_USER');
INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');

INSERT INTO `modulos` (name) VALUES ('ADMINISTRACION');
INSERT INTO `modulos` (name) VALUES ('AGENDA');
INSERT INTO `modulos` (name) VALUES ('LOGISTICA');
INSERT INTO `modulos` (name) VALUES ('USUARIOS');

INSERT INTO `submodulos` (name) VALUES ('SUB1');
INSERT INTO `submodulos` (name) VALUES ('SUB2');
INSERT INTO `submodulos` (name) VALUES ('SUB3');
INSERT INTO `submodulos` (name) VALUES ('SUB4');
INSERT INTO `submodulos` (name) VALUES ('SUB5');
INSERT INTO `submodulos` (name) VALUES ('SUB6');

INSERT INTO `instructores` (name) VALUES ('SUB1');
INSERT INTO `instructores` (name) VALUES ('Ing. Javier Castillo');
INSERT INTO `instructores` (name) VALUES ('Ing. Cesar Vazquez');
INSERT INTO `instructores` (name) VALUES ('Ing. Carlos Dominguez');
INSERT INTO `instructores` (name) VALUES ('Ing. Antonio Gomez');
INSERT INTO `instructores` (name) VALUES ('Ing. Gerardo Moreno');
INSERT INTO `instructores` (name) VALUES ('Lic. Mayra Padron');
INSERT INTO `instructores` (name) VALUES ('Lic. Alejandra Campos');
INSERT INTO `instructores` (name) VALUES ('Ing. Alberto Zuñiga');
INSERT INTO `instructores` (name) VALUES ('Ing. Paola Govea');
INSERT INTO `instructores` (name) VALUES ('Ing. Ricardo Velazquez');
INSERT INTO `instructores` (name) VALUES ('Ing. Diana Vazquez');
INSERT INTO `instructores` (name) VALUES ('Ing. Ramon Noriega');
INSERT INTO `instructores` (name) VALUES ('Ing. Fernando Mares');
INSERT INTO `instructores` (name) VALUES ('Ing. Maximino Garcia');
INSERT INTO `instructores` (name) VALUES ('Ing. Rafael Cebada');
INSERT INTO `instructores` (name) VALUES ('Externo');

INSERT INTO `cursos` (name) VALUES ('OPERADOR DE MONTACARGAS');
INSERT INTO `cursos` (name) VALUES ('OPERADOR DE MONTACARGAS (ORDER PICKER)');
INSERT INTO `cursos` (name) VALUES ('OPERADOR DE GRUAS VIAJERAS Y POLIPASTOS');
INSERT INTO `cursos` (name) VALUES ('OPERADOR DE GRUAS VIAJERAS');
INSERT INTO `cursos` (name) VALUES ('OPERADOR DE POLIPASTOS');
INSERT INTO `cursos` (name) VALUES ('OPERADOR DE GRUA MOVIL');
INSERT INTO `cursos` (name) VALUES ('OPERADOR DE PATIN ELECTRICO');
INSERT INTO `cursos` (name) VALUES ('OPERADOR DE PATIN HIDRAULICO');
INSERT INTO `cursos` (name) VALUES ('OPERADOR DE PLATAFORMA (COLCHON DE AIRE)');
INSERT INTO `cursos` (name) VALUES ('OPERADOR DE PATIN (MOVER TRUCK)');
INSERT INTO `cursos` (name) VALUES ('OPERADOR DE TRACTOR REMOLQUE CLASE VI (TUGGER)');
INSERT INTO `cursos` (name) VALUES ('OPERADOR DE PLATAFORMA ELEVADORA');
INSERT INTO `cursos` (name) VALUES ('OPERADOR DE EQUIPO MOVIL TODO TERRENO (POLARIS)');
INSERT INTO `cursos` (name) VALUES ('PLAN DE IZAJE');
INSERT INTO `cursos` (name) VALUES ('SEGURIDAD EN EL MANEJO DE MAQUINARIA, EQUIPO Y HERRAMIENTAS NOM-004-STPS');
INSERT INTO `cursos` (name) VALUES ('MANEJO Y ALMACENAMIENTO DE MATERIALES NOM-006-STPS');
INSERT INTO `cursos` (name) VALUES ('BLOQUEO DE ENERGIA LOTO');
INSERT INTO `cursos` (name) VALUES ('MANEJO DE SUSTANCIAS QUIMICAS ');
INSERT INTO `cursos` (name) VALUES ('MANEJO DE RESIDUOS PELIGROSOS');
INSERT INTO `cursos` (name) VALUES ('SEGURIDAD EN TRABAJOS EN ALTURA NOM 009 STPS');
INSERT INTO `cursos` (name) VALUES ('CONDICIONES DE RUIDO EN LOS CENTROS DE TRABAJO NOM 011 STPS');
INSERT INTO `cursos` (name) VALUES ('EQUIPO DE PROTECCION PERSONAL EPP NOM 017 STPS');
INSERT INTO `cursos` (name) VALUES ('SGA SISTEMA GLOBALMENTE ARMONIZADO NOM 018 STPS');
INSERT INTO `cursos` (name) VALUES ('COMISION DE SEGURIDAD E HIGIENE NOM 019 STPS');
INSERT INTO `cursos` (name) VALUES ('OPERADOR DE RECIPIENTES SUJETOS A PRESION NOM 020 STPS');
INSERT INTO `cursos` (name) VALUES ('OPERACIÓN SEGURA Y FUNCIONAMIENTO DE CALDERAS NOM 020 STPS');
INSERT INTO `cursos` (name) VALUES ('OPERADOR DE RECIPIENTES SUJETOS A PRESION Y CALDERAS NOM 020 STPS');
INSERT INTO `cursos` (name) VALUES ('ELECTRICIDAD ESTATICA EN LOS CENTROS DE TRABAJO CONDICIONES DE SEGURIDAD NOM- 022-STPS-2015 ');
INSERT INTO `cursos` (name) VALUES ('CONDICIONES DE ILUMINACION EN LOS CENTROS DE TRABAJO NOM 025 STPS');
INSERT INTO `cursos` (name) VALUES ('INTERPRETACION DE LOS ELEMENTOS DE SEÑALIZACION NOM 026 STPS');
INSERT INTO `cursos` (name) VALUES ('SEGURIDAD EN CORTE Y SOLDADURA NOM 027-STPS');
INSERT INTO `cursos` (name) VALUES ('PROCESOS Y EQUIPOS CRITICOS QUE MANEJAN SUSTANCIAS QUIMICAS NOM 028 STPS');
INSERT INTO `cursos` (name) VALUES ('SEGURIDAD EN MANTENIMIENTO A INSTALACIONES ELECTRICAS NOM 029 STPS');
INSERT INTO `cursos` (name) VALUES ('SERVICIOS DE SEGURIDAD Y SALUD EN EL TRABAJO NOM-030-STPS ');
INSERT INTO `cursos` (name) VALUES ('SEGURIDAD EN TRABAJOS EN ESPACIOS CONFINADOS NOM-033-STPS-2015');
INSERT INTO `cursos` (name) VALUES ('FACTORES DE RIESGOS PSICOSOCIALES EN EL TRABAJO, IDENTIFICACION, ANALISIS Y PREVENCION NOM-035-STPS-2018');
INSERT INTO `cursos` (name) VALUES ('MANEJO DEFENSIVO');
INSERT INTO `cursos` (name) VALUES ('LEGISLACION AMBIENTAL');
INSERT INTO `cursos` (name) VALUES ('LEGISLACION, SEGURIDAD HIGIENE Y OCUPACIONAL');
INSERT INTO `cursos` (name) VALUES ('IDENTIDIFICACION DE PELIGROS Y EVALUACION DE RIESGO');
INSERT INTO `cursos` (name) VALUES ('INVESTIGACION DE ACCIDENTES E INCIDENTES');
INSERT INTO `cursos` (name) VALUES ('ANALISIS DE CAUSA RAIZ Y DETERMINACION DE PLANES DE ACCION');
INSERT INTO `cursos` (name) VALUES ('FACTORES DE RIESGO ERGONOMICO NOM-036-STPS ');
INSERT INTO `cursos` (name) VALUES ('SALUD OCUPACIONAL (ATMOSFERAS EXPLOSIVAS ATEX)');
INSERT INTO `cursos` (name) VALUES ('MANEJO DE GAS NATURAL GAS LP');
INSERT INTO `cursos` (name) VALUES ('FORMACION DE SUPERVISORES');
INSERT INTO `cursos` (name) VALUES ('TRABAJO EN CALIENTE');
INSERT INTO `cursos` (name) VALUES ('CUIDADO DE LAS MANOS');
INSERT INTO `cursos` (name) VALUES ('SEGURIDAD EN EL MANEJO DE HORNOS');
INSERT INTO `cursos` (name) VALUES ('MANTENIMIENTO A PLANTAS DE TRATAMIENTO DE AGUAS RESIDUALES');
INSERT INTO `cursos` (name) VALUES ('8 DS');
INSERT INTO `cursos` (name) VALUES ('SIX SIGMA ');
INSERT INTO `cursos` (name) VALUES ('MANTENIMIENTO PRODUCTIVO TOTAL (TPM LEAN MANUFACTURING)');
INSERT INTO `cursos` (name) VALUES ('LAS 5S');
INSERT INTO `cursos` (name) VALUES ('PROCESOS DE SOLDADURA ');
INSERT INTO `cursos` (name) VALUES ('PLC ');
INSERT INTO `cursos` (name) VALUES ('INTSTRUMENTOS DE MEDICION');
INSERT INTO `cursos` (name) VALUES ('MAQUINAS Y HERRAMIENTAS');
INSERT INTO `cursos` (name) VALUES ('ELECTRICIDAD INDUSTRIAL');
INSERT INTO `cursos` (name) VALUES ('MANTENIMIENTO A SUBESTACIONES ELECTRICAS');
INSERT INTO `cursos` (name) VALUES ('ELECTRONICA INDUSTRIAL');
INSERT INTO `cursos` (name) VALUES ('NEUMATICA');
INSERT INTO `cursos` (name) VALUES ('HIDRAULICA');
INSERT INTO `cursos` (name) VALUES ('ELECTRO NEUMATICA');
INSERT INTO `cursos` (name) VALUES ('SISTEMAS DE TIERRA Y PARARRAYOS');
INSERT INTO `cursos` (name) VALUES ('AIRE ACONDICIONADO');
INSERT INTO `cursos` (name) VALUES ('CONTROL DE MOTORES CONTROL ELECTRICO');
INSERT INTO `cursos` (name) VALUES ('FORMACION DE BRIGADA DE COMBATE CONTRA INCENDIO');
INSERT INTO `cursos` (name) VALUES ('MANEJO DE EXTINTORES');
INSERT INTO `cursos` (name) VALUES ('FORMACION DE BRIGADA DE PRIMEROS AUXILIOS');
INSERT INTO `cursos` (name) VALUES ('FORMACION DE BRIGADA DE COMUNICACION, EVACUACION Y RESCATE');
INSERT INTO `cursos` (name) VALUES ('COMUNICACIÓN SCI');
INSERT INTO `cursos` (name) VALUES ('RESPUESTA A EMERRGENCIA DERRAMES');
INSERT INTO `cursos` (name) VALUES ('BRIGADA MULTIFUNCIONAL');
INSERT INTO `cursos` (name) VALUES ('RESCATE EN ALTURAS Y ESPACIOS CONFINADOS');
INSERT INTO `cursos` (name) VALUES ('FORMACION DE INSTRUCTORES');
INSERT INTO `cursos` (name) VALUES ('HABITOS DE LA GENTE EFECTIVA');
INSERT INTO `cursos` (name) VALUES ('COMUNICACIÓN EFECTIVA');
INSERT INTO `cursos` (name) VALUES ('MOTIVACION');
INSERT INTO `cursos` (name) VALUES ('TRABAJO EN EQUIPO');
INSERT INTO `cursos` (name) VALUES ('LIDERAZGO');


INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 1);

INSERT INTO `usuarios_modulos` (usuario_id, modulo_id) VALUES (1,1);
INSERT INTO `usuarios_modulos` (usuario_id, modulo_id) VALUES (1,2);
INSERT INTO `usuarios_modulos` (usuario_id, modulo_id) VALUES (1,3);
INSERT INTO `usuarios_modulos` (usuario_id, modulo_id) VALUES (2,1);
INSERT INTO `usuarios_modulos` (usuario_id, modulo_id) VALUES (2,2);


INSERT INTO `modulos_submodulos` (modulo_id, submodulo_id) VALUES (1,1);
INSERT INTO `modulos_submodulos` (modulo_id, submodulo_id) VALUES (1,2);
INSERT INTO `modulos_submodulos` (modulo_id, submodulo_id) VALUES (1,3);
INSERT INTO `modulos_submodulos` (modulo_id, submodulo_id) VALUES (2,1);
INSERT INTO `modulos_submodulos` (modulo_id, submodulo_id) VALUES (2,2);


