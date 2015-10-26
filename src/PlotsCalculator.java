import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlotsCalculator extends JFrame {

    ArrayList<XYSeries> series = new ArrayList<XYSeries>();
    private XYSeriesCollection dataset;

    public PlotsCalculator(String title) {

        super(title);

        JPanel chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.CENTER);

        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    private JPanel createChartPanel() {

        XYDataset dataset = createDataset();

        JFreeChart chart = ChartFactory.createXYLineChart("Static Characteristic", "Pressure", "Gap", dataset, PlotOrientation.VERTICAL, true, true, false);

        chart.getXYPlot().setBackgroundPaint(Color.WHITE);
        chart.getXYPlot().setDomainGridlinePaint(Color.BLACK);
        chart.getXYPlot().setRangeGridlinePaint(Color.BLACK);
        return new ChartPanel(chart);
    }

    private XYDataset createDataset() {

        dataset = new XYSeriesCollection();

        for (int i = 0; i < series.size(); i++){

            dataset.addSeries(series.get(i));
            dataset.setAutoWidth(true);

        }

        return dataset;
    }

    public int createCurve(String name){

        series.add(new XYSeries(name));

        return series.size()-1;
    }

    public void updateDataset () {

        dataset.removeAllSeries();

        for (int i = 0; i < series.size(); i++){

            dataset.addSeries(series.get(i));
        }
    }

    public void addPoint1(double u, double p, int i) {

        series.get(i).add(u, p);

    }
}
