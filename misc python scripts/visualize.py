import matplotlib.pyplot as plt
import matplotlib.animation as animation
import numpy as np
from time import sleep


m=[]
with open("D:\\learn\workspace\\Java\\school\\neuralNet\\visualizations\\w2.txt", "r") as file:
  n=[]
  m=file.read().split("\n\n")[:-1]
  print(len(m))
total=[]
for i in m:
  temp=[]
  for j in i[1:-1].replace("[","").replace("]","").split("\n"):
    temp.append(list(map(float, j.split(","))))
  total.append(temp)
print(len(total))
def update(i):
  ax.clear()
  # mat=ax.imshow(total[i], aspect='auto')
  mat=ax.matshow(total[i], aspect='auto')
fig, ax = plt.subplots()
# ax.set_aspect('auto')
# mat = ax.imshow(total[0], aspect='auto')
mat = ax.matshow(total[0], aspect='auto')
plt.colorbar(mat)

ani = animation.FuncAnimation(fig,update, frames=len(total)-1, interval=100)
ani.save("BBBBB.gif", writer="imagemagick")
# plt.show()

