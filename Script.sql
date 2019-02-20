CREATE TABLE persona(
	
	idpersona VARCHAR(20) NOT NULL PRIMARY KEY,
	nombres VARCHAR(30),
	apellidos VARCHAR(30),
	fechanacimiento DATE,
	telefono VARCHAR(13),
	sexo VARCHAR(10),
	salario NUMERIC(6,2),
	cupo INT
	
);



CREATE TABLE usuarios(

	idusuario serial NOT NULL PRIMARY KEY,
	username VARCHAR(20),
	password bytea,
	nombre VARCHAR(30),
	foto bytea

);
