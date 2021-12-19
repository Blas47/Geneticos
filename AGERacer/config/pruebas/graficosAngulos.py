import matplotlib.pyplot as plt

minGlobal, maxGlobal = 1000, 0
files = ['1', '2', '3']
color = ['blue', 'orange', 'red']

fig, axes = plt.subplots(ncols = 4, figsize = (13, 3))

for f, i in zip(files, range(len(color))):
    angulos1, angulos2, angulos3, angulos4 = [], [], [], []
    file = open(f+'/log0-0.txt', "r")
    file = file.readlines()

    for linea in file:
        if linea[:3] == 'Val':
            dividir = linea.split(', ')
            angulos1.append(float(dividir[0].replace('Values: [', ''))%181)
            angulos2.append(float(dividir[3])%181)
            angulos3.append(float(dividir[6])%181)
            angulos4.append(float(dividir[9])%181)
    
    axes[0].plot(angulos1, label = "modelo"+f, color=color[i])
    axes[1].plot(angulos2, label = "modelo"+f, color=color[i])
    axes[2].plot(angulos3, label = "modelo"+f, color=color[i])
    axes[3].plot(angulos4, label = "modelo"+f, color=color[i])

axes[0].set_ylim(0, 190)
axes[1].set_ylim(0, 190)
axes[2].set_ylim(0, 190)
axes[3].set_ylim(0, 190)

axes[0].set_title('Angulo 1', fontdict={'fontsize':11, 'fontweight':'bold'})
axes[1].set_title('Angulo 2', fontdict={'fontsize':11, 'fontweight':'bold'})
axes[2].set_title('Angulo 3', fontdict={'fontsize':11, 'fontweight':'bold'})
axes[3].set_title('Angulo 4', fontdict={'fontsize':11, 'fontweight':'bold'})

lines, labels = fig.axes[-1].get_legend_handles_labels()
fig.legend(lines, labels, loc='center right')

plt.show()
