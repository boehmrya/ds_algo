import json

def keywithmaxval(d):
     """ a) create a list of the dict's keys and values; 
         b) return the key with the max value"""  
     v = list(d.values())
     k = list(d.keys())
     return k[v.index(max(v))]

pieces = {}

with open('test_data.json') as json_data:
    d = json.load(json_data)

for item in d:
	key = item['piece_id']
	if key in pieces:
		pieces[key] += (str(item['status']) + ",")
	else:
		pieces[key] = ""


freq = {}
pathValues = list(pieces.values()) #turn paths into a list
for p in pathValues:
	if p in freq:
		freq[p] += 1
	else:
		freq[p] = 0


maxPath = keywithmaxval(freq)
pathStat = maxPath.split(",")
for stat in pathStat:
	print(stat)

