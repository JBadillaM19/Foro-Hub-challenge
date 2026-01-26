CREATE TABLE topicos (
  id BIGINT NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(100) NOT NULL,
  mensaje VARCHAR(500) NOT NULL,
  nombre_curso VARCHAR(100) NOT NULL,
  fecha_creacion DATETIME NOT NULL,
  usuario_id BIGINT NOT NULL,

  PRIMARY KEY(id),
  CONSTRAINT fk_topicos_usuario_id FOREIGN KEY(usuario_id) REFERENCES usuarios(id)
);