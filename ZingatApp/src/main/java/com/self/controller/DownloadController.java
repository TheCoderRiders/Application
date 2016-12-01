package com.self.controller;

import com.self.business.WorkingPageBusiness;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

/**
 * Created by akash.p on 30/11/16.
 */

@Controller
public class DownloadController {

    @Autowired
    private WorkingPageBusiness workingPageBusiness;

    @RequestMapping("/downloadPdf")
    public void downloadPDFResource(String fileId,HttpServletResponse response) throws Exception {
        File downloadFile = workingPageBusiness.getDownloadFile(fileId);
        /*String fileName = "2.pdf";
        File file = new File(documentPdfBasePath+ fileName);*/
        if (downloadFile.exists())
        {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename="+downloadFile.getName());
            try
            {
                FileUtils.copyFile(downloadFile, response.getOutputStream());
                response.getOutputStream().flush();
                downloadFile.delete();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
