package org.mwo.agh.edu.converters;

import javafx.scene.chart.XYChart;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MapToXYChartDataConverter {

    private static final Logger LOGGER = Logger.getLogger(MapToXYChartDataConverter.class.getName());

    public <K, V> XYChart.Series<K, V> convert(Map<K, V> items) {
        if (items == null) {
            throw new IllegalArgumentException("Input map must not be null");
        }
        XYChart.Series<K, V> series = new XYChart.Series();
        try {
            for (Map.Entry<K, V> entry : items.entrySet()) {
                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }
        } catch (NullPointerException | UnsupportedOperationException e) {
            LOGGER.log(Level.SEVERE, "Error occurred during map conversion", e);
        }

        return series;
    }
}
