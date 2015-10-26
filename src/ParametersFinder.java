/**
 * Created by alonakhalizeva on 10/21/15.
 */
public class ParametersFinder {

    double chamberDeep = 0.0015;
    double hi   = 0.095;

    double omega = 150;
    double delta = 0.000_005;
    double r1 = 0.12;
    double r3 = 0.14;
    double rc = 0.003;
    double p1 = 10 * 10_000_000;
    double p3 = 0.9 * 10_000_000;
    double pn = 10 * 10_000_000;
    double E  = 5_000_000;
    double r2 = 0.5 * (r1 + r3);
    double r22 = r2 + rc;
    double r21 = r2 - rc;
    double l3 = r21 - r1;
    double A1 = Math.PI * (r3 * r3 - r22 * r22);
    double A2 = Math.PI * (r22 * r22 - r21 * r21);
    double A3 = Math.PI * (r21 * r21 - r1 * r1);
    double A = 0.5 * (A1 + 2 * A2 + A3);
    double alfaC = 6.0 * (rc / r2);
    double B1 = Math.PI * (r3 * r3 - r2 * r2);
    double ks = (B1 - 0.5 * A1) / A;
    double V = chamberDeep * alfaC * r21 * rc;
    double Psi3 = p3 / pn;
    double beta = 1 - Psi3;
    double l1   = r3 - r22;
    double density = 1.2;
    double viscosity = 0.0000018;
    double PsiIn   = (0.5 * density * omega * omega * (r3 * r3 - r2 * r2)) / pn;

    double Psi1 = p1 / pn;
    public double alfaS;
    public double d;
    public double tau;
    public double l;

    public double g1n;
    public double g3n;
    public double g2n;
    public double gi;
    public double gsn;

    public double alfaSI;
    public double alfa32;
    public double alfa12;
    public double alfa1I;
    public double alfa3I;
    public double G;
    public double Psi21h;
    public double Psi21;

    public void Calculate(double n, double nc) {

        r2 = 0.5 * (r1 + r3);
        l = r3 - r2;

        alfaS = 2.0 * (Math.PI / nc);
        d     = Math.pow((60 * l * delta * delta * delta * r3) / ( nc * l1), 0.25);
        tau   = (n * alfaC) / (2 * Math.PI);

        g1n = (alfaS * r3 * delta * delta * delta) / (12 * viscosity * l1);
        g3n = (alfaS * r21 * delta * delta * delta) / (12 * viscosity * l3);
        g2n = ((V * n * omega) / (E * Math.PI));
        gi  = (Math.PI * d * d * d * d) / (128 * viscosity * l);
        gsn = (0.5 * g2n * ((1 / tau) - (1 / (1 - tau))));

        alfaSI = (2.0 * (gsn / gi));
        alfa12 = (g1n / g2n);
        alfa32 = (g3n / g2n);
        G      = (alfa12 + alfa32);
        alfa1I = (2.0 * (g1n / gi));
        alfa3I = (2.0 * (g3n / gi));
        Psi21h = (((alfa1I * Psi1 + alfa3I * Psi3) + Psi1) / ((alfa1I + alfa3I) + 1));
        Psi21  = ((alfa1I * Psi1 + alfa3I * Psi3) + (Psi1 * (1 - alfaSI)) + alfaSI * PsiIn) / ((alfa1I + alfa3I) + 1 - alfaSI);

    }
}
