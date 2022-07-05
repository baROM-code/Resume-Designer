create table users
(
    id            bigserial primary key,
    photo         varchar(200),
    userfirstname varchar(36) not null,
    userlastname  varchar(36) not null,
    password      varchar(16) not null,
    gender        char        not null,
    dateofbirth   timestamp,
    email         varchar(50) unique,
    created_at    timestamp default current_timestamp,
    updated_at    timestamp default current_timestamp
);

create table working
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

create table education
(
    id           bigserial primary key,
    user_id      bigint       not null references users (id),
    organization varchar(150) not null,
    speciality   varchar(100) not null,
    year_end     date         not null,
    created_at   timestamp default current_timestamp,
    updated_at   timestamp default current_timestamp
);

create table resume
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



