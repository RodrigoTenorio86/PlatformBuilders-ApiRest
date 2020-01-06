CREATE DATABASE IF NOT EXISTS plaform;
USE plaform;
--CREATE TABLE IF NOT EXISTS cliente (id bigint not null auto_increment, cpf varchar(255), data_nascimento date, idade integer not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
insert into cliente(nome,cpf,data_nascimento,idade) values('Jose maria', '12245608900','2019-11-05',0);
insert into cliente(nome,cpf,data_nascimento,idade) values('Jose ', '11345678900','2019-11-05',0);
insert into cliente(nome,cpf,data_nascimento,idade) values('Jose ', '00000000000','2019-11-05',0);
insert into cliente(nome,cpf,data_nascimento,idade) values('Rodrigo Tenorio', '12345678900','2019-11-05',0);
insert into usuario(nome,email,senha) values('Rodrigo Tenorio','rodrigo@com','$2a$10$F6AZ8ZnosXSdwhDDl4V5Su/rFspD4h6Lnncg7Li5rpAt51zs3hRd6');
