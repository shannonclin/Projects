# Project: Textclouds
# Name: Shannon Lin

from hmc_urllib import getHTML
import string

# helper function to remove punctuation
def removePunct(s):
    """ removes puncutation from the given string """
    NoPunct = ''
    for i in s:
        if i not in string.punctuation:
            NoPunct += i
    return NoPunct

# helper function to remove \n
def removeN(n):
    """ removes \n from the given string """
    NoN = n.replace('\n', ' ')
    return NoN

# helper function to remove uninteresting words
def removeUnint(g):
    """ removes uninteresting words - a, i, the, am, at, on, in,
        of, is, to, so, at, and, text, clouds, example, one, by
        as, after, it, number, from, oh, with, or, have, has, you,
        ive, were - from the given string
    """
    NoUnint = g.replace('a ', '')
    NoUnint = NoUnint.replace(' i ', ' ')
    NoUnint = NoUnint.replace(' the ', ' ')
    NoUnint = NoUnint.replace(' am ', ' ')
    NoUnint = NoUnint.replace(' at ',' ')
    NoUnint = NoUnint.replace(' on ', ' ')
    NoUnint = NoUnint.replace(' in ', ' ')
    NoUnint = NoUnint.replace(' of ', ' ')
    NoUnint = NoUnint.replace(' is ', ' ')
    NoUnint = NoUnint.replace(' to ', ' ')
    NoUnint = NoUnint.replace(' so ', ' ')
    NoUnint = NoUnint.replace(' and ', ' ')
    NoUnint = NoUnint.replace(' at ', ' ')
    NoUnint = NoUnint.replace(' text ', ' ')
    NoUnint = NoUnint.replace(' clouds ', ' ')
    NoUnint = NoUnint.replace(' example ', ' ')
    NoUnint = NoUnint.replace(' one ', ' ')
    NoUnint = NoUnint.replace(' by ', ' ')
    NoUnint = NoUnint.replace(' as ', ' ')
    NoUnint = NoUnint.replace(' after ', ' ')
    NoUnint = NoUnint.replace(' it ', ' ')
    NoUnint = NoUnint.replace(' number ', ' ')
    NoUnint = NoUnint.replace(' from ', ' ')
    NoUnint = NoUnint.replace(' oh ', ' ')
    NoUnint = NoUnint.replace(' with ', ' ')
    NoUnint = NoUnint.replace(' or ', ' ')
    NoUnint = NoUnint.replace(' have ', ' ')
    NoUnint = NoUnint.replace(' has ', ' ')
    NoUnint = NoUnint.replace(' you ', ' ')
    NoUnint = NoUnint.replace(' ive ', ' ')
    NoUnint = NoUnint.replace(' were ', ' ')
    return NoUnint
    
# helper function to remove numbers
def removeNum(e):
    """ removes numbers from the given string """
    NoNum = e.replace('0', '')
    NoNum = NoNum.replace('1', '')
    NoNum = NoNum.replace('2', '')
    NoNum = NoNum.replace('3', '')
    NoNum = NoNum.replace('4', '')
    NoNum = NoNum.replace('5', '')
    NoNum = NoNum.replace('6', '')
    NoNum = NoNum.replace('7', '')
    NoNum = NoNum.replace('8', '')
    NoNum = NoNum.replace('9', '')
    return NoNum

# helper function to figure out stems
# stemming rules: -s, -ed, -ing, -er, -en, -es, re-,

# change each word in the string to an element of a list
# this allows the user to look through each word in the list for variations of the word
def splitLine(d):
    """ takes the words in the given string and puts them in a list """
    e = d.split()
    return e

# sorts the elements of the list by length
def OrderedLenList (g):
    """ takes the given list and orders the elements by length """
    g.sort(key = len)
    return g

# the following function lets the user check each word in the list
    """ goes through each element in the given list and checks every
        the other elements in the list for a similarities. if the element
        finds a similar variation, the function switches that element to
        match the original
    """
def checkWords(a):
    """ parses the text to standardize the words """
    for h in range (len(a)):
        for r in range (len(a)):
            # if the words are the same, keep them the same, ie. jog -> jog
            if a[h] == a[r]:
                a[r] = a[h]
            # if the word is in present tense, ie. jogs -> jog
            elif a[h] == a[r] + 's':
                a[r] = a[h]
            # if the word is in past tense, ie. jogged -> jog
            elif a[h] == a[r] + a[r][-1] + 'ed' or a[h] == a[r] + 'ed':
                a[r] = a[h]
            # if the word is a nominalization, ie. jogger -> jog
            elif a[h] == a[r] + a[r][-1] + 'er' or a[h] == a[r] + 'er':
                a[r] = a[h]
            # another nominalization, ie. defecation -> defecate
            elif a[h] == a[r][0:-3] + 'e':
                a[r] = a[h]
            # variation of previous nominalization, ie. destinate -> destine
            elif a[h] == a[r][0:-3] + 'e':
                a[r] = a[h]
            # if the word is a plural noun, ie. joggers -> jog
            elif a[h] == a[r][0:-3] or a[h] == a[r][0:-4]:
                a[r] = a[h]
            # if the word is a present participle, ie. jogging -> jog
            elif a[h] == a[r][0:-3] or a[h] == a[r][0:-4]:
                a[r] = a[h]
            # if the word is prefixed with 're', ie. reassemble -> assemble
            elif a[h] == 're' + a[r]:
                a[r] = a[h]
            # if the word is prefixed with 'un', ie. undo -> do
            elif a[h] == 'un' + a[r]:
                a[r] = a[h]
            # words are not related
            else:
                a[r] = a[r]
    return a



