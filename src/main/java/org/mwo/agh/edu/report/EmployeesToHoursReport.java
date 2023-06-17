package org.mwo.agh.edu.report;

import org.mwo.agh.edu.models.Activity;
import org.mwo.agh.edu.models.Person;
import org.mwo.agh.edu.models.Project;
import org.mwo.agh.edu.models.SpreadSheet;

import java.util.HashMap;
import java.util.Map;

public class EmployeesToHoursReport implements ReportStrategy{

    @Override
    public Map<Object, Object> getReport(SpreadSheet spreadsheet) {
        Map<Object, Object> personToHours = new HashMap<>();
        for (Person s : spreadsheet.getPersons()) {
            double sum = 0.0d;
            for (Project p : s.getProjects()) {
                for (Activity a : p.getActivities()) {
                    sum += a.getDuration();
                }
            }
            personToHours.put(s, sum);
        }
        return personToHours;
    }
}
