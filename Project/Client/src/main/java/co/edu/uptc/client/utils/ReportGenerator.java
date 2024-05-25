package co.edu.uptc.client.utils;

import co.edu.uptc.client.dto.TransactionData;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportGenerator {

    private static final String CSV_FILE_PATH = "data/report.csv";
    private static final String TXT_FILE_PATH = "data/report.txt";
    private static final String PDF_FILE_PATH = "data/report.pdf";

    public void generateCSVReport(List<TransactionData> transactions) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("User ID", "ID de Transacción", "Cantidad", "Fecha y Hora", "Categoría", "Descripción", "Tipo"))) {
            for (TransactionData transaction : transactions) {
                csvPrinter.printRecord(transaction.getUserId(), transaction.getTransactionId(), transaction.getAmount(), transaction.getDateTime(), transaction.getCategory(), transaction.getDescription(), transaction.getType());
            }
        }
    }


    public void generateTXTReport(List<TransactionData> transactions) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TXT_FILE_PATH))) {
            for (TransactionData transaction : transactions) {
                writer.write(transaction.toString());
                writer.newLine();
            }
        }
    }

    public void generatePDFReport(List<TransactionData> transactions) throws IOException {
        try (PdfWriter writer = new PdfWriter(PDF_FILE_PATH);
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)) {
            document.add(new Paragraph("Reporte de Tranascciónes"));
            Table table = new Table(new float[]{2, 2, 2, 2, 2, 2, 2});
            table.addHeaderCell("User ID");
            table.addHeaderCell("ID de Transacción");
            table.addHeaderCell("Cantidad");
            table.addHeaderCell("Fecha y Hora");
            table.addHeaderCell("Categoría");
            table.addHeaderCell("Descripción");
            table.addHeaderCell("Tipo");
            for (TransactionData transaction : transactions) {
                table.addCell(transaction.getUserId());
                table.addCell(transaction.getTransactionId());
                table.addCell(String.valueOf(transaction.getAmount()));
                table.addCell(transaction.getDateTime());
                table.addCell(transaction.getCategory());
                table.addCell(transaction.getDescription());
                table.addCell(transaction.getType());
            }
            document.add(table);
        }
    }
}
