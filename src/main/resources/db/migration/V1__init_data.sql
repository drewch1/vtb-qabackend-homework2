create table authors (id bigserial primary key, name varchar(255));
insert into authors (name)
values
('Author1'),
('Author2');

create table books (id bigserial primary key, title varchar(255), price int, author_id bigint references authors (id));
insert into books (title, price, author_id)
values
('Book1', 195, 1),
('Book2', 125, 1),
('Book3', 240, 2);