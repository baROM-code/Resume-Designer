create table users
(
    id            bigserial primary key,
    photo         varchar(200),
    firstname     varchar(36) not null,
    lastname      varchar(36) not null,
    password      varchar(80) not null,
    gender        char,
    dateofbirth   date,
    email         varchar(50) unique,
    enabled        boolean,
    created_at    timestamp default current_timestamp,
    updated_at    timestamp default current_timestamp
);

create table registration_tokens
(
    id            bigserial primary key,
    token         varchar(255),
    expired_at    timestamp not null ,
    user_id       bigint references users (id)
);

create table works
(
    id           bigserial primary key,
    user_id      bigint       references users (id),
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
    user_id      bigint        references users (id),
    organization varchar(150) not null,
    speciality   varchar(100) not null,
    year_start   date         not null,
    year_end     date         ,
    created_at   timestamp default current_timestamp,
    updated_at   timestamp default current_timestamp
);

create table resumes
(
    id           bigserial primary key,
    user_id      bigint        references users (id),
    post         varchar(150) not null,
    salary       bigint       not null,
    schedule     varchar(100) not null,
    about_myself text,
    created_at   timestamp default current_timestamp,
    updated_at   timestamp default current_timestamp
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table users_roles
(
    user_id    bigint not null references users (id),
    role_id    bigint not null references roles (id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (firstname, lastname, password, gender, dateofbirth, email, photo, enabled)
values ('Иван', 'Иванов', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'M', '1980-01-01', 'ivan@email.ru', 'https://pokleikaoboev.ru/wp-content/uploads/2015/10/infochel1.jpg', 'true'),
       ('Екатерина', 'Ли', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'M', '1985-05-01', 'katya@email.ru', 'https://hopeci.org/wp-content/uploads/2019/06/woman-blank-image.jpg', 'true');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 1);

insert into works (user_id, organization, post, startwork, endwork, progress)
values (1, 'R&k', 'Системный администратор', '2000-01-01', '2010-01-01', 'Много работал'),
       (1, '1C', 'Программист', '2011-01-01', '2015-01-01',
        'Обновлял и редактировал конфигурацию 1С, делал формы отчетов');

insert into educations (user_id, organization, speciality, year_start, year_end)
values (1, 'МГУ', 'информационные технологии', '2002-09-01', '2006-07-01'),
       (1, 'НГУ', 'системная инженерия', '2010-09-01', '2013-07-01');