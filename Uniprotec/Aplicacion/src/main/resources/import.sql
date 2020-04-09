INSERT INTO db_uniprotec.usuarios (username, password, enabled, name, apellido, email) VALUES ('admin','$2a$10$RmdEsvEfhI7Rcm9f/uZXPebZVCcPC7ZXZwV51efAvMAp1rIaRAfPK',1, 'Admin', 'Uno','uno@gmail.com');
INSERT INTO db_uniprotec.usuarios (username, password, enabled, name, apellido, email) VALUES ('olivier','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq',1, 'Olivier', 'Sanchez','operacion@gmail.com');

INSERT INTO db_uniprotec.roles (name) VALUES ('ROLE_USER');
INSERT INTO db_uniprotec.roles (name) VALUES ('ROLE_ADMIN');

INSERT INTO db_uniprotec.usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO db_uniprotec.usuarios_roles (usuario_id, role_id) VALUES (1, 2);
INSERT INTO db_uniprotec.usuarios_roles (usuario_id, role_id) VALUES (2, 2);
INSERT INTO db_uniprotec.usuarios_roles (usuario_id, role_id) VALUES (2, 1);

INSERT INTO db_uniprotec.modulos  VALUES (1	,'CLIENTES');
INSERT INTO db_uniprotec.modulos  VALUES (2	,'CURSOS');
INSERT INTO db_uniprotec.modulos  VALUES (3	,'INSTRUCTORES');
INSERT INTO db_uniprotec.modulos  VALUES (4	,'USUARIOS');
INSERT INTO db_uniprotec.modulos  VALUES (5	,'OPERACION');
INSERT INTO db_uniprotec.modulos  VALUES (6	,'LOGISTICA');
INSERT INTO db_uniprotec.modulos  VALUES (7	,'ADMINISTRACION');

INSERT INTO db_uniprotec.usuarios_modulos (usuario_id, modulo_id) VALUES (1,1);
INSERT INTO db_uniprotec.usuarios_modulos (usuario_id, modulo_id) VALUES (1,2);
INSERT INTO db_uniprotec.usuarios_modulos (usuario_id, modulo_id) VALUES (1,3);
INSERT INTO db_uniprotec.usuarios_modulos (usuario_id, modulo_id) VALUES (2,1);
INSERT INTO db_uniprotec.usuarios_modulos (usuario_id, modulo_id) VALUES (2,2);
INSERT INTO db_uniprotec.usuarios_modulos (usuario_id, modulo_id) VALUES (1,4);
INSERT INTO db_uniprotec.usuarios_modulos (usuario_id, modulo_id) VALUES (1,5);

insert into db_uniprotec.submodules values(1	,1	,'Alta Cliente' , 'ACliente');
insert into db_uniprotec.submodules values(2	,1	,'Edicion Cliente' , 'BCliente');
insert into db_uniprotec.submodules values(3	,1	,'Consulta Clientes' , 'CCliente');
insert into db_uniprotec.submodules values(4	,2	,'Alta Curso' , 'ACurso');
insert into db_uniprotec.submodules values(5	,2	,'Edicion Curso' , 'BCurso');
insert into db_uniprotec.submodules values(6	,2	,'Consulta Cursos' , 'CCurso');
insert into db_uniprotec.submodules values(7	,3	,'Alta Instructor' , 'AInstructor');
insert into db_uniprotec.submodules values(8	,3	,'Edicion Instructor' , 'BInstructor');
insert into db_uniprotec.submodules values(9	,3	,'Consulta Instructores' , 'CInstrunctor');
insert into db_uniprotec.submodules values(10	,4	,'Alta Usuario' , 'AUsuario');
insert into db_uniprotec.submodules values(11	,4	,'Edicion Usuario' , 'BUsuario');
insert into db_uniprotec.submodules values(12	,4	,'Consulta Ususarios' , 'CUsuario');

