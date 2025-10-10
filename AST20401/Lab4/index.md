_p.s. i have made the primary keys `auto_increment` because i don't want to type useless stuff constantly._

# Question 1

![Screenshot of the required output.](./Screenshot_20251010_102014.png)

The use of `LEFT JOIN` is required to list all available customers, even those without any orders (e.g. UID 4; Dave).

# Question 2

![](./Screenshot_20251010_102331.png)

The foreign key successfully protected the database from inserting garbage data (i.e. with a non-existent customer), that means references to the customers are protected and checked at row insertion time.

# Question 3

![](./Screenshot_20251010_102942.png)

![](./q3_p2.png)

`INNER JOIN` (i.e. join with nothing; regular join) and `OUTER JOIN` does not really have any difference in my situation because the user with any difference is already excluded.

# Question 4

![](./Screenshot_20251010_105054.png)

![](./Screenshot_20251010_105452.png)

The delete operation failed because there are other rows in other tables that still reference the current row. The foreign key constraint prevents the database from getting out of sync with data inside.

# Question 5

![](./Screenshot_20251010_112101.png)

![](./Screenshot_20251010_112133.png)

![](./Screenshot_20251010_112602.png)

With `JOIN`, `SUM` can be used to calculate summations for data that matches specific things, especially with `group by`, it can be used to calculate values for multiple rows.
