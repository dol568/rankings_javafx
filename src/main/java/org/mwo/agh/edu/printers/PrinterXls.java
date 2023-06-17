package org.mwo.agh.edu.printers;

import javafx.scene.control.TableView;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class PrinterXls {

    public void print(TableView<Map.Entry<Object, Object>> tableView, String fileName) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet(fileName);

        Row titleRow = spreadsheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(fileName);

        spreadsheet.addMergedRegion(new CellRangeAddress(0, 0, 0, tableView.getColumns().size() - 1));

        spreadsheet.autoSizeColumn(0);

        Row headerRow = spreadsheet.createRow(1);
        for (int j = 0; j < tableView.getColumns().size(); j++) {
            headerRow.createCell(j).setCellValue(tableView.getColumns().get(j).getText());
        }

        for (int i = 0; i < tableView.getItems().size(); i++) {
            Row dataRow = spreadsheet.createRow(i + 2);
            for (int j = 0; j < tableView.getColumns().size(); j++) {
                if (tableView.getColumns().get(j).getCellData(i) != null) {
                    dataRow.createCell(j).setCellValue(tableView.getColumns().get(j).getCellData(i).toString());
                } else {
                    dataRow.createCell(j).setCellValue("");
                }
            }
        }

        for (int j = 0; j < tableView.getColumns().size(); j++) {
            spreadsheet.autoSizeColumn(j);
            int columnWidth = spreadsheet.getColumnWidth(j);
            spreadsheet.setColumnWidth(j, columnWidth + 1000);
        }

        FileOutputStream fileOut = new FileOutputStream(fileName + ".xls");
        workbook.write(fileOut);
        fileOut.close();
    }
}
