package com.iscout.excelreader.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.collections.api.list.MutableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;

@Slf4j
public class FileHelper
{

    public static String getBaseNameFromFileName(final String file)
    {
        return file == null ? ""
                : file.lastIndexOf('.') >= 0 ? file.substring(0, file.lastIndexOf('.')) : file;
    }

    public static void writeDataToFile(String fileName, String backUpFolder, MutableList<MutableList<String>> dataToCSV, String delimiter)
    {
        try (FileWriter writer = new FileWriter(new File(backUpFolder + "/" + fileName)))
        {

            for(MutableList row : dataToCSV)
            {
                writer.write(row.makeString(delimiter).concat(Constants.NEW_LINE));
            }
        }
        catch (Exception e)
        {
            log.error("Unable to write data to file {} due to Excepton {}", fileName, e);
        }
    }

    public static void convertCsvToXlsx(String backUpFolder, String originalFileName, String newFileName)
    {
        try
        {
            String csvFileAddress = backUpFolder + "/" + FileHelper.getBaseNameFromFileName(
                    originalFileName) + newFileName + Constants.CSV; //csv file address
            String xlsxFileAddress = backUpFolder + "/" + FileHelper.getBaseNameFromFileName(
                    originalFileName) + newFileName + Constants.XLSX; //xlsx file address
            try (XSSFWorkbook workBook = new XSSFWorkbook()) {

                XSSFSheet sheet = workBook.createSheet("sheet1");
                String currentLine = null;
                int RowNum = 0;
                try (BufferedReader br = new BufferedReader(new FileReader(csvFileAddress))) {
                    while ((currentLine = br.readLine()) != null) {
                        String str[] = currentLine.split(",");
                        XSSFRow currentRow = sheet.createRow(RowNum);
                        RowNum++;
                        setDataToRow(str, currentRow);
                    }

                    FileOutputStream fileOutputStream = new FileOutputStream(xlsxFileAddress);
                    workBook.write(fileOutputStream);
                    fileOutputStream.close();
                    File file = new File(backUpFolder + "/" + FileHelper.getBaseNameFromFileName(
                            originalFileName) + newFileName + Constants.CSV);
                    file.delete();
                }
            }
        }
        catch (Exception ex)
        {
            log.error(ex.getMessage() + "Exception in try");
        }
    }

    public static File getFileFromResouce(String fileLocation, String fileName) throws Exception {
        //Resource resource = FileHelper.class.getResourceAsStream(fileLocation + "/" + fileName);
        InputStream inputStream = FileHelper.class.getClassLoader().getResourceAsStream(fileLocation + "/" + fileName);
        File configFile = new File(fileName);
        FileUtils.copyInputStreamToFile(inputStream, configFile);
        return configFile;
    }
    protected static void setDataToRow(String[] str, XSSFRow currentRow)
    {
        for (int i = 0; i < str.length; i++)
        {
            currentRow.createCell(i).setCellValue(str[i]);
        }
    }

    private FileHelper()
    {

    }
}

