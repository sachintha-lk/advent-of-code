def get_int_lists_from_file(file_name):
    file = open(file_name, "r")
    left = []
    right = []

    for line in file:
        left_str , right_str = line.split("   ")
        left.append(int(left_str))
        right.append(int(right_str))

    file.close()
    return left, right


def main():
    left, right = get_int_lists_from_file("input.txt")

    left.sort()
    right.sort()

    n = len(left)

    sum = 0
    for i in range(n):
        diff = right[i] - left[i]   
        diff = abs(diff)
        sum += diff
        print(diff)
        
    print(sum)

if __name__ == "__main__":
    main()