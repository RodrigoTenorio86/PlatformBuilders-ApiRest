CREATE DATABASE IF NOT EXISTS plaform;
use plaform;


CREATE TABLE IF NOT EXISTS cliente ( id bigint not null auto_increment,cpf varchar(255),data_nascimento date,idade integer not null,nome varchar(255) not null,primary key (id) ) engine=MyISAM

insert into cliente(nome,cpf,data_nascimento,idade) values('Jose maria', '12345678900','2019-11-05',0);
