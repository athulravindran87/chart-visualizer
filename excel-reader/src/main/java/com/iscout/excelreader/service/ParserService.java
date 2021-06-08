package com.iscout.excelreader.service;

import com.iscout.excelreader.model.SummaryChart;
import com.iscout.excelreader.model.SummarySheet;
import com.iscout.excelreader.model.UIModel;
import com.iscout.excelreader.util.FileHelper;
import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Maps;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class ParserService {

    private final static MutableList<String> COLORS = Lists.mutable.of("gtaskblue",
                                                                       "gtaskred", "gtaskgreen", "gtaskyellow", "gtaskpurple", "gtaskpink");

    public MutableSet<UIModel> parseExcelToJson(String fileLocation, String fileName) throws Exception {

        AtomicInteger parentId = new AtomicInteger();
        MutableList<SummarySheet> summarySheets = this.parseExcel(fileLocation, fileName);
        SummaryChart summaryChart = this.preProcessData(summarySheets);
        return summaryChart
                .getChartInfo()
                .keyValuesView()
                .collect(pair -> this.createUIModel(pair, parentId))
                .flatCollect(uiModels -> uiModels).toSet();
    }

    protected SummaryChart preProcessData(MutableList<SummarySheet> excelData) {
        SummaryChart summaryChart = new SummaryChart();
        MutableMap<String, MutableList<SummaryChart.SummaryChartLineItem>> summaryMapItem = Maps.mutable.empty();
        String chartYAxis = "";
        MutableList<SummaryChart.SummaryChartLineItem> summaryChartLineItems = Lists.mutable.empty();
        for (int i = 0, j = i + 1; i < excelData.size() && j < excelData.size(); i++, j++) {
            SummarySheet configCurrItem = excelData.get(i);
            SummarySheet configNextItem = excelData.get(j);
            if (StringUtils.isAllBlank(configCurrItem.getActivityName(), configCurrItem.getSummaryProgram(),
                                       configNextItem.getActivityName(), configNextItem.getSummaryProgram())) {
                chartYAxis = configCurrItem.getActivityId();
                if (StringUtils.isNotBlank(chartYAxis)) {
                    summaryChartLineItems = Lists.mutable.empty();
                }
            }
            if (StringUtils.isAllBlank(configCurrItem.getActivityName(), configCurrItem.getSummaryProgram())
                && StringUtils.isNoneBlank(configNextItem.getActivityName(), configNextItem.getSummaryProgram())) {
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

    protected MutableList<SummarySheet> parseExcel(String fileLocation, String fileName) throws Exception {
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

    private MutableList<UIModel> createUIModel(Pair<String, MutableList<SummaryChart.SummaryChartLineItem>> summaryChart, AtomicInteger atomicInteger) {
        AtomicInteger childId = new AtomicInteger(atomicInteger.incrementAndGet() * 10);
        MutableList<UIModel> models = Lists.mutable.of(UIModel.builder()
                                                               .pName(summaryChart.getOne())
                                                               .pID(atomicInteger.get())
                                                               .pClass("ggroupblack")
                                                               .build())
                .withAll(summaryChart.getTwo()
                                 .collect(summaryChartLineItem -> UIModel.builder()
                                         .pName(summaryChartLineItem.getDescription())
                                         .pStart(summaryChartLineItem.getStartDate().toString())
                                         .pEnd(summaryChartLineItem.getEndDate().toString())
                                         .pParent(atomicInteger.get())
                                         .pID(childId.incrementAndGet())
                                         .pClass(COLORS.get(getChiildColorIndex(childId)))
                                         .build()).toList());
        return models;
    }

    private int getChiildColorIndex(AtomicInteger childId) {
        int index = Math.toIntExact(childId.get() % 10);
        return index > 5 ? index - 5 : index;
    }
}
