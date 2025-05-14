create database if not exists todo_lists;
use todo_lists;

drop table if exists todo_lists, todo_list_items;

create table todo_lists (
	id int primary key auto_increment,
    name varchar(255)
);


create table todo_list_items (
	id int primary key auto_increment,
    text varchar(255),
    todo_list_id int,
    completed boolean,
    foreign key (todo_list_id) references todo_lists(id)
);