package org.mwo.agh.edu.converters;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MapToPieChartDataConverter {

    private static final Logger LOGGER = Logger.getLogger(MapToXYChartDataConverter.class.getName());

    public <K, V> ObservableList<PieChart.Data> convert(Map<K, V> items) {
        if (items == null) {
            throw new IllegalArgumentException("Input map must not be null");
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        try {
            for (Map.Entry<K, V> entry : items.entrySet()) {
                pieChartData.add(new PieChart.Data(entry.getKey().toString(), (Double) entry.getValue()));
            }
            pieChartData.forEach(data -> {
                data.nameProperty().bind(Bindings.concat(data.getName(), ", ", data.pieValueProperty(), " h"));
            });
        } catch (NullPointerException | UnsupportedOperationException e) {
            LOGGER.log(Level.SEVERE, "Error occurred during map conversion", e);
        }
        return pieChartData;
    }
}
