package main

import (
	"bufio"
	"fmt"
	"os"
	"regexp"
	"strconv"
)

func main() {
	// https://medium.com/@ebsouza/two-ways-to-read-text-files-in-go-16d60101a2ed
	file, err := os.Open("../input.txt")
	if err != nil {
		fmt.Println("Error: ", err)
		return
	}

	defer file.Close()

	regexPattern := `mul\((\d{1,3}),(\d{1,3})\)`
	regex := regexp.MustCompile(regexPattern)

	r := bufio.NewReader(file)

	sumOfMultiplications := 0

	for {
		line, _, err := r.ReadLine()
		if len(line) > 0 {
			match := regex.FindAllStringSubmatch(string(line), -1)
			if len(match) > 0 {
				for _, match := range match {

					first, err := strconv.ParseInt(match[1], 10, 64)
					if err != nil {
						fmt.Println("Error: ", err)
						return
					}

					second, err := strconv.ParseInt(match[2], 10, 64)
					if err != nil {
						fmt.Println("Error: ", err)
						return
					}

					sumOfMultiplications += int(first * second)

				}
			}

		}
		if err != nil {
			break
		}
	}

	fmt.Println(sumOfMultiplications)

}