INSERT INTO db_uniprotec.modulos_submodulos (modulo_id, submodule_id) VALUES (1,1);
INSERT INTO db_uniprotec.modulos_submodulos (modulo_id, submodule_id) VALUES (1,2);
INSERT INTO db_uniprotec.modulos_submodulos (modulo_id, submodule_id) VALUES (1,3);
INSERT INTO db_uniprotec.modulos_submodulos (modulo_id, submodule_id) VALUES (2,4);
INSERT INTO db_uniprotec.modulos_submodulos (modulo_id, submodule_id) VALUES (2,5);
INSERT INTO db_uniprotec.modulos_submodulos (modulo_id, submodule_id) VALUES (3,7);
INSERT INTO db_uniprotec.modulos_submodulos (modulo_id, submodule_id) VALUES (3,8);
INSERT INTO db_uniprotec.modulos_submodulos (modulo_id, submodule_id) VALUES (3,9);
INSERT INTO db_uniprotec.modulos_submodulos (modulo_id, submodule_id) VALUES (4,10);
INSERT INTO db_uniprotec.modulos_submodulos (modulo_id, submodule_id) VALUES (4,11);
INSERT INTO db_uniprotec.modulos_submodulos (modulo_id, submodule_id) VALUES (4,12);


INSERT INTO db_uniprotec.regiones (id_region, nombre_region) VALUES (1, 'ESTADO DE SAN LUIS POTOSI');
INSERT INTO db_uniprotec.regiones (id_region, nombre_region) VALUES (2, 'ESTADO DE QUERETARO + CELAYA Y SAN JOSE ITURBIDE GTO + TEPEJI DEL RIO HIDALGO');
INSERT INTO db_uniprotec.regiones (id_region, nombre_region) VALUES (3, 'ESTADO DE GUANAJUATO MENOS CELAYA Y SAN JOSE ITURBIDE + LAGOS DE MORENO + EDO DE AGUASCALIENTES');
INSERT INTO db_uniprotec.regiones (id_region, nombre_region) VALUES (4, 'ESTADO DE JALISCO MENOS LAGOS DE MORENO');
INSERT INTO db_uniprotec.regiones (id_region, nombre_region) VALUES (5, 'ESTADO DE NUEVO LEON + ESTADO DE COAHUILA');
INSERT INTO db_uniprotec.regiones (id_region, nombre_region) VALUES (6, 'CIUDAD DE MEXICO + EDO DE MEXICO');
INSERT INTO db_uniprotec.regiones (id_region, nombre_region) VALUES (7, 'ESTADO DE PUEBLA + ESTADO DE TLAXCALA + ESTADO DE MORELOS');
INSERT INTO db_uniprotec.regiones (id_region, nombre_region) VALUES (8, 'CUALQUIER OTRA UBICACIÓN');

