jsonFile = open("data.json","r+")
update = json.load(jsonFile)

tmp = update["totalamount"]
update["totalamount"] = "10000"

