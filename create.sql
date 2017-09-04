create table memo (id varchar(255) not null, created_date varchar(255), data varchar(255), location varchar(255), name varchar(255), status varchar(255), user_id varchar(255), primary key (id))
create table user (id varchar(255) not null, email varchar(255), name varchar(255), password varchar(255), username varchar(255), primary key (id))
alter table memo add constraint FK5vlmhksso19pshk0ac1qo1p82 foreign key (user_id) references user (id)
