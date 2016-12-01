package com.self.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.self.dao.CodeRejectionReasonDao;
import com.self.dao.DocRejectionReasonDao;
import com.self.dao.DocumentMasterDao;
import com.self.dao.SolrSuggesterRepository;
import com.self.dto.CodeAction;
import com.self.dto.CodeSearchResult;
import com.self.dto.Codes;
import com.self.dto.FileStatusChangeRequest;
import com.self.enums.ButtonVisibleUtility;
import com.self.enums.FileStatus;
import com.self.enums.ProductRole;
import com.self.models.CodeRejectionReasonListEntity;
import com.self.models.DocRejectionReasonListEntity;
import com.self.models.DocumentMasterEntity;
import com.self.pojo.ActualCode;
import com.self.pojo.DocumentCodeInfo;
import com.self.pojo.SolrCodeSuggesterBean;
import com.self.service.WorkingPageService;
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

    @Value("${document.base.path}")
    private String documentBasePath;

    @Value("${document.pdf.base.path}")
    private String documentPdfBasePath;

    @Value("${document.pdf.output.base.path}")
    private String documentPdfOutputBasePath;

    @Override
    public Codes getCodes(String fileId, String currentRole) throws IOException {
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

        acceptedSuggestedRejected.setButtonVisibleInfo(ButtonVisibleUtility.getButtonVisibleInfo(ProductRole.valueOf(currentRole)));
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
    public Boolean documentStatusChange(FileStatusChangeRequest fileStatusChangeRequest) {
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

        if(status.equals(FileStatus.REJECTED)) {
            try {
                documentMasterEntity.setDocumentRejectionReason(documentRejectionReason==null?null:new ObjectMapper().writeValueAsString(documentRejectionReason));
                documentMasterEntity.setDocumentRejectedDatetime(currentTime);
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
    public File getDownloadFile(String fileId) throws Exception {
        DocumentMasterEntity documentMasterEntity = documentMasterDao.findByDocumentId(fileId/*"20161129111818_rap536.pdf"*/);

        String documentName = documentMasterEntity.getDocumentName();
        File file = new File(documentPdfBasePath + documentName);
        File destinationFile = new File(documentPdfOutputBasePath + documentName+"_output.pdf");

        PdfReader pdfReader = new PdfReader(new FileInputStream(file));
        //int pages = pdfReader.getNumberOfPages();
        PdfStamper pdfStamper = new PdfStamper(pdfReader,new FileOutputStream(destinationFile));

        /*for(int i=1; i<=pages; i++) {
            PdfContentByte pageContentByte = pdfStamper.getOverContent(i);
            String pageText = new String(pdfReader.getPageContent(i));
            pageText.contains("Complaint:");
            PdfTextExtractor. getTextFromPage(pdfReader, i);
        }*/

        /*pdfStamper.insertPage(pages+1, PageSize.A4);
        PdfContentByte pdfContentByte = pdfStamper.getOverContent(pages+1);
        pdfContentByte.beginText();

        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
        pdfContentByte.setFontAndSize(bf, 18);
        pdfContentByte.showTextAligned(Element.ALIGN_CENTER, "CODED CODE", 0, 0, 0);
        pdfContentByte.newlineText();
        p
        pdfContentByte.beginMarkedContentSequence();

        pdfContentByte.endText();*/

        //Close the pdfStamper.
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
                        //document.add(new Phrase("\n"));
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
            // Source pdfs
            list.add(new FileInputStream(destinationFile));
            list.add(new FileInputStream(codeFile));

            // Resulting pdf
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
                //import the page from source pdf
                PdfImportedPage page = writer.getImportedPage(reader, i);
                //add the page to the destination pdf
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
            if(documentMasterEntity.getDocumentAcceptedCodes()!=null) {
                List<DocumentCodeInfo> acceptedCodes = Arrays.asList(objectMapper.readValue(documentMasterEntity.getDocumentAcceptedCodes(), DocumentCodeInfo[].class));
                List<DocumentCodeInfo> newlyAddedCode = acceptedCodes.stream().filter(documentCodeInfo -> documentCodeInfo.getSectionName().equalsIgnoreCase("Added Code")).collect(Collectors.toList());
                if(newlyAddedCode.size()!=0) {
                    List<SolrCodeSuggesterBean> solrCodeSuggesterBeanList = new ArrayList<>();
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
                    });

                    solrSuggesterRepository.saveCodeSuggesterBean(solrCodeSuggesterBeanList);
                }
            }
        }catch (Exception e) {
                e.printStackTrace();
        }
    }
}

