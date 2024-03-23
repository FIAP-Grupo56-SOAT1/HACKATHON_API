package com.hackathon.fiap.timesheet.adapter.out;

import com.hackathon.fiap.timesheet.application.core.contants.PointRecordType;
import com.hackathon.fiap.timesheet.application.core.ports.out.SendDotMirrorEmailOutputPort;
import com.hackathon.fiap.timesheet.application.core.reports.DefaultDotMirrorReportData;
import com.hackathon.fiap.timesheet.application.core.valueobject.PointRecordReportData;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SendDotMirrorEmailAdapter implements SendDotMirrorEmailOutputPort {
    private final JavaMailSender mailSender;

    @Override
    public void send(String email, DefaultDotMirrorReportData reportData)  {
       try{

           MimeMessage message = mailSender.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(message, true);

           helper.setTo(email);
           helper.setSubject("Relatório de Espelho de ponto do mês " + reportData.getMonth() + "/" + reportData.getYear());
           helper.setText("Relatório de espelho de ponto solicitado pelo usuário: "
                   + reportData.getEmployeeName()
                   + "  referente ao a data "
                   + reportData.getMonth()
                   + "/" + reportData.getYear() );

           var path  = creatFileExcel(reportData);

           FileSystemResource file = new FileSystemResource(new File(path));
           helper.addAttachment(file.getFilename(), file);

           mailSender.send(message);
       }catch (Exception ex){

       }
    }

    private String creatFileExcel(DefaultDotMirrorReportData reportData){

        try {

            DateTimeFormatter formatDay = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
            Workbook workbook = new XSSFWorkbook();

            Sheet sheet = workbook.createSheet("Relatório - Espenho de ponto.");
            sheet.addMergedRegion(CellRangeAddress.valueOf("A1:D1"));

            XSSFColor color = new XSSFColor(new java.awt.Color(43,150,150), null);

            XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
            cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setFillForegroundColor(color);
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Row headerRowNameRepost = sheet.createRow(0);
            Cell headerCell1 = headerRowNameRepost.createCell(0);
            headerCell1.setCellValue("Relatório - Espenho de ponto");
            headerCell1.setCellStyle(cellStyle);


            Row headerRowMonth = sheet.createRow(1);
            Cell headerCellMonth = headerRowMonth.createCell(0);
            headerCellMonth.setCellValue("Data base do relátorio: " + reportData.getMonth() + "/" + reportData.getYear());

            Row headerRowEmployeeName = sheet.createRow(2);
            Cell headerCellEmployeeName = headerRowEmployeeName.createCell(0);
            headerCellEmployeeName.setCellValue("Dados referente ao funcionário: " + reportData.getEmployeeName());


            Row headerRowTotalWorkedTime = sheet.createRow(3);
            Cell headerCelltotalWorkedTime = headerRowTotalWorkedTime.createCell(0);
            headerCelltotalWorkedTime.setCellValue("Total de horas contabilizadas no mês: " + reportData.getTotalWorkedTime());


            Map<String, Object[]> data = new TreeMap<String, Object[]>();

            var colunas = new ArrayList<Object>();
            colunas.add("Data da Entrada");
            colunas.add("Hora");
            colunas.add("Tipo Entrada");
            colunas.add("Status do Ponto");

            data.put("1",colunas.toArray());

            int rownumData = 3;

            for (PointRecordReportData point : reportData.getPointRecords()){
                var dateFormat = point.getDate().format(formatDay);
                var time  = point.getTime().format(formatTime);
                var type  = point.getType() == PointRecordType.IN ? "Entrada" : "Saída";
                var valid = point.getValid() == true ? "Contabilizado": "Não Contabilizado";

                data.put(Integer.toString(rownumData),
                        new Object[] {
                                dateFormat,
                                time,
                                type,
                                valid
                });
                rownumData++;
            }

            Set<String> keyset = data.keySet();
            int rownum = 5;
            for (String key : keyset) {
                XSSFRow row = (XSSFRow) sheet.createRow(rownum++);
                Object[] objArr = data.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {

                    XSSFCell cell = row.createCell(cellnum++);
                    if (obj instanceof String)
                        cell.setCellValue((String)obj);
                    else if (obj instanceof Integer)
                        cell.setCellValue((Integer)obj);
                }
            }


            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            String fileLocation = path.substring(0, path.length() - 1) + "RelatorioEspenhoDePonto.xlsx";

            FileOutputStream outputStream = new FileOutputStream(fileLocation);
            workbook.write(outputStream);
            workbook.close();

            return fileLocation;

        }catch (Exception ex){

        }
        return null;
    }
}
