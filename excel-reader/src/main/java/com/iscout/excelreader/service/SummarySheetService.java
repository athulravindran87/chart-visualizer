package com.iscout.excelreader.service;

import com.iscout.excelreader.model.SummarySheet;
import org.eclipse.collections.api.list.MutableList;
import org.springframework.stereotype.Service;

@Service
public class SummarySheetService {

    public void createPayLoad() throws Exception
    {
        MutableList<SummarySheet> excelData = SummarySheetParser.parseExcel("", "");

    }
}
