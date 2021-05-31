package com.iscout.excelreader.model;

import com.poiji.annotation.ExcelCellName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SummarySheet {

    @ExcelCellName("No")
    private int rowCount;

    @ExcelCellName("Activity ID")
    private String activityId;

    @ExcelCellName("Activity Name")
    private String activityName;

    @ExcelCellName("Summary Program")
    private String summaryProgram;

    @ExcelCellName("Dicipline")
    private String discipline;

    @ExcelCellName("Remaining Duration")
    private int remainingDuration;

    @ExcelCellName("Start")
    private String startDate;

    @ExcelCellName("Finish")
    private String endDate;

    @ExcelCellName("Total Float")
    private int totalFloat;
}
