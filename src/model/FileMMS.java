package model;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

public class FileMMS
{
    private double lambda;
    private double mu;
    private double rho;
    private int nbServer;

    public FileMMS(double lambdaParam, double muParam, int nbServerParam)
    {
        this.lambda = lambdaParam;
        this.mu = muParam;
        this.rho = lambdaParam / (nbServerParam * muParam);
        this.nbServer = nbServerParam;
    }

   
/*
    public double dureeMoyenneService()
    {
        return 1/mu;
    }*/
    
    public double factorial(int number)
    {
        double result = 1;

        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }

    public double q0()
    {
    	double sum=0;
    	
    	for ( int j = 0 ; j < nbServer ; j++)
    	{
    		sum = sum + (pow((rho * nbServer),j) / factorial(j));
    	}

        return 1 / (sum + ((pow((rho * nbServer),nbServer)) / (factorial(nbServer) * (1 - rho))));
    }

    public double qJ(int j)
    {
        double q0 = q0();
        double result = 0;
        if(j < nbServer && j >= 0)
        {
            result = ((pow((rho*nbServer), j))/factorial(j))*q0;
        }
        else if(j >= nbServer)
        {
            result = ((pow(nbServer, nbServer)*pow(rho, j))/ factorial(nbServer))*q0;
        }
        return result;
    }

    public double L()
    {
        return lambda * W();
    }

    public double Lq()
    {
        return q0()*((pow(rho*nbServer, nbServer)*rho)/(factorial(nbServer)*(pow(1-rho,2))));
    }

    public double W()
    {
        return Wq()+(1/mu);
    }

    public double Wq()
    {
        return Lq()/lambda;
    }

    public double probaTempsSejour(double t)
    {
        double q0 = q0();
        return exp(-mu*t)*(1+((q0*pow((rho*nbServer), nbServer))/(factorial(nbServer)*(1-rho)))*((1-exp(-mu*t*(nbServer-1-rho*nbServer)))/(nbServer-1-pow(rho, nbServer))));
    }

    public double probaTempsAttenteSansService()
    {
        double q0 = q0();
        return (q0 * pow((rho * nbServer), nbServer))/(factorial(nbServer)*(1-rho));
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
}

