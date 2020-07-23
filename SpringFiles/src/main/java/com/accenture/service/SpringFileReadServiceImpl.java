/*
 * package com.accenture.service;
 * 
 * import java.io.File; import java.io.FileOutputStream; import
 * java.io.FileWriter; import java.io.InputStream; import
 * java.io.InputStreamReader; import java.util.ArrayList; import
 * java.util.Arrays; import java.util.Iterator; import java.util.List;
 * 
 * import javax.servlet.ServletContext; import
 * javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import org.apache.commons.io.FilenameUtils; import
 * org.apache.poi.hssf.usermodel.HSSFCell; import
 * org.apache.poi.hssf.usermodel.HSSFCellStyle; import
 * org.apache.poi.hssf.usermodel.HSSFRow; import
 * org.apache.poi.hssf.usermodel.HSSFSheet; import
 * org.apache.poi.hssf.usermodel.HSSFWorkbook; import
 * org.apache.poi.hssf.util.HSSFColor; import
 * org.apache.poi.ss.usermodel.CellType; import
 * org.apache.poi.ss.usermodel.FillPatternType; import
 * org.apache.poi.ss.usermodel.Row; import org.apache.poi.ss.usermodel.Sheet;
 * import org.apache.poi.ss.usermodel.Workbook; import
 * org.apache.poi.ss.util.NumberToTextConverter; import
 * org.apache.poi.xssf.usermodel.XSSFWorkbook;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service; import
 * org.springframework.transaction.annotation.Transactional; import
 * org.springframework.web.multipart.MultipartFile;
 * 
 * import com.accenture.model.User; import
 * com.accenture.repository.SpringFileReadRepository; import
 * com.fasterxml.jackson.databind.ObjectMapper; import
 * com.itextpdf.text.BaseColor; import com.itextpdf.text.Document; import
 * com.itextpdf.text.Element; import com.itextpdf.text.Font; import
 * com.itextpdf.text.FontFactory; import com.itextpdf.text.PageSize; import
 * com.itextpdf.text.Paragraph; import com.itextpdf.text.pdf.PdfPCell; import
 * com.itextpdf.text.pdf.PdfPTable; import com.itextpdf.text.pdf.PdfWriter;
 * import com.opencsv.CSVReader; import com.opencsv.CSVReaderBuilder; import
 * com.opencsv.CSVWriter;
 * 
 * @Service
 * 
 * @Transactional public class SpringFileReadServiceImpl implements
 * SpringFileReadService {
 * 
 * @Autowired private SpringFileReadRepository fileReadRepository;
 * 
 * @Override public List<User> findAll() { return fileReadRepository.findAll();
 * }
 * 
 * @Override public boolean saveDataFromFileUpload(MultipartFile file) { boolean
 * isFlag = false; String extension =
 * FilenameUtils.getExtension(file.getOriginalFilename()); if
 * (extension.equalsIgnoreCase("json")) { isFlag = readDataFromJson(file); }
 * else if (extension.equalsIgnoreCase("csv")) { isFlag = readDataFromCsv(file);
 * } else if (extension.equalsIgnoreCase("xls") ||
 * extension.equalsIgnoreCase("xlsx")) { isFlag = readDataDromExcel(file); }
 * 
 * return isFlag; }
 * 
 * private boolean readDataDromExcel(MultipartFile file) { Workbook workbook =
 * getWorkBook(file); Sheet sheet = workbook.getSheetAt(0); Iterator<Row> rows =
 * sheet.iterator(); rows.next(); while (rows.hasNext()) { Row row =
 * rows.next(); User user = new User();
 * 
 * if (row.getCell(0).getCellType() == CellType.STRING) {
 * user.setFirstName(row.getCell(0).getStringCellValue()); } if
 * (row.getCell(1).getCellType() == CellType.STRING) {
 * user.setLastName(row.getCell(1).getStringCellValue()); } if
 * (row.getCell(2).getCellType() == CellType.STRING) {
 * user.setEmail(row.getCell(2).getStringCellValue()); } if
 * (row.getCell(3).getCellType() == CellType.NUMERIC) { String phoneNumber =
 * NumberToTextConverter.toText(row.getCell(3).getNumericCellValue());
 * user.setPhoneNumber(phoneNumber); } else if (row.getCell(3).getCellType() ==
 * CellType.STRING) { user.setPhoneNumber(row.getCell(2).getStringCellValue());
 * }
 * 
 * user.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
 * fileReadRepository.save(user); }
 * 
 * return true; }
 * 
 * private Workbook getWorkBook(MultipartFile file) { Workbook workbook = null;
 * String extension = FilenameUtils.getExtension(file.getOriginalFilename());
 * try { if (extension.equalsIgnoreCase("xls")) { workbook = new
 * HSSFWorkbook(file.getInputStream()); } else if
 * (extension.equalsIgnoreCase("xlsx")) { workbook = new
 * XSSFWorkbook(file.getInputStream()); }
 * 
 * } catch (Exception e) { e.printStackTrace(); }
 * 
 * return workbook; }
 * 
 * private boolean readDataFromCsv(MultipartFile file) { try { InputStreamReader
 * inputStreamReader = new InputStreamReader(file.getInputStream()); CSVReader
 * csvReader = new CSVReaderBuilder(inputStreamReader).withSkipLines(1).build();
 * List<String[]> allData = csvReader.readAll(); for (String[] row : allData) {
 * fileReadRepository.save(new User(row[0], row[1], row[2], row[3],
 * FilenameUtils.getExtension(file.getOriginalFilename()))); } return true; }
 * catch (Exception e) { return false; } }
 * 
 * private boolean readDataFromJson(MultipartFile file) { try { InputStream
 * inputStream = file.getInputStream(); ObjectMapper mapper = new
 * ObjectMapper(); List<User> users =
 * Arrays.asList(mapper.readValue(inputStream, User[].class)); if (users != null
 * && users.size() > 0) { for (User user : users) {
 * user.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
 * fileReadRepository.save(user); } } return true; } catch (Exception e) {
 * return false; } }
 * 
 * @Override public boolean createPdf(List<User> listuser, ServletContext
 * context, HttpServletRequest request, HttpServletResponse response) { Document
 * document = new Document(PageSize.A4, 15, 15, 45, 30); try { String filePath =
 * context.getRealPath("/resources/reports"); File file = new File(filePath);
 * boolean exist = new File(filePath).exists(); if (!exist) { new
 * File(filePath).mkdirs(); }
 * 
 * PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file
 * + "/" + "user" + ".pdf")); document.open();
 * 
 * Font mainFont = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
 * 
 * Paragraph paragraph = new Paragraph("All User", mainFont);
 * paragraph.setAlignment(Element.ALIGN_CENTER);
 * paragraph.setIndentationLeft(50); paragraph.setIndentationRight(50);
 * paragraph.setSpacingAfter(10); document.add(paragraph);
 * 
 * PdfPTable table = new PdfPTable(4); table.setTotalWidth(100);
 * table.setSpacingAfter(10f); table.setSpacingBefore(10f);
 * 
 * Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK); Font
 * tableBody = FontFactory.getFont("Arial", 9, BaseColor.BLACK);
 * 
 * float[] columnwidth = { 2f, 2f, 2f, 2f }; table.setWidths(columnwidth);
 * 
 * PdfPCell firstName = new PdfPCell(new Paragraph("First Name", tableHeader));
 * firstName.setBorderColor(BaseColor.BLACK); firstName.setPaddingLeft(10f);
 * firstName.setHorizontalAlignment(Element.ALIGN_CENTER);
 * firstName.setVerticalAlignment(Element.ALIGN_CENTER);
 * firstName.setBackgroundColor(BaseColor.GRAY);
 * firstName.setExtraParagraphSpace(5f); table.addCell(firstName);
 * 
 * PdfPCell lastName = new PdfPCell(new Paragraph("Last Name", tableHeader));
 * lastName.setBorderColor(BaseColor.BLACK); lastName.setPaddingLeft(10f);
 * lastName.setHorizontalAlignment(Element.ALIGN_CENTER);
 * lastName.setVerticalAlignment(Element.ALIGN_CENTER);
 * lastName.setBackgroundColor(BaseColor.GRAY);
 * lastName.setExtraParagraphSpace(5f); table.addCell(lastName);
 * 
 * PdfPCell email = new PdfPCell(new Paragraph("Email", tableHeader));
 * email.setBorderColor(BaseColor.BLACK); email.setPaddingLeft(10f);
 * email.setHorizontalAlignment(Element.ALIGN_CENTER);
 * email.setVerticalAlignment(Element.ALIGN_CENTER);
 * email.setBackgroundColor(BaseColor.GRAY); email.setExtraParagraphSpace(5f);
 * table.addCell(email);
 * 
 * PdfPCell phoneNumber = new PdfPCell(new Paragraph("Phone Number",
 * tableHeader)); phoneNumber.setBorderColor(BaseColor.BLACK);
 * phoneNumber.setPaddingLeft(10f);
 * phoneNumber.setHorizontalAlignment(Element.ALIGN_CENTER);
 * phoneNumber.setVerticalAlignment(Element.ALIGN_CENTER);
 * phoneNumber.setBackgroundColor(BaseColor.GRAY);
 * phoneNumber.setExtraParagraphSpace(5f); table.addCell(phoneNumber);
 * 
 * for (User user : listuser) {
 * 
 * PdfPCell firstNameValue = new PdfPCell(new Paragraph(user.getFirstName(),
 * tableBody)); firstNameValue.setBorderColor(BaseColor.BLACK);
 * firstNameValue.setPaddingLeft(10f);
 * firstNameValue.setHorizontalAlignment(Element.ALIGN_CENTER);
 * firstNameValue.setVerticalAlignment(Element.ALIGN_CENTER);
 * firstNameValue.setBackgroundColor(BaseColor.WHITE);
 * firstNameValue.setExtraParagraphSpace(5f); table.addCell(firstNameValue);
 * 
 * PdfPCell lastNamevalue = new PdfPCell(new Paragraph(user.getLastName(),
 * tableBody)); lastNamevalue.setBorderColor(BaseColor.BLACK);
 * lastNamevalue.setPaddingLeft(10f);
 * lastNamevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
 * lastNamevalue.setVerticalAlignment(Element.ALIGN_CENTER);
 * lastNamevalue.setBackgroundColor(BaseColor.WHITE);
 * lastNamevalue.setExtraParagraphSpace(5f); table.addCell(lastNamevalue);
 * 
 * PdfPCell emailvalue = new PdfPCell(new Paragraph(user.getEmail(),
 * tableBody)); emailvalue.setBorderColor(BaseColor.BLACK);
 * emailvalue.setPaddingLeft(10f);
 * emailvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
 * emailvalue.setVerticalAlignment(Element.ALIGN_CENTER);
 * emailvalue.setBackgroundColor(BaseColor.WHITE);
 * emailvalue.setExtraParagraphSpace(5f); table.addCell(emailvalue);
 * 
 * PdfPCell phoneNumbervalue = new PdfPCell(new Paragraph(user.getPhoneNumber(),
 * tableBody)); phoneNumbervalue.setBorderColor(BaseColor.BLACK);
 * phoneNumbervalue.setPaddingLeft(10f);
 * phoneNumbervalue.setHorizontalAlignment(Element.ALIGN_CENTER);
 * phoneNumbervalue.setVerticalAlignment(Element.ALIGN_CENTER);
 * phoneNumbervalue.setBackgroundColor(BaseColor.WHITE);
 * phoneNumbervalue.setExtraParagraphSpace(5f); table.addCell(phoneNumbervalue);
 * } document.add(table); document.close(); writer.close(); return true; } catch
 * (Exception e) { return false; } }
 * 
 * @Override public boolean createExcel(List<User> listuser, ServletContext
 * context, HttpServletRequest request, HttpServletResponse response) {
 * 
 * String filePath = context.getRealPath("/resources/reports"); File file = new
 * File(filePath); boolean exist = new File(filePath).exists(); if (!exist) {
 * new File(filePath).mkdirs(); } try {
 * 
 * FileOutputStream OutputStream = new FileOutputStream(file + "/" + "user" +
 * ".xls"); HSSFWorkbook workBook = new HSSFWorkbook();
 * 
 * HSSFSheet worksheet = workBook.createSheet("user");
 * worksheet.setDefaultColumnWidth(30);
 * 
 * HSSFCellStyle headercellStyle = workBook.createCellStyle();
 * headercellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.
 * GREY_50_PERCENT.getIndex());
 * headercellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
 * 
 * HSSFRow headrow = worksheet.createRow(0); // Header decoration HSSFCell
 * Firstname = headrow.createCell(0); Firstname.setCellValue("First Name");
 * Firstname.setCellStyle(headercellStyle);
 * 
 * HSSFCell Lastname = headrow.createCell(1);
 * Lastname.setCellValue("Last Name"); Lastname.setCellStyle(headercellStyle);
 * 
 * HSSFCell email = headrow.createCell(2); email.setCellValue("Email");
 * email.setCellStyle(headercellStyle);
 * 
 * HSSFCell phoneNumber = headrow.createCell(3);
 * phoneNumber.setCellValue("Phone Number");
 * phoneNumber.setCellStyle(headercellStyle);
 * 
 * int i = 1; for (User user : listuser) { HSSFRow bodyRow =
 * worksheet.createRow(i);
 * 
 * HSSFCellStyle bodyCellStyle = workBook.createCellStyle();
 * bodyCellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.WHITE.
 * getIndex());
 * 
 * HSSFCell FirstnameValue = bodyRow.createCell(0);
 * FirstnameValue.setCellValue(user.getFirstName());
 * FirstnameValue.setCellStyle(bodyCellStyle);
 * 
 * HSSFCell LastnameValue = bodyRow.createCell(1);
 * LastnameValue.setCellValue(user.getLastName());
 * LastnameValue.setCellStyle(bodyCellStyle);
 * 
 * HSSFCell emailValue = bodyRow.createCell(2);
 * emailValue.setCellValue(user.getEmail());
 * emailValue.setCellStyle(bodyCellStyle);
 * 
 * HSSFCell phoneNumberValue = bodyRow.createCell(3);
 * phoneNumberValue.setCellValue(user.getPhoneNumber());
 * phoneNumberValue.setCellStyle(bodyCellStyle);
 * 
 * i++; } workBook.write(OutputStream); OutputStream.flush();
 * OutputStream.close(); return true;
 * 
 * } catch (Exception e) { return false; } }
 * 
 * @Override public boolean createJson(List<User> listuser, ServletContext
 * context) {
 * 
 * String filePath = context.getRealPath("/resources/reports"); boolean exist =
 * new File(filePath).exists(); if (!exist) { new File(filePath).mkdirs(); }
 * 
 * File file = new File(filePath + "/" + File.separator + "user.json");
 * ObjectMapper mapper = new ObjectMapper(); try { mapper.writeValue(file,
 * listuser);
 * 
 * return true; } catch (Exception e) { return false; } }
 * 
 * @Override public boolean createCSV(List<User> listuser, ServletContext
 * context) { String filePath = context.getRealPath("/resources/reports");
 * boolean exist = new File(filePath).exists(); if (!exist) { new
 * File(filePath).mkdirs(); }
 * 
 * File file = new File(filePath + "/" + File.separator + "user.csv"); try {
 * FileWriter fileWriter = new FileWriter(file); CSVWriter csvWriter = new
 * CSVWriter(fileWriter); List<String[]> data = new ArrayList<>(); data.add(new
 * String[] { "First Name", "Last Name", "Email", "Phone Number" }); for (User
 * user : listuser) { data.add(new String[] { user.getFirstName(),
 * user.getLastName(), user.getEmail(), user.getPhoneNumber() }); }
 * csvWriter.writeAll(data); csvWriter.close(); return true; } catch (Exception
 * e) { return false; }
 * 
 * }
 * 
 * }
 */