CREATE DATABASE TPO_APIs;
drop DATABASE TPO_APIs;

CREATE TABLE usuarios(
	idUsuario BIGINT IDENTITY,
	usuario  VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
	cuil     VARCHAR(27),
	nombreCompleto VARCHAR(100),
	tipoUsuario VARCHAR(9),
	
	CONSTRAINT CHK_TIPOUSUARIO CHECK (tipoUsuario = 'admin' or tipoUsuario = 'duenio' or tipoUsuario = 'inquilino'),
	CONSTRAINT PK_idUsuario PRIMARY KEY (idUsuario)
);

CREATE TABLE edificios(
	idEdificio BIGINT IDENTITY,
	direccion  VARCHAR(100) NOT NULL,
	CONSTRAINT PK_idEdificio PRIMARY KEY (idEdificio)
);

CREATE TABLE unidades(
	idUnidad  BIGINT IDENTITY,
	piso INT NOT NULL,
	numero INT NOT NULL,
	duenio BIGINT NOT NULL,
	inquilino BIGINT,
	edificio BIGINT NOT NULL,

	CONSTRAINT PK_idUnidad PRIMARY KEY (idUnidad),
	CONSTRAINT FK_duenio FOREIGN KEY (duenio)
	REFERENCES usuarios(idUsuario),
	CONSTRAINT FK_inquilino FOREIGN KEY (inquilino)
	REFERENCES usuarios(idUsuario),
	CONSTRAINT FK_edificio_Unidad FOREIGN KEY (edificio)
	REFERENCES edificios(idEdificio)
);

CREATE TABLE espaciosComunes(
	idEspacioComun BIGINT IDENTITY,
	piso INT,
	descripcion VARCHAR(100),
	edificio BIGINT,

	CONSTRAINT PK_idEspacioComun PRIMARY KEY (idEspacioComun),
	CONSTRAINT FK_edificio_espaciosComunes FOREIGN KEY (edificio)
	REFERENCES edificios(idEdificio)
);

CREATE TABLE reclamos(
	idReclamo BIGINT IDENTITY,
	estado VARCHAR(100),
	descripcion VARCHAR(100),
	usuario BIGINT,
	edificio BIGINT,
	unidad BIGINT,

	CONSTRAINT PK_idReclamo PRIMARY KEY (idReclamo),
	CONSTRAINT FK_usuario FOREIGN KEY (usuario)
	REFERENCES usuarios(idUsuario),
	CONSTRAINT FK_edificio_reclamo FOREIGN KEY (edificio)
	REFERENCES edificios(idEdificio),
	CONSTRAINT FK_unidad_reclamo FOREIGN KEY (unidad)
	REFERENCES unidades(idunidad)	
);

