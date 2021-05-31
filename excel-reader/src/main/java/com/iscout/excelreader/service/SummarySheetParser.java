package com.iscout.excelreader.service;

import com.iscout.excelreader.model.SummaryChart;
import com.iscout.excelreader.model.SummarySheet;
import com.iscout.excelreader.util.FileHelper;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SummarySheetParser {

    public static MutableList<SummarySheet> parseExcel(String fileLocation, String fileName) throws Exception {
        File xlsxFile = FileHelper.getFileFromResouce(fileLocation, fileName);
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings().trimCellValue(true)
                .caseInsensitive(true)
                .ignoreWhitespaces(true)
                .preferNullOverDefault(true)
                .sheetIndex(0)
                .build();
        MutableList<SummarySheet> summarySheets = Lists.adapt(Poiji.fromExcel(xlsxFile, SummarySheet.class, options));
        return summarySheets;
    }

    public static SummaryChart preProcessData(MutableList<SummarySheet> excelData)
    {
        SummaryChart summaryChart = new SummaryChart();
        MutableMap<String, MutableList<SummaryChart.SummaryChartLineItem>> summaryMapItem = Maps.mutable.empty();
        String chartYAxis = "";
        MutableList<SummaryChart.SummaryChartLineItem> summaryChartLineItems = Lists.mutable.empty();
        for(int i=0, j=i+1; i < excelData.size() && j < excelData.size(); i++,j++)
        {
            SummarySheet configCurrItem = excelData.get(i);
            SummarySheet configNextItem = excelData.get(j);
            if (StringUtils.isAllBlank(configCurrItem.getActivityName(), configCurrItem.getSummaryProgram(),
                    configNextItem.getActivityName(), configNextItem.getSummaryProgram()))
            {
                chartYAxis = configCurrItem.getActivityId();
                if(StringUtils.isNotBlank(chartYAxis))
                {
                    summaryChartLineItems = Lists.mutable.empty();
                }
            }
            if (StringUtils.isAllBlank(configCurrItem.getActivityName(), configCurrItem.getSummaryProgram())
                    && StringUtils.isNoneBlank(configNextItem.getActivityName(), configNextItem.getSummaryProgram()))
            {
                summaryChartLineItems.add(SummaryChart.SummaryChartLineItem
                        .builder()
                        .description(configCurrItem.getActivityId() + " ( " + configCurrItem.getStartDate() + " to " + configCurrItem.getEndDate() + " )")
                        .startDate(LocalDate.parse(configCurrItem.getStartDate(), DateTimeFormatter.ofPattern("d-MMM-yy", Locale.ENGLISH)))
                        .endDate(LocalDate.parse(configCurrItem.getEndDate(), DateTimeFormatter.ofPattern("d-MMM-yy", Locale.ENGLISH)))
                        .build());
            }
            summaryMapItem.put(chartYAxis, summaryChartLineItems);
        }
        summaryChart.setChartInfo(summaryMapItem);

        MutableBag<LocalDate> dates = summaryMapItem.flatCollect(v -> v.collect(SummaryChart.SummaryChartLineItem::getStartDate)
                .withAll(v.collect(SummaryChart.SummaryChartLineItem::getEndDate)).distinct());
        summaryChart.setChartRangeStart(dates.min().minusMonths(3));
        summaryChart.setChartRangeEnd(dates.max().plusMonths(3));
        return summaryChart;
    }
}
