      
import os.path, subprocess
from subprocess import STDOUT, PIPE

class JavaParser:
    def __init__(self, nombreFichero):
        self.nombreFichero = nombreFichero
        self.indices = self.leerJava()

    def leerJava(self):
        with open(self.nombreFichero, 'r') as fichero:
           return self.obtenerIndices(fichero)
    
    def obtenerIndices(self, fichero):
        lineas = [linea.strip() for linea in fichero.readlines()]
        return [index for index, linea in enumerate(lineas, start = 1) if linea == "//MODIFICAR"]

    
    def modificarJava(self, thrust, brakeDist, brakeThrust):
        # Cambiar las lineas 
        data = self.obtenerDatosFicheroModificado(thrust, brakeDist, brakeThrust)
        # Escribir lineas
        overwriteDataInFile(data)
        
    def obtenerDatosFicheroModificado(self, thrust, brakeDist, brakeThrust):
        with open(self.nombreFichero, 'r') as file:
            # Leer lineas
            data = file.readlines()
            # Modificar lineas
            data[self.indices[0]] = 'int thrust = ' + str(thrust) + ';\n'
            data[self.indices[1]] = 'if(targ.distance(current) < '+str(brakeDist)+' ){\n'
            data[self.indices[2]] = 'thrust = ' + str(brakeThrust) + ';\n'
            return data

    def overwriteDataInFile(self, data):
        with open(self.nombreFichero, 'w') as file:
            file.writelines(data)

    def correrJava(self, thrust, brakeDist, brakeThrust):
        self.modificarJava(thrust, brakeDist, brakeThrust)
        self.compilarJava()
        self.ejecutarSkeleton()

    def compilarJava(self):
        subprocess.run(['javac', self.nombreFichero])
        print('Agente compilado')


    def ejecutarSkeleton(self):
        #ir al directorio donde este el pom.xml
        print('CAMBIANDO DIRECTORIO')
        os.chdir('AGERacer Code/AGERacer')
        print(os.getcwd())
        print('COMPILANDOOO')
        subprocess.run(['mvn', 'compile'])
        print('COMPILADO')
        #subprocess.run(['java','SkeletonMain'])
        """
        cmd=['java', self.nombreFichero]
        proc=subprocess.Popen(cmd, stdout = PIPE, stderr = STDOUT)
        #input
        subprocess.Popen(cmd, stdin = PIPE)
        print(proc.stdout.read())
        """

if __name__ == "__main__":
    print("Run the file desafioFinal!")
    parser = JavaParser('/Users/nachoblascoalis/Documents/Geneticos/AGERacer Code/AGERacer/src/test/java/Agent1.java')
    parser.compilarJava()
    parser.ejecutarSkeleton()