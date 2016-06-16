CREATE DATABASE hotelaria;

CREATE TABLE usuarios(
    id_usuario serial NOT NULL,
    nome_usuario varchar(60) NOT NULL,
    cpf char(14) NOT NULL,
    email varchar(60) NOT NULL,
    telefone varchar(15) NOT NULL,
    senha varchar(10) NOT NULL,
    endereco varchar(60) NOT NULL,
    numero varchar(5) NOT NULL,
    cep char(10) NOT NULL,
    bairro varchar(30) NOT NULL,
    cidade varchar (60) NOT NULL,
    estado char(2) NOT NULL,
    tipo_usuario varchar(15) NOT NULL, --Para tipo de usuário, 1 define o nível comum e 2 o nível administrador
    PRIMARY KEY (id_usuario)
);

CREATE TABLE quartos(
    id_quarto serial NOT NULL,
    numero_quarto varchar(60) NOT NULL,
    tipo_quarto varchar(10) NOT NULL,
    valor_diaria decimal(6,2) NOT NULL,
    informacoes text NOT NULL,
    PRIMARY KEY (id_quarto)
);

CREATE TABLE reservas(
    id_reserva serial NOT NULL,
    id_cliente int NOT NULL REFERENCES usuarios (id_usuario),
    id_quarto int NOT NULL REFERENCES quartos (id_quarto),
    data_hora_entrada timestamp NOT NULL,
    data_hora_saida timestamp NOT NULL,
    PRIMARY KEY (id_reserva)
);
