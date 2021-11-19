class AlgoritmoEvolutivo():
    def __init__(self, nombreFichero, indices):
        self.nombreFichero = nombreFichero
        self.indices = indices

    #region JAVA
    def modificarJava(self, initialThrust, brakeDist, brakeThrust):
        # Cambiar las lineas 
        with open(self.nombreFichero, 'r') as file:
            # Leer lineas
            data = file.readlines()
            # Modificar lineas
            data[self.indices[0]] = 'int thrust = ' + str(initialThrust) + ';\n'
            data[self.indices[1]] = 'if(targ.distance(current) < '+str(brakeDist)+' ){\n'
            data[self.indices[2]] = 'thrust = ' + str(brakeThrust) + ';\n'

        with open(self.nombreFichero, 'w') as file:
            # Escribir lineas
            file.writelines(data)

    # def compilarJava():
        
        
    # def ejecutarSkeleton(distFrenado, accFren, accNormal):
    #endregion
    def run(self):
        #generar población inicial
        print("Corriendo Algortimo...")
        #evaluar poblacion

class nuevoIndividuo():
    def __init__(self, cromosoma, fit):
        self.cromosoma = cromosoma
        self.fit = fit        
        


if __name__ == "__main__":
    agente1 = 'AGERacer Code/AGERacer/src/test/java/Agent1.java'
    indices = [30, 32, 34]
    algoritmoEvolutivo = AlgoritmoEvolutivo(agente1, indices)
    algoritmoEvolutivo.run()
    algoritmoEvolutivo.modificarJava(80, 2000, 80)
