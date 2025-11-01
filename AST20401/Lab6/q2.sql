select
	departments.location,
	count(employees.name)
from
	departments
left join
	employees
	using (dept_id)
group by
	departments.location;
