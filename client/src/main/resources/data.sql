insert into user_entity (id, login, password, private_key, public_key, address)
    values
        (1, 's1ckret', '1',
        '303e020100301006072a8648ce3d020106052b8104000a042730250201010420bbe8feee370cb315d1541b2a721e39e7ddfee81e29fcb4585c919f518dfb5b16',
        '3056301006072a8648ce3d020106052b8104000a03420004d069446b811b97d1a355e8ba59bf044e64b990adaea6f7d3ff1e20214b0f3063a689d7223c7adcb2dc2e138c678267b6b0f13e1dab99bf9db12346c4886e0264',
        'ff0a1ae640a87d48baf91011db16d2e70ae628c2'),
        (2, 'tanos', '2',
        '303e020100301006072a8648ce3d020106052b8104000a042730250201010420d943a1ea695696510d1c7e7f6076b1f0e2b65c84bc61407f0be301db7119cdf0',
        '3056301006072a8648ce3d020106052b8104000a03420004a2cf472c8f512ecf240f4a5cb21ed4e201422ebbc6200a3fb2d4ef3917c9a0ed62bb0c043c1e38e4335e1caf69f082b8f7b82244a9d6d53b3c596b5efe4ac3bb',
        '7960d74d75b70095301a102ec83e63312589e885');

insert into interlocutor (id, nickname, address, public_key, user_id)
    values
        (1, 'Anrew', '0xacab', '0x123456789', 1),
        (2, 'Cap', '0xcabaddadad', '0xdef1dasfer', 1),
        (3, 'Levy', '0x241d1s14', '0x23f111s', 2),
        (4, 's1ckret', 'ff0a1ae640a87d48baf91011db16d2e70ae628c2', '3056301006072a8648ce3d020106052b8104000a03420004d069446b811b97d1a355e8ba59bf044e64b990adaea6f7d3ff1e20214b0f3063a689d7223c7adcb2dc2e138c678267b6b0f13e1dab99bf9db12346c4886e0264', 1);

insert into envelope (id, address_from, address_to, interlocutor_id)
    values
        (1, 'ff0a1ae640a87d48baf91011db16d2e70ae628c2', '0xacab', 1),
        (2, '0xacab', 'ff0a1ae640a87d48baf91011db16d2e70ae628c2', 1),
        (3, 'ff0a1ae640a87d48baf91011db16d2e70ae628c2', '0xacab', 1),
        (4, '0xacab', 'ff0a1ae640a87d48baf91011db16d2e70ae628c2', 1),

        (5, 'ff0a1ae640a87d48baf91011db16d2e70ae628c2', '0xcabaddadad', 2),
        (6, '0xcabaddadad', 'ff0a1ae640a87d48baf91011db16d2e70ae628c2', 2),

        (7, '7960d74d75b70095301a102ec83e63312589e885', '0x241d1s14', 3),
        (8, '0x241d1s14', '7960d74d75b70095301a102ec83e63312589e885', 3),
        (9, '7960d74d75b70095301a102ec83e63312589e885', '0x241d1s14', 3);

insert into letter (envelope_id, create_time, text)
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
