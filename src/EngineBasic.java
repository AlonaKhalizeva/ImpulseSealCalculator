import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class EngineBasic implements ActionListener{

    CalculatorBasic parent;

    EngineBasic (CalculatorBasic parent) {

        this.parent = parent;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object src = e.getSource();

        if (src == parent.evaluateStaticCharacteristics) {

            Calculator calc = new Calculator();

        } else if (src == parent.graphicDynamicCharacteristics) {

        }
    }
}