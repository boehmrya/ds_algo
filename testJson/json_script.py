import json

statusDict = {}

with open('test_data.json') as json_data:
    d = json.load(json_data)


for item in d:
	statusDict[item['status']] = 1

count = 0
for s in statusDict:
	count += 1

print(count)

