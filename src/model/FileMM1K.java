package model;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

public class FileMM1K
{
    private double lambda;
    private double mu;
    private int k;
    private double rho;

    public FileMM1K(double lambdaParam, double muParam, int kParam)
    {
        this.lambda = lambdaParam;
        this.mu = muParam;
        this.k = kParam;
        this.rho = lambdaParam / muParam;
    }


    public double dureeMoyenneService()
    {
        return 1/mu;
    }

    public double qI(int i)
    {
        if(rho == 1)
        {
            return (double) 1/(k+1);
        }
        else
        {
            return ((1-rho)*pow(rho, i))/(1-pow(rho, k+1));
        }
    }

    public double L()
    {
        if(rho == 1)
        {
            return (double) k/2;
        }
        else
        {
            return (rho*(1-(k+1)*pow(rho, k)+k*pow(rho, k+1)))/((1-rho)*(1-pow(rho, k+1)));
        }
    }

    public double Lq()
    {
        return L() - (1-qI(0));
    }

    /*public double W()
    {
        return 1 / (mu-lambda);
    }

    public double Wq()
    {
        return lambda/(mu*(mu-lambda));
    }

    public double probaTempsSejour(double t)
    {
        return exp(-mu * (1 - rho) * t);
    }*/

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
