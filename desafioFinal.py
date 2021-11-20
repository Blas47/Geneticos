import subprocess
from algoritmoEvolutivo import * 
from javaTranslator import *
  
if __name__ == "__main__":
    AGENTE1 = 'AGERacer Code/AGERacer/src/test/java/Agent1.java'
    myJavaParser = JavaParser(AGENTE1)
    #Algoritmo genétcio
    myAlgoritmoEvolutivo = AlgoritmoEvolutivo(AGENTE1, myJavaParser.indices)
    myAlgoritmoEvolutivo.run()
