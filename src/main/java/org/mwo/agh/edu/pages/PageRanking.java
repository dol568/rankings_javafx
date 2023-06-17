package org.mwo.agh.edu.pages;

import org.mwo.agh.edu.printers.PrinterPdf;
import org.mwo.agh.edu.printers.PrinterXls;
import org.mwo.agh.edu.converters.MapToListConverter;
import org.mwo.agh.edu.converters.MapToPieChartDataConverter;
import org.mwo.agh.edu.converters.MapToXYChartDataConverter;
import org.mwo.agh.edu.models.Data;
import org.mwo.agh.edu.report.Report;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class PageRanking implements Initializable {

    protected String FILE_NAME;
    protected String LABEL_TEXT;
    protected String LABEL_TEXT_REVERSE;
    protected Data data;
    protected Report report;
    protected Map<Object, Object> items;
    protected MapToListConverter mapToObservableListConverter;
    protected MapToXYChartDataConverter mapToXYChartSeriesConverter;
    protected MapToPieChartDataConverter mapToPieChartSeriesConverter;
    protected PrinterXls printerXls;
    protected PrinterPdf printerPdf;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = Data.getInstance();
        report = createReport();
        mapToObservableListConverter = new MapToListConverter();
        mapToXYChartSeriesConverter = new MapToXYChartDataConverter();
        mapToPieChartSeriesConverter = new MapToPieChartDataConverter();
        printerXls = new PrinterXls();
        printerPdf = new PrinterPdf();

        if (data.getSpreadSheet() != null) {
            items = report.generateReport();
            pageInitialization();
        }
    }

    protected abstract Report createReport();

    protected abstract void pageInitialization();
}
