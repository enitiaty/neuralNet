f = open(".\\datasets\\mnist_train.csv", 'r')
w = open(".\\datasets\\mnist_trainALLSCALEDDOWN.csv", "w+")
count=0
data = f.read().split("\n")
for i in data:
  count+=1
  *temp, = map(int,i[2:].split(','))
  print(count)
  out=[]
  for j in range(len(temp)):
    if j%2:out.append(0)
    else:out.append((temp[j]+temp[j+1])/2)
  w.write(i[:2]+str(out)[1:-1].replace(" ","")+"\n")
  # w.write(i[:2]+str(list(map(lambda x: [0,1][int(x)>0],i[2:].split(','))))[1:-1].replace(" ","")+"\n")