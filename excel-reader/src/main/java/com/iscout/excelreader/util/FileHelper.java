package com.iscout.excelreader.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileHelper {
    public static String getBaseNameFromFileName(final String file) {
        return file == null ? ""
                            : file.lastIndexOf('.') >= 0 ? file.substring(0, file.lastIndexOf('.')) : file;
    }

    public static String getFileExtension(final String file) {
        return file == null ? ""
                            : file.lastIndexOf('.') >= 0 ? file.substring(file.lastIndexOf('.')) : file;
    }

    private FileHelper() {

    }
}

