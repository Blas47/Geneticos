import matplotlib.pyplot as plt
import numpy as np

minGlobal, maxGlobal = 1000, 0
files = ['1', '2', '3']
color = ['blue', 'orange', 'red']

fig, axes = plt.subplots(nrows = 3, ncols = 4, figsize = (13, 9))

for f, i in zip(files, range(len(color))):
    grafico = []
    angulos1, angulos2, angulos3, angulos4 = [], [], [], []
    distancias1, distancias2, distancias3, distancias4 = [], [], [], []
    velocidades1, velocidades2, velocidades3, velocidades4 = [], [], [], []
    file = open('config/pruebas/'+f+'/log0-0.txt', "r")
    file = file.readlines()

    for linea in file:
        if linea[0] == 'F':
            dividir = linea.split(': ')
            grafico.append(float(dividir[1].split('\n')[0]))

        if linea[:3] == 'Val':
            dividir = linea.split(', ')
            angulos1.append(float(dividir[0].replace('Values: [', ''))%181)
            angulos2.append(float(dividir[3])%181)
            angulos3.append(float(dividir[6])%181)
            angulos4.append(float(dividir[9])%181)

            distancias1.append(float(dividir[1]))
            distancias2.append(float(dividir[4]))
            distancias3.append(float(dividir[7]))
            distancias4.append(float(dividir[10]))

            velocidades1.append(float(dividir[2])%201)
            velocidades2.append(float(dividir[5])%201)
            velocidades3.append(float(dividir[8])%201)
            velocidades4.append(float(dividir[11].replace(']', ''))%201)
    
    axes[0][0].plot(angulos1, label = "modelo"+f, color=color[i])
    axes[0][1].plot(angulos2, label = "modelo"+f, color=color[i])
    axes[0][2].plot(angulos3, label = "modelo"+f, color=color[i])
    axes[0][3].plot(angulos4, label = "modelo"+f, color=color[i])

    axes[1][0].plot(distancias1, label = "modelo"+f, color=color[i])
    axes[1][1].plot(distancias2, label = "modelo"+f, color=color[i])
    axes[1][2].plot(distancias3, label = "modelo"+f, color=color[i])
    axes[1][3].plot(distancias4, label = "modelo"+f, color=color[i])

    distancias = np.array(distancias1 + distancias2 + distancias3 + distancias4)
    if np.max(distancias) > maxGlobal: maxGlobal = np.max(distancias)

    axes[2][0].plot(velocidades1, label = "modelo"+f, color=color[i])
    axes[2][1].plot(velocidades2, label = "modelo"+f, color=color[i])
    axes[2][2].plot(velocidades3, label = "modelo"+f, color=color[i])
    axes[2][3].plot(velocidades4, label = "modelo"+f, color=color[i])

axes[0][0].set_ylim(0, 190)
axes[0][1].set_ylim(0, 190)
axes[0][2].set_ylim(0, 190)
axes[0][3].set_ylim(0, 190)

axes[1][0].set_ylim(0, maxGlobal+800)
axes[1][1].set_ylim(0, maxGlobal+800)
axes[1][2].set_ylim(0, maxGlobal+800)
axes[1][3].set_ylim(0, maxGlobal+800)

axes[2][0].set_ylim(0, 210)
axes[2][1].set_ylim(0, 210)
axes[2][2].set_ylim(0, 210)
axes[2][3].set_ylim(0, 210)

axes[0][0].set_title('Angulo 1', fontdict={'fontsize':8, 'fontweight':'bold'})
axes[0][1].set_title('Angulo 2', fontdict={'fontsize':8, 'fontweight':'bold'})
axes[0][2].set_title('Angulo 3', fontdict={'fontsize':8, 'fontweight':'bold'})
axes[0][3].set_title('Angulo 4', fontdict={'fontsize':8, 'fontweight':'bold'})

axes[1][0].set_title('Distancia 1', fontdict={'fontsize':8, 'fontweight':'bold'})
axes[1][1].set_title('Distancia 2', fontdict={'fontsize':8, 'fontweight':'bold'})
axes[1][2].set_title('Distancia 3', fontdict={'fontsize':8, 'fontweight':'bold'})
axes[1][3].set_title('Distancia 4', fontdict={'fontsize':8, 'fontweight':'bold'})

axes[2][0].set_title('Velocidad 1', fontdict={'fontsize':8, 'fontweight':'bold'})
axes[2][1].set_title('Velocidad 2', fontdict={'fontsize':8, 'fontweight':'bold'})
axes[2][2].set_title('Velocidad 3', fontdict={'fontsize':8, 'fontweight':'bold'})
axes[2][3].set_title('Velocidad 4', fontdict={'fontsize':8, 'fontweight':'bold'})

lines, labels = fig.axes[-1].get_legend_handles_labels()
fig.legend(lines, labels, loc='center right')

plt.show()
