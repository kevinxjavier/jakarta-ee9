create database enterprise;

------------------------------------------

use enterprise;

------------------------------------------

create table productos(
	id int primary key auto_increment, 
	nombre varchar(30), 
	precio float, 
	fecha date
);

insert into productos (nombre, precio, fecha) values ('TV Samsung TH580', 568.99, '2023-05-28'), ('Raspberry Pi 4 8GB', 89.56, '2023-05-28');

------------------------------------------

create table categorias(
	id int primary key auto_increment, 
	nombre varchar(30)
);

insert into categorias (nombre) values ('Deporte'), ('Tecnología'), ('Computación'); 

------------------------------------------

alter table productos add column categoria_id int;

alter table productos add index producto_categorias_idx (categoria_id);  

alter table productos 
add constraint producto_categoria 
foreign key (categoria_id) 
references categorias(id)
on delete no action
on update no action;

------------------------------------------

update productos set categoria_id = 3;

------------------------------------------

ALTER TABLE productos ADD COLUMN sku VARCHAR(10) NULL AFTER categoria_id, ADD UNIQUE INDEX sku_unique (sku ASC) VISIBLE;

UPDATE productos SET sku = 'abcde12345' WHERE id = 1;
UPDATE productos SET sku = 'abcdf12345' WHERE id = 2;

------------------------------------------
-- RESUME
drop table productos;
truncate categorias;
create table productos(id int primary key auto_increment, nombre varchar(30), precio float, fecha date);
alter table productos add column categoria_id int;
alter table productos add index producto_categorias_idx (categoria_id);  
alter table productos add constraint producto_categoria foreign key (categoria_id) references categorias(id) on delete no action on update no action;
ALTER TABLE productos ADD COLUMN sku VARCHAR(10) NULL AFTER categoria_id, ADD UNIQUE INDEX sku_unique (sku ASC) VISIBLE;
insert into categorias (nombre) values ('Deporte'), ('Tecnologia'), ('Computacion');
insert into productos (nombre, precio, fecha) values ('TV Samsung TH580', 568.99, '2023-05-28'), ('Raspberry Pi 4 8GB', 89.56, '2023-05-28'); 
UPDATE productos SET sku = 'abcde12345' WHERE id = 1;
UPDATE productos SET sku = 'abcdf12345' WHERE id = 2;
update productos set categoria_id = 3;
select * from productos;
