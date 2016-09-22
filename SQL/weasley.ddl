DROP TABLE customer;

CREATE TABLE customer (
	customerId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
	firstName VARCHAR(50),
	lastName VARCHAR(50),
	phoneNumber VARCHAR(20),
	email VARCHAR(50)
);

INSERT INTO customer (firstName, lastName, phoneNumber, email) VALUES
	('Harry', 'Potter', '+44 0206 949-1948', 'harry@hogwarts.ac.uk');
INSERT INTO customer (firstName, lastName, phoneNumber, email) VALUES
	('Ron', 'Weasley', '+44 0206 949-4882', 'ron@hogwarts.ac.uk');
INSERT INTO customer (firstName, lastName, phoneNumber, email) VALUES
	('Hermione', 'Granger', '+44 0206 949-2466', 'hermione@hogwarts.ac.uk');

SELECT * FROM customer;
