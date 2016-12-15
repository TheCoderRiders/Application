package com.self.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.self.constants.Constants;
import com.self.dao.*;
import com.self.dto.*;
import com.self.enums.*;
import com.self.models.*;
import com.self.pojo.ActualCode;
import com.self.pojo.DocumentCodeInfo;
import com.self.pojo.SolrCodeSuggesterBean;
import com.self.service.WorkingPageService;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by akash.p on 25/7/16.
 */
@Component
public class WorkingPageBusinessImpl implements WorkingPageBusiness {

    @Autowired
    private WorkingPageService workingPageService;

    @Autowired
    private DocumentMasterDao documentMasterDao;

    @Autowired
    private DocRejectionReasonDao docRejectionReasonDao;

    @Autowired
    private CodeRejectionReasonDao codeRejectionReasonDao;

    @Autowired
    private SolrSuggesterRepository solrSuggesterRepository;

    @Autowired
    private DoubtListDao doubtListDao;

    @Autowired
    private RebuttalListDao rebuttalListDao;

    @Autowired
    private AcknowledgementDetailsDao acknowledgementDetailsDao;

    @Autowired
    private EvidenceUpdateDetailsDao evidenceUpdateDetailsDao;

    @Autowired
    private TLCoderMapDao tlCoderMapDao;

    @Autowired
    private AuditorCoderMapDao auditorCoderMapDao;

    @Value("${document.base.path}")
    private String documentBasePath;

    @Value("${document.pdf.base.path}")
    private String documentPdfBasePath;

    @Value("${document.pdf.output.base.path}")
    private String documentPdfOutputBasePath;

    @Override
    public Codes getCodes(String fileId, String currentRole, String bucketName) throws IOException {
        DocumentMasterEntity documentMasterEntity = workingPageService.getCodes(fileId);
        Codes acceptedSuggestedRejected = new Codes();
        ObjectMapper objectMapper = new ObjectMapper();

        if(documentMasterEntity.getDocumentAcceptedCodes()!=null) {
            acceptedSuggestedRejected.setAcceptedCode(Arrays.asList(
                    objectMapper.readValue(documentMasterEntity.getDocumentAcceptedCodes(), DocumentCodeInfo[].class)));
        }
        if(documentMasterEntity.getDocumentRejectedCodes()!=null) {
            acceptedSuggestedRejected.setRejectedCode(Arrays.asList(
                    objectMapper.readValue(documentMasterEntity.getDocumentRejectedCodes(), DocumentCodeInfo[].class)));
        }
        if(documentMasterEntity.getDocumentSuggestedCodes()!=null) {
            acceptedSuggestedRejected.setSuggestedCode(Arrays.asList(
                    objectMapper.readValue(documentMasterEntity.getDocumentSuggestedCodes(), DocumentCodeInfo[].class)));
        }

        if(documentMasterEntity.getDocumentMayBeCodes()!=null) {
            acceptedSuggestedRejected.setMayBeCode(Arrays.asList(
                    objectMapper.readValue(documentMasterEntity.getDocumentMayBeCodes(), DocumentCodeInfo[].class)));
        }

        acceptedSuggestedRejected.setFileId(documentMasterEntity.getDocumentId());

        acceptedSuggestedRejected.setButtonVisibleInfo(ButtonVisibleUtility.getButtonVisibleInfo(ProductRole.valueOf(currentRole),bucketName));
        return acceptedSuggestedRejected;
    }

    @Override
    public Boolean saveCodes(Codes codes) throws JsonProcessingException {
        DocumentMasterEntity documentMasterEntity = documentMasterDao.findByDocumentId(codes.getFileId());
        ObjectMapper objectMapper = new ObjectMapper();
        List<DocumentCodeInfo> suggestedCode = codes.getSuggestedCode();
        List<DocumentCodeInfo> acceptedCode = codes.getAcceptedCode();
        List<DocumentCodeInfo> rejectedCode = codes.getRejectedCode();
        List<DocumentCodeInfo> mayBeCode = codes.getMayBeCode();

        documentMasterEntity.setDocumentSuggestedCodes(suggestedCode==null?null:objectMapper.writeValueAsString(suggestedCode));
        documentMasterEntity.setDocumentAcceptedCodes(acceptedCode==null?null:objectMapper.writeValueAsString(acceptedCode));
        documentMasterEntity.setDocumentRejectedCodes(rejectedCode==null?null:objectMapper.writeValueAsString(rejectedCode));
        documentMasterEntity.setDocumentMayBeCodes(mayBeCode==null?null:objectMapper.writeValueAsString(mayBeCode));

        documentMasterDao.save(documentMasterEntity);

        return true;
    }

