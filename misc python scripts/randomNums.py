import random
#generates the numbers 0-9 in a random order 5 times
#used to generate user training data

a=[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
for i in "."*5:
  random.shuffle(a)
  print(*a)

