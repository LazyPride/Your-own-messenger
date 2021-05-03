insert into user_entity (id, login, password, private_key, public_key, address)
    values
        (1, 's1ckret', '123456789', '0xSercretPrivateKey', '0xPublic', '0xAddress'),
        (2, 'tanos', 'not_master', '0xDEADBEEF', '0xtea', '0xhi_there');

insert into letter (id, create_time, text)
    values
        (1, '2021-04-17 12:54:30+02:00', 'My maaan!'),
        (2, '2021-04-17 12:55:30+02:00', 'eeeelllloooo'),
        (3, '2021-04-17 12:55:32+02:00', 'how its going?'),
        (4, '2021-04-17 12:56:00+02:00', 'not bad u now, just chilin'),

        (5, '2021-04-19 12:00:30+02:00', 'hi'),
        (6, '2021-04-27 12:13:30+02:00', 'just ignored, ha-ha'),

        (7, '2021-04-21 12:55:30+02:00', 'lets try this mess'),
        (8, '2021-04-21 12:56:30+02:00', 'okey, idk, feels weird'),
        (9, '2021-04-21 12:57:30+02:00', 'very weird');

insert into interlocutor (id, nickname, address, public_key, user_id)
    values
        (1, 'Anrew', '0xacab', '0x123456789', 1),
        (2, 'Cap', '0xcabaddadad', '0xdef1dasfer', 1),
        (3, 'Levy', '0x241d1s14', '0x23f111s', 2);

insert into envelope (id, address_from, address_to, letter_id, interlocutor_id)
    values
        (1, '0xAddress', '0xacab', 1, 1),
        (2, '0xacab', '0xAddress', 2, 1),
        (3, '0xAddress', '0xacab', 3, 1),
        (4, '0xacab', '0xAddress', 4, 1),

        (5, '0xAddress', '0xcabaddadad', 5, 2),
        (6, '0xcabaddadad', '0xAddress', 6, 2),

        (7, '0xhi_there', '0x241d1s14', 7, 3),
        (8, '0x241d1s14', '0xhi_there', 8, 3),
        (9, '0xhi_there', '0x241d1s14', 9, 3);