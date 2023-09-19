CREATE TABLE usuarios(
	id_usuario BIGINT IDENTITY,
	usuario  VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
	cuil VARCHAR(27),
	nombre_completo VARCHAR(100),
	tipo_usuario VARCHAR(20),
	
	CONSTRAINT PK_id_usuario PRIMARY KEY (id_usuario)
);

CREATE TABLE edificios(
	id_edificio BIGINT IDENTITY,
	direccion  VARCHAR(100) NOT NULL,
	CONSTRAINT PK_idEdificio PRIMARY KEY (id_edificio)
);

CREATE TABLE unidades(
	id_unidad  BIGINT IDENTITY,
	piso INT NOT NULL,
	numero INT NOT NULL,
	duenio BIGINT NOT NULL,
	inquilino BIGINT,
	edificio BIGINT NOT NULL,

	CONSTRAINT PK_id_unidad PRIMARY KEY (id_unidad),
    CONSTRAINT FK_duenio FOREIGN KEY (duenio) REFERENCES usuarios(id_usuario),
	CONSTRAINT FK_inquilino FOREIGN KEY (inquilino) REFERENCES usuarios(id_usuario),
	CONSTRAINT FK_edificio_Unidad FOREIGN KEY (edificio) REFERENCES edificios(id_edificio)
);

CREATE TABLE espacios_comunes(
	id_espacio_comun BIGINT IDENTITY,
	piso INT,
	descripcion VARCHAR(100),
	edificio BIGINT,

	CONSTRAINT PK_id_espacio_comun PRIMARY KEY (id_espacio_comun),
	CONSTRAINT FK_edificio_espaciosComunes FOREIGN KEY (edificio) REFERENCES edificios(id_edificio)
);

CREATE TABLE reclamos(
	id_reclamo BIGINT IDENTITY,
	estado VARCHAR(100),
	descripcion VARCHAR(100),
	usuario BIGINT,
	edificio BIGINT,
	unidad BIGINT,

	CONSTRAINT PK_id_reclamo PRIMARY KEY (id_reclamo),
	CONSTRAINT FK_usuario FOREIGN KEY (usuario) REFERENCES usuarios(id_usuario),
	CONSTRAINT FK_edificio_reclamo FOREIGN KEY (edificio) REFERENCES edificios(id_edificio),
	CONSTRAINT FK_unidad_reclamo FOREIGN KEY (unidad) REFERENCES unidades(id_unidad)
);

