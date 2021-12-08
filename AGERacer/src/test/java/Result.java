public class Result {
    public Individual best;
    public double executionTime;
    public int generations;
    public int nEjecucion;

    public Result(Individual best, double executionTime, int generations, int nEjecucion) {
        this.best = best;
        this.executionTime = executionTime;
        this.generations = generations;
        this.nEjecucion = nEjecucion;
    }

}
