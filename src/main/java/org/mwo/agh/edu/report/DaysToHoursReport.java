package org.mwo.agh.edu.report;

import org.mwo.agh.edu.models.Activity;
import org.mwo.agh.edu.models.Person;
import org.mwo.agh.edu.models.Project;
import org.mwo.agh.edu.models.SpreadSheet;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DaysToHoursReport implements ReportStrategy {

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE ;

    @Override
    public Map<Object, Object> getReport(SpreadSheet spreadsheet) {
        Map<Object, Object> daystoHours = new HashMap<>();
        for (Person s : spreadsheet.getPersons()) {
            for (Project project : s.getProjects()) {
                for (Activity activity : project.getActivities()) {
                    String date = formatter.format(activity.getDate());
                    double duration = activity.getDuration();
                    if (daystoHours.containsKey(date)) {
                        double add = (double) daystoHours.get(date) + duration;
                        daystoHours.put(date, add);
                    } else {
                        daystoHours.put(date, activity.getDuration());
                    }
                }
            }
        }
        return daystoHours;
    }


}
