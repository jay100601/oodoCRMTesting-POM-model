package fcm.qa.util;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Xls_Reader {
    public String path;
    public static FileInputStream fis = null;
    public static FileOutputStream fileOut = null;
    private static Workbook workbook = null;
    private static Sheet sheet = null;
    private static Row row = null;
    private static Cell cell = null;

    public Xls_Reader(String path) {
        this.path = path;
        try {
            fis = new FileInputStream(path);
            workbook = WorkbookFactory.create(fis); // Use WorkbookFactory to handle both XLS and XLSX formats
            sheet = workbook.getSheetAt(0);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

    // Get cell data by column name
    public String getCellData(String sheetName, String colName, int rowNum) {
        try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            int col_Num = -1;
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                    col_Num = i;
            }
            if (col_Num == -1)
                return "";

            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";

            cell = row.getCell(col_Num);
            if (cell == null)
                return "";

            // Determine cell type and retrieve cell value accordingly
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                case FORMULA:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        // Handle date formatting if needed
                        return cell.getDateCellValue().toString(); // Example, adjust formatting as per your need
                    } else {
                        return String.valueOf(cell.getNumericCellValue());
                    }
                case BLANK:
                    return "";
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                default:
                    return ""; // Handle other cell types if needed
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "row " + rowNum + " or column " + colName + " does not exist in xls";
        }
    }

    // Get cell data by column index
    public String getCellData(String sheetName, int colNum, int rowNum) {
        try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";

            cell = row.getCell(colNum);
            if (cell == null)
                return "";

            // Determine cell type and retrieve cell value accordingly
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                case FORMULA:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        // Handle date formatting
                        Date javaDate = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Adjust date format as needed
                        return sdf.format(javaDate);
                    } else {
                        return String.valueOf(cell.getNumericCellValue());
                    }
                case BLANK:
                    return "";
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                default:
                    return ""; // Handle other cell types if needed
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
        }
    }

    // returns true if data is set successfully else false
    public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
        try {
            fis = new FileInputStream(path);
            workbook = WorkbookFactory.create(fis);

            if (rowNum <= 0)
                return false;

            int index = workbook.getSheetIndex(sheetName);
            int colNum = -1;
            if (index == -1)
                return false;

            sheet = workbook.getSheetAt(index);

            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                // System.out.println(row.getCell(i).getStringCellValue().trim());
                if (row.getCell(i).getStringCellValue().trim().equals(colName))
                    colNum = i;
            }
            if (colNum == -1)
                return false;

            sheet.autoSizeColumn(colNum);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                row = sheet.createRow(rowNum - 1);

            cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);

            // cell style
            // CellStyle cs = workbook.createCellStyle();
            // cs.setWrapText(true);
            // cell.setCellStyle(cs);
            cell.setCellValue(data);

            fileOut = new FileOutputStream(path);

            workbook.write(fileOut);

            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // returns true if data is set successfully else false
    public boolean setCellData(String sheetName, String colName, int rowNum, String data, String url) {
        try {
            fis = new FileInputStream(path);
            workbook = WorkbookFactory.create(fis);

            if (rowNum <= 0)
                return false;

            int index = workbook.getSheetIndex(sheetName);
            int colNum = -1;
            if (index == -1)
                return false;

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equals(colName))
                    colNum = i;
            }
            if (colNum == -1)
                return false;

            sheet.autoSizeColumn(colNum);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                row = sheet.createRow(rowNum - 1);

            cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);

            cell.setCellValue(data);

            // Create hyperlink
            CreationHelper createHelper = workbook.getCreationHelper();
            Hyperlink hyperlink = createHelper.createHyperlink(HyperlinkType.FILE);
            hyperlink.setAddress(url);
            cell.setHyperlink(hyperlink);

            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // returns true if sheet is created successfully else false
    public boolean addSheet(String sheetname) {

        FileOutputStream fileOut;
        try {
            workbook.createSheet(sheetname);
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // returns true if sheet is removed successfully else false if sheet does not exist
    public boolean removeSheet(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1)
            return false;

        FileOutputStream fileOut;
        try {
            workbook.removeSheetAt(index);
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // returns true if column is created successfully
    public boolean addColumn(String sheetName, String colName) {
        // System.out.println("**************addColumn*********************");

        try {
            fis = new FileInputStream(path);
            workbook = WorkbookFactory.create(fis);
            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return false;

            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            sheet = workbook.getSheetAt(index);

            row = sheet.getRow(0);
            if (row == null)
                row = sheet.createRow(0);

            // cell = row.getCell();
            // if (cell == null)
            // System.out.println(row.getLastCellNum());
            if (row.getLastCellNum() == -1)
                cell = row.createCell(0);
            else
                cell = row.createCell(row.getLastCellNum());

            cell.setCellValue(colName);
            cell.setCellStyle(style);

            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    // removes a column and all the contents
    public boolean removeColumn(String sheetName, int colNum) {
        try {
            if (!isSheetExist(sheetName))
                return false;
            fis = new FileInputStream(path);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);
            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.NO_FILL);

            for (int i = 0; i < getRowCount(sheetName); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    cell = row.getCell(colNum);
                    if (cell != null) {
                        cell.setCellStyle(style);
                        row.removeCell(cell);
                    }
                }
            }
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    // find whether sheet exists
    public boolean isSheetExist(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1) {
            index = workbook.getSheetIndex(sheetName.toUpperCase());
            if (index == -1)
                return false;
            else
                return true;
        } else
            return true;
    }

    // returns number of columns in a sheet
    public int getColumnCount(String sheetName) {
        // check if sheet exists
        if (!isSheetExist(sheetName))
            return -1;

        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);

        if (row == null)
            return -1;

        return row.getLastCellNum();

    }

    // String sheetName, String testCaseName,String keyword ,String URL,String message
    public boolean addHyperLink(String sheetName, String screenShotColName, String testCaseName, int index, String url,
            String message) {
        // System.out.println("ADDING addHyperLink******************");

        url = url.replace('\\', '/');
        if (!isSheetExist(sheetName))
            return false;

        sheet = workbook.getSheet(sheetName);

        for (int i = 2; i <= getRowCount(sheetName); i++) {
            if (getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)) {
                // System.out.println("**caught "+(i+index));
                setCellData(sheetName, screenShotColName, i + index, message, url);
                break;
            }
        }

        return true;
    }

    public int getCellRowNum(String sheetName, String colName, String cellValue) {

        for (int i = 2; i <= getRowCount(sheetName); i++) {
            if (getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
                return i;
            }
        }
        return -1;

    }
    
    // Get the number of rows in a sheet by name
    public int getRowCount(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1) {
            return 0; // Sheet not found
        }
        sheet = workbook.getSheetAt(index);
        return sheet.getLastRowNum() + 1;
    }

    // to run this on stand alone
    public static void main(String arg[]) throws IOException {

        // System.out.println(filename);
      //  Xls_Reader datatable = null;

        /*
         * datatable = new Xls_Reader(System.getProperty("user.dir")+
         * "\\src\\com\\qtpselenium\\xls\\Controller.xlsx"); for(int col=0 ;col<
         * datatable.getColumnCount("TC5"); col++){
         * System.out.println(datatable.getCellData("TC5", col, 1)); }
         */
    }
}
