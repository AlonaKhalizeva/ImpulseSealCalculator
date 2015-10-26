import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;


public class Plots extends JFrame {

    XYSeries series1 = new XYSeries("");

    public Plots(String title, String xAxis, String yAxis) {

        super(title);

        JPanel chartPanel = createChartPanel(title,xAxis, yAxis);
        add(chartPanel, BorderLayout.CENTER);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    private JPanel createChartPanel(String title, String xAxis, String yAxis) {

        XYDataset dataset = createDataset();

        JFreeChart chart = ChartFactory.createXYLineChart(title, xAxis, yAxis, dataset, PlotOrientation.VERTICAL, true, true, false);
        chart.getXYPlot().setBackgroundPaint(Color.WHITE);
        chart.getXYPlot().setDomainGridlinePaint(Color.BLACK);
        chart.getXYPlot().setRangeGridlinePaint(Color.BLACK);
        return new ChartPanel(chart);
    }

    private XYDataset createDataset() {

        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(series1);

        return dataset;
    }

    public void addPoint(double u, double p) {

        series1.add(u, p);

    }
}
