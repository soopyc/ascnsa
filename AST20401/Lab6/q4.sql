-- Using aggregate and GROUP BY: Write a query to find the average number of
-- employees per department (use COUNT and divide by the number of departments).

select
	count(*) / (select count(*) from departments) as average_employees_per_department
from
	employees;
