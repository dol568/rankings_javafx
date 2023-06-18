package org.mwo.agh.edu.printers;

import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;

import java.util.ArrayList;
import java.util.List;

public class PrinterPdf {

    public void print(Pane pane) {

        List<Button> buttonsToExclude = new ArrayList<>();
        for (Node node : pane.getChildren()) {
            if (node instanceof Button) {
                buttonsToExclude.add((Button) node);
            }
        }

        pane.getChildren().removeAll(buttonsToExclude);
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);

        double originalWidth = pane.getBoundsInParent().getWidth();
        double originalHeight = pane.getBoundsInParent().getHeight();

        double scaleX = Math.min(pageLayout.getPrintableWidth() / originalWidth, pageLayout.getPrintableHeight() / originalHeight);

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            job.getJobSettings().setPageLayout(pageLayout);
            pane.getTransforms().add(new Scale(scaleX, scaleX));
            boolean success = job.printPage(pane);
            if (success) {
                job.endJob();
            }
        }

        pane.getTransforms().clear();
        pane.setPrefSize(originalWidth, originalHeight);
        pane.getChildren().addAll(buttonsToExclude);
    }
}
