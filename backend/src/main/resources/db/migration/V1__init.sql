create table users (
    id bigserial primary key,
    phone varchar(20) not null unique,
    role varchar(20) not null,
    name varchar(120) not null,
    created_at timestamp not null default now()
);

create table cars (
    id bigserial primary key,
    user_id bigint not null references users(id),
    brand varchar(80) not null,
    model varchar(80) not null,
    year integer not null,
    vin varchar(32) not null
);

create table services (
    id bigserial primary key,
    name varchar(120) not null,
    description text,
    duration_minutes integer not null,
    base_price integer not null
);

create table service_stations (
    id bigserial primary key,
    name varchar(120) not null,
    address varchar(255) not null,
    lat double precision not null,
    lng double precision not null,
    owner_id bigint not null references users(id)
);

create table station_services (
    station_id bigint not null references service_stations(id),
    service_id bigint not null references services(id),
    primary key (station_id, service_id)
);

create table masters (
    id bigserial primary key,
    user_id bigint not null references users(id),
    station_id bigint not null references service_stations(id),
    rating numeric(3,2) not null default 5.0
);

create table orders (
    id bigserial primary key,
    client_id bigint not null references users(id),
    master_id bigint not null references masters(id),
    car_id bigint not null references cars(id),
    service_id bigint not null references services(id),
    status varchar(30) not null,
    start_time timestamp not null,
    end_time timestamp not null,
    total_price integer not null,
    client_rating integer,
    created_at timestamp not null default now()
);

create index idx_cars_user_id on cars(user_id);
create index idx_masters_user_id on masters(user_id);
create index idx_masters_station_id on masters(station_id);
create index idx_orders_client_id on orders(client_id);
create index idx_orders_master_id on orders(master_id);
