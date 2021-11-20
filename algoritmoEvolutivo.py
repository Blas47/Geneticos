import json
from EEvolutivaMultipleUplusl import *

class AlgoritmoEvolutivo():
    def __init__(self, nombreFichero, indices, ficheroParams="Params.json" ,poblacion=None):
        self.nombreFichero = nombreFichero
        self.indices = indices
        self.params = self.loadParams(ficheroParams)
        self.nIterations = self.params["NumberMaxIterations"]
        self.sizePoblacion = self.params["SizePoblacion"]
        self.sizeTournament = self.params["SizeTournament"]
        if poblacion is None:
            self.poblacion = Poblacion(self.sizePoblacion, self.params["SizeIndividuo"])
        else:
            self.poblacion = poblacion

        # To trace result
        self.DataHistorical = {
            "nIterations": self.nIterations,
            "numIndividuos": self.sizePoblacion,
            "nEvaluations": self.nIterations * self.sizePoblacion,
            "sizeTournament": self.sizeTournament,
            "BestIndividuo": None,
            "BestFitness": None,
            "BestIndividuoMotors": None,
            "ResultadoObtenido": []
        }
    
    def loadParams(self, ficheroName):
        with open(ficheroName) as json_file:
            return json.load(json_file)

    #region TRACE
    def updateResults(self):
        self.DataHistorical["ResultadoObtenido"].append(
            {
                "CurrentIteration": self.poblacion.currentGeneration,
                "BestFitnessOfthisIteration": self.poblacion.getBestIndividuo().fitnessValue,
                "BestIndividualOfthisIteration": str(self.poblacion.getBestIndividuo()),
                "WorstFitnessOfthisIteration": self.poblacion.getWorstIndividuo().fitnessValue,
                "WorstIndividualOfthisIteration": str(self.poblacion.getWorstIndividuo()),
                "PresionSelectiva": self.poblacion.getPS(),
                "Diversidad": self.poblacion.getDiversity(),
            }
        )

    def FinalupdateResults(self):
        self.eevolutiva.DataHistorical["BestIndividuo"] = str(self.poblacion.getBestIndividuo())
        self.eevolutiva.DataHistorical["BestFitness"] = self.poblacion.getBestIndividuo().fitnessValue
        self.eevolutiva.DataHistorical["nIterations"] = len(self.DataHistorical["ResultadoObtenido"])
        self.eevolutiva.DataHistorical["nEvaluations"] = self.poblacion.nRequests
    #endregion


    def run(self):
        #generar población inicial
        print("Corriendo Algortimo...")
        while(self.poblacion.currentGeneration<self.nIterations):
            print(f"Generacion {self.poblacion.currentGeneration}")
            #Every time an ind is generated is evaluated
            # self.updateResults() #to trace result
            #prueba u+l
            self.poblacion = runProcedimientoUplusl(self.poblacion)
            #damos por terminada la generacion/iteracion
            self.poblacion.currentGeneration += 1
        #self.FinalupdateResults() #to trace result
        #print(f"Mejor individuo: {self.poblacion.getBestIndividuo().fitnessValue}")
        return self.DataHistorical
        #evaluar poblacion




class Poblacion():
    def __init__(self, sizePoblacion, sizeIndividuo, individuos=None):
        self.sizePoblacion = sizePoblacion
        self.sizeIndividuo = sizeIndividuo
        if individuos is None:
            self.individuos = self.generarIndividuosAleatorios()
        else:
            self.individuos = individuos
        self.currentGeneration = 0
        #inicialmente, al crear los individuos se evaluan, hay que incrementarlo al realizar una nueva evaluacion
        self.nRequests = sizePoblacion

    def generarIndividuosAleatorios(self):
        #todo
        return [Individuo() for individuo in range(self.sizePoblacion)]
    
    #region getDataPoblacion
    def sort(self):
        self.individuos = sorted(self.individuos, key=lambda x: float(x.fitnessValue), reverse=False)

    #revisar orden sort
    def getBestNIndividuos(self,n):
        qualy = sorted(self.individuos, key=lambda x: float(x.fitnessValue), reverse=False)
        return qualy[:n]
    
    def getBestIndividuo(self):
        return self.getBestNIndividuos(1)[0]
    
    def getWorstIndividuo(self):
        qualy = sorted(self.individuos, key=lambda x: float(x.fitnessValue), reverse=False)
        return qualy[-1]
    
    def getMeanFit(self):
        fmean = 0
        for individuo in self.individuos:
            fmean += float(individuo.fitnessValue)
        return (fmean / self.sizepoblacion)
    
    def getFr(self, individuoFR):
        ftotal = 0
        for individuo in self.individuos:
            ftotal += float(individuo.fitnessValue)
        return (float(individuoFR.fitnessValue) / ftotal)
    #Desviacion
    def getDiversity(self):
        sumatory = 0
        fmean = self.getMeanFit()
        for ind in self.individuos:
            frelative = self.getFr(ind)
            sumatory += ((frelative - fmean)**2)
        return math.sqrt(sumatory)

    def getPS(self):
        fmax = float(self.getBestIndividuo().fitnessValue)
        fmean = self.getMeanFit()
        # return fmax / fmean if were maximization
        if fmax != 0:
            return  fmean / fmax # as minimization
        else:
            return -1
    #endregion

class Individuo():
    def __init__(self, cromosoma=None, fit=None):
        if cromosoma is None:
            self.cromosoma = self.generarCromosomaRandom()
        else:
            self.cromosoma = cromosoma
        if fit is None:
            self.fitnessValue = self.computefit()   
        else:
            self.fitnessValue = fit   
    
    def generarCromosomaRandom(self):
        #todo
        pass 

    def computefit(self):
        #todo
        pass     
        #self.poblacion.nRequests += 1

if __name__ == "__main__":
    print("Run the file desafioFinal!")
