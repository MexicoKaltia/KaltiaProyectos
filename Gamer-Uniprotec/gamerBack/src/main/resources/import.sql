INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('administrador','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq',1, 'administrador', '1','administrador@correo.com');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('instructor','$2a$10$RmdEsvEfhI7Rcm9f/uZXPebZVCcPC7ZXZwV51efAvMAp1rIaRAfPK',1, 'instructor', '1','instructor@correo.com');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('audiencia','$2a$10$RmdEsvEfhI7Rcm9f/uZXPebZVCcPC7ZXZwV51efAvMAp1rIaRAfPK',1, 'audiencia', '1','audiencia@correo.com');

INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO `roles` (nombre) VALUES ('ROLE_INSTR');
INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');


INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (3, 3);