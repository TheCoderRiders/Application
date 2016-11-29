package com.self.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.self.dao.*;
import com.self.dto.*;
import com.self.enums.Action;
import com.self.enums.ProductRole;
import com.self.enums.SortingParameters;
import com.self.models.*;
import com.self.service.WorklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by akash.p on 17/6/16.
 */
@Component
public class WorklistBusinessImpl implements WorklistBusiness{

    @Autowired
    private WorklistService worklistService;

    @Autowired
    private UserMasterDao userMasterDao;

    @Autowired
    private DocumentMasterDao documentMasterDao;

    @Autowired
    private RoleBucketRightSideMapDao roleBucketRightSideMapDao;

    @Autowired
    private RolewiseDefaultBucketDao rolewiseDefaultBucketDao;

    @Autowired
    private AllocatorTLMapDao allocatorTLMapDao;

    @Autowired
    private TLCoderMapDao tlCoderMapDao;

    @Autowired
    private AuditorCoderMapDao auditorCoderMapDao;

    private List<Integer> assignedFileStatusIds = Arrays.asList(444,555,666);

    private List<Integer> assignedToTLFileStatusIds = Arrays.asList(333);

    private String defaultHtmlContent = "<!DOCTYPE html PUBLIC \\\"-//W3C//DTD HTML 4.01 Transitional//EN\\\"\\\"http://www.w3.org/TR/html4/loose.dtd\\\"><html><head><title> </title><meta http-equiv=\\\"Content-Type\\\" content=\\\"text/html; charset=\\\"UTF-16\\\"></head><body><div><p> </p><p><b>Office visit </b></p><p><b>REASON FOR CONSULTATION:</b> </p><p>Uncontrolled <mark class=\\\"I10\\\">hypertension</mark>, HLD  </p><p><b>HISTORY OF PRESENT ILLNESS:</b> </p><p>This is a 58-year-old male patient, Asian origin, with history of long standing </p><p><mark class=\\\"I10\\\">hypertension</mark> and diabetes.  Has had fluctuations in his blood pressure with systolic blood </p><p>pressure in the 170s to 180s and some times its 120s and 130s.  The patient has had few </p><p>medications changed before, currently he is on Norvasc 10 mg daily.  </p><p><b>PAST MEDICAL HISTORY:</b> </p><p>Diabetes for 18 years, <mark class=\\\"I10\\\">hypertension</mark> for 18 years, and without history of bronchitis.  </p><p><b>MEDICATIONS:</b> </p><p>Norvasc 10 mg daily, metformin 1000 mg b.i.d., glipizide 10 mg b.i.d., Zocor 40 mg </p><p>daily, aspirin 325 mg daily, and albuterol with breathing inhalers.  </p><p><b>ALLERGIES:</b> </p><p>No known drug allergies.  </p><p><b>SOCIAL HISTORY:</b> </p><p>Heavy smoker for many years, reduced smoking little bit now.  Still smokes few </p><p>cigarettes a week.  No alcohol or drug abuse.  </p><p><b>FAMILY HISTORY:</b> </p><p>Father had <mark class=\\\"I10\\\">hypertension</mark> and died of CVA.  </p><p><b>REVIEW OF SYSTEMS:</b> </p><p>Positive for nocturia two to three times in the night, also has some sweating and </p><p>palpitations at times.  Denies any nausea, vomiting, diarrhea, abdominal pain, hiccups, </p><p>itching, dysuria, frequency or urgency.  Has some headache off and on and dizziness </p><p>sometimes.  Has shortness of breath all the time, both at rest and with </p><p>exertion.  Denies  any chest pain.  No fever, chills, rigors or sore throat.  Denies any loss </p><p>of weight, loss of appetite, extreme weakness or fatigue.  </p><p><b>PHYSICAL EXAMINATION:</b> </p><p>GENERAL:  The patient is alert, oriented, and not in any acute distress.  Speaks </p><p>coherently. </p><p>VITAL SIGNS:  BP:  121/84.  Heart rate:  84.  Respirations:  15.  Temperature:  96.5. </p><p>HEENT:  Normocephalic, atraumatic.  Pupils are equal and reactive to light and </p><p>accommodation. Extraocular muscles intact. </p><p>NECK:  Supple.  No JVD. No lymphadenopathy, thyromegaly, masses or bruits. </p></div></body></html>'";

    @Value("${document.base.path}")
    private String documentBasePath;

