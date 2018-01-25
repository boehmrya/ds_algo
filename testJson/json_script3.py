import json

errors = {}

with open('test_data.json') as json_data:
    d = json.load(json_data)

for item in d:
	status = str(item['status'])
	if status[-1] == "3":
		print("Yes")
	key = item['piece_id']
	if key in errors:
		if status[-1] == "3":
			errors[key] += 1
	else:
		if status[-1] == "3":
			errors[key] = 1
		else:
			errors[key] = 0


v = list(errors.values())
count = 0

for item in v:
	print(item)
	if item > 1:
		count += 1

perc = count / len(v)
print(perc)



