package org.mwo.agh.edu.pages;

import org.mwo.agh.edu.report.MonthsToHoursReport;
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
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Map;

public class MonthsToHoursRankingPage extends PageRanking {

    @FXML
    private Button button;

    @FXML
    private Pane pane;

    @FXML
    private Label label;

    @FXML
    private TableView<Map.Entry<Object, Object>> tableView;

    @FXML
    private TableColumn<Map.Entry<String, Double>, Integer> id;

    @FXML
    private TableColumn<Map.Entry<String, Double>, String> month;

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
        return new Report(new MonthsToHoursReport());
    }

    @Override
    protected void pageInitialization() {
        LABEL_TEXT = label.getText();
        LABEL_TEXT_REVERSE = LABEL_TEXT.substring(0, LABEL_TEXT.length() - 9) + " ROSNĄCO";
        FILE_NAME = "RANKING-MIESIECY";
        month.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey()));
        hours.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getValue()).asObject());
        id.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(tableView.getItems().indexOf(p.getValue()) + 1));
        tableView.setItems(mapToObservableListConverter.convert(items));
        barChart.getData().add(mapToXYChartSeriesConverter.convert(items));
    }
}