    @Override
    public BucketActions getBucketsAndActions(String role, Integer roleId, Integer userId) {
        List<Bucket> bucketsInfo = worklistService.getBucketsInfo(role,userId);
        /*if(role.equalsIgnoreCase("TL_Allocater") || role.equalsIgnoreCase("TL_Allocater_Coder")|| role.equalsIgnoreCase("Allocater")) {
            List<Bucket> additionalBuckets = worklistService.getBucketsInfo("Allocater");
            bucketsInfo.removeAll(additionalBuckets);
            bucketsInfo.addAll(additionalBuckets);
        }*/

        List<Integer> userIds = ProductRole.valueOf(role).equals(ProductRole.Allocator)? allocatorTLMapDao.getTlIdByAllocatorId(userId).stream().map(entity->entity.getTlId()).collect(Collectors.toList())
                :ProductRole.valueOf(role).equals(ProductRole.TlCoder)? tlCoderMapDao.getCoderIdByTlId(userId).stream().map(entity -> entity.getCoderId()).collect(Collectors.toList())
                :ProductRole.valueOf(role).equals(ProductRole.Auditor)? auditorCoderMapDao.findByAuditorId(userId).stream().map(entity -> entity.getCoderId()).collect(Collectors.toList())
                :new ArrayList<>();

        final List<Integer> allUserIds = new ArrayList<>(userIds);

        if(ProductRole.valueOf(role).equals(ProductRole.Allocator)){userIds.parallelStream().forEach(tlId->allUserIds.addAll(tlCoderMapDao.getCoderIdByTlId(tlId).stream().map(entity->entity.getCoderId()).collect(Collectors.toList())));}

        if(!userIds.isEmpty()) {
            List<Bucket> additionalBuckets = documentMasterDao.getBucketInfoByUserIdList(role,allUserIds.stream().map(id->id.toString()).collect(Collectors.toList()));
            bucketsInfo.removeAll(additionalBuckets);
            bucketsInfo.addAll(additionalBuckets);
        }

        if(ProductRole.valueOf(role).equals(ProductRole.Allocator)) {
            List<Bucket> additionalBuckets = worklistService.getBucketsInfo(role);
            bucketsInfo.removeAll(additionalBuckets);
            bucketsInfo.addAll(additionalBuckets);
        }

        RolewiseDefaultBucketEntity rolewiseDefaultBucketEntity = rolewiseDefaultBucketDao.findByRoleId(roleId);
        Bucket defaultBucket = new Bucket();
        defaultBucket.setBucketName(rolewiseDefaultBucketEntity.getBucketName());
        int index = bucketsInfo.indexOf(defaultBucket);
        if(index!=-1) {
            defaultBucket = bucketsInfo.get(index);
            defaultBucket.setDefaultBucket(Boolean.TRUE);
        }

        Collections.sort(bucketsInfo);
        Set<AvailableOption> availableOptions = new LinkedHashSet<AvailableOption>();

        BucketActions bucketActions = new BucketActions();
        bucketActions.setBuckets(bucketsInfo);
        Actions actions = new Actions();

        List<String> allRoles = worklistService.getAllRoles();

        for (String userRole : allRoles){
            if (userRole.equalsIgnoreCase(role)) continue;

            ProductRole actualRoleEnum = ProductRole.valueOf(role);
            List<Action> actionList = actualRoleEnum.getActions();
            for(int i =0 ; i<actionList.size() ; i++) {
                Action action = actionList.get(i);
                AvailableOption availableOption = new AvailableOption(action.getId(),action.getDisplayValue());
                if(i==0 && actions.getSelectedOption()==null){
                    actions.setSelectedOption(availableOption);
                }//else if (!actions.getSelectedOption().equals(availableOption)){
                    availableOptions.add(availableOption);
                //}
            }
        }

        actions.setAvailableOptions(new ArrayList<AvailableOption>(availableOptions));

        bucketActions.setActions(actions);

        //sort
        Actions sortObject = new Actions();
        Set<AvailableOption> sortList = new LinkedHashSet<AvailableOption>();
        SortingParameters[] sortingParameters = SortingParameters.class.getEnumConstants();
        for(int i =0 ; i<sortingParameters.length ; i++) {
            SortingParameters parameters = sortingParameters[i];
            AvailableOption availableOption = new AvailableOption(parameters.getKey(),parameters.getValue());
            if(i==0 && sortObject.getSelectedOption()==null){
                sortObject.setSelectedOption(availableOption);
            }//else if (!sortObject.getSelectedOption().equals(availableOption)){
                sortList.add(availableOption);
            //}
        }
        sortObject.setAvailableOptions(new ArrayList<AvailableOption>(sortList));
        bucketActions.setSortParams(sortObject);

        return bucketActions;
    }

