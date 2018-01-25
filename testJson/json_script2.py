import json


def keywithmaxval(d):
     """ a) create a list of the dict's keys and values; 
         b) return the key with the max value"""  
     v=list(d.values())
     k=list(d.keys())
     return k[v.index(max(v))]

def maxval(d):
     """ a) create a list of the dict's keys and values; 
         b) return the key with the max value"""  
     v = list(d.values())
     m = max(v)
     return m

users = {}

with open('test_data.json') as json_data:
    d = json.load(json_data)

for item in d:
	key = item['user_id']
	if key in users:
		users[key] += 1
	else:
		users[key] = 1


# first user
firstKey = keywithmaxval(users)
firstVal = maxval(users)
print("1. " + str(firstKey) + ": " + str(firstVal))
del users[firstKey]

# second user
secKey = keywithmaxval(users)
secVal = maxval(users)
print("2. " + str(secKey) + ": " + str(secVal))
del users[secKey]

# third user
thirdKey = keywithmaxval(users)
thirdVal = maxval(users)
print("3. " + str(thirdKey) + ": " + str(thirdVal))
del users[thirdKey]

# fourth user
fourthKey = keywithmaxval(users)
fourthVal = maxval(users)
print("4. " + str(fourthKey) + ": " + str(fourthVal))
del users[fourthKey]

# fifth user
fifthKey = keywithmaxval(users)
fifthVal = maxval(users)
print("5. " + str(fifthKey) + ": " + str(fifthVal))
del users[fifthKey]


