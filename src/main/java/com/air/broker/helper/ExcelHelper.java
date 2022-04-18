package com.air.broker.helper;

import com.air.broker.model.Ticket;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "source", "sourceTerminal", "destination","destinationTerminal","airline","flightNumber","departureTime","arrivalTime" };
    static String SHEET = "Sheet1";
    public static boolean hasExcelFormat(MultipartFile file) {
        System.out.println(file.getContentType());
        System.out.println(TYPE.equals(file.getContentType()));
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<Ticket> excelToTutorials(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Ticket> tickets = new ArrayList<Ticket>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Ticket ticket = new Ticket();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
//                        case 0:
//                            ticket.setId((long) currentCell.getNumericCellValue());
//                            break;
                        case 1:
                            ticket.setSource(currentCell.getStringCellValue());
                            break;
                        case 2:
                            ticket.setSourceTerminal(currentCell.getStringCellValue());
                            break;
                        case 3:
                            ticket.setDestination(currentCell.getStringCellValue());
                            break;
                        case 4:
                            ticket.setDestinationTerminal(currentCell.getStringCellValue());
                            break;
                        case 5:
                            ticket.setAirline(currentCell.getStringCellValue());
                            break;
                        case 6:
                            ticket.setFlightNumber(currentCell.getStringCellValue());
                            break;
                        case 7:
                            ticket.setDepartureTime(currentCell.getStringCellValue());
                            break;
                        case 8:
                            ticket.setArrivalTime(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                tickets.add(ticket);
            }
            workbook.close();
            return tickets;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
