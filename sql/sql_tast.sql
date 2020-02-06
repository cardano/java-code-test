-- Create a query to return the unique rows in a table
select distinct name, type from product;

-- Write a command to insert values into a table
insert into student(roll_umber, name, grade) values ("101", "Michael Munro", "B");

-- Create a query that joins two tables together. Note, all rows from the first table must be in the result-set (e.g. given customer and order tables, return all customers and any orders for each customer)
select * from customer
left outer join order
where customer.id = order.customer_id