INSERT INTO client (name)
VALUES
	('Client_1'),
	('Client_2'),
	('Client_3'),
	('Client_4'),
	('Client_5'),
	('Client_6'),
	('Client_7'),
	('Client_8'),
	('Client_9'),
	('Client_10');

INSERT INTO planet (id, name)
VALUES
	('PL1', 'Planet_1'),
	('PL2', 'Planet_2'),
	('PL3', 'Planet_3'),
	('PL4', 'Planet_4'),
	('PL5', 'Planet_5');

INSERT INTO ticket (client_id, from_planet_id, to_planet_id)
VALUES
    (2, 'PL1', 'PL3'),
    (1, 'PL3', 'PL2'),
    (3, 'PL2', 'PL4'),
    (4, 'PL5', 'PL3'),
    (2, 'PL1', 'PL3'),
    (10, 'PL1', 'PL3'),
    (8, 'PL1', 'PL3'),
    (7, 'PL4', 'PL1'),
    (5, 'PL1', 'PL3'),
    (4, 'PL1', 'PL3');