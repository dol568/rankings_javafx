package org.mwo.agh.edu.pages;

import org.mwo.agh.edu.report.EmployeesToHoursReport;
import org.mwo.agh.edu.report.Report;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import org.mwo.agh.edu.models.Person;

import java.io.IOException;
import java.util.*;

public class EmployeesToHoursRankingPage extends PageRanking {

    @FXML
    private Button button;

    @FXML
    private Pane pane;

    @FXML
    private Label label;

    @FXML
    private TableView<Map.Entry<Object, Object>> tableView;

    @FXML
    private TableColumn<Map.Entry<Person, Double>, Integer> id;

    @FXML
    private TableColumn<Map.Entry<Person, Double>, String> name;

    @FXML
    private TableColumn<Map.Entry<Person, Double>, Double> hours;

    @FXML
    private PieChart pieChart;

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
                String direction = report.getDirection();
                report.setDirection(direction.equalsIgnoreCase("ASC") ? "DESC" : "ASC");
                items = report.generateReport();
                tableView.setItems(mapToObservableListConverter.convert(items));
                button.setText(direction.equalsIgnoreCase("ASC") ? "MALEJĄCO" : "ROSNĄCO");
                label.setText(direction.equalsIgnoreCase("ASC") ? LABEL_TEXT : LABEL_TEXT_REVERSE);
            }
    }

    @Override
    protected Report createReport() {
        return new Report(new EmployeesToHoursReport());
    }

    @Override
    protected void pageInitialization() {
        FILE_NAME = "RANKING-PRACOWNIKOW";
        LABEL_TEXT = label.getText();
        LABEL_TEXT_REVERSE = LABEL_TEXT.substring(0, LABEL_TEXT.length() - 9) + " ROSNĄCO";
        name.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey().getName() + " " + p.getValue().getKey().getSurname()));
        hours.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getValue()).asObject());
        id.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(tableView.getItems().indexOf(p.getValue()) + 1)
        );
        ObservableList<Map.Entry<Object, Object>> personList = mapToObservableListConverter.convert(items);
        tableView.setItems(personList);
        pieChart.getData().addAll(mapToPieChartSeriesConverter.convert(items));
    }
}

