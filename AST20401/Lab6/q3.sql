select
	`name`
from
	`employees`
where
	`dept_id` in (
		select
			`dept_id`
		from
			`departments`
		where
			`location` = 'CA'
	);