# helper helper function
def NumList(a):
   """ counts the number of times a word appears in a string and
       puts the information in a list
   """
   c = [(b, a.count(b)) for b in set(a)]
   return c

# removing words are not within the top 50 most frequently appearing
def MaxWords(a):
   """ keeps only the top 50 more frequent words in the list """
   a = sorted(a, key = lambda pair : pair[1]) [-50:]
   return a

# depth 2 helper function
def LoLtoL(b):
    """ takes the elements in a list of lists and makes it a list """
    sum = []
    for x in b[0]:
        sum += [x]
    return sum

# removing repeating URLs
def uniqify(c):
    """ takes the given lists and removes any repeating elements """
    unique = []
    for x in c:
        if x not in unique:
            unique.append(x)
    return unique

# main function
def textcloud(url, depth):
    """ generates a textcloud with the given link """
    b = getHTML(url)

    # for depth 0
    if depth == 0:
        # make a list of text in first tuple
        ListofText = b[0]
        # remove punctuation, \n, uninteresting text, and numbers from the list
        NoPunct = removePunct(ListofText)
        NoN = removeN(NoPunct)
        NoUnint = removeUnint(NoN)
        NoNum = removeNum(NoUnint)
        # stemming the words
        WordList = splitLine(NoNum)
        OrdList = OrderedLenList(WordList)
        StemmedWords = checkWords(OrdList)
        FreqList = NumList(StemmedWords)
        Top50 = MaxWords(FreqList)
        # make n the list of words in first tuple
        return Top50

    # for depth 1
    elif depth == 1:
        ListofText = [getHTML(x)[0] for x in b[1]]
        StringofText = ''.join(ListofText)
        StringofText += b[0]
        # with the string of text from pages connected with a depth of 1,
        # follow the same process as before
        NoPunct = removePunct(StringofText)
        NoN = removeN(NoPunct)
        NoUnint = removeUnint(NoN)
        NoNum = removeNum(NoUnint)
        # stemming the words
        WordList = splitLine(NoNum)
        OrdList = OrderedLenList(WordList)
        StemmedWords = checkWords(OrdList)
        FreqList = NumList(StemmedWords)
        Top50 = MaxWords(FreqList)
        # make n the list of words in first tuple
        return Top50

    # technically for depth 2
    else:
        # depth 0 and 1 urls
        ListofURL1 = [url] + b[1]
        # depth 2 urls
        ListofURL2 = [getHTML(x)[1] for x in b[1]]
        # all the urls, using a helper function
        ListofAll = ListofURL1 + LoLtoL(ListofURL2)
        # removing duplicates, using another helper function
        UniqueURL = uniqify(ListofAll)
        # getting a list of texts from all the URLs
        ListofText = [getHTML(x)[0] for x in UniqueURL]
        # processing the text
        StringofText = ''.join(ListofText)
        # from here on, everything is the same
        NoPunct = removePunct(StringofText)
        NoN = removeN(NoPunct)
        NoUnint = removeUnint(NoN)
        NoNum = removeNum(NoUnint)
        # stemming the words
        WordList = splitLine(NoNum)
        OrdList = OrderedLenList(WordList)
        StemmedWords = checkWords(OrdList)
        FreqList = NumList(StemmedWords)
        Top50 = MaxWords(FreqList)
        return Top50

# the web-crawler portion:
def crawlURL(url, depth):
    """ creates the dictionary of the URL """
    dictionary = textcloud(url, depth)
    return dictionary[::-1]

# the HTML text-cloud creator
def makeTextCloud(dict_of_words):
    """ makes text cloud! """
    html_string = ''
    for wd, fq in dict_of_words:
        # different colors for frequency!
        if fq >= 15:
            html_string = html_string + '<abbr title =' + str(fq) + ' style="font-size:' + str(70*fq) + '%; color:red">' + str(wd) + '</abbr> &nbsp; &nbsp; '
        elif fq >= 10:
            html_string = html_string + '<abbr title =' + str(fq) + ' style="font-size:' + str(70*fq) + '%; color:orange">' + str(wd) + '</abbr> &nbsp; &nbsp; '
        elif fq >= 5:
            html_string = html_string + '<abbr title =' + str(fq) + ' style="font-size:' + str(70*fq) + '%; color:green">' + str(wd) + '</abbr> &nbsp; &nbsp; '
        elif fq >= 3:
            html_string = html_string + '<abbr title =' + str(fq) + ' style="font-size:' + str(70*fq) + '%; color:blue">' + str(wd) + '</abbr> &nbsp; &nbsp; '
        elif fq > 1:            
            html_string = html_string + '<abbr title =' + str(fq) + ' style="font-size:' + str(70*fq) + '%; color:purple">' + str(wd) + '</abbr> &nbsp; &nbsp; '
        else:
            html_string = html_string + '<abbr title =' + str(fq) + ' style="font-size:' + str(70*fq) + '%; color:black">' + str(wd) + '</abbr> &nbsp; &nbsp; '
    return html_string

# putting on the Internet
def testTextCloud( html_string ):
    """ takes in a string produces by makeTextCloud 
        and outputs the file test.html """
    f = open( "test.html", "w" )  # open for writing
    print >> f, "<html>"
    print >> f, "<body><p style='font-family: calibri, serif' align='center'>My Text Cloud"
    print >> f, "<p style='font-family: calibri, serif'>"
    print >> f, html_string
    print >> f, "</p></p></body>"
    print >> f, "</html>"
    f.close()
    # no return needed, as there is no return value
    # BUT, the file test.html should now exist or be updated...
        
