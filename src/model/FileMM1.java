package model;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

public class FileMM1
{
    private double lambda;
    private double mu;
    private double rho;

    public FileMM1(double lambdaParam, double muParam)
    {
        this.lambda = lambdaParam;
        this.mu = muParam;
        this.rho = lambdaParam / muParam;
    }

    public double rho()
    {
        return lambda/mu;
    }

    public double dureeMoyenneService()
    {
        return 1/mu;
    }

    public double q0()
    {
        return 1 - rho();
    }

    public double L()
    {
        return lambda/(mu-lambda);
    }

    public double Lq()
    {
        return pow(lambda, 2) / (mu * (mu-lambda));
    }

    public double W()
    {
        return 1 / (mu-lambda);
    }

    public double Wq()
    {
        return lambda/(mu*(mu-lambda));
    }

    public double probaTempsSejour(double t)
    {
        return exp(-mu * (1 - rho()) * t);
    }

    public double getLambda()
    {
        return lambda;
    }

    public void setLambda(double lambda)
    {
        this.lambda = lambda;
    }

    public double getMu()
    {
        return mu;
    }

    public void setMu(double mu)
    {
        this.mu = mu;
    }

    public double getRho()
    {
        return rho;
    }

    public void setRho(double rho)
    {
        this.rho = rho;
    }
}

