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
    

def get_counts(left, right):

    left = set(left)
    count_dict = {}
    for i in left:
        count = 0
        for j in right:
            if i == j:
                count += 1

        count_dict[i] = count

    return count_dict

def main():
    left, right = get_int_lists_from_file("input.txt")

    left.sort()
    right.sort()

    count_dict = get_counts(left, right)
# 
    # print(count_dict)

    sim_score = 0

    for num in left:
        sim_score += count_dict[num] * num


    print(sim_score)

if __name__ == "__main__":
    main()

