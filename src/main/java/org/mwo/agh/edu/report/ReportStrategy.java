package org.mwo.agh.edu.report;

import org.mwo.agh.edu.models.SpreadSheet;

import java.util.Map;

public interface ReportStrategy {

    Map<Object, Object> getReport(SpreadSheet spreadsheet);
}

