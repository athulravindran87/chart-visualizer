package com.iscout.excelreader.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.factory.Maps;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SummaryChart {

    private MutableMap<String, MutableList<SummaryChartLineItem>> chartInfo = Maps.mutable.empty();
    private LocalDate chartRangeStart;
    private LocalDate chartRangeEnd;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SummaryChartLineItem {
        private String description;
        private LocalDate startDate;
        private LocalDate endDate;
    }
}
