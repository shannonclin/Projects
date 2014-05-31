import random
import csv

# Helper Function for problemGenerator
# Generates random numbers for the problem
def numberGenerator(numDigits):
    lowLimitPower = numDigits - 1
    return random.randrange(10**lowLimitPower, 10**numDigits)

# Helper Function for problemGenerator
# Ensures that the sum will never become negative was the terms
# are being added
def possibleOrder(allNumbers):
    sumOfNumbers = 0

    for i in range(len(allNumbers)):
        sumOfNumbers += allNumbers[i]
        if sumOfNumbers < 0:
            return False
        
    return True

# Generates a problem
def problemGenerator(numNumber, numDigits, numSubtraction):

    # Provides a limit to the number of subtraction terms in the problem
    while int(numSubtraction) > numNumber/2:
        numSubtraction = raw_input('At most half the numbers in the problem should be subtraction. Please enter a valid number: ')

    numSubtraction = int(numSubtraction)

    # Creates lists of addition terms and subtraction terms
    additionNumbers = [numberGenerator(numDigits) for x in range(numNumber-numSubtraction)]
    subtractionNumbers = [numberGenerator(numDigits) for x in range(numSubtraction)]

    # If the sum of subtraction numbers is greater than that of addition numbers,
    # swap the contents, so the answer will be positive
    while sum(additionNumbers) < sum(subtractionNumbers):
         subtractionNumbers = [numberGenerator(numDigits) for x in range(numSubtraction)]

    # Makes each of the subtracted numbers negative
    subtractionNumbers = [x*-1 for x in subtractionNumbers]

    # Creates a list with all the terms and randomizes them
    allNumbers = additionNumbers + subtractionNumbers
    random.shuffle(allNumbers)

    while possibleOrder(allNumbers) == False:
        random.shuffle(allNumbers)

    # Includes the answer to the problem at the end of the list
    sumOfNumbers = sum(allNumbers)
    allNumbers.append(sumOfNumbers)

    return allNumbers

# Helper Function for main
# Ensures that the input is a numerical value
def isRealNumber(inputValue):
    state = True
    while state == True:
        try:
            int(inputValue)
            break
        except ValueError:
            inputValue = raw_input('Invalid choice. Please enter a number: ')
    return inputValue

# Helper Function for convertByElement
# Takes the element in a given position in each of the inner lists of
# a list of lists and creates a list of each of the nth elements
def extractElement(position, listOfProblemLists):
    lengthOfList = len(listOfProblemLists)
    elementHolder = []

    for x in range(lengthOfList):
        elementHolder.append(listOfProblemLists[x][position])

    return elementHolder

# Helper Function for main
# Creates a list of lists with each inner list grouped by their position
# in the original list
def convertByElement(listOfProblemLists, numNumber):
    lengthOfList = len(listOfProblemLists)
    problemListHolder = []

    for x in range(numNumber+1):
        problemListHolder.append(extractElement(x, listOfProblemLists))

    return problemListHolder

# Main Function
# Creates a CSV document with problems in each column
def main():
    # Obtains parameters for the problem set
    numProblems = raw_input('How many problems would you like to generate? ')
    numProblems = isRealNumber(numProblems)

    numDigits = raw_input('How many digits should be in each number of the problem? ')
    numDigits = isRealNumber(numDigits)

    numNumber = raw_input('How many numbers are in each problem? ')
    numNumber = isRealNumber(numNumber)

    numSubtraction = raw_input('How many of these numbers should be subtraction? ')
    numSubtraction = isRealNumber(numSubtraction)

    # Converts the input string into integers
    numProblems = int(numProblems)
    numNumber = int(numNumber)
    numDigits = int(numDigits)

    # Creates a list of lists with each problem and its solution0
    listOfProblemLists = [problemGenerator(numNumber, numDigits, numSubtraction) for x in range(numProblems)]
    listOfProblemLists = convertByElement(listOfProblemLists, numNumber)

    # Saves the list of problems into a CSV file
    fileNameCSV = raw_input('These problems will be saved in a CSV file. What would you like to name it? ')
    fileNameCSV = fileNameCSV + '.csv'
    excelCSV = open(fileNameCSV, 'w')
    for row in listOfProblemLists:
        for column in row:
            excelCSV.write('%d,' % column)
        excelCSV.write('\n')
    excelCSV.close()

if __name__ == "__main__":
    main()
        
