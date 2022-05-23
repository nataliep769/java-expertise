The database has the following schema in SQL:

create table node (id integer not null, label varchar(255), parent_id integer, primary key (id))
alter table node add constraint {constraint ID} foreign key (parent_id) references node