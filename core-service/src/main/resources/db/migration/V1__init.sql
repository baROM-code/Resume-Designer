create table users
(
    id            bigserial primary key,
    photo         varchar(200),
    firstname varchar(36) not null,
    lastname  varchar(36) not null,
    password      varchar(16) not null,
    gender        char,
    dateofbirth   varchar(50),
    email         varchar(50) unique
--     created_at    timestamp default current_timestamp,
--     updated_at    timestamp default current_timestamp
);

create table works
(
    id           bigserial primary key,
    user_id      bigint      not null references users (id),
    organization varchar(90) not null,
    post         varchar(40) not null,
    startwork    date        not null,
    endwork      date,
    progress     text        not null,
    created_at   timestamp default current_timestamp,
    updated_at   timestamp default current_timestamp
);

create table educations
(
    id           bigserial primary key,
    user_id      bigint       not null references users (id),
    organization varchar(150) not null,
    speciality   varchar(100) not null,
    year_end     date         not null,
    created_at   timestamp default current_timestamp,
    updated_at   timestamp default current_timestamp
);

create table resumes
(
    id           bigserial primary key,
    user_id      bigint       not null references users (id),
    post         varchar(150) not null,
    salary       bigint       not null,
    schedule     varchar(100) not null,
    about_myself text,
    created_at   timestamp default current_timestamp,
    updated_at   timestamp default current_timestamp
);

insert into users (firstname, lastname, password, gender, dateofbirth, email)
values ('Иван', 'Иванов', '', 'M', '1980-01-01', 'ivan@email.ru');

insert into works (user_id, organization, post, startwork, endwork, progress)
values (1, 'R&k', 'Системный администратор', '2000-01-01', '2010-01-01', 'Много работал'),
       (1, '1C', 'Программист', '2011-01-01', '2015-01-01',
        'Обновлял и редактировал конфигурацию 1С, делал формы отчетов');
<<<<<<< HEAD

insert into educations (user_id, organization, speciality, year_end)
values (1, 'МГУ', 'информационные технологии', '2002-07-01');
=======
>>>>>>> Lesson_3

insert into educations (user_id, organization, speciality, year_end)
values (1, 'МГУ', 'информационные технологии', '2002-07-01');