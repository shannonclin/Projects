# A game of Hangman
from random import choice
import time
import sys
import os

play = True

while play is True:

	# List of 50 First Names
	firstName = ['Kari', 'Kristie', 'Brandon', 'Joyce', 'Ada', 'Mark', 'Alex', 'Jordan', 'Rossie', 'Jarvis', 'Lucio',
	'Edwin', 'Madeline', 'Catrice', 'Candice', 'Mohamed', 'Tasha', 'Rey', 'Leanna', 'Justine', 'Leandro', 'Diane', 
	'Emma', 'Burton', 'Mindy', 'Randall', 'Anthony', 'Jeremiah', 'Alyssa', 'Scottie', 'Alicia', 'Carlos', 'Yukiko', 
	'Quinton', 'Santos', 'Edwina', 'Shannon', 'Leland', 'Kathleen', 'Silas', 'Laura', 'Nelson', 'Olga', 'Sean', 'Heath',
	'Buck', 'Juanita', 'Sibyl', 'Brittany', 'Kathryn']
	# List of 50 Last Names
	lastName = ['Smith', 'Brixey', 'Littell', 'Husain', 'Donatelli', 'Okamoto', 'Boice', 'Neault', 'Boice', 'Fey',
	'Lin', 'Pavlak', 'Wyble', 'Pontius', 'Samford', 'Ancheta', 'Angus', 'Brouse', 'Li', 'Chen', 'Hugh', 'Whitten',
	'Aikens', 'An', 'Rossell', 'Cahill', 'McCormick', 'Paton', 'Robertson', 'Jagger', 'Faust', 'Holmes', 'Yi', 
	'Madden', 'Uzzle', 'Briant', 'Matthews', 'Dilday', 'Spillers', 'Vitela', 'Mcelhany', 'Yung', 'Karcher', 'Kimmer', 
	'Frick', 'Pike', 'Lassen', 'Conway', 'Boyko', 'Igo']
	# List of 10 Adjectives
	adjective = ['Villanous', 'Fallen', 'Deceitful', 'Corrupted', 'Rebel', 'Treacherous', 'Duplicitous', 'Criminal,'
	'Incompetent', 'Cowardly']
	# List of 25 Occupations
	occupation = ['Architect', 'Barber', 'Medic', 'Knight', 'Ascetic', 'Herald', 'Scribe', 'Drunkard', 'Diplomat', 
	'Page', 'Butcher', 'Carpenter', 'Chandler', 'Cook', 'Glassblower', 'Strumpet', 'Potter', 'Merchant', 'Gravedigger',
	'Bandit', 'Clown', 'Minstrel', 'Slave', 'Squire', 'Prisoner']
	# List of 50 Easy Words: 5 to 13
	easyWords = ['fattening', 'disaster', 'horrid', 'alfalfa', 'tradition', 'time', 'mom', 'prayer', 
	'country', 'truth', 'bikini', 'dwarf', 'doctor', 'hat', 'history', 'modern', 'molecule', 'anger', 'season',
	'soldier', 'solution', 'evening', 'planet', 'division', 'print', 'thin', 'determine', 'dawn', 'slave', 
	'earth', 'charge', 'growth', 'water', 'night', 'place', 'life', 'coast', 'love', 'friend', 'marble', 'door',
	'plain', 'among', 'interest', 'listen', 'grand', 'syllable', 'sense', 'finger', 'fraction']
	# List of Medium Words: 10 - 20
	mediumWords = ['difference', 'pregnancy', 'quotient', 'availability', 'importance', 'fallacy', 'machine',
	'ebony', 'maverick', 'tryst', 'vacuole', 'cull', 'reconcile', 'refurbish', 'cultivate', 'kingdom', 'orca', 'djinn',
	'juncture', 'judicious', 'ketchup', 'mask', 'junk', 'fragile', 'affictitious', 'uviferous', 'yelve', 'stigma', 
	'dumbfound', 'vermillion', 'tenacious', 'lemma', 'lucid', 'carcass', 'pyrrhic', 'ripple', 'quadrant', 'eclectic',
	'blaze', 'hedonist', 'ignoble', 'wrawl', 'paradox', 'obligation', 'obvert', 'demarche', 'ferity', 'scruple', 'umbrage',
	'quoz']
	# List of 50 Difficult Words
	difficultWords = ['quorum', 'rhubarb', 'hymn', 'jaundice', 'gazebo', 'exodus', 'guffaw', 'jazz', 'whomever',
	'zephyr', 'czar', 'pygmy', 'jockey', 'myrrh', 'vortex', 'topaz', 'schizophrenia', 'gypsy', 'twelfth', 'xanthic',
	'strengths', 'fjord', 'jinx', 'crypt', 'avenue', 'fuchsia', 'buzz', 'puff', 'jiff', 'quiz', 'zit', 'importance', 
	'vex', 'fax', 'faux', 'muck', 'funny', 'lucky', 'eugenic', 'octogenarian', 'ambidextrous', 'wax', 'froth', 
	'abbey', 'azure', 'affix', 'galvanize', 'haiku', 'daiquiri', 'jazzy']

	# Allow each letter to be equivlanet to their letter value in Scrabble
	# Finding Difficulty of Word
	def removeRepeats(word):
	        if len(word) == 0:
	                return word
	        elif word[0] in word[1:]:
	                word1 = word[1:].replace(word[0], "")
	                return word[0] + removeRepeats(word1)
	        else:
	                return word[0] + removeRepeats(word[1:])

	def wordScore(word):
		word = removeRepeats(word)
		if len(word) == 0:
			return 0
		elif word[0] in 'aeiunroslt':
			return 1 + wordScore(word[1:])
		elif word[0] in 'gd':
			return 2 + wordScore(word[1:])
		elif word[0] in 'mbcp':
			return 3 + wordScore(word[1:])
		elif word[0] in 'yfvwh':
			return 4 + wordScore(word[1:])
		elif word[0] in 'k':
			return 5 + wordScore(word[1:])
		elif word[0] in 'jx':
			return 8 + wordScore(word[1:])
		else:
			return 10 + wordScore(word[1:])

	# Here is each stage of the hanging
	one = "    _____    \n   |    \|   \n         |   \n         |   \n         |   \n         |   \n      ___|___ \n"
	two = "    _____    \n   |    \|   \n   O     |   \n         |   \n         |   \n         |   \n      ___|___ \n"
	three = "    _____    \n   |    \|   \n   O     |   \n   |     |   \n         |   \n         |   \n      ___|___ \n"
	four = "    _____    \n   |    \|   \n   O     |   \n   |     |   \n   |     |   \n         |   \n      ___|___ \n"
	five = "    _____    \n   |    \|   \n   O     |   \n  \|     |   \n   |     |   \n         |   \n      ___|___ \n"
	six = "    _____    \n   |    \|   \n   O     |   \n  \|/    |   \n   |     |   \n         |   \n      ___|___ \n"
	seven = "    _____    \n   |    \|   \n   O     |   \n  \|/    |   \n   |     |   \n  /      |   \n      ___|___ \n"
	eight = "    _____    \n   |    \|   \n   O     |   \n  \|/    |   \n   |     |   \n  / \    |   \n      ___|___ \n"

	# Find instances of a letter in a word
	def findPosition(letter, word):
		letterPositions = []
		for i in range(len(word)):
			if letter == word[i]:
				letterPositions += [i]
			else:
				continue
		return letterPositions

	# Creates the visual for the word
	def blockMaker(word):
		blockHolder = ''
		for x in range(len(word)):
			blockHolder += ' _'
		return blockHolder[1:]

	# 
	def gameOperator(word, letter, wordHolder, guessed, current):
		guessed += letter + ' '

		if letter in word:
			print
			print 'Ah, a close call.'
			letterPosition = findPosition(letter, word)
			for i in range(len(letterPosition)):
				wordHolder[2*letterPosition[i]] = letter
			wordHolder = ''.join(wordHolder)
			return wordHolder, guessed, current

		elif current != seven:
			print 'Another step closer to death!'
			wordHolder = ''.join(wordHolder)
			if current == one:
				current = two
			elif current == two:
				current = three
			elif current == three:
				current = four
			elif current == four:
				current = five
			elif current == five:
				current = six
			else:
				current = seven
			return wordHolder, guessed, current

		else:
			current = eight
			wordHolder = ''.join(wordHolder)
			return wordHolder, guessed, current

	# This lets the reader decide which level he/she would like to play.
	def main():
		print
		print "Below are the playing option."
		print "(1) AI - Easy"
		print "(2) AI - Medium"
		print "(3) AI - Difficult"
		print "(4) PVP Mode"
		print "(5) Exit"
		print

	# This is used to interact with the user.
	def tts():
		while True:
			# Background Story for game
			fName = choice(firstName)
			lName = choice(lastName)
			adj = choice(adjective)
			occ = choice(occupation)

			print
			print "Welcome to Hangman!"

			main()

			choice1 = input("Which mode would you like to play on? ")

			if choice1 == 5:
				print "Another poor soul has been sent to die at the gallows."
				time.sleep(3)
				sys.exit(0)

			elif choice1 == 1:
				print
				print "Once again, we are here in the gallows! "
				print "The person who stands here today is " + fName + " " + lName + ", "
				print "the " + adj + " " + occ + ". Will this person be hanged? "
				print "Let us now decide."
				print
				answer = choice(easyWords).upper()

				# Setting initial conditions
				guessed = 'you have guessed: '
				wordHolder = list(blockMaker(answer))
				current = one
				visual = ''.join(wordHolder)
				print current
				print blockMaker(answer)

				# While loop that keeps the game running
				while current != eight or answer != ''.join(visual.split()):
					print
					print "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
					print
					letter = raw_input("Pick a letter: ").upper()

					# If the user picks a letter that has already been guessed, make them pick again.
					while letter in guessed:
						letter = raw_input("You've already guessed that! Pick again: ").upper()

					while letter.isalpha() != True or len(letter) != 1:
						letter = raw_input("That's not a valid guess! Pick again: ").upper()

					condition = gameOperator(answer, letter, wordHolder, guessed, current)

					wordHolder = list(condition[0])
					guessed = condition[1]
					current = condition[2]
					
					visual = ''.join(wordHolder)

					print
					print guessed
					print current
					print visual

					if ''.join(visual.split()) == answer:
						break

					elif current == eight:
						break

				if current == eight:
					print
					print "The answer was: " + answer
					print "And so death befalls " + fName + " " + lName + " !"
					time.sleep(2)

					again = raw_input("Would you like to play again? Yes/No ").upper()
					if again == 'YES':
						print
						print "Sending another one to death, I see."
						print "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
						continue
					elif again == 'NO':
						print "Wise decision. We don't want another person dying!"
						sys.exit(0)
					else:
						again = raw_input("That's not a valid choice! Would you like to play again? Yes/No ").upper()

				else:
					print
					print "You may have saved one, but the others won't be so lucky."
					time.sleep(2)

					again = raw_input("Would you like to play again? Yes/No: ").upper()
					if again == 'YES':
						print
						print "Wise decision!"
						print "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
						continue
					elif again == 'NO':
						print "I'll meet you again."
						sys.exit(0)
					else:
						again = raw_input("That's not a valid choice! Would you like to play again? Yes/No: ").upper()
						

			elif choice1 == 2:
				print
				print "Once again, we are here in the gallows! "
				print "The person who stands here today is " + fName + " " + lName + ", "
				print "the " + adj + " " + occ + ". Will this person be hanged? "
				print "Let us now decide."
				print
				answer = choice(mediumWords).upper()

				# Setting initial conditions
				guessed = 'you have guessed: '
				wordHolder = list(blockMaker(answer))
				current = one
				visual = ''.join(wordHolder)
				print current
				print blockMaker(answer)

				# While loop that keeps the game running
				while current != eight or answer != ''.join(visual.split()):
					print
					print "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
					print
					letter = raw_input("Pick a letter: ").upper()

					# If the user picks a letter that has already been guessed, make them pick again.
					while letter in guessed:
						letter = raw_input("You've already guessed that! Pick again: ").upper()

					while letter.isalpha() != True or len(letter) != 1:
						letter = raw_input("That's not a valid guess! Pick again: ").upper()

					condition = gameOperator(answer, letter, wordHolder, guessed, current)

					wordHolder = list(condition[0])
					guessed = condition[1]
					current = condition[2]
					
					visual = ''.join(wordHolder)

					print
					print guessed
					print current
					print visual

					if ''.join(visual.split()) == answer:
						break

					elif current == eight:
						break

				if current == eight:
					print
					print "The answer was: " + answer
					print "And so death befalls " + fName + " " + lName + " !"
					time.sleep(2)

					again = raw_input("Would you like to play again? Yes/No ").upper()
					if again == 'YES':
						print
						print "Sending another one to death, I see."
						print "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
						continue
					elif again == 'NO':
						print "Wise decision. We don't want another person dying!"
						sys.exit(0)
					else:
						again = raw_input("That's not a valid choice! Would you like to play again? Yes/No ").upper()

				else:
					print
					print "You may have saved one, but the others won't be so lucky."
					time.sleep(2)

					again = raw_input("Would you like to play again? Yes/No: ").upper()
					if again == 'YES':
						print
						print "Wise decision!"
						print "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
						continue
					elif again == 'NO':
						print "I'll meet you again."
						sys.exit(0)
					else:
						again = raw_input("That's not a valid choice! Would you like to play again? Yes/No: ").upper()


			elif choice1 == 3:
				print
				print "Once again, we are here in the gallows! "
				print "The person who stands here today is " + fName + " " + lName + ", "
				print "the " + adj + " " + occ + ". Will this person be hanged? "
				print "Let us now decide."
				print
				answer = choice(difficultWords).upper()

				# Setting initial conditions
				guessed = 'you have guessed: '
				wordHolder = list(blockMaker(answer))
				current = one
				visual = ''.join(wordHolder)
				print current
				print blockMaker(answer)

				# While loop that keeps the game running
				while current != eight or answer != ''.join(visual.split()):
					print
					print "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
					print
					letter = raw_input("Pick a letter: ").upper()

					# If the user picks a letter that has already been guessed, make them pick again.
					while letter in guessed:
						letter = raw_input("You've already guessed that! Pick again: ").upper()

					while letter.isalpha() != True or len(letter) != 1:
						letter = raw_input("That's not a valid guess! Pick again: ").upper()

					condition = gameOperator(answer, letter, wordHolder, guessed, current)

					wordHolder = list(condition[0])
					guessed = condition[1]
					current = condition[2]
					
					visual = ''.join(wordHolder)

					print
					print guessed
					print current
					print visual

					if ''.join(visual.split()) == answer:
						break

					elif current == eight:
						break

				if current == eight:
					print
					print "The answer was: " + answer
					print "And so death befalls " + fName + " " + lName + " !"
					time.sleep(2)

					again = raw_input("Would you like to play again? Yes/No ").upper()
					if again == 'YES':
						print
						print "Sending another one to death, I see."
						print "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
						continue
					elif again == 'NO':
						print "Wise decision. We don't want another person dying!"
						sys.exit(0)
					else:
						again = raw_input("That's not a valid choice! Would you like to play again? Yes/No ").upper()
						

				else:
					print
					print "You may have saved one, but the others won't be so lucky."
					time.sleep(2)

					again = raw_input("Would you like to play again? Yes/No: ").upper()
					if again == 'YES':
						print
						print "Wise decision!"
						print "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
						continue
					elif again == 'NO':
						print "I'll meet you again."
						sys.exit(0)
					else:
						again = raw_input("That's not a valid choice! Would you like to play again? Yes/No: ").upper()
						

			elif choice1 == 4:
				answer = raw_input("What word would you like to pick? ").upper()
				os.system('cls')

				if answer.isalpha() == False:
					answer = raw_input("Please pick a word without any special characters: ").upper()

				# Setting initial conditions
				guessed = 'you have guessed: '
				wordHolder = list(blockMaker(answer))
				current = one
				visual = ''.join(wordHolder)
				print current
				print blockMaker(answer)

				# While loop that keeps the game running
				while current != eight or answer != ''.join(visual.split()):
					print
					print "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
					print
					letter = raw_input("Pick a letter: ").upper()

					# If the user picks a letter that has already been guessed, make them pick again.
					while letter in guessed:
						letter = raw_input("You've already guessed that! Pick again: ").upper()

					while letter.isalpha() != True or len(letter) != 1:
						letter = raw_input("That's not a valid guess! Pick again: ").upper()

					condition = gameOperator(answer, letter, wordHolder, guessed, current)

					wordHolder = list(condition[0])
					guessed = condition[1]
					current = condition[2]
					
					visual = ''.join(wordHolder)

					print
					print guessed
					print current
					print visual

					if ''.join(visual.split()) == answer:
						break

					elif current == eight:
						break

				if current == eight:
					print
					print "The answer was: " + answer
					print "And so death befalls another person !"
					time.sleep(2)

					again = raw_input("Would you like to play again? Yes/No ").upper()
					if again == 'YES':
						print
						print "Sending another one to death, I see."
						print "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
						continue
					elif again == 'NO':
						print "Wise decision. We don't want another person dying!"
						sys.exit(0)
					else:
						again = raw_input("That's not a valid choice! Would you like to play again? Yes/No ").upper()
						

				else:
					print
					print "You may have saved one, but the others won't be so lucky."
					time.sleep(2)

					again = raw_input("Would you like to play again? Yes/No: ").upper()
					if again == 'YES':
						print
						print "Wise decision!"
						print "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
						continue
					elif again == 'NO':
						print "I'll meet you again."
						sys.exit(0)
					else:
						again = raw_input("That's not a valid choice! Would you like to play again? Yes/No: ").upper()
						

			else:
				print "That's not one of the choices!"

	tts()

