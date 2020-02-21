-- tested using https://www.db-fiddle.com/

-- creating example tables
CREATE TABLE customers (
    id INTEGER,
    name VARCHAR(255),
    description VARCHAR(255),
    PRIMARY KEY(id)
);

CREATE TABLE orders (
    id INTEGER,
    customer_id INTEGER,
    description VARCHAR(255)
);

-- 1.	Create a query to return the unique rows in a table

-- if using a primary or unique key constraint you can guarantee unique data per-row
SELECT id, name, description FROM customers;
-- you can use the distinct keyword to prune any duplicate data
SELECT DISTINCT id, customer_id, description FROM orders;
-- or you can use group by which is more for aggregation but produces the same output
SELECT id, customer_id, description FROM orders GROUP BY id, customer_id, description;

-- 2.	Write a command to insert values into a table

INSERT INTO customers (name, id, description) VALUES ('John Smith', 1, 'Our First User');
INSERT INTO customers VALUES (2, 'Jane Smith', 'Our Second User');
INSERT INTO customers VALUES (3, 'Someone Else', 'Our Third User');

INSERT INTO orders (description, customer_id, id) VALUES
    ('Order 1', 1, 1),
    ('Order 2', 2, 2);
INSERT INTO orders VALUES
    (3, 1, 'Order 3'),
    (4, 2, 'Order 4');

-- 3.	Create a query that joins two tables together. Note, all rows from the first table must be in the result-set (e.g. given customer and order tables, return all customers and any orders for each customer)

-- SELECT * -- select all data from all tables
-- SELECT c.*, o.* -- specify tables to select all data from
SELECT c.name, o.description -- specify required columns
FROM customers c
LEFT JOIN orders o ON o.customer_id = c.id
--JOIN orders o ON o.customer_id = c.id -- exclude customers without orders
;