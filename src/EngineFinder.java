import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

//Engine for Button1

public class EngineFinder implements ActionListener {

    Finder parent;

    double chamberDeep = 0.0015;
    double hi = 0.095;

    double omega = 150;
    double delta = 0.000_005;
    double r1 = 0.12;
    double r3 = 0.14;
    double rc = 0.003;
    double p1 = 10 * 10_000_000;
    double p3 = 0.9 * 10_000_000;
    double pn = 10 * 10_000_000;
    double E = 5_000_000;

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
    double l1 = r3 - r22;
    double density = 1.2;
    double viscosity = 0.0000018;
    double PsiIn = (0.5 * density * omega * omega * (r3 * r3 - r2 * r2)) / pn;

    double Psi1 = p1 / pn;

    List<Double> ListFeeders = new ArrayList<Double>();
    List<Double> ListChambers = new ArrayList<Double>();

    EngineFinder(Finder parent) {

        this.parent = parent;

    }

    private String getResultAsString(double n, double nc, double gi) {

        String StrKs;

        if (ks > 0) {

            StrKs = "WARNING!!! System is Unstable for Current Set of Parameters.";

        } else {

            StrKs = "Everything is OK! System is Stable for Current Set of Parameters.";

        }

        return new String("\n______________________\n"
                + "\n Chambers Amount:\n" + nc + ";"
                + "\n Feeders Amount: " + n + ";\n"
                + "\n Feeder's Conductivity: " + gi
                + "\n Information about System Stability: \n" + "\n" + StrKs);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String dispR1 = Calculator.fieldR1.getText();
        String dispR3 = Calculator.fieldR3.getText();
        String dispRc = Calculator.fieldRc.getText();
        String dispOmega = Calculator.fieldOmega.getText();
        String dispGap = Calculator.fieldGap.getText();
        String dispP1 = Calculator.fieldPressure1.getText();
        String dispP3 = Calculator.fieldPressure3.getText();
        String dispPn = Calculator.fieldPressureN.getText();

        if (!"".equals(dispR1) & !"".equals(dispR3)) {

            try {

                r1 = Double.parseDouble(dispR1);
                r3 = Double.parseDouble(dispR3);
                p1 = Double.parseDouble(dispP1);
                p3 = Double.parseDouble(dispP3);
                pn = Double.parseDouble(dispPn);
                rc = Double.parseDouble(dispRc);
                omega = Double.parseDouble(dispOmega);
                delta = Double.parseDouble(dispGap);

            } catch (NumberFormatException e1) {

                javax.swing.JOptionPane.showConfirmDialog(null, "WARNING!!! Initial Data Must be a Number !!!", "", JOptionPane.PLAIN_MESSAGE);

            }

        }

        Object src = e.getSource();

        if (src == parent.but2) {

            ListFeeders.add(2.0);

        } else if (src == parent.but4) {

            ListFeeders.add(4.0);

        } else if (src == parent.but12) {

            for (double i = 2.0; i <= 12.0; i += 2) {

                ListChambers.add(i);
            }

        } else if (src == parent.but24) {

            for (double i = 14.0; i <= 24.0; i += 2) {

                ListChambers.add(i);
            }

        } else if (src == parent.but36) {

            for (double i = 26.0; i <= 36.0; i += 2) {

                ListChambers.add(i);
            }

        } else if (src == parent.but48) {

            for (double i = 38.0; i <= 48.0; i += 2) {

                ListChambers.add(i);
            }

        } else if (src == parent.butWatter) {

            E = 2_000_000_000.0;
            viscosity = 0.001;
            density = 1000.0;

        } else if (src == parent.butGas) {

            E = 5_000_000.0;
            viscosity = 0.000_0018;
            density = 1.2;

        } else if (src == parent.findOPt) {

            for (int i = 0; i < ListFeeders.size(); i++) {

                double n = ListFeeders.get(i);

                for (int j = 0; j < ListChambers.size(); j++) {

                    double nc = ListChambers.get(j);

                    Result result = new Result();
                    result.Calculate(n, nc);

                    JOptionPane.showConfirmDialog(null, "THE BEST SET OF PARAMETERS IS:" + getResultAsString(n, nc, result.gi), "", JOptionPane.PLAIN_MESSAGE);
                }
            }

        } else if (src == parent.createOPtGraphics) {

            for (int i = 0; i < ListFeeders.size(); i++) {

                double n = ListFeeders.get(i);

                for (int j = 0; j < ListChambers.size(); j++) {

                    double nc = ListChambers.get(j);

                    int curveGap = parent.newPlot.createCurve((int) n + " " + (int) nc);
                    int curve    = parent.newPlotRate.createCurve((int) n + " " + (int) nc);
                    Result result = new Result();
                    result.Calculate(n, nc);

                    for (double Psi = 0.2; Psi < 2.0; Psi += 0.01) {

                        double u = (((1.0 - result.alfaSI) * ((1.0 - ks) * Psi - hi) + result.alfaSI * PsiIn) / ((ks * (result.alfa1I + result.alfa3I) - result.alfa1I) * Psi + (result.alfa1I + result.alfa3I) * hi));

                        parent.newPlot.addPoint1(Psi, Math.pow(u, 0.3), curveGap);
                        parent.newPlotRate.addPoint(Psi, (u * (1 + result.alfa12 * u) * beta - PsiIn) / (1 + (result.G * u)), curve);

                    }
                }
            }

            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {

                    parent.newPlot.updateDataset();
                    parent.newPlot.setVisible(true);
                    parent.newPlotRate.updateDataset();
                    parent.newPlotRate.setVisible(true);

                }
            });
        }
    }

    private class Result {

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
}