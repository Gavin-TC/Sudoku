# This script is used to input '1' to every cell to test a win.

import pyautogui as gui
import time

letters = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i']
numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9]

time.sleep(3)

for letter in letters:
	for number in numbers:
		gui.typewrite(letter + str(number))
		gui.press("enter")
		gui.typewrite(str(1))
		gui.press("enter")