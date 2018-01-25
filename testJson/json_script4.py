import json

times = []

with open('test_data.json') as json_data:
    d = json.load(json_data)

for item in d:
	if item['status'] == 8951:
		if item['start_time'] is not None and item['end_time'] is not None:
			time = item['end_time'] - item['start_time']
			times.append(time)


totalTime = 0
for t in times:
	totalTime += t


avgTime = totalTime / len(times)
print(avgTime)



