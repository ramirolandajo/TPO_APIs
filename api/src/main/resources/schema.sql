CREATE TABLE usuarios(
	idUsuario BIGINT AUTO_INCREMENT,
	usuario  VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
	cuil VARCHAR(27),
	nombreCompleto VARCHAR(100),
	tipoUsuario VARCHAR(20),
	
	CONSTRAINT PK_idUsuario PRIMARY KEY (idUsuario)
);

CREATE TABLE edificios(
	idEdificio BIGINT AUTO_INCREMENT,
	direccion  VARCHAR(100) NOT NULL,
	CONSTRAINT PK_idEdificio PRIMARY KEY (idEdificio)
);

CREATE TABLE unidades(
	idUnidad  BIGINT AUTO_INCREMENT,
	piso INT NOT NULL,
	numero INT NOT NULL,
	duenio BIGINT NOT NULL,
	inquilino BIGINT,
	edificio BIGINT NOT NULL,

	CONSTRAINT PK_idUnidad PRIMARY KEY (idUnidad),
    CONSTRAINT FK_duenio FOREIGN KEY (duenio) REFERENCES usuarios(idUsuario),
	CONSTRAINT FK_inquilino FOREIGN KEY (inquilino) REFERENCES usuarios(idUsuario),
	CONSTRAINT FK_edificio_Unidad FOREIGN KEY (edificio) REFERENCES edificios(idEdificio)
);

CREATE TABLE espacios_comunes(
	idEspacioComun BIGINT AUTO_INCREMENT,
	piso INT,
	descripcion VARCHAR(100),
	edificio BIGINT,

	CONSTRAINT PK_idEspacioComun PRIMARY KEY (idEspacioComun),
	CONSTRAINT FK_edificio_espaciosComunes FOREIGN KEY (edificio) REFERENCES edificios(idEdificio)
);

CREATE TABLE reclamos(
	idReclamo BIGINT AUTO_INCREMENT,
	estado VARCHAR(100) NOT NULL,
	descripcion VARCHAR(200) NOT NULL,
	usuario BIGINT NOT NULL,
	edificio BIGINT NOT NULL,
	unidad BIGINT,
	espacioComun BIGINT,

	CONSTRAINT PK_id_reclamo PRIMARY KEY (idReclamo),
	CONSTRAINT FK_usuario FOREIGN KEY (usuario) REFERENCES usuarios(idUsuario),
	CONSTRAINT FK_edificio_reclamo FOREIGN KEY (edificio) REFERENCES edificios(idEdificio),
	CONSTRAINT FK_unidad_reclamo FOREIGN KEY (unidad) REFERENCES unidades(idUnidad),
	CONSTRAINT FK_espacioComun_reclamo FOREIGN KEY (espacioComun) REFERENCES espacios_comunes(idEspacioComun)
);