    @Override
    public FileDetailsResponse getFileDetails(String bucketName, String currentRole, int userId, String orderBy, boolean isAsc, int pageNumber) {

        List<String> userIdList = new ArrayList<>();
        String userIdStr = String.valueOf(userId);
        userIdList.add(userIdStr);

        boolean userIdAssignedFileOnly = ProductRole.valueOf(currentRole).equals(ProductRole.Coder)
                                         /*|| ProductRole.valueOf(currentRole).equals(ProductRole.Auditor)*/
                                        ;
        if(userIdAssignedFileOnly){
            return  worklistService.getFileDetailsByUserId(bucketName, Arrays.asList(currentRole), userIdList, orderBy, isAsc, pageNumber);
        }

        if(("New".equalsIgnoreCase(bucketName))){
            return getNewFileDetailsResponse(bucketName, currentRole, orderBy, isAsc, pageNumber);
        }

        List<Integer> userIds = ProductRole.valueOf(currentRole).equals(ProductRole.Allocator)? allocatorTLMapDao.getTlIdByAllocatorId(userId).stream().map(entity->entity.getTlId()).collect(Collectors.toList())
                                :ProductRole.valueOf(currentRole).equals(ProductRole.TlCoder)? tlCoderMapDao.getCoderIdByTlId(userId).stream().map(entity->entity.getCoderId()).collect(Collectors.toList())
                                :ProductRole.valueOf(currentRole).equals(ProductRole.Auditor)? auditorCoderMapDao.findByAuditorId(userId).stream().map(entity -> entity.getCoderId()).collect(Collectors.toList())
                                :new ArrayList<>();

        final List<Integer> allUserIds = new ArrayList<>(userIds);

        if(ProductRole.valueOf(currentRole).equals(ProductRole.Allocator)){userIds.parallelStream().forEach(tlId->allUserIds.addAll(tlCoderMapDao.getCoderIdByTlId(tlId).stream().map(entity->entity.getCoderId()).collect(Collectors.toList())));}

        return  worklistService.getFileDetailsByUserId(bucketName, Arrays.asList(currentRole), allUserIds.stream().map(id->id.toString()).collect(Collectors.toList()), orderBy, isAsc, pageNumber);

        /*FileDetailsResponse fileDetailsResponse = worklistService.getFileDetails(bucketName, Arrays.asList(currentRole), orderBy, isAsc, pageNumber);
        fileDetailsResponse.getFileDetailsList().parallelStream()
        .filter(fileDetail -> !(assignedFileStatusIds.contains(fileDetail.getFileStatusId().intValue()) || (("Allocater").equalsIgnoreCase(currentRole) && assignedToTLFileStatusIds.contains(fileDetail.getFileStatusId().intValue()))))
        .forEach(fileDetail -> fileDetail.setCheckBoxVisible(true));*/
    }

    private FileDetailsResponse getNewFileDetailsResponse(String bucketName, String currentRole, String orderBy, boolean isAsc, int pageNumber) {
        FileDetailsResponse newFileDetails = worklistService.getFileDetails(bucketName, Arrays.asList(currentRole), orderBy, isAsc, pageNumber);
        newFileDetails.getFileDetailsList().parallelStream().forEach(fileDetail -> fileDetail.setCheckBoxVisible(true));
        return newFileDetails;
    }

    @Override
    public FileContent getFileContents(String fileId, String currentRole, String page) {
        //String fileContents = worklistService.getFileContents(fileId);
        DocumentMasterEntity documentMasterEntity = documentMasterDao.findByDocumentId(fileId);
        //String fileContents =documentMasterEntity.getDocumentContents();
        String filePath = documentMasterEntity.getDocumentFilePath();
        File file = new File(documentBasePath+filePath);
        /*if(!file.exists()){
            try {
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(defaultHtmlContent.getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        char[] fileBuffer = new char[(int)file.length()];
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            reader.read(fileBuffer);
        }catch (Exception e){
            e.printStackTrace();
        }
        String fileContents = new String(fileBuffer);
        String fileStatus = documentMasterEntity.getDocumentCurrentStatus();
        DocRejectionReasonListEntity docRejectionReasonListEntity = null;
        String documentRejectionReason = documentMasterEntity.getDocumentRejectionReason();
        if (documentRejectionReason !=null && !documentRejectionReason.isEmpty())
        try {
            docRejectionReasonListEntity = new ObjectMapper().readValue(documentRejectionReason,DocRejectionReasonListEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileMode = "View";
        /*if(currentRole.toLowerCase().contains("coder") && ("Allocate to Coder".equalsIgnoreCase(fileStatus) || "Draft".equalsIgnoreCase(fileStatus))){*/
        if((currentRole.equalsIgnoreCase("coder") && ("Allocate to Coder".equalsIgnoreCase(fileStatus) || "Draft".equalsIgnoreCase(fileStatus)))
        || (currentRole.equalsIgnoreCase("TLCoder") && ("Allocate to TLCoder".equalsIgnoreCase(fileStatus) || "Draft".equalsIgnoreCase(fileStatus))) ) {
            fileMode = "Edit";
            if("workingPage".equalsIgnoreCase(page) && documentMasterEntity.getDocumentStartDatetime()==null){
                documentMasterEntity.setDocumentStartDatetime(new Timestamp(Calendar.getInstance().getTime().getTime()));
                documentMasterDao.save(documentMasterEntity);
            }
        }

        FileContent fileContent = new FileContent();
        fileContent.setData(fileContents);
        fileContent.setFileMode(fileMode);
        fileContent.setDocumentRejectionReason(docRejectionReasonListEntity);
        return fileContent;
    }

