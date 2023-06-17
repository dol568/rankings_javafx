package org.mwo.agh.edu.pages;

import org.mwo.agh.edu.report.DaysToHoursReport;
import org.mwo.agh.edu.report.Report;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.*;

public class DaysToHoursRankingPage extends PageRanking {

    @FXML
    private Button button;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label label;

    @FXML
    private TableView<Map.Entry<Object, Object>> tableView;

    @FXML
    private TableColumn<Map.Entry<String, Double>, Integer> id;

    @FXML
    private TableColumn<Map.Entry<String, Double>, String> day;

    @FXML
    private TableColumn<Map.Entry<String, Double>, Double> hours;

    @FXML
    private BarChart<Object, Object> barChart;

    @FXML
    void printXLS(ActionEvent event) throws IOException {
        if (tableView != null && label != null) {
            printerXls.print(tableView, FILE_NAME);
        }
    }

    @FXML
    void printPDF(ActionEvent event) throws IOException {
        if (pane != null) {
            printerPdf.print(pane);
        }
    }

    @FXML
    void toggleResult(ActionEvent event) throws IOException {
        if (data.getSpreadSheet() != null) {
            tableView.getItems().clear();
            barChart.getData().clear();
            barChart.layout();
            String direction = report.getDirection();
            report.setDirection(direction.equalsIgnoreCase("ASC") ? "DESC" : "ASC");
            items = report.generateReport();

            tableView.setItems(mapToObservableListConverter.convert(items));
            button.setText(direction.equalsIgnoreCase("ASC") ? "MALEJĄCO" : "ROSNĄCO");

            barChart.getData().add(mapToXYChartSeriesConverter.convert(items));
            label.setText(direction.equalsIgnoreCase("ASC") ? LABEL_TEXT : LABEL_TEXT_REVERSE);
        }
    }

    @Override
    protected Report createReport() {
        return new Report(new DaysToHoursReport());
    }

    @Override
    protected void pageInitialization() {
        FILE_NAME = "RANKING-DNI";
        LABEL_TEXT = label.getText();
        LABEL_TEXT_REVERSE = LABEL_TEXT.substring(0, 15) + " NAJMNIEJ " + LABEL_TEXT.substring(27, LABEL_TEXT.length());
        day.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey()));
        hours.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getValue()).asObject());
        id.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(tableView.getItems().indexOf(p.getValue()) + 1));
        tableView.setItems(mapToObservableListConverter.convert(items));
        barChart.getData().add(mapToXYChartSeriesConverter.convert(items));
    }
}