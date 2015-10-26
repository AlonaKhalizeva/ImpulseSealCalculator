import javax.swing.*;
import java.awt.*;

//Button 1 "Evaluate Static Characteristic" on BASIC FRAME

public class Calculator {

    JButton evaluateStaticCharacteristics = new JButton("Evaluate Static Characteristics");
    JButton graphicCreation = new JButton("Create Static and Flow Rate Characteristics");
    JButton findBestSet = new JButton("Find the best set of seal parameters");

    JButton clearButton = new JButton("Clear");

    JLabel lableTitleInput = new JLabel("INITIAL DATA:");

    JLabel lableTitle  = new JLabel("");
    JLabel lableSpace  = new JLabel("");
    JLabel lableSpace2 = new JLabel("");
    JRadioButton butWatter = new JRadioButton("Water");
    JRadioButton butGas    = new JRadioButton("Gas");

    JLabel labelR1     = new JLabel("Radius r1, [ m ]");
    static JTextField fieldR1 = new JTextField(5);

    JLabel labelR3     = new JLabel("Radius r3, [ m ]");
    static JTextField fieldR3 = new JTextField(5);

    JLabel labelRc     = new JLabel("Radius rc, [ m ]");
    static JTextField fieldRc = new JTextField(5);

    JLabel labelGap     = new JLabel("Gap, [ m ]");
    static JTextField fieldGap = new JTextField(5);

    JLabel labelPressure1     = new JLabel("Pressure p1, [ Pa ]");
    static JTextField fieldPressure1 = new JTextField(5);

    JLabel labelPressure3     = new JLabel("Pressure p3, [ Pa ]");
    static JTextField fieldPressure3 = new JTextField(5);

    JLabel labelPressureN     = new JLabel("Nominal Pressure pn, [ Pa ]");
    static JTextField fieldPressureN = new JTextField(5);

    JLabel labelOmega     = new JLabel("Frequency, [ rad/sec ]");
    static JTextField fieldOmega = new JTextField(5);

    JLabel labelFeedersAmount     = new JLabel("Amount of feeders n");
    JTextField fieldFeedersAmount = new JTextField(5);

    JLabel labelChambersAmount     = new JLabel("Amount of Chambers nc");
    JTextField fieldChambersAmount = new JTextField(10);

    ImageIcon image   = new ImageIcon("Seal.jpg");
    JLabel labelImage = new JLabel("", image, JLabel.CENTER);

    Plots newPlot     = new Plots("Static Characteristic", "Pressure", "Gap");
    Plots newPlotRate = new Plots("Flow Rate Characteristic", "Pressure", "Flow Rate");

    Calculator() {

        JPanel windowContent = new JPanel();
        FlowLayout fl = new FlowLayout();
        windowContent.setLayout(fl);

        JPanel pInput = new JPanel();
        GridLayout glInput = new GridLayout(14, 2);

        pInput.setLayout(glInput);

        pInput.add(lableTitleInput);
        pInput.add(lableTitle);
        pInput.add(labelR1);
        pInput.add(fieldR1);
        pInput.add(labelR3);
        pInput.add(fieldR3);
        pInput.add(labelRc);
        pInput.add(fieldRc);
        pInput.add(labelPressure1);
        pInput.add(fieldPressure1);
        pInput.add(labelPressure3);
        pInput.add(fieldPressure3);
        pInput.add(labelPressureN);
        pInput.add(fieldPressureN);
        pInput.add(labelOmega);
        pInput.add(fieldOmega);
        pInput.add(labelGap);
        pInput.add(fieldGap);
        pInput.add(labelChambersAmount);
        pInput.add(fieldChambersAmount);
        pInput.add(labelFeedersAmount);
        pInput.add(fieldFeedersAmount);
        pInput.add(butGas);
        pInput.add(butWatter);

        windowContent.add(pInput);

        JPanel p2 = new JPanel();
        GridLayout gl2 = new GridLayout(9, 1);
        p2.setLayout(gl2);
        p2.add(evaluateStaticCharacteristics);
        p2.add(graphicCreation);
        p2.add(findBestSet);
        p2.add(lableSpace);
        p2.add(lableSpace2);
        p2.add(clearButton);

        windowContent.add(p2);

        JPanel pIm = new JPanel();
        GridLayout glIm = new GridLayout(1, 1);
        pIm.setLayout(glIm);
        pIm.add(labelImage, BorderLayout.CENTER);

        windowContent.add(pIm);

        JFrame frame = new JFrame("CALCULATOR FOR IMPULSE FACE SEAL");
        frame.setContentPane(windowContent);
        frame.setSize(800, 800);
        frame.setVisible(true);

        CalculatorEngine calcEngine = new CalculatorEngine(this);

        evaluateStaticCharacteristics.addActionListener(calcEngine);
        graphicCreation.addActionListener(calcEngine);
        findBestSet.addActionListener(calcEngine);
        butGas.addActionListener(calcEngine);
        butWatter.addActionListener(calcEngine);

        clearButton.addActionListener(calcEngine);
    }
}