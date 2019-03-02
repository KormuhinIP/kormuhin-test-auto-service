create table CLIENT
(
  ID           BIGINT identity primary key,
  FIRST_NAME   varchar(255),
  LAST_NAME    varchar(255),
  PATRONYMIC   varchar(255),
  NUMBER_PHONE varchar(255)
);
INSERT INTO CLIENT(FIRST_NAME, LAST_NAME, PATRONYMIC, NUMBER_PHONE)
VALUES ('Шеянова', 'Екатерина', 'Андреевна', '+7-999-888-7777');
INSERT INTO CLIENT(FIRST_NAME, LAST_NAME, PATRONYMIC, NUMBER_PHONE)
VALUES ('Головин', 'Семен', 'Аркадич', '+7-777-888-7777');
INSERT INTO CLIENT(FIRST_NAME, LAST_NAME, PATRONYMIC, NUMBER_PHONE)
VALUES ('Антипов', 'Аркадий', 'Семенович', '+7-909-808-7077');
INSERT INTO CLIENT(FIRST_NAME, LAST_NAME, PATRONYMIC, NUMBER_PHONE)
VALUES ('Голоша', 'Семен', 'Павлович', '+7-700-888-7777');
INSERT INTO CLIENT(FIRST_NAME, LAST_NAME, PATRONYMIC, NUMBER_PHONE)
VALUES ('Звездина', 'Екатерина', 'Петровна', '+8-999-999-7777');
INSERT INTO CLIENT(FIRST_NAME, LAST_NAME, PATRONYMIC, NUMBER_PHONE)
VALUES ('Гнязев', 'Андрей', 'Аркадич', '+7-111-222-7777');
INSERT INTO CLIENT(FIRST_NAME, LAST_NAME, PATRONYMIC, NUMBER_PHONE)
VALUES ('Половинкина', 'Мария', 'Андреевна', '+7-999-000-0000');
INSERT INTO CLIENT(FIRST_NAME, LAST_NAME, PATRONYMIC, NUMBER_PHONE)
VALUES ('Тявкин', 'Семен', 'Семенович', '+7-777-999-7798');
create table MECHANIC
(
  ID         BIGINT identity primary key,
  FIRST_NAME varchar(255),
  LAST_NAME  varchar(255),
  PATRONYMIC varchar(255),
  HOURLY_PAY double not null
);
INSERT INTO MECHANIC(first_name, last_name, patronymic, hourly_pay)
VALUES ('Веревкин', 'Сергей', 'Дмитриевич', 100.0);
INSERT INTO MECHANIC(first_name, last_name, patronymic, hourly_pay)
VALUES ('Пупкин', 'Артем', 'Генадьевич', 150.0);
INSERT INTO MECHANIC(first_name, last_name, patronymic, hourly_pay)
VALUES ('Полов', 'Максим', '', 200.0);
INSERT INTO MECHANIC(first_name, last_name, patronymic, hourly_pay)
VALUES ('Владимиров', 'Анатолий', 'Данилович', 300.0);
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