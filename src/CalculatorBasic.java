import javax.swing.*;
import java.awt.*;

public class CalculatorBasic {

    JButton evaluateStaticCharacteristics = new JButton("Evaluate Static Characteristics");
    JButton graphicDynamicCharacteristics = new JButton("Evaluate Dynamic Characteristics");

    ImageIcon imageMain   = new ImageIcon("Seal3D.png");
    JLabel labelImage = new JLabel("", imageMain, JLabel.CENTER);

    CalculatorBasic () {

        JPanel windowContentBasic = new JPanel();
        FlowLayout fl             = new FlowLayout();
        windowContentBasic.setLayout(fl);

        JPanel calculatorBasicPanel      = new JPanel();
        GridLayout glImBasic = new GridLayout(1,1);
        calculatorBasicPanel.setLayout(glImBasic);
        calculatorBasicPanel.add(labelImage, BorderLayout.CENTER);

        windowContentBasic.add(calculatorBasicPanel);

        JPanel pInput = new JPanel();
        GridLayout glInput = new GridLayout(1, 2);

        pInput.setLayout(glInput);

        pInput.add(evaluateStaticCharacteristics);
        pInput.add(graphicDynamicCharacteristics);
        windowContentBasic.add(pInput);

        JFrame frame = new JFrame("CALCULATOR FOR IMPULSE FACE SEAL");
        frame.setContentPane(windowContentBasic);
        frame.setSize(600, 500);
        frame.setVisible(true);

        EngineBasic calcEngineBasic = new EngineBasic(this);

        evaluateStaticCharacteristics.addActionListener(calcEngineBasic);
        graphicDynamicCharacteristics.addActionListener(calcEngineBasic);
    }

    public static void main(String[] args) {

        CalculatorBasic calcBasic = new CalculatorBasic();

    }
}


