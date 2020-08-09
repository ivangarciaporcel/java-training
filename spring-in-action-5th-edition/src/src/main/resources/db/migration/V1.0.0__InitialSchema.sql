create sequence hibernate_sequence start 1 increment 1;
create table ingredient (id varchar(255) not null, name varchar(255), type varchar(255), primary key (id));
create table taco (id int8 not null, created_at timestamp, name varchar(255) not null, primary key (id));
create table taco_ingredients (taco_id int8 not null, ingredients_id varchar(255) not null);
create table taco_order (id int8 not null, cccvv varchar(255), cc_expiration varchar(255), cc_number varchar(255), city varchar(255), name varchar(255), placed_at timestamp, state varchar(255), street varchar(255), zip varchar(255), user_principal_id int8, primary key (id));
create table taco_order_tacos (order_id int8 not null, tacos_id int8 not null);
create table user_principal (id int8 not null, city varchar(255), fullname varchar(255), password varchar(255), phone_number varchar(255), state varchar(255), street varchar(255), username varchar(255), zip varchar(255), primary key (id));
alter table taco_ingredients add constraint FK7y679y77n5e75s3ss1v7ff14j foreign key (ingredients_id) references ingredient;
alter table taco_ingredients add constraint FK27rycuh3mjaepnba0j6m8xl4q foreign key (taco_id) references taco;
alter table taco_order add constraint FKiaodw6ojewrnft4bafxvhqxkt foreign key (user_principal_id) references user_principal;
alter table taco_order_tacos add constraint FKfwvqtnjfview9e5f7bfqtd1ns foreign key (tacos_id) references taco;
alter table taco_order_tacos add constraint FKcxwvdkndaqmrxcen10vkneexo foreign key (order_id) references taco_order;