package Excel_File_Processing.com.digiquad_2.service;



import org.apache.commons.csv.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Excel_File_Processing.com.digiquad_2.dto.RowData;

import java.io.*;
import java.util.*;

@Service
public class FileProcessingService {

    public String processFile(MultipartFile file, int startRow, int endrow) throws IOException {
        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            throw new IOException("Invalid file name");
        }

//        if (fileName.endsWith(".csv")) {
//            return processCSV(file, startRow,endrow);
//        } 
        else if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {
            return processExcel(file, startRow, endrow);
        } else {
            throw new IOException("Unsupported file type. Please upload Excel or CSV.");
        }
    }

    // Process CSV File
//    private String processCSV(MultipartFile file, int startRow, int endrow) throws IOException {
//        List<RowData> dataList = new ArrayList<>();
//        try (Reader reader = new InputStreamReader(file.getInputStream());
//             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
//
//            int rowNum = 0;
//            for (CSVRecord record : csvParser) {
//                if (rowNum >= startRow) {
//                    dataList.add(new RowData(record.get(0), record.get(1), record.get(2)));
//                }
//                rowNum++;
//            }
//        }
//        return dataList;
//    }

    // Process Excel File
    private String processExcel(MultipartFile file, int startRow, int endrow) throws IOException {
    	String cellValue = "";

        List<RowData> dataList = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            Row row=sheet.getRow(startRow);
            if (row != null) {
                Cell cell = row.getCell(endrow); // Get cell
                if (cell != null) {
                     cellValue = getCellAsString(cell);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return cellValue;
//            int rowNum = 0;
//            for (Row row : sheet) {
//                if (rowNum >= startRow) {
//                    dataList.add(new RowData(
//                            getCellValue(row, 0),
//                            getCellValue(row, 1),
//                            getCellValue(row, 2)
//                    ));
//                }
//                rowNum++;
//            }
//        }
//        return dataList;
    }

        private static String getCellAsString(Cell cell) {
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        return cell.getDateCellValue().toString();
                    }
                    return String.valueOf(cell.getNumericCellValue());
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                case FORMULA:
                    return cell.getCellFormula();
                default:
                    return "";
            }
        }

	private String getCellValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        return cell.toString().trim();
    }
}

