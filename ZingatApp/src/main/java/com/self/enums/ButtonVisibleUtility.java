package com.self.enums;

import com.self.constants.BucketConstants;
import com.self.constants.Constants;
import com.self.dto.ButtonVisibleInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.self.enums.ProductRole.*;

/**
 * Created by akash.p on 1/12/16.
 */
public class ButtonVisibleUtility {

   static List<String> coderBuckets = Arrays.asList(BucketConstants.NEW,BucketConstants.DRAFT,BucketConstants.CLEARIFICATION,BucketConstants.QA_RESPONSE,BucketConstants.REBUTTAL_CLARIFICATION);
   static List<String> auditorBuckets = Arrays.asList(BucketConstants.NEEDS_TO_AUDIT,BucketConstants.REWORK);
   static List<String> editAccessRoleBucket = Arrays.asList(
           Constants.AUDITOR+Constants.UNDERSCORE+BucketConstants.NEEDS_TO_AUDIT,
           Constants.AUDITOR+Constants.UNDERSCORE+BucketConstants.REWORK,
           Constants.TL_CODER+Constants.UNDERSCORE+BucketConstants.REJECTED,
           Constants.CODER+Constants.UNDERSCORE+BucketConstants.NEW,
           Constants.CODER+Constants.UNDERSCORE+BucketConstants.DRAFT,
           Constants.CODER+Constants.UNDERSCORE+BucketConstants.CLEARIFICATION,
           Constants.CODER+Constants.UNDERSCORE+BucketConstants.QA_RESPONSE);


    public static ButtonVisibleInfo getButtonVisibleInfo(ProductRole role, String bucketName){
        ButtonVisibleInfo buttonVisibleInfo = new ButtonVisibleInfo();
        switch (role){
            case Allocator:
            case TlCoder:
                    break;
            case Coder:
                    if(coderBuckets.contains(bucketName)) {
                        buttonVisibleInfo.setIsCompletedButton(true);
                        buttonVisibleInfo.setIsDraftButton(true);
                        buttonVisibleInfo.setIsRejectedButton(true);
                        buttonVisibleInfo.setIsDoubtButton(true);
                    }
                    break;
            case Auditor:
                    if(auditorBuckets.contains(bucketName)) {
                        buttonVisibleInfo.setDownloadButton(true);
                        buttonVisibleInfo.setIsRebuttalButton(true);
                        buttonVisibleInfo.setIsSentButton(true);
                    }
                    break;
        }
        return buttonVisibleInfo;
    }

    public static String getWorkingPageMode(ProductRole role, String bucketName){
        if(editAccessRoleBucket.contains(role.name()+Constants.UNDERSCORE+bucketName)){
            return Constants.EDIT;
        }
        return Constants.VIEW;
    }
}
