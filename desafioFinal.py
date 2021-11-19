import subprocess
from algoritmoEvolutivo import * 

AGENTE1 = 'AGERacer Code/AGERacer/src/test/java/Agent1.java'
        
#region JavaParser

def leerJava(nombreFichero):
    with open(nombreFichero, 'r') as fichero:
        indices = obtenerIndices(fichero)
        print(indices)
    return indices
    
def obtenerIndices(fichero):
    lineas = [linea.strip() for linea in fichero.readlines()]
    return [index for index, linea in enumerate(lineas, start = 1) if linea == "//MODIFICAR"]

    
def modificarJava(agentfile, initialThrust, brakeDist, brakeThrust, indices):
    # initialThrust = 171
    # brakeDist = 4001
    # brakeThrust = 91
    #agentfile = 'agent1.java'

    # Cambiar las lineas 
    with open(agentfile, 'r') as file:
        # Leer lineas
        data = file.readlines()
        # Modificar lineas
        data[indices[0]] = 'int thrust = ' + str(initialThrust) + ';\n'
        data[indices[1]] = 'if(targ.distance(current) < '+str(brakeDist)+' ){\n'
        data[indices[2]] = 'thrust = ' + str(brakeThrust) + ';\n'

    with open(agentfile, 'w') as file:
        # Escribir lineas
        file.writelines(data)

# def compilarJava():
    
    
# def ejecutarSkeleton(distFrenado, accFren, accNormal):

#Enderregion




if __name__ == "__main__":
    indices = leerJava(AGENTE1)
    #Algoritmo genétcio
    algoritmoEvolutivo = AlgoritmoEvolutivo(AGENTE1, indices)
    algoritmoEvolutivo.run()