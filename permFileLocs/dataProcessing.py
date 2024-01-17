f = open(".\\permFileLocs\\mnist_test_OLD.csv", 'r')
w = open(".\\permFileLocs\\mnist_test.csv", "w+")

data = f.read().split("\n")
for i in data:
  w.write(i[:2]+str(list(map(lambda x: [0,1][int(x)>0],i[2:].split(','))))[1:-1].replace(" ","")+"\n")