    @Override
    public List<UserBasicInfo> getUsersForAllocation(String actionId, String currentRole, int userId) {
        /*List<String> roleList = Action.getRoleByAction(actionId);
        List<UserMasterEntity> users = userMasterDao.findByRoleNameIn(roleList);
        return Arrays.asList( new ObjectMapper().convertValue(users, UserBasicInfo[].class));*/

        List<Integer> userIds = ProductRole.valueOf(currentRole).equals(ProductRole.Allocator)? allocatorTLMapDao.getTlIdByAllocatorId(userId).stream().map(entity->entity.getTlId()).collect(Collectors.toList())
                :ProductRole.valueOf(currentRole).equals(ProductRole.TlCoder)? tlCoderMapDao.getCoderIdByTlId(userId).stream().map(entity->entity.getCoderId()).collect(Collectors.toList())
                :new ArrayList<>();
        List<UserMasterEntity> users = userMasterDao.findByUserIdIn(userIds);
        return Arrays.asList( new ObjectMapper().convertValue(users, UserBasicInfo[].class));
    }

    @Override
    public Boolean assignedTo(DocAssignInfo docAssignInfo, UserMasterEntity userInfo) {
        List<DocumentMasterEntity> documentMasterEntityList = documentMasterDao.findByDocumentIdIn(docAssignInfo.getFileId());
        StatusMasterEntity statusMasterEntity = Action.getAssignedStatusByAction(docAssignInfo.getActionId());
        documentMasterEntityList.forEach(documentMasterEntity ->{
            documentMasterEntity.setDocumentCurrentStatus(statusMasterEntity.getStatusValue());
            documentMasterEntity.setDocumentCurrentStatusId(statusMasterEntity.getStatusId());
            documentMasterEntity.setDocumentAssignedDatetime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            documentMasterEntity.setDocumentAssignedId(docAssignInfo.getAssignedUserId());
            documentMasterEntity.setDocumentAssignedName(docAssignInfo.getAssignedUserName());
            documentMasterEntity.setDocumentAssigneeId(String.valueOf(userInfo.getUserId()));
            documentMasterEntity.setDocumentAssigneeName(userInfo.getUsername());
        });

        documentMasterDao.save(documentMasterEntityList);

        return true;
    }

    @Override
    public List<RightSideColumnResponse> getRightSideColumns(String fileId, int roleId, String bucketName) {
        List<RoleBucketRightsideMapEntity> roleBucketRightsideMapEntities = roleBucketRightSideMapDao.findByRoleIdAndBucketValue(roleId, bucketName);
        DocumentMasterEntity documentMasterEntity = documentMasterDao.findByDocumentId(fileId);
        List<RightSideColumnResponse> rightSideColumnResponseList = new ArrayList<>();
        roleBucketRightsideMapEntities.forEach(valueMap->{
            RightSideColumnResponse rightSideColumnResponse = new RightSideColumnResponse();
            rightSideColumnResponse.setColumnName(valueMap.getRightsideColumnKey());
            rightSideColumnResponse.setColumnSequenceNumber(valueMap.getRightsideColumnSequence());

            try {
                Field declaredField = documentMasterEntity.getClass().getDeclaredField(getClassVariableName(valueMap.getRightsideColumnName()));
                declaredField.setAccessible(true);
                Object value = declaredField.get(documentMasterEntity);
                rightSideColumnResponse.setColumnValue(value==null?null:value.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            rightSideColumnResponseList.add(rightSideColumnResponse);
        });

        return rightSideColumnResponseList;
    }

    private String getClassVariableName(String rightsideColumnKey) {
        String[] variableNameArray = rightsideColumnKey.split("_");
        String variableName="";
        for (int i=0;i<variableNameArray.length;i++){
            String var = variableNameArray[i];
            if (i==0){
                variableName+=var;
            }else {
                variableName += (char) (var.charAt(0) - 32) + var.substring(1, var.length());
            }
        }
        return variableName;
    }

    /*@Override
    public List<AvailableOption> getSortParameters(String currentRole) {

        for(SortingParameters sortingParameters:SortingParameters.class.getEnumConstants(){

        }

        return null;
    }*/
}
