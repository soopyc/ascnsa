select
	employees.name,
	departments.location
from
	employees
inner join
	departments
	on employees.dept_id = departments.dept_id;
