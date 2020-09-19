package com.telstra.amazon.mobile.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

	private static Logger logger = Logger.getLogger(ExcelUtils.class);
	
	/**
	 * Read the data from excel 
	 * @param FilePath : Excel file path
	 * @param SheetName : Excel sheet name
	 * @return array of data
	 * @throws Exception
	 */
	public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {

		String[][] tabArray = null;

		try {

			FileInputStream ExcelFile = new FileInputStream(FilePath);

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int startRow = 1;

			int startCol = 0;

			int ci, cj;

			int totalRows = ExcelWSheet.getLastRowNum();

			int totalCols = ExcelWSheet.getRow(0).getLastCellNum();

			tabArray = new  String [totalRows][totalCols];

			ci = 0;

			for (int i = startRow; i <= totalRows; i++, ci++) {

				cj = 0;

				for (int j = startCol; j <totalCols; j++, cj++) {

					tabArray[ci][cj] = getCellData(i, j);

				}

			}

		}

		catch (FileNotFoundException e) {

			logger.info("Could not read the Excel sheet");
			
			e.printStackTrace();

		}

		catch (IOException e) {

			logger.info("Could not read the Excel sheet");

			e.printStackTrace();

		}

		return (tabArray);

	}

	/**
	 *  Get the provided cell data
	 * @param RowNum
	 * @param ColNum
	 * @return
	 * @throws Exception
	 */
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {

			Row = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum);

			if (Cell.getCellType() == CellType.STRING)
				return Cell.getStringCellValue();
			else if (Cell.getCellType() == CellType.NUMERIC || Cell.getCellType() == CellType.FORMULA) {
				String cellValue =   NumberToTextConverter.toText(Cell.getNumericCellValue());

				if (HSSFDateUtil.isCellDateFormatted(Cell)) {
					DateFormat df = new SimpleDateFormat("dd/MM/yy");
					Date date = Cell.getDateCellValue();
					cellValue = df.format(date);
				}
				return cellValue;
			} else if (Cell.getCellType() == CellType.BLANK)
				return "";
			else
				return String.valueOf(Cell.getBooleanCellValue());
		} catch (Exception e) {
			e.printStackTrace();
			return "row " + RowNum + " or column " + ColNum + " does not exist  in Excel";
		}

	}
}
