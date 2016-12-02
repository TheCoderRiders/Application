package com.self.enums;

import com.self.dto.ButtonVisibleInfo;

import java.util.ArrayList;
import java.util.List;

import static com.self.enums.ProductRole.*;

/**
 * Created by akash.p on 1/12/16.
 */
public class ButtonVisibleUtility {

    public static ButtonVisibleInfo getButtonVisibleInfo(ProductRole role){
        ButtonVisibleInfo buttonVisibleInfo = new ButtonVisibleInfo();
        switch (role){
            case Allocator:
                break;
            case TlCoder:
                    buttonVisibleInfo.setIsCompletedButton(true);
                    buttonVisibleInfo.setIsDraftButton(true);
                    buttonVisibleInfo.setIsRejectedButton(true);
                    buttonVisibleInfo.setIsSentButton(true);
                    //buttonVisibleInfo.setIsResolvedButton(true);
                    break;
            case Coder:
                    buttonVisibleInfo.setIsCompletedButton(true);
                    buttonVisibleInfo.setIsDraftButton(true);
                    buttonVisibleInfo.setIsRejectedButton(true);
                    buttonVisibleInfo.setIsDoubtButton(true);
                    break;
            case Auditor:
                    //buttonVisibleInfo.setIsCompletedButton(true);
                    buttonVisibleInfo.setDownloadButton(true);
                    buttonVisibleInfo.setIsRebuttalButton(true);
                    buttonVisibleInfo.setIsSentButton(true);
                    break;
        }
        return buttonVisibleInfo;
    }
}
