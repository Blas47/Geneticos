import matplotlib.pyplot as plt

minGlobal, maxGlobal = 1000, 0
files = ['1', '2', '3']
color = ['blue', 'orange', 'red']

for f, i in zip(files, range(len(color))):
    grafico = []
    file = open(f+'/log0-0.txt', "r")
    file = file.readlines()
    xValue = 0
    minValue = 0
    counter = 0

    for linea in file:
        if linea[0] == 'F':
            dividir = linea.split(': ')
            grafico.append(float(dividir[1].split('\n')[0]))

    minValue = grafico[-1]
    if minValue < minGlobal: minGlobal = minValue
    if grafico[0] > maxGlobal: maxGlobal = grafico[0]
    xValue = len(grafico)

    plt.plot(grafico, label="modelo"+f, color=color[i])
    #plt.hlines(y=minValue, xmin=0, xmax=len(grafico)+10, label=("Valor minimo: " + str(minValue)), color="pink")
    #plt.vlines(x=xValue, ymin=0, ymax=500, color="darkblue")

plt.title("Comparación de Modelos", fontdict={'fontsize':14, 'fontweight':'bold'})
plt.xlabel("Generaciones")
plt.ylabel("Mejor individuo")
plt.ylim(minGlobal-10, maxGlobal+10)
plt.legend()
plt.show()