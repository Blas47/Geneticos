import matplotlib.pyplot as plt

minGlobal, maxGlobal = 1000, 0
files = ['1', '2', '3']
color = ['blue', 'orange', 'red']

fig, axes = plt.subplots(ncols = 4, figsize = (13, 3))

for f, i in zip(files, range(len(color))):
    velocidades1, velocidades2, velocidades3, velocidades4 = [], [], [], []
    file = open('config/pruebas/'+f+'/log0-0.txt', "r")
    file = file.readlines()

    for linea in file:
        if linea[:3] == 'Val':
            dividir = linea.split(', ')
            velocidades1.append(float(dividir[2])%201)
            velocidades2.append(float(dividir[5])%201)
            velocidades3.append(float(dividir[8])%201)
            velocidades4.append(float(dividir[11].replace(']', ''))%201)
    
    axes[0].plot(velocidades1, label = "modelo"+f, color=color[i])
    axes[1].plot(velocidades2, label = "modelo"+f, color=color[i])
    axes[2].plot(velocidades3, label = "modelo"+f, color=color[i])
    axes[3].plot(velocidades4, label = "modelo"+f, color=color[i])

axes[0].set_ylim(0, 210)
axes[1].set_ylim(0, 210)
axes[2].set_ylim(0, 210)
axes[3].set_ylim(0, 210)

axes[0].set_title('Velocidad 1', fontdict={'fontsize':11, 'fontweight':'bold'})
axes[1].set_title('Velocidad 2', fontdict={'fontsize':11, 'fontweight':'bold'})
axes[2].set_title('Velocidad 3', fontdict={'fontsize':11, 'fontweight':'bold'})
axes[3].set_title('Velocidad 4', fontdict={'fontsize':11, 'fontweight':'bold'})

lines, labels = fig.axes[-1].get_legend_handles_labels()
fig.legend(lines, labels, loc='center right')

plt.show()
