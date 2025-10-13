import random
from collections import deque

template = "insert into sales (product, quantity, price, sale_date) values {};";
generate_count = 1000000
date = (1, 10)
qty = (1, 10)
products = {
	'Laptop': (199, 1599),
	'Mouse': (5, 19),
	'Keyboard': (29, 69),
	'Monitor': (199, 499),
}

dq = deque()
_prod_choice = tuple(products.keys())
for i in range(generate_count):
	product = random.choice(_prod_choice)
	dq.append(template.format(
		f"('{product}', {random.randint(*qty)}, {round(random.uniform(*products[product]), 2)}, '2025-09-{random.randint(*date):02}')"
	))
	print(f'\r{i}/{generate_count} done ({round(i/generate_count*100, 2)})... ', end='')

print('done.')

with open("sample_data.sql", 'w+') as f:
	print('writing file... ', end='')
	print(f'{f.write('\n'.join(dq))} bytes')
