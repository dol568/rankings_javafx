package org.mwo.agh.edu.report;

import org.mwo.agh.edu.models.Activity;
import org.mwo.agh.edu.models.Person;
import org.mwo.agh.edu.models.Project;
import org.mwo.agh.edu.models.SpreadSheet;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class MonthsToHoursRanking implements Ranking {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

    @Override
    public Map<Object, Object> getReport(SpreadSheet spreadsheet) {
        Map<Object, Object> monthsToHours = new HashMap<>();
        for (Person s : spreadsheet.getPersons()) {
            for (Project project : s.getProjects()) {
                for (Activity activity : project.getActivities()) {
                    String date = formatter.format(activity.getDate());
                    double duration = activity.getDuration();
                    if (monthsToHours.containsKey(date)) {
                        double add = (double) monthsToHours.get(date) + duration;
                        monthsToHours.put(date, add);
                    } else {
                        monthsToHours.put(date, activity.getDuration());
                    }
                }
            }
        }
        return monthsToHours;
    }
}
