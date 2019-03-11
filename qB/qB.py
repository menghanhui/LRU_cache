import sys



#Since the program accepts two "version" string,
# we can assume the following that
# 1. there are no "+/-" sign in the string
# 2. there are no 2 consecutive '.' signs in a string
# 3. there could be leading 0s in the version, for example: 3.1 is equal to 3.001
# 4. Assume that none of the inputs is empty


# Output:
# if v1 < v2: -1
# if v1 = v2: 0
# if v1 > v2: 1
def compare(version1:str, version2:str)->int:
    v1 = version1.split('.')
    v2 = version2.split('.')

    n1 = 0
    n2 = 0
    for i in range(max(len(v1), len(v2))):
        if i >= len(v1):
            n1 = 0
            n2 = int(v2[i])
        elif i >= len(v2):
            n1 = int(v1[i])
            n2 = 0
        else:
            n1 = int(v1[i])
            n2 = int(v2[i])
        if n1 < n2:
            return -1
        elif n1 > n2:
            return 1
    return 0

