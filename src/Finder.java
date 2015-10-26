import javax.swing.*;
import java.awt.*;

//Button 2 " Find the best set of seal parameters" on BASIC FRAME

public class Finder {

    JButton findOPt           = new JButton("Find the best set of parameters");
    JButton createOPtGraphics = new JButton("Create Graphics");

    JCheckBox but2 = new JCheckBox("2 Feeders");
    JCheckBox but4 = new JCheckBox("4 Feeders");

    JCheckBox butWatter = new JCheckBox("Water");
    JLabel labelWatter  = new JLabel("");
    JLabel labelWatter2 = new JLabel("");
    JCheckBox butGas    = new JCheckBox("Gas");
    JLabel labelGas  = new JLabel("");
    JLabel labelGas2 = new JLabel("");

    JCheckBox but12 = new JCheckBox(" 2 - 12 Chambers");
    JCheckBox but24 = new JCheckBox("13 - 24 Chambers");
    JCheckBox but36 = new JCheckBox("25 - 36 Chambers");
    JCheckBox but48 = new JCheckBox("35 - 48 Chambers");

    ImageIcon image2   = new ImageIcon("2.png");
    JLabel labelImage2 = new JLabel("", image2, JLabel.RIGHT);
    ImageIcon image4   = new ImageIcon("4.png");
    JLabel labelImage4 = new JLabel("", image4, JLabel.RIGHT);

    ImageIcon image12   = new ImageIcon("12.png");
    JLabel labelImage12 = new JLabel("", image12, JLabel.CENTER);
    ImageIcon image24   = new ImageIcon("24.png");
    JLabel labelImage24 = new JLabel("", image24, JLabel.CENTER);
    ImageIcon image36   = new ImageIcon("36.png");
    JLabel labelImage36 = new JLabel("", image36, JLabel.CENTER);
    ImageIcon image48   = new ImageIcon("48.png");
    JLabel labelImage48 = new JLabel("", image48, JLabel.CENTER);

    PlotsCalculator newPlot          = new PlotsCalculator("Plots");
    PlotsCalculatorRates newPlotRate = new PlotsCalculatorRates("Plot Rate");

    Finder() {

        JPanel windowContent = new JPanel();
        FlowLayout fl = new FlowLayout();
        windowContent.setLayout(fl);

        JPanel pIm = new JPanel();
        GridLayout glIm = new GridLayout(3, 5);
        pIm.setLayout(glIm);
        pIm.add(labelImage2);
        pIm.add(but2);
        pIm.add(labelWatter);
        pIm.add(labelImage4);
        pIm.add(but4);
        pIm.add(butGas);
        pIm.add(labelImage12);
        pIm.add(but12);
        pIm.add(labelGas);
        pIm.add(labelImage24);
        pIm.add(but24);
        pIm.add(butWatter);
        pIm.add(labelImage36);
        pIm.add(but36);
        pIm.add(labelGas2);
        pIm.add(labelImage48);
        pIm.add(but48);
        pIm.add(labelWatter2);

        windowContent.add(pIm);

        JPanel p2 = new JPanel();
        GridLayout gl2 = new GridLayout(1, 2);
        p2.setLayout(gl2);
        p2.add(findOPt);
        p2.add(createOPtGraphics);

        windowContent.add(p2);

        JFrame frame = new JFrame("CALCULATOR FOR IMPULSE FACE SEAL");
        frame.setContentPane(windowContent);
        frame.setSize(1100, 650);
        frame.setVisible(true);

        EngineFinder findEngine = new EngineFinder(this);

        findOPt.addActionListener(findEngine);
        createOPtGraphics.addActionListener(findEngine);
        but2.addActionListener(findEngine);
        but4.addActionListener(findEngine);
        but12.addActionListener(findEngine);
        but24.addActionListener(findEngine);
        but36.addActionListener(findEngine);
        but48.addActionListener(findEngine);
        butGas.addActionListener(findEngine);
        butWatter.addActionListener(findEngine);

    }
}