    @Override
    public CodeSearchResult searchCode(String key, Integer start) {
        QueryResponse queryResponse = solrSuggesterRepository.searchCode(key,start);
        CodeSearchResult codeSearchResult = new CodeSearchResult();

        codeSearchResult.setCodes(queryResponse.getBeans(ActualCode.class));
        codeSearchResult.setStart(start);
        codeSearchResult.setPageSize(solrSuggesterRepository.getPaginationSize());
        codeSearchResult.setTotal(queryResponse.getResults().getNumFound());
        return codeSearchResult;
    }

    @Override
    public Codes codeAction(CodeAction codeAction) throws JsonProcessingException {
        Codes allCodes = codeAction.getAllCodes();
        ActualCode code = codeAction.getCode();
        String sectionName = codeAction.getSectionName()==null?"Others:":codeAction.getSectionName();
        String dos = codeAction.getDos()==null?"":codeAction.getDos();
        String sign = codeAction.getSign()==null?"":codeAction.getSign();
        String action = codeAction.getAction();
        String codeActionType = codeAction.getCodeActionType();
        List<DocumentCodeInfo> suggestedCode = allCodes.getSuggestedCode()==null? new ArrayList<>():allCodes.getSuggestedCode();
        List<DocumentCodeInfo> acceptedCode = allCodes.getAcceptedCode()==null? new ArrayList<>():allCodes.getAcceptedCode();
        List<DocumentCodeInfo> rejectedCode = allCodes.getRejectedCode()==null? new ArrayList<>():allCodes.getRejectedCode();
        List<DocumentCodeInfo> mayBeCode = allCodes.getMayBeCode()==null? new ArrayList<>():allCodes.getMayBeCode();

        Predicate<DocumentCodeInfo> sectionPredicate = codeInfo -> sectionName.equalsIgnoreCase(codeInfo.getSectionName());
        switch (codeActionType + "_" +action){
            case "Suggested_Accept" :
                extractAndInsertCode(suggestedCode, acceptedCode, code, sectionName, sectionPredicate,dos,sign);
                break;

            case "Suggested_Reject" :
                extractAndInsertCode(suggestedCode, rejectedCode, code, sectionName, sectionPredicate, dos, sign);
                break;
            case "Accepted_Reject" :
                extractAndInsertCode(acceptedCode, rejectedCode, code, sectionName, sectionPredicate, dos, sign);
                break;
            case "Rejected_Accept" :
                extractAndInsertCode(rejectedCode, acceptedCode, code, sectionName, sectionPredicate, dos, sign);
                break;
            case "New_AddCode" :
                extractAndInsertCode(new ArrayList<>(), acceptedCode, code, sectionName, sectionPredicate, dos, sign);
                break;
            case "MayBe_Accept" :
                extractAndInsertCode(mayBeCode, acceptedCode, code, sectionName, sectionPredicate, dos, sign);
                break;

            case "MayBe_Reject" :
                extractAndInsertCode(mayBeCode, rejectedCode, code, sectionName, sectionPredicate, dos, sign);
                break;
        }

        allCodes.setAcceptedCode(acceptedCode);
        allCodes.setSuggestedCode(suggestedCode);
        allCodes.setRejectedCode(rejectedCode);
        allCodes.setMayBeCode(mayBeCode);
        saveCodes(allCodes);
        return allCodes;
    }

