CREATE TABLE address (
	address_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
	line1 VARCHAR(50),
	line2 VARCHAR(50),
	city VARCHAR(50),
	state VARCHAR(50),
	zip VARCHAR(50)
);

INSERT INTO address (line1, line2, city, state, zip) VALUES
	('#4 Privet Drive', 'Cupboard Under the Stairs', 'Little Whinging', 'Surrey', 'EN1912');
INSERT INTO address (line1, line2, city, state, zip) VALUES
	('#4 Privet Drive', 'The Smallest Bedroom', 'Little Whinging', 'Surrey', 'EN1912');
INSERT INTO address (line1, line2, city, state, zip) VALUES
	('The Burrow', 'Topmost room', 'Ottery St. Catchpole', 'Wessex', 'EN9842');
INSERT INTO address (line1, line2, city, state, zip) VALUES
	('Gryffindor Tower', 'Boys Dorm', 'Hogwarts', 'Hogsmeade', 'SC93992');
INSERT INTO address (line1, line2, city, state, zip) VALUES
	('Gryffindor Tower', 'Boys Dorm', 'Hogwarts', 'Hogsmeade', 'SC93992');