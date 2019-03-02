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
  CLIENT_NAME     varchar(255),
  MECHANIC_ID     bigint,
  CLIENT_ID       bigint,
  DATE_CREATE     timestamp,
  DATE_COMPLETION timestamp,
  COST            double,
  STATUS_ORDER    varchar(255)
);