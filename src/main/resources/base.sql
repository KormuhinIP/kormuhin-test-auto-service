create table CLIENT
(
  ID           BIGINT identity primary key,
  FIRST_NAME   varchar(255),
  LAST_NAME    varchar(255),
  PATRONYMIC   varchar(255),
  NUMBER_PHONE varchar(255)
);
INSERT INTO CLIENT(FIRST_NAME, LAST_NAME, PATRONYMIC, NUMBER_PHONE)
VALUES ('Екатерина', 'Шеянова', 'Андреевна', '+7-999-888-7777');
INSERT INTO CLIENT(FIRST_NAME, LAST_NAME, PATRONYMIC, NUMBER_PHONE)
VALUES ('Семен', 'Головин', 'Аркадич', '+7-777-888-7777');
INSERT INTO CLIENT(FIRST_NAME, LAST_NAME, PATRONYMIC, NUMBER_PHONE)
VALUES ('Аркадий', 'Антипов', 'Семенович', '+7-909-808-7077');
INSERT INTO CLIENT(FIRST_NAME, LAST_NAME, PATRONYMIC, NUMBER_PHONE)
VALUES ('Семен', 'Голоша', 'Павлович', '+7-700-888-7777');
INSERT INTO CLIENT(FIRST_NAME, LAST_NAME, PATRONYMIC, NUMBER_PHONE)
VALUES ('Екатерина', 'Звездина', 'Петровна', '+8-999-999-7777');
INSERT INTO CLIENT(FIRST_NAME, LAST_NAME, PATRONYMIC, NUMBER_PHONE)
VALUES ('Андрей', 'Гнязев', 'Аркадич', '+7-111-222-7777');
INSERT INTO CLIENT(FIRST_NAME, LAST_NAME, PATRONYMIC, NUMBER_PHONE)
VALUES ('Мария', 'Половинкина', 'Андреевна', '+7-999-000-0000');
INSERT INTO CLIENT(FIRST_NAME, LAST_NAME, PATRONYMIC, NUMBER_PHONE)
VALUES ('Семен', 'Тявкин', 'Семенович', '+7-777-999-7798');
create table MECHANIC
(
  ID         BIGINT identity primary key,
  FIRST_NAME varchar(255),
  LAST_NAME  varchar(255),
  PATRONYMIC varchar(255),
  HOURLY_PAY double not null
);
INSERT INTO MECHANIC(first_name, last_name, patronymic, hourly_pay)
VALUES ('Сергей', 'Веревкин', 'Дмитриевич', 100.0);
INSERT INTO MECHANIC(first_name, last_name, patronymic, hourly_pay)
VALUES ('Артем', 'Пупкин', 'Генадьевич', 150.0);
INSERT INTO MECHANIC(first_name, last_name, patronymic, hourly_pay)
VALUES ('Максим', 'Полов', '', 200.0);
INSERT INTO MECHANIC(first_name, last_name, patronymic, hourly_pay)
VALUES ('Анатолий', 'Владимиров', 'Данилович', 300.0);
create table ORDER_AUTO
(
  ID              BIGINT identity primary key,
  DESCRIPTION     varchar(255),
  MECHANIC_ID     bigint,
  CLIENT_ID       bigint,
  DATE_CREATE     timestamp,
  DATE_COMPLETION timestamp,
  COST            double,
  STATUS_ORDER    varchar(255)
);

insert into ORDER_AUTO(description, mechanic_id, client_id, date_create, date_completion, cost, status_order)
values ('Замена фары', 2, 2, '2019-02-20 12:10:00', '2019-02-20 19:20:00', 1400.0, 'Принят клиентом');
insert into ORDER_AUTO(description, mechanic_id, client_id, date_create, date_completion, cost, status_order)
values ('Ремонт стекла', 2, 0, '2019-02-21 10:30:00', '2019-02-21 15:20:00', 1000.0, 'Принят клиентом');
insert into ORDER_AUTO(description, mechanic_id, client_id, date_create, date_completion, cost, status_order)
values ('Ремонт проводки', 1, 1, '2019-02-21 17:10:00', '2019-02-21 21:30:00', 2500.0, 'Принят клиентом');
insert into ORDER_AUTO(description, mechanic_id, client_id, date_create, date_completion, cost, status_order)
values ('Замена радиатора', 0, 0, '2019-02-22 12:10:00', '2019-02-22 17:20:00', 3000.0, 'Принят клиентом');
insert into ORDER_AUTO(description, mechanic_id, client_id, date_create, date_completion, cost, status_order)
values ('Балансировка колес', 3, 3, '2019-02-23 10:00:00', '2019-02-23 15:00:00', 1000.0, 'Выполнен');
insert into ORDER_AUTO(description, mechanic_id, client_id, date_create, date_completion, cost, status_order)
values ('Замена дворников', 0, 4, '2019-02-23 14:10:00', '2019-02-23 17:20:00', 500.0, 'Принят клиентом');
insert into ORDER_AUTO(description, mechanic_id, client_id, date_create, date_completion, cost, status_order)
values ('Покраска капота', 2, 5, '2019-02-24 10:40:00', '2019-02-24 19:20:00', 5400.0, 'Выполнен');
insert into ORDER_AUTO(description, mechanic_id, client_id, date_create, date_completion, cost, status_order)
values ('Замена свечей', 2, 6, '2019-02-24 12:20:00', '2019-02-24 15:20:00', 1000.0, 'Запланирован');
insert into ORDER_AUTO(description, mechanic_id, client_id, date_create, date_completion, cost, status_order)
values ('Ремонт руля', 2, 7, '2019-02-24 17:00:00', '2019-02-24 18:00:00', 600.0, 'Запланирован');
insert into ORDER_AUTO(description, mechanic_id, client_id, date_create, date_completion, cost, status_order)
values ('Замена масла', 2, 1, '2019-02-24 19:30:00', '2019-02-24 21:20:00', 500.0, 'Запланирован');

