package org.mwo.agh.edu.converters;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MapToListConverter {

    private static final Logger LOGGER = Logger.getLogger(MapToXYChartDataConverter.class.getName());

    public <K, V> ObservableList<Map.Entry<K, V>> convert(Map<K, V> items) {
        if (items == null) {
            throw new IllegalArgumentException("Input map must not be null");
        }
        ObservableList<Map.Entry<K, V>> list = FXCollections.observableArrayList();
        try {
            list.addAll(items.entrySet());
        } catch (NullPointerException | UnsupportedOperationException e) {
            LOGGER.log(Level.SEVERE, "Error occurred during map conversion", e);
        }
        return list;
    }
}
