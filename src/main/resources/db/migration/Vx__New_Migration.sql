create table category (id int8 not null, name varchar(255) not null, parent_id int8, primary key (id));
create table item (id int8 not null, bn_price int4, description varchar(255), image varchar(255), name varchar(255) not null, price int4, url varchar(255), vendor varchar(255), category_id int8 not null, primary key (id));
alter table category add constraint FK2y94svpmqttx80mshyny85wqr foreign key (parent_id) references category;
alter table item add constraint FK2n9w8d0dp4bsfra9dcg0046l4 foreign key (category_id) references category;
