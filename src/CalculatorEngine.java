import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

//Engine for Button1

public class CalculatorEngine implements ActionListener{

    Calculator parent;

        double g2n    = 0;
        double gsn    = 0;
        double alfa12 = 0;
        double alfa32 = 0;
        double alfaSI = 0;
        double G      = 0;
        double Psi21  = 0;
        double alfa1I = 0;
        double alfa3I = 0;
        double Psi21h = 0;

        double chamberDeep = 0.0015;
        double hi   = 0.095;
        double g1n  = 0;
        double g3n  = 0;
        double gi   = 0;
        double ks   = 0;
        double Psi1 = 0;

    CalculatorEngine (Calculator parent) {

        this.parent = parent;

    }

    private String getResultAsString(double A1, double A2, double A3, double A, double alfaC, double alfaS, double alfaSI, double alfa1I, double alfa3I, double PsiIn, double Psi21h, double Psi21) {

        String StrKs;

        if (ks > 0) {

            StrKs = "WARNING!!! System is Unstable for Current Set of Parameters." ;

        } else {

            StrKs = "Everything is OK! System is Stable for Current Set of Parameters." ;

        }

        return new String (  "\n______________________\n"
                           + "\n Areas, [ m^2 ]:\n"
                           + "\n A1 = " + A1 + "; " + " A2 = " + A2 + "; " + "A3 = " + A3 + "; " + "A = " + A + ".\n"
                           + "\n Angles, [ rad ]:\n"
                           + "\n alfC = " + alfaC + "; " + " alfS = " + alfaS + ".\n"
                           + "\n Conductivitites, [m^5/(N*s)]:\n"
                           + "\n g1n = " + g1n + " ; " + "g2n = " + g2n + "; " + "g3n = " + g3n + "; " + " gi = " + gi + "; " + "gsn = " + gsn + ".\n"
                           + "\n Dimensionless Parameters:\n"
                           + "\n alfa12 = " + alfa12 + "; " + ";" + " alfa32 = " + alfa32 +  "; " + " G = " + G + ".\n"
                           + "\n alfa`si = " + alfaSI + "; " +  "alfa`1i = " + alfa1I + "; " + " alfa`3i = " + alfa3I + ".\n"
                           + "\n Psi1 = " + Psi1 + "; " + " Psi21(h) = " + Psi21h + "; " + " PsiIn = " + PsiIn + "; " + " Psi21 = " + Psi21 + ".\n"
                           + "\n Information about System Stability: \n" + "\n" + StrKs);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        double omega = 150;
        double delta = 0.000_005;
        double r1    = 0.12;
        double r3    = 0.14;
        double rc    = 0.003;
        double p1    = 10 * 10_000_000;
        double p3    = 0.9 * 10_000_000;
        double pn    = 10 * 10_000_000;
        double n     = 2;
        double nc    = 48;
        double E     = 5_000_000;
        double chambersAmount = 48;

        String dispR1      = parent.fieldR1.getText();
        String dispR3      = parent.fieldR3.getText();
        String dispRc      = parent.fieldRc.getText();
        String dispOmega   = parent.fieldOmega.getText();
        String dispGap     = parent.fieldGap.getText();
        String dispP1      = parent.fieldPressure1.getText();
        String dispP3      = parent.fieldPressure3.getText();
        String dispPn      = parent.fieldPressureN.getText();
        String dispChambAm = parent.fieldChambersAmount.getText();
        String dispFeedAm  = parent.fieldFeedersAmount.getText();

        if (!"".equals(dispR1) & !"".equals(dispR3)) {

            try {

                r1    = Double.parseDouble(dispR1);
                r3    = Double.parseDouble(dispR3);
                p1    = Double.parseDouble(dispP1);
                p3    = Double.parseDouble(dispP3);
                pn    = Double.parseDouble(dispPn);
                n     = Double.parseDouble(dispFeedAm);
                rc    = Double.parseDouble(dispRc);
                omega = Double.parseDouble(dispOmega);
                delta = Double.parseDouble(dispGap);

                chambersAmount = Double.parseDouble(dispChambAm);

            } catch (NumberFormatException e1) {

                javax.swing.JOptionPane.showConfirmDialog(null, "WARNING!!! Initial Data Must be a Number !!!", "", JOptionPane.PLAIN_MESSAGE);

            }

        }

        double r2    = 0.5 * (r1 + r3);
        double r22   = r2 + rc;
        double r21   = r2 - rc;
        double l1    = r3 - r22;
        double l3    = r21 - r1;
        double A1    = Math.PI * (r3 * r3 - r22 * r22);
        double A2    = Math.PI * (r22 * r22 - r21 * r21);
        double A3    = Math.PI * (r21 * r21 - r1 * r1);
        double A     = 0.5 * (A1 + 2 * A2 + A3);
        double alfaC = 6.0 * (rc / r2);
        double alfaS = 2.0 * (Math.PI / chambersAmount);

        double tau       = (n * alfaC) / (2 * Math.PI);

        double B1        = Math.PI * (r3 * r3 - r2 * r2);
        double ks        = (B1 - 0.5 * A1) / A;
        double V         = chamberDeep * alfaC * r21 * rc;
        double Psi3      = p3/pn;
        double beta      = 1 - Psi3;
        double l         = r3 - r2;
        double viscosity = 0.0000018;
        double d         = Math.pow((60 * l * delta * delta * delta * r3) /( nc * l1), 0.25);

        double density = 1.2;
        double PsiIn   = (0.5 * density * omega * omega * (r3 * r3 - r2 * r2)) / pn;

        Psi1   = p1 / pn;
        g1n    = (alfaS * r3 * delta * delta * delta) / (12 * viscosity * l1);
        g3n    = (alfaS * r21 * delta * delta * delta) / (12 * viscosity * l3);
        g2n    = ((V * n * omega) / (E * Math.PI));
        gi     = (Math.PI * d * d * d * d) / (128 * viscosity * l);
        gsn    = (0.5 * g2n * ((1 / tau) - (1 / (1 - tau) )));
        alfaSI = (2.0 * (gsn / gi));
        alfa12 = (g1n/g2n);
        alfa32 = (g3n/g2n);
        alfa1I = (2.0 * (g1n / gi));
        alfa3I = (2.0 * (g3n / gi));

        G = (alfa12 + alfa32);

        Psi21h = (((alfa1I * Psi1 + alfa3I * Psi3) + Psi1) / ((alfa1I + alfa3I) + 1));
        Psi21  = ((alfa1I * Psi1 + alfa3I * Psi3) + (Psi1 * (1 - alfaSI)) + alfaSI * PsiIn) / ((alfa1I + alfa3I) + 1 - alfaSI);

        Object src = e.getSource();

        if (src == parent.butWatter) {

            E         = 2_000_000_000.0;
            viscosity = 0.001;
            density   = 1000.0;

        } else if (src == parent.butGas) {

            E = 5_000_000.0;
            viscosity = 0.000_0018;
            density = 1.2;

        } else if (src == parent.evaluateStaticCharacteristics) {

            if (g1n < 0 || g2n < 0 || g3n < 0 || gi < 0) {

                JOptionPane.showConfirmDialog(null, "WARNING!!! Conductivity can't be less than zero!", "", JOptionPane.PLAIN_MESSAGE);

            } else {

                JOptionPane.showConfirmDialog(null, "CALCULATION RESULTS:" + getResultAsString(A1, A2, A3, A, alfaC, alfaS, alfaSI, alfa1I, alfa3I, PsiIn, Psi21h, Psi21), "", JOptionPane.PLAIN_MESSAGE);

            }

        } else if (src == parent.graphicCreation) {

            int curve    = parent.newPlotRate.createCurve((int) n + " " + (int) nc);

            for (double Psi = 0.2; Psi < 2.0; Psi += 0.01) {

                double u = (((1.0 - alfaSI) * ((1.0 - ks) * Psi - hi) + alfaSI * PsiIn) / ((ks * (alfa1I + alfa3I) - alfa1I) * Psi + (alfa1I + alfa3I) * hi));

                parent.newPlot.addPointSimple(Psi, Math.pow(u, 0.3));
                parent.newPlotRate.addPoint(Psi, ((u * (1 + alfa12 * u) * beta - PsiIn) / (1 + (G * u))),curve);

            }

            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {

                    parent.newPlot.setVisible(true);

                    parent.newPlotRate.setVisible(true);

                }
            });

        } else if (src == parent.findBestSet) {

            Finder find = new Finder();

        } else if (src == parent.clearButton) {

             parent.fieldR1.setText("" + "0");
             parent.fieldOmega.setText("" + "0");
             parent.fieldGap.setText("" + "0");
             parent.fieldR3.setText("" + "0");
             parent.fieldRc.setText("" + "0");
             parent.fieldPressure1.setText("" + "0");
             parent.fieldPressure3.setText("" + "0");
             parent.fieldPressureN.setText("" + "0");
             parent.fieldChambersAmount.setText("" + "0");
             parent.fieldFeedersAmount.setText("" + "0");

        }
    }
}