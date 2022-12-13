

def listComparator(a1, a2):
    if len(a1) == 0:
        if len(a2) == 0:
            return 0
        return -1
    elif len(a2) == 0:
        return 1
    if isinstance(a1[0], int) and isinstance(a2[0], int):
        if a1[0] == a2[0]:
            return listComparator(a1[1:], a2[1:])
        if a1[0] > a2[0]:
            return 1
        if a1[0] < a2[0]:
            return -1
    b1 = []
    b2 = []
    if isinstance(a1[0], int):
        b1.append(a1[0])
    else:
        b1 = a1[0]
    if isinstance(a2[0], int):
        b2.append(a2[0])
    else:
        b2 = a2[0]
    match listComparator(b1, b2):
        case 1:
            return 1
        case -1:
            return -1
        case 0:
            return listComparator(a1[1:], a2[1:])
    

def part01(file):
    pairCounter = 0
    pairIndex = 1
    total = 0
    line1 = []
    line2 = []
    for line in file:
        if pairCounter == 0:
            line1 = eval(line)
            pairCounter += 1
        elif pairCounter == 1:
            line2 = eval(line)
            comp = listComparator(line1, line2)
            if comp == -1:
                total += pairIndex
            pairCounter += 1
        else:
            pairCounter = 0
            pairIndex += 1
    return total


def main(filepath):
    f = open(filepath)
    part1 = part01(f)
    print(part1)


main("C:/Users/Bailey/Documents/AdventOfCode2022/docs/Day13.txt")
