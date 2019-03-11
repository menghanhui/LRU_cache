
# sample input:
# [1 5] [2 6]
# [1 3] [7 9]
# Assume this function accepts two lists, where in each list there are two numbers,
# which specifies the start and end point of a line.

# Here we assume that for any line [x1, x2], if x1 > x2, such as [5, 1], it is also valid.
# It means this line is: 1->5
def find_overlap(line1: list, line2: list)->str:

        if len(line1) < 2 or len(line2)<2:
            raise Exception("The input is not valid")

        if line1[0] > line1[1]:
            line1[0], line1[1] = line1[1], line1[0]

        if line2[0] > line2[1]:
            line2[0], line2[1] = line2[1], line2[0]

        if (line1[0] <= line2[1]) & (line1[1]>=line2[0]):
            return "Overlap"
        else:
            return "Not Overlap"
