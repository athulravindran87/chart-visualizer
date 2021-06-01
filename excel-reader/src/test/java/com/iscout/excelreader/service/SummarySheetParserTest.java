package com.iscout.excelreader.service;


import com.iscout.excelreader.model.SummaryChart;
import com.iscout.excelreader.model.SummarySheet;
import org.eclipse.collections.api.list.MutableList;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;

public class SummarySheetParserTest {

    @Test
    public void testSpreadSheetParser() throws Exception {
        MutableList<SummarySheet> test = SummarySheetParser.parseExcel("test", "Summary program.xlsx");
        assertThat(test, hasSize(19));
        assertThat(test, allOf(hasItem(allOf(
                hasProperty("rowCount", equalTo(1)),
                hasProperty("activityId", equalTo("Arden Station")),
                hasProperty("activityName", nullValue()),
                hasProperty("summaryProgram", nullValue()),
                hasProperty("discipline", nullValue()),
                hasProperty("remainingDuration", equalTo(1437)),
                hasProperty("startDate", equalTo("18-Apr-18")),
                hasProperty("endDate", equalTo("24-Mar-22")),
                hasProperty("totalFloat", equalTo(357))))));

        assertThat(test, allOf(hasItem(allOf(
                hasProperty("rowCount", equalTo(3)),
                hasProperty("activityId", equalTo("ARD.ST.10050")),
                hasProperty("activityName", equalTo("Arden - West - Construct Guide Wall")),
                hasProperty("summaryProgram", equalTo("Arden")),
                hasProperty("discipline", equalTo("Piling")),
                hasProperty("remainingDuration", equalTo(26)),
                hasProperty("startDate", equalTo("18-Apr-18")),
                hasProperty("endDate", equalTo("23-May-18")),
                hasProperty("totalFloat", equalTo(86))))));
    }

    @Test
    public void testPreProcessData() throws Exception {
        MutableList<SummarySheet> test = SummarySheetParser.parseExcel("test", "Summary program.xlsx");
        SummaryChart summaryChart = SummarySheetParser.preProcessData(test);
        assertThat(summaryChart.getChartInfo(), aMapWithSize(2));
        assertThat(summaryChart.getChartRangeStart(), equalTo(LocalDate.of(2018, 1, 18)));
        assertThat(summaryChart.getChartRangeEnd(), equalTo(LocalDate.of(2024, 6, 24)));

        assertThat(summaryChart.getChartInfo().get("Arden Station"), allOf(
                                hasItem(
                                        allOf(
                                                hasProperty("description", equalTo("Piling Works ( 18-Apr-18 to 20-Dec-18 )")),
                                                hasProperty("startDate", equalTo(LocalDate.of(2018,4,18))),
                                                hasProperty("endDate", equalTo(LocalDate.of(2018,12, 20))))),
                                hasItem(
                                        allOf(
                                                hasProperty("description", equalTo("Excavation Works ( 10-Aug-18 to 14-Jun-19 )")),
                                                hasProperty("startDate", equalTo(LocalDate.of(2018, 8, 10))),
                                                hasProperty("endDate", equalTo(LocalDate.of(2019,6, 14)))))));

    }
}