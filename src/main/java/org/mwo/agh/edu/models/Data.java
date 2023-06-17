package org.mwo.agh.edu.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class Data {

    private static final Data INSTANCE = new Data();
    private static final String XLS_NAME_PATTERN = "[a-zA-Z]+_[a-zA-Z]+\\.xls";
    private SpreadSheet spreadSheet;

    private Data() {}

    public static Data getInstance() {
        return INSTANCE;
    }

    public void setSpreadSheet(String dir) throws IOException {
        this.spreadSheet = new SpreadSheet();
        if (dir == null) {
            this.spreadSheet = null;
            return;
        }

        for (File file : listFiles(dir)) {
            Workbook wb = new HSSFWorkbook(new FileInputStream(file));
            String fileName = file.getName();
            String surname = fileName.substring(0, fileName.indexOf("_"));
            String name = fileName.substring(fileName.indexOf("_") + 1, fileName.indexOf("."));
            Person person = null;

            for (Person person1 : this.spreadSheet.getPersons()) {
                if (person1.getName().equals(name) && person1.getSurname().equals(surname)) {
                    person = person1;
                    break;
                }
            }
            if (person == null) {
                person = new Person(name, surname);
                this.spreadSheet.addPerson(person);
            }

            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                Sheet sheet = wb.getSheetAt(i);
                String projectName = sheet.getSheetName();
                Project project = null;
                for (Project p : person.getProjects()) {
                    if (p.getName().equals(projectName)) {
                        project = p;
                        break;
                    }
                }
                if (project == null) {
                    project = new Project(projectName);
                    person.addProject(project);
                }

                Iterator<Row> rows = sheet.iterator();

                if (rows.hasNext()) {
                    rows.next();
                }
                while (rows.hasNext()) {
                    Row row = rows.next();
                    Cell dateCell = row.getCell(0);
                    Cell descriptionCell = row.getCell(1);
                    Cell durationCell = row.getCell(2);

                    if (dateCell == null || descriptionCell == null || durationCell == null)
                        continue;

                    LocalDate date;
                    if (dateCell.getCellType() == CellType.NUMERIC)
                        date = dateCell.getLocalDateTimeCellValue().toLocalDate();
                    else
                        continue;

                    String description;
                    if (descriptionCell.getCellType() == CellType.STRING)
                        description = descriptionCell.getStringCellValue();
                    else
                        continue;

                    double duration;
                    if (durationCell.getCellType() == CellType.NUMERIC)
                        duration = durationCell.getNumericCellValue();
                    else
                        continue;

                    project.addActivity(new Activity(date, description, duration));
                }
            }
        }
    }

    public SpreadSheet getSpreadSheet() {
        return this.spreadSheet;
    }

    public List<File> listFiles(String directory) {
        if (directory == null) {
            return Collections.emptyList();
        }
        List<File> fileList = new ArrayList<>();
        File[] files = new File(directory).listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    fileList.addAll(listFiles(f.getPath()));
                } else {
                    if (f.isFile() && f.getName().matches(XLS_NAME_PATTERN)) {
                        fileList.add(f);
                    }
                }
            }
        }
        return fileList;
    }

}
