import matplotlib.pyplot as plt
import numpy as np

minGlobal, maxGlobal = 1000, 0
files = ['1', '2', '3']
color = ['blue', 'orange', 'red']

fig, axes = plt.subplots(ncols = 4, figsize = (13, 3))

for f, i in zip(files, range(len(color))):
    distancias1, distancias2, distancias3, distancias4 = [], [], [], []
    file = open('config/pruebas/'+f+'/log0-0.txt', "r")
    file = file.readlines()

    for linea in file:
        if linea[:3] == 'Val':
            dividir = linea.split(', ')
            distancias1.append(float(dividir[1]))
            distancias2.append(float(dividir[4]))
            distancias3.append(float(dividir[7]))
            distancias4.append(float(dividir[10]))
    
    axes[0].plot(distancias1, label = "modelo"+f, color=color[i])
    axes[1].plot(distancias2, label = "modelo"+f, color=color[i])
    axes[2].plot(distancias3, label = "modelo"+f, color=color[i])
    axes[3].plot(distancias4, label = "modelo"+f, color=color[i])

    distancias = np.array(distancias1 + distancias2 + distancias3 + distancias4)
    if np.max(distancias) > maxGlobal: maxGlobal = np.max(distancias)

axes[0].set_ylim(0, maxGlobal+800)
axes[1].set_ylim(0, maxGlobal+800)
axes[2].set_ylim(0, maxGlobal+800)
axes[3].set_ylim(0, maxGlobal+800)

axes[0].set_title('Distancia 1', fontdict={'fontsize':11, 'fontweight':'bold'})
axes[1].set_title('Distancia 2', fontdict={'fontsize':11, 'fontweight':'bold'})
axes[2].set_title('Distancia 3', fontdict={'fontsize':11, 'fontweight':'bold'})
axes[3].set_title('Distancia 4', fontdict={'fontsize':11, 'fontweight':'bold'})

lines, labels = fig.axes[-1].get_legend_handles_labels()
fig.legend(lines, labels, loc='center right')

plt.show()
