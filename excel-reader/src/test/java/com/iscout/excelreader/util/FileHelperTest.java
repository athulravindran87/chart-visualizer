package com.iscout.excelreader.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class FileHelperTest {

    @Test
    public void getBaseNameFromFileName() {
        assertThat(FileHelper.getBaseNameFromFileName("ABC.xls"), equalTo("ABC"));
    }

    @Test
    public void getFileExtension() {
        assertThat(FileHelper.getFileExtension("ABC.xls"), equalTo(".xls"));
    }
}