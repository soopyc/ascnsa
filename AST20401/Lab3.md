# Question 1

```
+------+-------------+----------+------+---------------+------+---------+------+------+-------------+
| id   | select_type | table    | type | possible_keys | key  | key_len | ref  | rows | Extra       |
+------+-------------+----------+------+---------------+------+---------+------+------+-------------+
|    1 | SIMPLE      | products | ALL  | NULL          | NULL | NULL    | NULL | 3    | Using where |
+------+-------------+----------+------+---------------+------+---------+------+------+-------------+
```

It shows that it is querying the `products` table with 3 rows, using where.

# Question 2

```shell
$ ls /var/lib/mysql/lab3
db.opt  products.frm  products.ibd
```

The `products.frm` file stores the table schema.

# Question 3

The query manager operates on the conceptual level of the architecture as it is basically mapping the external view to the internal view.

# Question 4

The storage manager is on the internal level of the DBMS architecture, as it manages low-level file storage and handling.

# Question 5

`products.ibd`'s update time was modified. Updates via SQL commands propagate to physical DBMS internal files.