    @Override
    public Boolean documentStatusChange(FileStatusChangeRequest fileStatusChangeRequest, String bucketName) throws IOException {
        String fileId = fileStatusChangeRequest.getFileId();
        FileStatus status = fileStatusChangeRequest.getStatus();
        DocRejectionReasonListEntity documentRejectionReason = fileStatusChangeRequest.getDocRejectionReason();

        DocumentMasterEntity documentMasterEntity = documentMasterDao.findByDocumentId(fileId);
        documentMasterEntity.setDocumentCurrentStatus(status.getStatus());
        documentMasterEntity.setDocumentCurrentStatusId(status.getId());

        String fileContents = fileStatusChangeRequest.getFileContents();
        if(fileContents !=null && !fileContents.isEmpty()){
            String filePath = documentMasterEntity.getDocumentFilePath();
            File file = new File(documentBasePath+filePath);
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
                writer.write(fileContents);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        Timestamp currentTime = new Timestamp(Calendar.getInstance().getTime().getTime());

        ObjectMapper objectMapper = new ObjectMapper();
        if(status.equals(FileStatus.REJECTED)) {
            try {
                documentMasterEntity.setDocumentRejectionReason(documentRejectionReason == null ? null : objectMapper.writeValueAsString(documentRejectionReason));
                documentMasterEntity.setDocumentRejectedDatetime(currentTime);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        if(status.equals(FileStatus.DOUBT) || status.equals(FileStatus.REBUTTAL) || status.equals(FileStatus.REWORK) || status.equals(FileStatus.NEEDS_REBUTTAL_CLARIFICATION) ) {
            try {
                DoubtRebuttalInfo doubtRebuttalInfo = fileStatusChangeRequest.getDoubtRebuttalInfo();
                doubtRebuttalInfo.setDocumentAssigneeName(documentMasterEntity.getDocumentAssigneeName());
                doubtRebuttalInfo.setDocumentAssignedName(documentMasterEntity.getDocumentAssignedName());
                doubtRebuttalInfo.setDate(currentTime);

                List<DoubtRebuttalInfo> existingDoubtRebuttalList = documentMasterEntity.getComments()==null?new ArrayList<>():new ArrayList<>(Arrays.asList(objectMapper.readValue(documentMasterEntity.getComments(), DoubtRebuttalInfo[].class)));
                existingDoubtRebuttalList.add(doubtRebuttalInfo);
                documentMasterEntity.setComments(objectMapper.writeValueAsString(existingDoubtRebuttalList));

                if(status.equals(FileStatus.REBUTTAL))  documentMasterEntity.setRebuttalCount(documentMasterEntity.getRebuttalCount()+1);

                RebuttalActionInfo rebuttalActionInfo = doubtRebuttalInfo.getRebuttalActionInfo();
                //Rebuttal Doubt
                if(status.equals(FileStatus.REWORK) || status.equals(FileStatus.NEEDS_REBUTTAL_CLARIFICATION)){
                    documentMasterEntity.setDocumentAssigneeId(documentMasterEntity.getDocumentAssignedId());
                    documentMasterEntity.setDocumentAssigneeName(documentMasterEntity.getDocumentAssignedName());
                    documentMasterEntity.setDocumentAssignedId(rebuttalActionInfo.getAssignedId());
                    documentMasterEntity.setDocumentAssignedName(rebuttalActionInfo.getAssignedUserName());
                }

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        if(status.equals(FileStatus.SUBMIT)) {
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.submit(() -> {
                        saveNewlyAddedCodeInSolr(documentMasterEntity);
                    }
            );

            documentMasterEntity.setDocumentEndDatetime(currentTime);
        }

        if(status.equals(FileStatus.RESOLVED_DOUBT) || status.equals(FileStatus.REBUTTAL_CLARIFICATION) ) {
            try {
                DoubtRebuttalInfo doubtRebuttalInfo = fileStatusChangeRequest.getDoubtRebuttalInfo();
                doubtRebuttalInfo.setDocumentAssigneeName(documentMasterEntity.getDocumentAssignedName());
                doubtRebuttalInfo.setDocumentAssignedName(documentMasterEntity.getDocumentAssigneeName());
                doubtRebuttalInfo.setDate(currentTime);

                List<DoubtRebuttalInfo> existingDoubtRebuttalList = documentMasterEntity.getComments()==null?new ArrayList<>():new ArrayList<>(Arrays.asList(objectMapper.readValue(documentMasterEntity.getComments(), DoubtRebuttalInfo[].class)));
                existingDoubtRebuttalList.add(doubtRebuttalInfo);
                documentMasterEntity.setComments(objectMapper.writeValueAsString(existingDoubtRebuttalList));

                //if(status.equals(FileStatus.REBUTTAL))  documentMasterEntity.setRebuttalCount(documentMasterEntity.getRebuttalCount()+1);

                //Rebuttal Doubt Resolved
                if( status.equals(FileStatus.REBUTTAL_CLARIFICATION)){
                    documentMasterEntity.setDocumentAssignedId(documentMasterEntity.getDocumentAssigneeId());
                    documentMasterEntity.setDocumentAssignedName(documentMasterEntity.getDocumentAssigneeName());
                    documentMasterEntity.setDocumentAssigneeId(documentMasterEntity.getDocumentAssignedId());
                    documentMasterEntity.setDocumentAssigneeName(documentMasterEntity.getDocumentAssignedName());
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        documentMasterEntity.setUpdatedDate(currentTime);
        documentMasterDao.save(documentMasterEntity);
        return true;
    }

    @Override
    public List<CodeRejectionReasonListEntity> getCodeRejectionReasonList() {
        return codeRejectionReasonDao.findAll();
    }

    @Override
    public List<DocRejectionReasonListEntity> getDocRejectionReasonList() {
        return docRejectionReasonDao.findAll();
    }

    @Override
    public File getDownloadFile(String fileId, FileType fileType) throws Exception {
        DocumentMasterEntity documentMasterEntity = documentMasterDao.findByDocumentId(fileId);

        if(fileType!=null && FileType.EXCEL.equals(FileType.EXCEL)){
            return getExcelDownloadFile(documentMasterEntity);
        }

        String documentName = documentMasterEntity.getDocumentName();

        File file = new File(documentPdfBasePath + documentName);
        File destinationFile = new File(documentPdfOutputBasePath + documentName+"_output.pdf");

        PdfReader pdfReader = new PdfReader(new FileInputStream(file));
        PdfStamper pdfStamper = new PdfStamper(pdfReader,new FileOutputStream(destinationFile));
        pdfStamper.close();


        Document document = new Document();
        File codeFile = new File(documentPdfOutputBasePath + documentName+"_output_code.pdf");
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(codeFile));
        document.open();
        Paragraph paragraph = new Paragraph("BILLABLE CODES");
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
        document.add(new Phrase("\n"));

        if(documentMasterEntity.getDocumentAcceptedCodes()!=null) {
            List<DocumentCodeInfo> documentCodeInfoList = Arrays.asList(new ObjectMapper().readValue(documentMasterEntity.getDocumentAcceptedCodes(), DocumentCodeInfo[].class));
            if(!documentCodeInfoList.isEmpty()) {

                Map<String, List<DocumentCodeInfo>> groupingByDos = documentCodeInfoList.stream().collect(Collectors.groupingBy(documentCodeInfo -> documentCodeInfo.getDos()));
                groupingByDos.forEach((key,value)->{
                    try {
                        PdfPTable table = new PdfPTable(1);
                        PdfPCell pdfPCell = new PdfPCell(new Phrase("Date Of Service : "+(key.isEmpty()?"Not Available":key)));
                        pdfPCell.setBackgroundColor(new GrayColor(0.5f));
                        pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        pdfPCell.setVerticalAlignment(Element.ALIGN_CENTER);
                        table.addCell(pdfPCell);

                        PdfTemplate template = pdfWriter.getDirectContent().createTemplate(pdfWriter.getPageSize().getWidth(), 80);
                        template.setColorFill(BaseColor.GRAY);
                        template.rectangle(0, 0, pdfWriter.getPageSize().getWidth(), 80);
                        template.fill();
                        pdfWriter.releaseTemplate(template);
                        document.add(table);
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                    final int[] count = {0};
                    value.forEach(docCodeInfo -> {
                    docCodeInfo.getCodes().forEach(code -> {
                        com.itextpdf.text.List mainList = new com.itextpdf.text.List(com.itextpdf.text.List.ORDERED);
                        mainList.setFirst(++count[0]);
                        mainList.setAlignindent(false);
                        mainList.setPostSymbol(". ");

                        mainList.add(code.getCode());

                        com.itextpdf.text.List list = new com.itextpdf.text.List(com.itextpdf.text.List.ORDERED);
                        list.setPreSymbol(String.valueOf("    " + count[0] + "."));
                        list.setSymbolIndent(10f);
                        list.setPostSymbol(" ");
                        List<String> tokenList = code.getToken();
                        if (tokenList != null && !tokenList.isEmpty()) {
                            tokenList.forEach(token -> {
                                list.add(token);
                            });
                        }

                        mainList.add(list);
                        try {
                            document.add(mainList);
                            DottedLineSeparator dottedline = new DottedLineSeparator();
                            dottedline.setOffset(-10);
                            dottedline.setGap(2f);
                            document.add(dottedline);
                            document.add(new Phrase("\n"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                });


                });
            }
        }
        document.close();
        List<InputStream> list = new ArrayList<>();
        File result = new File(documentPdfOutputBasePath + documentName+"_with_code.pdf");
        try {
            list.add(new FileInputStream(destinationFile));
            list.add(new FileInputStream(codeFile));

            OutputStream out = new FileOutputStream(result);

            doMerge(list, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        codeFile.delete();
        destinationFile.delete();

        return result;
    }

    private File getExcelDownloadFile(DocumentMasterEntity documentMasterEntity) throws Exception {
        String documentName = documentMasterEntity.getDocumentName();
        File excelFile = new File(documentPdfOutputBasePath + documentName+".xls");
        excelFile.createNewFile();

        FileOutputStream outputStream = new FileOutputStream(excelFile);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("BILLABLE CODES");
        HSSFRow headerRow = sheet.createRow(0);

        HSSFCell firstCell = headerRow.createCell(0);
        firstCell.setCellValue("Sr. No.");

        HSSFCellStyle headerRowStyle = firstCell.getCellStyle();
        headerRowStyle.setBorderLeft(HSSFBorderFormatting.BORDER_DOUBLE);
        headerRowStyle.setBorderRight(HSSFBorderFormatting.BORDER_DOUBLE);
        headerRowStyle.setBorderTop(HSSFBorderFormatting.BORDER_DOUBLE);
        headerRowStyle.setBorderBottom(HSSFBorderFormatting.BORDER_DOUBLE);
        firstCell.setCellStyle(headerRowStyle);
        sheet.setDefaultColumnWidth(25);

        HSSFCell secondCell = headerRow.createCell(1);
        secondCell.setCellValue("Date Of Service");
        secondCell.setCellStyle(headerRowStyle);

        HSSFCell thirdCell = headerRow.createCell(2);
        thirdCell.setCellValue("Patient Name");
        thirdCell.setCellStyle(headerRowStyle);

        HSSFCell fourthCell = headerRow.createCell(3);
        fourthCell.setCellValue("Patient DOB");
        fourthCell.setCellStyle(headerRowStyle);

        HSSFCell fifthCell = headerRow.createCell(4);
        fifthCell.setCellValue("Patient Gender");
        fifthCell.setCellStyle(headerRowStyle);

        HSSFCell sixthCell = headerRow.createCell(5);
        sixthCell.setCellValue("Codes and Evidences");
        sixthCell.setCellStyle(headerRowStyle);

        if(documentMasterEntity.getDocumentAcceptedCodes()!=null) {
            List<DocumentCodeInfo> documentCodeInfoList = Arrays.asList(new ObjectMapper().readValue(documentMasterEntity.getDocumentAcceptedCodes(), DocumentCodeInfo[].class));
            if (!documentCodeInfoList.isEmpty()) {

                Map<String, List<DocumentCodeInfo>> groupingByDos = documentCodeInfoList.stream().collect(Collectors.groupingBy(documentCodeInfo -> documentCodeInfo.getDos()));
                final int[] rowCount = {0};
                groupingByDos.forEach((key, value) -> {
                    HSSFRow row = sheet.createRow(++rowCount[0]);
                    HSSFCell first = row.createCell(0);
                    first.setCellValue(rowCount[0]);

                    HSSFCellStyle rowStyle = first.getCellStyle();
                    rowStyle.setBorderLeft(HSSFBorderFormatting.BORDER_THIN);
                    rowStyle.setBorderRight(HSSFBorderFormatting.BORDER_THIN);
                    rowStyle.setBorderTop(HSSFBorderFormatting.BORDER_THIN);
                    rowStyle.setBorderBottom(HSSFBorderFormatting.BORDER_THIN);
                    rowStyle.setWrapText(true);
                    first.setCellStyle(rowStyle);

                    HSSFCell second = row.createCell(1);
                    second.setCellValue(key);
                    second.setCellStyle(rowStyle);

                    value.forEach(docCodeInfo -> {

                        HSSFCell third = row.createCell(2);
                        third.setCellValue(documentMasterEntity.getPatientName());
                        third.setCellStyle(rowStyle);

                        HSSFCell fourth = row.createCell(3);
                        Timestamp patientDob = documentMasterEntity.getPatientDob();
                        if(patientDob !=null) {
                            fourth.setCellValue(new SimpleDateFormat("MM-dd-yyyy").format(new Timestamp(patientDob.getTime())));
                            fourth.setCellStyle(rowStyle);
                        }

                        HSSFCell fifth = row.createCell(4);
                        fifth.setCellValue(documentMasterEntity.getPatientGender());
                        fifth.setCellStyle(rowStyle);

                        StringBuffer codeEvidence = new StringBuffer(Constants.EMPTY);
                        List<ActualCode> codes = docCodeInfo.getCodes();
                        codes.forEach(code -> {
                            codeEvidence.append(code.getCode());
                            codeEvidence.append(Constants.COLON);
                            if (code.getToken() != null) {
                                codeEvidence.append(code.getToken().stream().collect(Collectors.joining(Constants.COMMA)));
                            }
                            codeEvidence.append(Constants.NEW_LINE);
                        });

                        HSSFCell sixth = row.createCell(5);
                        sixth.setCellValue(codeEvidence.toString());
                        sixth.setCellStyle(rowStyle);
                        if(codes!=null) {
                            row.setHeight((short) ((codes.size()+1) * 240));
                        }
                    });
                });
            }
        }
        workbook.write(outputStream);
        outputStream.close();
        return excelFile;
    }

    @Override
    public List getDoubtList() {
        return doubtListDao.findAll();
    }

    @Override
    public List getRebuttalList() {
        return rebuttalListDao.findAll();
    }

    @Override
    public Boolean acknowledgeComment(AcknowledgeCommentInfo acknowledgeCommentInfo) throws IOException {
        AcknowledgementDetailsEntity acknowledgementDetailsEntity = new AcknowledgementDetailsEntity();
        DocumentMasterEntity documentMasterEntity = documentMasterDao.findByDocumentId(acknowledgeCommentInfo.getFileId());
        ObjectMapper objectMapper = new ObjectMapper();
        List<DoubtRebuttalInfo> existingDoubtRebuttalList = new ArrayList<>(Arrays.asList(objectMapper.readValue(documentMasterEntity.getComments(), DoubtRebuttalInfo[].class)));

        Optional<DoubtRebuttalInfo> any = existingDoubtRebuttalList.stream().filter(doubtInfo -> FileStatus.RESOLVED_DOUBT.name().equalsIgnoreCase(doubtInfo.getDoubtRebuttalType())).findAny();
        if(any.isPresent()) {
            DoubtRebuttalInfo doubtRebuttalInfo = any.get();
            doubtRebuttalInfo.setCommentAck(true);
        }else {
            DoubtRebuttalInfo doubtRebuttalInfo = existingDoubtRebuttalList.stream().filter(doubtInfo -> FileStatus.REBUTTAL.name().equalsIgnoreCase(doubtInfo.getDoubtRebuttalType())).findAny().get();
            doubtRebuttalInfo.setCommentAck(true);
        }

        acknowledgementDetailsEntity.setDocumentAssignedId(documentMasterEntity.getDocumentAssignedId());
        acknowledgementDetailsEntity.setDocumentAssignedName(documentMasterEntity.getDocumentAssignedName());
        acknowledgementDetailsEntity.setDocumentAssigneeId(documentMasterEntity.getDocumentAssigneeId());
        acknowledgementDetailsEntity.setDocumentAssigneeName(documentMasterEntity.getDocumentAssigneeName());
        acknowledgementDetailsEntity.setClientId(documentMasterEntity.getClientId());
        acknowledgementDetailsEntity.setClientName(documentMasterEntity.getClientName());
        acknowledgementDetailsEntity.setCommentDate(acknowledgeCommentInfo.getCommentDate());
        acknowledgementDetailsEntity.setCommentDisplay(acknowledgeCommentInfo.getCommentDisplay());
        acknowledgementDetailsEntity.setCommentText(acknowledgeCommentInfo.getCommentText());
        acknowledgementDetailsEntity.setCommentStatus(acknowledgeCommentInfo.getCommentStatus());
        acknowledgementDetailsDao.save(acknowledgementDetailsEntity);

        documentMasterEntity.setComments(objectMapper.writeValueAsString(existingDoubtRebuttalList));
        documentMasterDao.save(documentMasterEntity);

        return Boolean.TRUE;
    }

    @Override
    public List getAuditorList(int userId) {
        return getTlAuditorInfo(auditorCoderMapDao.getAuditorIdByCoderId(userId));
    }

    @Override
    public List getTlList(int userId) {
        return getTlAuditorInfo(tlCoderMapDao.getTlIdByCoderId(userId));
    }

    private List getTlAuditorInfo(List auditorIdByCoderId) {
        List<TlAuditorInfo> tlAuditorInfoList = new ArrayList<>();
        auditorIdByCoderId.forEach(auditor-> {
            if(auditor instanceof AuditorCoderMapEntity) {
                AuditorCoderMapEntity auditorCoderMapEntity = (AuditorCoderMapEntity)auditor;
                tlAuditorInfoList.add(new TlAuditorInfo(auditorCoderMapEntity.getAuditorId(), auditorCoderMapEntity.getAuditorFirstname(), auditorCoderMapEntity.getAuditorMiddlename(), auditorCoderMapEntity.getAuditorLastname(),auditorCoderMapEntity.getAuditorUserName()));
            }else if(auditor instanceof TLCoderMapEntity) {
                TLCoderMapEntity tlCoderMapEntity = (TLCoderMapEntity)auditor;
                tlAuditorInfoList.add(new TlAuditorInfo(tlCoderMapEntity.getTlId(), tlCoderMapEntity.getTlFirstname(), tlCoderMapEntity.getTlMiddlename(), tlCoderMapEntity.getTlLastname(),tlCoderMapEntity.getTlUserName()));
            }

        });
        return tlAuditorInfoList;
    }

    public static void doMerge(List<InputStream> list, OutputStream outputStream)
            throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();
        PdfContentByte cb = writer.getDirectContent();

        for (InputStream in : list) {
            PdfReader reader = new PdfReader(in);
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                document.newPage();
                PdfImportedPage page = writer.getImportedPage(reader, i);
                cb.addTemplate(page, 0, 0);
            }
        }

        outputStream.flush();
        document.close();
        outputStream.close();
    }

    private void extractAndInsertCode(List<DocumentCodeInfo> fromCode, List<DocumentCodeInfo> toCode, ActualCode whichCode, String sectionName, Predicate<DocumentCodeInfo> sectionPredicate, String dos, String sign) {
        Map<Boolean, List<DocumentCodeInfo>> partition = fromCode.stream().collect(Collectors.partitioningBy(sectionPredicate));
        List<DocumentCodeInfo> suggestedCodeWithValidSection = partition.get(Boolean.TRUE);
        List<DocumentCodeInfo> suggestedCodeWithInValidSection = partition.get(Boolean.FALSE);

        fromCode.retainAll(suggestedCodeWithInValidSection);
        if(suggestedCodeWithValidSection!=null &&suggestedCodeWithValidSection.size()!=0 && suggestedCodeWithValidSection.get(0).getCodes().size()!=1){
            suggestedCodeWithValidSection.get(0).getCodes().remove(whichCode);
            fromCode.addAll(suggestedCodeWithValidSection);
        }

        partition = toCode.stream().collect(Collectors.partitioningBy(sectionPredicate));
        suggestedCodeWithValidSection = partition.get(Boolean.TRUE);
        suggestedCodeWithInValidSection = partition.get(Boolean.FALSE);

        if(suggestedCodeWithValidSection==null || suggestedCodeWithValidSection.size()==0){
            DocumentCodeInfo documentCodeInfo = new DocumentCodeInfo();
            documentCodeInfo.setSectionName(sectionName);
            if("Added Code".equalsIgnoreCase(sectionName)){
                documentCodeInfo.setDos(new SimpleDateFormat("MM-dd-yyyy").format(new Timestamp(Calendar.getInstance().getTime().getTime())));
            }else {
                documentCodeInfo.setDos(dos);
            }
            documentCodeInfo.setSign(sign);
            documentCodeInfo.setCodes(Arrays.asList(whichCode));
            toCode.add(documentCodeInfo);
        }else {
            DocumentCodeInfo documentCodeInfo = suggestedCodeWithValidSection.get(0);
            documentCodeInfo.getCodes().add(whichCode);
            toCode.retainAll(suggestedCodeWithInValidSection);
            toCode.addAll(suggestedCodeWithValidSection);
        }
    }

    private void saveNewlyAddedCodeInSolr(DocumentMasterEntity documentMasterEntity) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Timestamp currentTime = new Timestamp(Calendar.getInstance().getTime().getTime());
            if(documentMasterEntity.getDocumentAcceptedCodes()!=null) {
                List<DocumentCodeInfo> acceptedCodes = Arrays.asList(objectMapper.readValue(documentMasterEntity.getDocumentAcceptedCodes(), DocumentCodeInfo[].class));
                List<DocumentCodeInfo> newlyAddedCode = acceptedCodes.stream().filter(documentCodeInfo -> documentCodeInfo.getSectionName().equalsIgnoreCase("Added Code")).collect(Collectors.toList());
                if(newlyAddedCode.size()!=0) {
                    List<SolrCodeSuggesterBean> solrCodeSuggesterBeanList = new ArrayList<>();
                    List<EvidenceUpdateDetailsEntity> evidenceUpdateDetailsEntities  = new ArrayList<>();
                    newlyAddedCode.get(0).getCodes().forEach(documentCode -> {
                        QueryResponse queryResponse = solrSuggesterRepository.searchCode(documentCode.getCode(), 1);
                        List<SolrCodeSuggesterBean> solrCodeSuggesterBeans = queryResponse.getBeans(SolrCodeSuggesterBean.class);
                        SolrCodeSuggesterBean actualBean = solrCodeSuggesterBeans.get(0);
                        List<String> evidenceList = new ArrayList<String>(); //actualBean.getSearchFieldNgram();
                        List<String> token = documentCode.getToken();
                        if(token!=null && token.size()!=0) {
                            token.forEach(evidence -> {
                                List<String> collect = actualBean.getSearchFieldNgram().stream().filter(existingEvidence -> existingEvidence.equalsIgnoreCase(evidence)).collect(Collectors.toList());
                                if (collect.size() == 0) {
                                    evidenceList.add(evidence);
                                }
                            });
                        }
                        actualBean.setSearchFieldNgram(evidenceList);
                        solrCodeSuggesterBeanList.add(actualBean);
                        EvidenceUpdateDetailsEntity evidenceUpdateDetailsEntity = new EvidenceUpdateDetailsEntity();
                        evidenceUpdateDetailsEntity.setCodeId(documentCode.getCode());
                        evidenceUpdateDetailsEntity.setDocumentId(documentMasterEntity.getDocumentId());
                        evidenceUpdateDetailsEntity.setEvidenceAddById(documentMasterEntity.getDocumentAssignedId());
                        evidenceUpdateDetailsEntity.setEvidenceAddDate(currentTime);
                        try {
                            evidenceUpdateDetailsEntity.setEvidence(objectMapper.writeValueAsString(evidenceList));
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        evidenceUpdateDetailsEntity.setClientId(documentMasterEntity.getClientId());
                        evidenceUpdateDetailsEntity.setClientName(documentMasterEntity.getClientName());
                        evidenceUpdateDetailsEntities.add(evidenceUpdateDetailsEntity);
                    });

                    solrSuggesterRepository.saveCodeSuggesterBean(solrCodeSuggesterBeanList);
                    evidenceUpdateDetailsDao.save(evidenceUpdateDetailsEntities);
                }
            }
        }catch (Exception e) {
                e.printStackTrace();
        }
    }
}

