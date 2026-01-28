-- Mock data for local/dev usage
insert into users (id, phone, role, name, created_at) values
    (1, '+10000000001', 'OWNER', 'Alexei Morozov', now()),
    (2, '+10000000002', 'OWNER', 'Marina Volkova', now()),
    (3, '+10000000003', 'MASTER', 'Ivan Petrov', now()),
    (4, '+10000000004', 'MASTER', 'Olga Sidorova', now()),
    (5, '+10000000005', 'CLIENT', 'Pavel Smirnov', now()),
    (6, '+10000000006', 'CLIENT', 'Elena Ivanova', now());

insert into services (id, name, description, duration_minutes, base_price) values
    (1, 'Oil change', 'Engine oil and filter replacement', 45, 2500),
    (2, 'Brake diagnostics', 'Brake system inspection', 30, 1500),
    (3, 'Tire swap', 'Seasonal tire change', 40, 2000),
    (4, 'AC recharge', 'Air conditioning service', 60, 3500);

insert into service_stations (id, name, address, lat, lng, owner_id) values
    (1, 'AutoHub Central', '12 Main St, Springfield', 40.7128, -74.0060, 1),
    (2, 'AutoHub West', '45 Oak Ave, Springfield', 40.7306, -73.9352, 2);

insert into station_services (station_id, service_id) values
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 2),
    (2, 3),
    (2, 4);

insert into masters (id, user_id, station_id, rating) values
    (1, 3, 1, 4.7),
    (2, 4, 2, 4.9);

insert into cars (id, user_id, brand, model, year, vin) values
    (1, 5, 'Toyota', 'Camry', 2018, 'JT1234567890ABC01'),
    (2, 6, 'Volkswagen', 'Golf', 2016, 'WV0987654321DEF02');

insert into orders (id, client_id, master_id, car_id, service_id, status, start_time, end_time, total_price, client_rating, created_at) values
    (1, 5, 1, 1, 1, 'COMPLETED', now() - interval '5 days', now() - interval '5 days' + interval '45 minutes', 2500, 5, now() - interval '5 days'),
    (2, 6, 2, 2, 3, 'COMPLETED', now() - interval '2 days', now() - interval '2 days' + interval '40 minutes', 2000, 4, now() - interval '2 days'),
    (3, 5, 1, 1, 2, 'CREATED', now() + interval '1 day', now() + interval '1 day' + interval '30 minutes', 1500, null, now());

select setval('users_id_seq', (select max(id) from users));
select setval('services_id_seq', (select max(id) from services));
select setval('service_stations_id_seq', (select max(id) from service_stations));
select setval('masters_id_seq', (select max(id) from masters));
select setval('cars_id_seq', (select max(id) from cars));
select setval('orders_id_seq', (select max(id) from orders));
