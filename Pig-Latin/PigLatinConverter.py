## As a user I can provide a phrase "hello" and
## see it translated to Pig Latin "ellohay"
##
## As a user I can provide a phrase "hello world" and
## see it translated to Pig Latin "ellohay orldway"
##
## As a user I can provide a phrase "Hello world" and
## see it translated to Pig Latin "Ellohay orldway"
##
## As a user I can provide a phrase "Hello, world!!" and
## see it translated to "Ellohay, orldway!!"
##
## As a user I can provide a phrase "eat apples" and
## see it translated to Pig Latin "eatay applesay"
##
## As a user I can provide a phrase "quick brown fox" and
## see it translated to Pig Latin "ickquay ownbray oxfay"

def pigLatinWord(str):
    # changes a given word to its pig latin counterpart
    firstLetter = str[0]
    wordLength = len(str)
    restOfWord = str[1:wordLength]
    restLength = len(restOfWord)
    latinEnding = 'ay'
    specialBegin2 = 'qu br al th sh gr pr st tr cr'
    specialBegin3 = 'thr'
    vowels = 'aeiouAEIOU'
    # special beginnings with three letters
    if str[0:3].lower() in specialBegin3:
        num = 3
        if firstLetter.isupper():
            return str[num].upper() + str[num+1:] + str[:num].lower() + latinEnding
        else:
            return str[num:] + str[:num] + latinEnding
    # special beginnings with two letters
    if str[0:2].lower() in specialBegin2:
        num = 2
        if firstLetter.isupper():
            return str[num].upper() + str[num+1:] + str[:num].lower() + latinEnding
        else:
            return str[num:] + str[:num] + latinEnding
    # if firstLetter is a lower or uppercase vowel, attach -ay
    elif firstLetter in vowels:
        return str + latinEnding
    # if word has one letter only
    elif wordLength == 1:
        return str + latinEnding
    # if the firstLetter is an uppercase consonant and str has
    # 2+ characters
    elif firstLetter.isupper():
        firstLetter = firstLetter.lower()
        restOfWord = restOfWord[0].upper() + restOfWord[1:restLength]
    return restOfWord + firstLetter + latinEnding

def punctuationChecker(str):
    # given a word, checks that punctuation is properly placed
    # if punctuation appears, move it to the end of the word
    # the placement of single quotations are preserved in the case
    # of contractions
    wordLength = len(str)
    punctHolder = ''
    for char in range(0, wordLength):
        if str[char] in ',;!?*.':
            punctHolder += str[char]
        else:
            continue
    for symbol in range(0, len(punctHolder)):
        str = str.replace(punctHolder[symbol], '')
    punctuation = ''.join(punctHolder)
    str = str + punctuation
    return str

def pigLatinSentence(str):
    # converts a setnence into pig latin
    sentenceWords = str.split(' ')
    convertedWords = [pigLatinWord(x) for x in sentenceWords]
    refinedWords = [punctuationChecker(x) for x in convertedWords]
    return ' '.join(refinedWords)


### TESTING ###
def assertEqual(expectedValue, givenValue):
    if (expectedValue == givenValue):
        print 'True'
    else:
        print 'False. Expected was: ' + expectedValue + '   Given was: ' + givenValue

def runTests():
    assertEqual('ellohay', pigLatinSentence('hello'))                           #True
    assertEqual('ellohay orldway', pigLatinSentence('hello world'))             #True
    assertEqual('Ellohay orldway', pigLatinSentence('Hello world'))             #True
    assertEqual('Ellohay, orldway!!', pigLatinSentence('Hello, world!!'))       #True
    assertEqual('eatay applesay', pigLatinSentence('eat apples'))               #True
    assertEqual('ickquay ownbray oxfay', pigLatinSentence('quick brown fox'))   #True
