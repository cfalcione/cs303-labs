from hashmap import regularMap, linearMap, quadraticMap, pythonMap

maps = {
    'regular': lambda: regularMap(hash),
    'linear': lambda: linearMap(hash),
    'quadratic': lambda: quadraticMap(hash),
    'python': lambda: pythonMap(hash)
}


def main():
    hashmap = getHashMap()
    while True:
        command, args = getNextInput()
        if command.lower().strip() in ['q', 'quit', 'exit']:
            break
        res = handle(command, hashmap, args)
        print(res)

def getHashMap():
    while True:
        user_input = input("Pick a table: {}\nyour choice: ".format( list(maps.keys())) ).strip().lower()
        if user_input not in maps:
            continue
        return maps[user_input]()

def getNextInput():
    userInput = input(">")
    parsed = userInput.split()
    return parsed[0], list(map(eval, parsed[1:]))

def handle(command, hashmap, args):
    if command not in handlers:
        return "Invalid command"
    try:
        res = handlers[command](hashmap, *args)
    except TypeError as e:
        return e
    return res

def handleGet(hashmap, key):
    return hashmap.get(key)

def handlePut(hashmap, key, value):
    hashmap.put(key, value)
    return True

def handleDelete(hashmap, key):
    try:
        hashmap.delete(key)
    except KeyError:
        return "{} not in hashmap".format(key)


handlers = {
    'put': handlePut,
    'get': handleGet,
    'del': handleDelete,
    'items': lambda hashmap: " ".join(map(str, hashmap.items())),
    'len': lambda hashmap: len(hashmap)
}

if __name__ == "__main__":
    main()