package org.mwo.agh.edu.report;

import org.mwo.agh.edu.models.Data;
import org.mwo.agh.edu.models.SpreadSheet;

import java.util.*;

public class Report {
    private final ReportStrategy strategy;
    Data data;
    String direction = "ASC";
    int limit = 10;

    public Report(ReportStrategy strategy) {
        this.strategy = strategy;
        this.data = Data.getInstance();
   }

    public Map<Object, Object> generateReport() {
        SpreadSheet spreadsheet = this.data.getSpreadSheet();
        Map<Object, Object> notSorted = this.strategy.getReport(spreadsheet);
        return this.sortMap(notSorted);
    }

    private Map<Object, Object> sortMap(Map<Object, Object> map) {
        List<Map.Entry<Object, Object>> sorted = new ArrayList<>(map.entrySet());
        sorted.sort(Comparator.comparingDouble(d -> (Double) d.getValue()));
        if (getDirection().equalsIgnoreCase("DESC")) {
            Collections.reverse(sorted);
        }
        return limitMap(sorted);
    }

    private Map<Object, Object> limitMap(List<Map.Entry<Object, Object>> entries) {
        Map<Object, Object> limitedMap = new LinkedHashMap<>();
        int count = 0;
        for (Map.Entry<Object, Object> entry : entries) {
            limitedMap.put(entry.getKey(), entry.getValue());
            count++;
            if (count >= limit) {
                break;
            }
        }
        return limitedMap;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