INSERT INTO db_uniprotec.instructores (nombre_instructor, region_instructor, nota_instructor, create_at_instructor, status_instructor, user_create_instructor) VALUES ('Ing. Javier Castillo',1,'nota','2020-04-05 15:24:15.183000','1',1);
INSERT INTO db_uniprotec.instructores (nombre_instructor, region_instructor, nota_instructor, create_at_instructor, status_instructor, user_create_instructor) VALUES ('Ing. Cesar Vazquez',1,'nota','2020-04-05 15:24:15.183000','1',1);
INSERT INTO db_uniprotec.instructores (nombre_instructor, region_instructor, nota_instructor, create_at_instructor, status_instructor, user_create_instructor) VALUES ('Ing. Carlos Dominguez',1,'nota','2020-04-05 15:24:15.183000','1',1);
INSERT INTO db_uniprotec.instructores (nombre_instructor, region_instructor, nota_instructor, create_at_instructor, status_instructor, user_create_instructor) VALUES ('Ing. Antonio Gomez',1,'nota','2020-04-05 15:24:15.183000','1',1);
INSERT INTO db_uniprotec.instructores (nombre_instructor, region_instructor, nota_instructor, create_at_instructor, status_instructor, user_create_instructor) VALUES ('Ing. Gerardo Moreno',1,'nota','2020-04-05 15:24:15.183000','1',1);
INSERT INTO db_uniprotec.instructores (nombre_instructor, region_instructor, nota_instructor, create_at_instructor, status_instructor, user_create_instructor) VALUES ('Lic. Mayra Padron',1,'nota','2020-04-05 15:24:15.183000','1',1);
INSERT INTO db_uniprotec.instructores (nombre_instructor, region_instructor, nota_instructor, create_at_instructor, status_instructor, user_create_instructor) VALUES ('Lic. Alejandra Campos',1,'nota','2020-04-05 15:24:15.183000','1',1);
INSERT INTO db_uniprotec.instructores (nombre_instructor, region_instructor, nota_instructor, create_at_instructor, status_instructor, user_create_instructor) VALUES ('Ing. Alberto Zuñiga',1,'nota','2020-04-05 15:24:15.183000','1',1);
INSERT INTO db_uniprotec.instructores (nombre_instructor, region_instructor, nota_instructor, create_at_instructor, status_instructor, user_create_instructor) VALUES ('Ing. Paola Govea',1,'nota','2020-04-05 15:24:15.183000','1',1);
INSERT INTO db_uniprotec.instructores (nombre_instructor, region_instructor, nota_instructor, create_at_instructor, status_instructor, user_create_instructor) VALUES ('Ing. Ricardo Velazquez',1,'nota','2020-04-05 15:24:15.183000','1',1);
INSERT INTO db_uniprotec.instructores (nombre_instructor, region_instructor, nota_instructor, create_at_instructor, status_instructor, user_create_instructor) VALUES ('Ing. Diana Vazquez',1,'nota','2020-04-05 15:24:15.183000','1',1);
INSERT INTO db_uniprotec.instructores (nombre_instructor, region_instructor, nota_instructor, create_at_instructor, status_instructor, user_create_instructor) VALUES ('Ing. Ramon Noriega',1,'nota','2020-04-05 15:24:15.183000','1',1);
INSERT INTO db_uniprotec.instructores (nombre_instructor, region_instructor, nota_instructor, create_at_instructor, status_instructor, user_create_instructor) VALUES ('Ing. Fernando Mares',1,'nota','2020-04-05 15:24:15.183000','1',1);
INSERT INTO db_uniprotec.instructores (nombre_instructor, region_instructor, nota_instructor, create_at_instructor, status_instructor, user_create_instructor) VALUES ('Ing. Maximino Garcia',1,'nota','2020-04-05 15:24:15.183000','1',1);
INSERT INTO db_uniprotec.instructores (nombre_instructor, region_instructor, nota_instructor, create_at_instructor, status_instructor, user_create_instructor) VALUES ('Ing. Rafael Cebada',1,'nota','2020-04-05 15:24:15.183000','1',1);
INSERT INTO db_uniprotec.instructores (nombre_instructor, region_instructor, nota_instructor, create_at_instructor, status_instructor, user_create_instructor) VALUES ('Externo',1,'nota','2020-04-05 15:24:15.183000','1',1);

INSERT INTO db_uniprotec.cursos (nombre_curso, nota_curso, user_create_curso, create_at_curso , status_curso ) VALUES ('OPERADOR DE MONTACARGAS','',1,'2020-04-05 15:24:15.183000','Creado');
INSERT INTO db_uniprotec.cursos (nombre_curso, nota_curso, user_create_curso, create_at_curso , status_curso ) VALUES ('OPERADOR DE MONTACARGAS (ORDER PICKER)','',1,'2020-04-05 15:24:15.183000','Creado');
INSERT INTO db_uniprotec.cursos (nombre_curso, nota_curso, user_create_curso, create_at_curso , status_curso ) VALUES ('OPERADOR DE GRUAS VIAJERAS Y POLIPASTOS','',1,'2020-04-05 15:24:15.183000','Creado');
INSERT INTO db_uniprotec.cursos (nombre_curso, nota_curso, user_create_curso, create_at_curso , status_curso ) VALUES ('OPERADOR DE GRUAS VIAJERAS','',1,'2020-04-05 15:24:15.183000','Creado');
INSERT INTO db_uniprotec.cursos (nombre_curso, nota_curso, user_create_curso, create_at_curso , status_curso ) VALUES ('OPERADOR DE POLIPASTOS','',1,'2020-04-05 15:24:15.183000','Creado');


