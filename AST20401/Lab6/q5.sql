-- Using subquery and aggregate: Write a query to find the department name(s) that have
-- the maximum number of employees.

select
	`dept_name`
from
	`departments`
left join
	`employees`
	using (`dept_id`)
order by (select count(`employees`.`name`) from employees where ) asc
limit 1;
