package com.ranba.app;

import com.ranba.model.ApiResponse;
import com.ranba.util.FileUploadTool;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ranba.model.FileEntity;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UploadController {

    @PostMapping("/api/upload")
    public ApiResponse upload(@RequestParam(value = "myfile", required = false) MultipartFile multipartFile,
                              HttpServletRequest request, ModelMap map) {
        ApiResponse apiResponse = new ApiResponse();

        FileEntity entity = new FileEntity();
        FileUploadTool fileUploadTool = new FileUploadTool();
        try {
            entity = fileUploadTool.createFile(multipartFile, request);
            if (entity != null) {
                // service.saveFile(entity);
                apiResponse.setCode(1);
                apiResponse.setMessage("上传成功");
            } else {
                apiResponse.setCode(0);
                apiResponse.setMessage("上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResponse;
    }

}
